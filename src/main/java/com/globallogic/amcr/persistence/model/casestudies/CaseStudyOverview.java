package com.globallogic.amcr.persistence.model.casestudies;

import java.util.Objects;
import java.util.UUID;

public class CaseStudyOverview {
    private UUID id;
    private boolean spotlight;
    private String title;
    private String overview;
    private String coverImageLink;

    public CaseStudyOverview(UUID id, boolean spotlight, String title, String overview, String coverImageLink) {
        this.id = id;
        this.spotlight = spotlight;
        this.title = title;
        this.overview = overview;
        this.coverImageLink = coverImageLink;
    }

    public CaseStudyOverview(boolean spotlight, String title, String overview, String coverImageLink) {
        this.spotlight = spotlight;
        this.title = title;
        this.overview = overview;
        this.coverImageLink = coverImageLink;
    }

    public CaseStudyOverview() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getSpotlight() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseStudyOverview that = (CaseStudyOverview) o;
        return getTitle().equals(that.getTitle()) && getOverview().equals(that.getOverview()) && getCoverImageLink().equals(that.getCoverImageLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getOverview(), getCoverImageLink());
    }

    @Override
    public String toString() {
        return "CaseStudyOverview{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", coverImageLink='" + coverImageLink + '\'' +
                '}';
    }
}
