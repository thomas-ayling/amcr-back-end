package com.globallogic.amcr.persistence.model.casestudies;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("unused")
public class CaseStudy extends CaseStudyOverview {
    @NotNull
    private Map<?, ?> body;
    private String[] downloadLinks;

    /**
     * @param id             the id of the object
     * @param spotlight      keeps track of whether the case study should be spotlighted on the case studies page
     * @param title          the title of the case study
     * @param overview       the text intro to be displayed in the main case study page
     * @param coverImageLink the link to the image to be used as the cover (on the carousels for example)
     * @param body           the main data to be displayed on the single page, stored as JSON
     * @param downloadLinks  the list of download links to related attachments
     */

    public CaseStudy(UUID id, boolean spotlight, String title, String overview, String coverImageLink, Map<?, ?> body, String[] downloadLinks) {
        super(id, spotlight, title, overview, coverImageLink);
        setBody(body);
        setDownloadLinks(downloadLinks);
    }

    public CaseStudy() {
        super();
    }

    public Map<?, ?> getBody() {
        return body;
    }

    public void setBody(Map<?, ?> body) {
        this.body = Assert.assertNull(body, "Case study's body cannot be null");
    }

    public String[] getDownloadLinks() {
        return downloadLinks;
    }

    public void setDownloadLinks(String[] downloadLinks) {
        this.downloadLinks = downloadLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CaseStudy caseStudy = (CaseStudy) o;
        return getBody().equals(caseStudy.getBody()) && Arrays.equals(getDownloadLinks(), caseStudy.getDownloadLinks());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), getBody());
        result = 31 * result + Arrays.hashCode(getDownloadLinks());
        return result;
    }

    @Override
    public String toString() {
        return "CaseStudy{" +
                "body=" + body +
                ", downloadLinks=" + Arrays.toString(downloadLinks) +
                '}' + super.toString();
    }
}
