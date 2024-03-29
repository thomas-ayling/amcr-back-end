package com.globallogic.amcr.repository.impl.casestudies;

import com.globallogic.amcr.repository.casestudies.CaseStudyDao;
import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CaseStudyDaoImpl implements CaseStudyDao {
    private final Logger Log = LoggerFactory.getLogger(CaseStudyDaoImpl.class);
    private final CaseStudyMapper caseStudyMapper;

    public CaseStudyDaoImpl(CaseStudyMapper caseStudyMapper) {
        this.caseStudyMapper = Assert.assertNotNull(caseStudyMapper, "Case study mapper cannot be null");
    }

    @Override
    public CaseStudy save(CaseStudy caseStudy, UUID caseStudyId) {
        try {
            caseStudy.setId(caseStudyId);
            Log.trace("DAO saving new case study:\n{}", caseStudy);
            caseStudyMapper.save(caseStudy);
            return caseStudy;
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyDaoImpl - could not save case study", e);
        }
    }

    @Override
    public CaseStudy get(UUID id) {
        Log.trace("DAO requesting case study with ID {}", id);
        return caseStudyMapper.get(id);
    }

    @Override
    public List<CaseStudy> getAll() {
        Log.trace("DAO requesting all case studies");
        return caseStudyMapper.getAll();
    }

    @Override
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
        if (newCaseStudy.getCoverImageId() == null) {
            newCaseStudy.setCoverImageId(oldCaseStudy.getCoverImageId());
        }
        if (newCaseStudy.getBody() == null) {
            newCaseStudy.setBody(oldCaseStudy.getBody());
        }
        if (newCaseStudy.getAttachmentIds() == null) {
            newCaseStudy.setAttachmentIds(oldCaseStudy.getAttachmentIds());
        }
        Log.trace("DAO updating case study with ID {} and content:\n{}\n\n\n\nwith new case study:\n\n{}", id, oldCaseStudy, newCaseStudy);
        caseStudyMapper.update(id, newCaseStudy);
        return newCaseStudy;
    }

    @Override
    public void delete(UUID id) {
        try {
            Log.trace("DAO deleting case study with ID {}", id);
            caseStudyMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error in CaseStudyDaoImpl - could not delete case study with id " + id, e);
        }
    }

    // Extra get methods

    @Override
    public List<CaseStudyOverview> getAllOverviews() {
        Log.trace("DAO requesting all case study overviews");
        return caseStudyMapper.getAllOverviews();
    }

    @Override
    public List<CaseStudyOverview> getSpotlitOverviews() {
        Log.trace("DAO requesting all spotlit case study overviews");
        return caseStudyMapper.getSpotlitOverviews();
    }

    @Override
    public List<CaseStudyOverview> getLatestOverviews(int entries) {
        Log.trace("DAO requesting the {} most recent overviews", entries);
        return caseStudyMapper.getLatestOverviews(entries);
    }
}
