package com.globallogic.amcr.model.casestudies;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@SuppressWarnings("unused")
public class CaseStudy extends CaseStudyOverview {
    @NotNull
    private List<Map<String, String>> body;
    private UUID[] attachmentIds;

    /**
     * @param id             the id of the object
     * @param spotlight      keeps track of whether the case study should be spotlighted on the case studies page
     * @param title          the title of the case study
     * @param overview       the text intro to be displayed in the main case study page
     * @param coverImageId the id of the image to be used as the cover image (on the carousels for example)
     * @param body           the main data to be displayed on the single page, stored as JSON
     * @param attachmentIds  the list of ids to related attachments
     */

    public CaseStudy(UUID id, boolean spotlight, String title, String overview, UUID coverImageId, List<Map<String, String>> body, UUID[] attachmentIds) {
        super(id, spotlight, title, overview, coverImageId);
        setBody(body);
        setAttachmentIds(attachmentIds);
    }

    public CaseStudy() {
        super();
    }

    public List<Map<String, String>> getBody() {
        return body;
    }

    public void setBody(List<Map<String, String>> body) {
        this.body = Assert.assertNotNull(body, "Case study's body cannot be null");
    }

    public UUID[] getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(UUID[] attachmentIds) {
        this.attachmentIds = attachmentIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CaseStudy caseStudy = (CaseStudy) o;
        return getBody().equals(caseStudy.getBody()) && Arrays.equals(getAttachmentIds(), caseStudy.getAttachmentIds());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), getBody());
        result = 31 * result + Arrays.hashCode(getAttachmentIds());
        return result;
    }

    @Override
    public String toString() {
        return "CaseStudy{" +
                "body=" + body +
                ", downloadLinks=" + Arrays.toString(attachmentIds) +
                '}' + super.toString();
    }
}
