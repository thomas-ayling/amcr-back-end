package com.globallogic.amcr.repository.casestudies;

import com.globallogic.amcr.repository.CrudDao;
import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.casestudies.CaseStudyOverview;

import java.util.List;

public interface CaseStudyDao extends CrudDao<CaseStudy, CaseStudy> {

    /**
     * Requests an overview of all entries in the case_studies table
     *
     * @return Returns a list of CaseStudyOverviews
     */
    List<CaseStudyOverview> getAllOverviews();

    /**
     * Requests an overview of all entries in the case_studies table where the spotlight field is true
     *
     * @return Returns a list of CaseStudyOverview objects
     */
    List<CaseStudyOverview> getSpotlitOverviews();

    /**
     * Requests the most recent entries in the case_studies table.
     * The number of entries is defined by the entries parameter
     *
     * @param entries The number of entries to be returned
     * @return The latest {entries} entries in the database
     */
    List<CaseStudyOverview> getLatestOverviews(int entries);
}
