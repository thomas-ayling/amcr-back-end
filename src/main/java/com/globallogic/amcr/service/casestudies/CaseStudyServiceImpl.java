package com.globallogic.amcr.service.casestudies;

import com.globallogic.amcr.persistence.dao.casestudies.CaseStudyDao;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CaseStudyServiceImpl implements CaseStudyService {
    private final CaseStudyDao caseStudyDao;
    public static final Logger LOGGER = LoggerFactory.getLogger(CaseStudyServiceImpl.class.getName());


    public CaseStudyServiceImpl(CaseStudyDao caseStudyDao) {
        this.caseStudyDao = Assert.assertNull(caseStudyDao, "CaseStudyDao is not present");
    }

    @Transactional
    public CaseStudy save(CaseStudy caseStudy) {
        Assert.assertNull(caseStudy, "Case study cannot be null");
        try {
            UUID caseStudyId = UUID.randomUUID();
            LOGGER.debug("Saving new case study with ID {} and content {}", caseStudyId, caseStudy);
            return caseStudyDao.save(caseStudy, caseStudyId);
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyService - could not save case study", e);
        }
    }

    @Transactional
    public CaseStudy get(UUID id) {
        Assert.assertNull(id, "ID cannot be null to request entry");
        LOGGER.debug("Requesting case study with ID {}", id);
        return caseStudyDao.get(id);
    }

    @Transactional
    public List<CaseStudy> getAll() {
        LOGGER.debug("Requesting all case studies");
        return caseStudyDao.getAll();
    }

    @Transactional
    public List<CaseStudyOverview> getAllOverviews() {
        LOGGER.debug("Requesting all case study overviews");
        return caseStudyDao.getAllOverviews();
    }

    @Transactional
    public List<CaseStudyOverview> getSpotlitOverviews() {
        LOGGER.debug("Requesting all spotlit case study overviews");
        return caseStudyDao.getSpotlitOverviews();
    }

    @Transactional
    public CaseStudy update(UUID id, CaseStudy newCaseStudy) {
        Assert.assertNull(id, "ID must be included to update a case study");
        Assert.assertNull(newCaseStudy, "New case study must not be null");
        CaseStudy oldCaseStudy = Assert.assertNull(caseStudyDao.get(id), "Object with specified ID could not be found. Revise ID and try again");
        LOGGER.debug("Updating case study with ID {} and content {} with {}", id, oldCaseStudy, newCaseStudy);
        return caseStudyDao.update(id, newCaseStudy, oldCaseStudy);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNull(id, "ID cannot be null to delete entry");
        LOGGER.debug("Deleting case study with ID {}", id);
        caseStudyDao.delete(id);
    }
}
