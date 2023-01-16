package com.globallogic.amcr.persistence.dao.contactcomponent;

import com.globallogic.amcr.persistence.dao.Dao;
import com.globallogic.amcr.persistence.model.contactcomponent.Feedback;

import java.util.List;

public interface FeedbackDao extends Dao<Feedback, Feedback> {

    /**
     * Requests the 10 latest entries in the feedback table
     *
     * @return Returns a list of the last 10 entries in the feedback table
     */
    List<Feedback> getLatest();


    /**
     * Requests 10 entries older than the {last} entry
     *
     * @param last The 'feedback order' of the last received feedback entry
     * @return Returns the 10 entries that follow the 'last' entry
     */
    List<Feedback> getOlder(int last);

}
