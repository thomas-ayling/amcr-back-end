package com.globallogic.amcr.service.casestudies;

import com.globallogic.amcr.model.casestudies.CaseStudy;
import com.globallogic.amcr.model.casestudies.CaseStudyOverview;
import com.globallogic.amcr.service.CrudService;

import java.util.List;

public interface CaseStudyService extends CrudService<CaseStudy> {
    // Extra get methods

    /**
     * Requests the overviews of all case studies where the spotlight boolean is set to true (for use on the case studies main page)
     *
     * @return Returns a list of all spotlit case studies
     */
    List<CaseStudyOverview> getSpotlitOverviews();

    /**
     * Requests the latest 5 overviews of entries in the database
     *
     * @return Returns a list of the last 5 overviews in the database
     */
    List<CaseStudyOverview> getLatestOverviews(int entries);

    /**
     * Requests the overviews of all case studies in the database
     *
     * @return Returns a list of overviews of all entries in the database
     */
    List<CaseStudyOverview> getAllOverviews();

}
