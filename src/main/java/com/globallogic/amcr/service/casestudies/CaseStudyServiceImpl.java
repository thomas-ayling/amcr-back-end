package com.globallogic.amcr.service.casestudies;

import com.globallogic.amcr.persistence.dao.casestudies.CaseStudyDao;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CaseStudyServiceImpl implements CaseStudyService {

    private final CaseStudyDao caseStudyDao;

    public CaseStudyServiceImpl(CaseStudyDao caseStudyDao) {
        Assert.assertNull(caseStudyDao, "CaseStudyDao is not present");
        this.caseStudyDao = caseStudyDao;
    }

    @Transactional
    public CaseStudy save(CaseStudy caseStudy) {
        Assert.assertNull(caseStudy, "Case study cannot be null");
        try {
            UUID caseStudyId = UUID.randomUUID();
            return caseStudyDao.save(caseStudy, caseStudyId);
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyService - could not save case study", e);
        }
    }

    @Transactional
    public CaseStudy get(UUID id) {
        Assert.assertNull(id, "ID cannot be null to request entry");
        return caseStudyDao.get(id);
    }

    @Transactional
    public List<CaseStudy> getAll() {
        return caseStudyDao.getAll();
    }

    @Transactional
    public List<CaseStudyOverview> getAllOverviews() {
        return caseStudyDao.getAllOverviews();
    }

    @Transactional
    public List<CaseStudyOverview> getSpotlitOverviews() {
        return caseStudyDao.getSpotlitOverviews();
    }

    @Transactional
    public CaseStudy update(UUID id, CaseStudy newCaseStudy) {
        Assert.assertNull(id, "ID must be included to update a case study");
        Assert.assertNull(newCaseStudy, "New case study must not be null");
        CaseStudy oldCaseStudy = caseStudyDao.get(id);
        Assert.assertNull(oldCaseStudy, "Object with specified ID could not be found. Revise ID and try again");
        return caseStudyDao.update(id, newCaseStudy, oldCaseStudy);
    }

    @Transactional
    public void delete(UUID id) {
        Assert.assertNull(id, "ID cannot be null to delete entry");
        caseStudyDao.delete(id);
    }
}
