package com.globallogic.amcr.persistence.dao.casestudies;

import com.globallogic.amcr.mapper.casestudies.CaseStudyMapper;
import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CaseStudyDao implements Dao<CaseStudy, CaseStudy> {
    public final Logger Log = LoggerFactory.getLogger(CaseStudyDao.class.getName());
    final CaseStudyMapper caseStudyMapper;

    public CaseStudyDao(CaseStudyMapper caseStudyMapper) {
        this.caseStudyMapper = Assert.assertNull(caseStudyMapper, "Case study mapper cannot be null");
    }

    @Override
    public CaseStudy save(CaseStudy caseStudy, UUID caseStudyId) {
        try {
            caseStudy.setId(caseStudyId);
            Log.trace("Saving new case study {}", caseStudy);
            caseStudyMapper.save(caseStudy);
            return caseStudy;
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyDao - could not save case study");
        }
    }

    @Override
    public CaseStudy get(UUID id) {
        Log.trace("Requesting case study with ID {}", id);
        return caseStudyMapper.get(id);
    }

    @Override
    public List<CaseStudy> getAll() {
        Log.trace("Requesting all case studies");
        return caseStudyMapper.getAll();
    }

    /**
     * requests an overview of all entries in the case_studies table
     *
     * @return returns a list of CaseStudyOverview objects
     */
    public List<CaseStudyOverview> getAllOverviews() {
        Log.trace("Requesting all case study overviews");
        return caseStudyMapper.getAllOverviews();
    }

    /**
     * requests an overview of all entries in the case_studies table where the spotlight field is true
     *
     * @return returns a list of CaseStudyOverview objects
     */
    public List<CaseStudyOverview> getSpotlitOverviews() {
        Log.trace("Requesting all spotlit case study overviews");
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
        newCaseStudy.setId(id);
        if (oldCaseStudy.equals(newCaseStudy)) {
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
        Log.trace("Updating case study with ID {} and content {} with {}", id, oldCaseStudy, newCaseStudy);
        caseStudyMapper.update(id, newCaseStudy);
        return newCaseStudy;
    }

    /**
     * requests deletion of an entry with specified id
     *
     * @param id the if of the entry to be deleted
     */
    public void delete(UUID id) {
        try {
            Log.trace("Deleting case study with ID {}", id);
            caseStudyMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyDao - could not delete case study with id " + id, e);
        }
    }
}
