package com.globallogic.amcr.service.impl.casestudies;

import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.model.casestudies.CaseStudyResponse;
import com.globallogic.amcr.repository.casestudies.CaseStudyDao;
import com.globallogic.amcr.service.casestudies.CaseStudyService;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CaseStudyServiceImpl implements CaseStudyService {
    private final Logger Log = LoggerFactory.getLogger(CaseStudyServiceImpl.class);
    private final CaseStudyDao caseStudyDao;

    public CaseStudyServiceImpl(CaseStudyDao caseStudyDao) {
        this.caseStudyDao = Assert.assertNotNull(caseStudyDao, "CaseStudyDao is not present");
    }

    @Override
    @Transactional
    public CaseStudy save(CaseStudy caseStudy) {
        Assert.assertNotNull(caseStudy, "Case study cannot be null");
        try {
            UUID caseStudyId = UUID.randomUUID();
            Log.debug("Service saving new case study");
            return caseStudyDao.save(caseStudy, caseStudyId);
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyService - could not save case study", e);
        }
    }

    @Override
    @Transactional
    public CaseStudyResponse get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to request entry");
        Log.debug("Service requesting case study with ID {}", id);
        final CaseStudy caseStudy = caseStudyDao.get(id);
        List<String> attachmentLinks = new ArrayList<>();
        if (caseStudy.getAttachmentIds() != null) {
            for (UUID attachmentId : caseStudy.getAttachmentIds()) {
                attachmentLinks.add(ServletUriComponentsBuilder.fromCurrentRequest().path("/attachment/{id}").buildAndExpand(attachmentId).toUri().toString());
            }
        }
        final String coverImageLink = ServletUriComponentsBuilder.fromCurrentRequest().path("/attachment/{id}").buildAndExpand(caseStudy.getCoverImageId()).toUri().toString();
        for (Map<String, String> row : caseStudy.getBody()) {
            row.replace("imageId", ServletUriComponentsBuilder.fromCurrentRequest().path("/attachment/{id}").buildAndExpand(row.get("imageId")).toUri().toString());
        }
        return new CaseStudyResponse(caseStudy, attachmentLinks, coverImageLink);
    }

    @Override
    @Transactional
    public List<CaseStudy> getAll() {
        Log.debug("Service requesting all case studies");
        return caseStudyDao.getAll();
    }

    @Override
    @Transactional
    public List<CaseStudyOverview> getAllOverviews() {
        Log.debug("Service requesting all case study overviews");
        return caseStudyDao.getAllOverviews();
    }

    @Override
    @Transactional
    public List<CaseStudyOverview> getSpotlitOverviews() {
        Log.debug("Service requesting all spotlit case study overviews");
        return caseStudyDao.getSpotlitOverviews();
    }

    @Override
    @Transactional
    public List<CaseStudyOverview> getLatestOverviews(int entries) {
        Assert.assertNotNull(entries, "Entries cannot be null");
        Log.debug("Service requesting 5 most recent overviews");
        return caseStudyDao.getLatestOverviews(entries);
    }

    @Override
    @Transactional
    public CaseStudy update(UUID id, CaseStudy newCaseStudy) {
        Assert.assertNotNull(id, "ID must be included to update a case study");
        Assert.assertNotNull(newCaseStudy, "New case study must not be null");
        CaseStudy oldCaseStudy = Assert.assertNotNull(caseStudyDao.get(id), "Object with specified ID could not be found. Revise ID and try again");
        Log.debug("Service updating case study with ID {}", id);
        return caseStudyDao.update(id, newCaseStudy, oldCaseStudy);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to delete entry");
        Log.debug("Service requesting deletion of case study with ID {}", id);
        caseStudyDao.delete(id);
    }
}
