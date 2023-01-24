package com.globallogic.amcr.service.impl.casestudies;

import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.repository.casestudies.CaseStudyDao;
import com.globallogic.amcr.service.casestudies.CaseStudyService;
import com.globallogic.amcr.utils.Assert;
import com.globallogic.amcr.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public CaseStudy get(UUID id) {
        Assert.assertNotNull(id, "ID cannot be null to request entry");
        Log.debug("Service requesting case study with ID {}", id);
        CaseStudy caseStudy = caseStudyDao.get(id);
        List<String> attachmentLinks = new ArrayList<>();
        if (caseStudy.getAttachmentIds() != null) {
            for (UUID attachmentId : caseStudy.getAttachmentIds()) {
                attachmentLinks.add(Utils.generateUri("/attachment/{id}", attachmentId).toString());
            }
        }
        for (Map<String, String> row : caseStudy.getBody()) {
            row.replace("imageId", Utils.generateUri("/attachment/{id}", UUID.fromString(row.get("imageId"))).toString());
        }
        caseStudy.setAttachmentLinks(attachmentLinks);
        caseStudy.setCoverImageLink(Utils.generateUri("/attachment/{id}", caseStudy.getCoverImageId()).toString());
        return caseStudy;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CaseStudy> getAll() {
        Log.debug("Service requesting all case studies");
        return caseStudyDao.getAll();
    }

    @Override
    @Transactional
    public List<CaseStudyOverview> getAllOverviews() {
        Log.debug("Service requesting all case study overviews");
        List<CaseStudyOverview> caseStudyOverviews = caseStudyDao.getAllOverviews();
        for (CaseStudyOverview caseStudyOverview : caseStudyOverviews) {
            caseStudyOverview.setCoverImageLink(Utils.generateUri("/attachment/{id}", caseStudyOverview.getCoverImageId()).toString());
        }
        return caseStudyOverviews;
    }

    @Override
    @Transactional
    public List<CaseStudyOverview> getSpotlitOverviews() {
        Log.debug("Service requesting all spotlit case study overviews");
        List<CaseStudyOverview> caseStudyOverviews = caseStudyDao.getSpotlitOverviews();
        for (CaseStudyOverview caseStudyOverview : caseStudyOverviews) {
            caseStudyOverview.setCoverImageLink(Utils.generateUri("/attachment/{id}", caseStudyOverview.getCoverImageId()).toString());
        }
        return caseStudyOverviews;
    }

    @Override
    @Transactional
    public List<CaseStudyOverview> getLatestOverviews(int entries) {
        Assert.assertNotNull(entries, "Entries cannot be null");
        Log.debug("Service requesting {} most recent overviews", entries);

        List<CaseStudyOverview> caseStudyOverviews = caseStudyDao.getLatestOverviews(entries);
        for (CaseStudyOverview caseStudyOverview : caseStudyOverviews) {
            caseStudyOverview.setCoverImageLink(Utils.generateUri("/attachment/{id}", caseStudyOverview.getCoverImageId()).toString());
        }
        return caseStudyOverviews;
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
