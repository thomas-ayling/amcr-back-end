package com.globallogic.amcr.service.casestudies;

import com.globallogic.amcr.persistence.model.casestudies.CaseStudy;
import com.globallogic.amcr.persistence.payload.casestudies.CaseStudyOverview;

import java.util.List;
import java.util.UUID;

public interface CaseStudyService {
    /**
     * saves a case study object to the database
     *
     * @param caseStudy takes in a case study object
     * @return returns the case study object if the request is successful
     */
    CaseStudy save(CaseStudy caseStudy);

    /**
     * requests a case study with a given id
     *
     * @param id the id of the case study to be found
     * @return returns the requested cases study with all fields if found, else throws 404 not found error
     */
    CaseStudy get(UUID id);

    /**
     * requests all case studies in the database
     *
     * @return returns a list of all saved case studies with all fields
     */
    List<CaseStudy> getAll();

    /**
     * requests the overviews of all case studies in the database
     *
     * @return returns a list of overviews of all entries in the database
     */
    List<CaseStudyOverview> getAllOverviews();

    /**
     * requests the overviews of all case studies where the spotlight boolean is set to true (for use on the case studies main page)
     *
     * @return returns a list of all spotlit case studies
     */
    List<CaseStudyOverview> getSpotlitOverviews();

    /**
     * requests an update to the specified case study
     *
     * @param id           the id of the case study to be updated
     * @param newCaseStudy the new case study object to overwrite the previously saved case study. the id must match the id of the entry to update
     * @return returns the complete updated case study
     */
    CaseStudy update(UUID id, CaseStudy newCaseStudy);

    /**
     * requests to delete an entry with a specified id
     *
     * @param id the id of the entry to be deleted
     */
    void delete(UUID id);
}
