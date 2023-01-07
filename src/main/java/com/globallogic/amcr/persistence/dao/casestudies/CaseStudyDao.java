package com.globallogic.amcr.persistence.dao.casestudies;

import com.globallogic.amcr.mapper.casestudies.CaseStudyMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.payload.casestudies.CaseStudyOverview;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CaseStudyDao implements Dao<CaseStudy, CaseStudy> {
    CaseStudyMapper caseStudyMapper;

    public CaseStudyDao(CaseStudyMapper caseStudyMapper) {
        this.caseStudyMapper = caseStudyMapper;
    }

    @Override
    public CaseStudy save(CaseStudy caseStudy, UUID caseStudyId) {
        try {
            caseStudy.setId(caseStudyId);
            caseStudyMapper.save(caseStudy);
            return caseStudy;
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyDao - could not save case study");
        }
    }

    @Override
    public CaseStudy get(UUID id) {
        return caseStudyMapper.get(id);
    }

    @Override
    public List<CaseStudy> getAll() {
        return caseStudyMapper.getAll();
    }

    /**
     * requests an overview of all entries in the case_studies table
     *
     * @return returns a list of CaseStudyOverview objects
     */
    public List<CaseStudyOverview> getAllOverviews() {
        return caseStudyMapper.getAllOverviews();
    }

    /**
     * requests an overview of all entries in the case_studies table where the spotlight field is true
     *
     * @return returns a list of CaseStudyOverview objects
     */
    public List<CaseStudyOverview> getSpotlitOverviews() {
        return caseStudyMapper.getSpotlitOverviews();
    }

    /**
     * requests an update to a specified object in the case_studies table
     *
     * @param newCaseStudy the new case study object with updated fields
     * @param oldCaseStudy the old case study object requested from table
     * @return returns the complete updated object
     */
    public CaseStudy update(UUID id, CaseStudy newCaseStudy, CaseStudy oldCaseStudy) {
        if (oldCaseStudy.equals(newCaseStudy)) {
            newCaseStudy.setId(id);
            return newCaseStudy;
        }
        if (newCaseStudy.getTitle() == null) {
            newCaseStudy.setTitle(oldCaseStudy.getTitle());
        }
        if (newCaseStudy.getOverview() == null) {
            newCaseStudy.setOverview(oldCaseStudy.getOverview());
        }
        if (newCaseStudy.getCoverImageLink() == null) {
            newCaseStudy.setCoverImageLink(oldCaseStudy.getCoverImageLink());
        }
        if (newCaseStudy.getBody() == null) {
            newCaseStudy.setBody(oldCaseStudy.getBody());
        }
        if (newCaseStudy.getDownloadLinks() == null) {
            newCaseStudy.setDownloadLinks(oldCaseStudy.getDownloadLinks());
        }
        caseStudyMapper.update(id, newCaseStudy);
        newCaseStudy.setId(id);
        return newCaseStudy;
    }

    /**
     * requests deletion of an entry with specified id
     *
     * @param id the if of the entry to be deleted
     */
    public void delete(UUID id) {
        try {
            caseStudyMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyDao - could not delete case study with id " + id, e);
        }
    }
}
