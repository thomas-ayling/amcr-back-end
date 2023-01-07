package com.globallogic.amcr.persistence.model.casestudies;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class CaseStudy {
    private UUID id;
    private boolean spotlight;
    private String title;
    private String overview;
    private String coverImageLink;
    private Map body;
    private String[] downloadLinks;

    /**
     * @param id             the id of the object
     * @param spotlight      keeps track of whether the case study should be spotlighted on the case studies page
     * @param title          the title of the case study
     * @param overview       the text intro to be displayed in the main case study page
     * @param coverImageLink the link to the image to be used as the cover (on the carousels for example)
     * @param body           the main data to be displayed on the single page, stored as JSON
     * @param downloadLinks  the list of download links to related files
     */

    public CaseStudy(UUID id, boolean spotlight, String title, String overview, String coverImageLink, Map body, String[] downloadLinks) {
        this.id = id;
        this.spotlight = spotlight;
        this.title = title;
        this.overview = overview;
        this.coverImageLink = coverImageLink;
        this.body = body;
        this.downloadLinks = downloadLinks;
    }

    public CaseStudy(boolean spotlight, String title, String overview, String coverImageLink, Map body, String[] downloadLinks) {
        this.spotlight = spotlight;
        this.title = title;
        this.overview = overview;
        this.coverImageLink = coverImageLink;
        this.body = body;
        this.downloadLinks = downloadLinks;
    }

    public CaseStudy() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isSpotlight() {
        return spotlight;
    }

    public void setSpotlight(boolean spotlight) {
        this.spotlight = spotlight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCoverImageLink() {
        return coverImageLink;
    }

    public void setCoverImageLink(String coverImageLink) {
        this.coverImageLink = coverImageLink;
    }

    public Map getBody() {
        return body;
    }

    public void setBody(Map body) {
        this.body = body;
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
        CaseStudy caseStudy = (CaseStudy) o;
        return isSpotlight() == caseStudy.isSpotlight() && getTitle().equals(caseStudy.getTitle()) && getOverview().equals(caseStudy.getOverview()) && getBody().equals(caseStudy.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSpotlight(), getTitle(), getOverview(), getBody());
    }

    @Override
    public String toString() {
        return "CaseStudy{" +
                "id=" + id +
                ", spotlight=" + spotlight +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", coverImageLink='" + coverImageLink + '\'' +
                ", body=" + body +
                ", downloadLinks=" + Arrays.toString(downloadLinks) +
                '}';
    }
}
