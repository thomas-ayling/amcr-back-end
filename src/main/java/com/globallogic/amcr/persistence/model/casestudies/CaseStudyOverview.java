package com.globallogic.amcr.persistence.model.casestudies;

import com.globallogic.amcr.utils.Assert;

import java.util.Objects;
import java.util.UUID;

public class CaseStudyOverview {
    private UUID id;
    private boolean spotlight;
    private String title;
    private String overview;
    private String coverImageLink;

    public CaseStudyOverview(UUID id, boolean spotlight, String title, String overview, String coverImageLink) {
        System.out.println("All args");
        this.id = id;
        this.spotlight = Assert.assertNull(spotlight, "Spotlight cannot be null");
        this.title = Assert.assertNull(title, "Title cannot be null");
        this.overview = Assert.assertNull(overview, "Overview cannot be null");
        this.coverImageLink = Assert.assertNull(coverImageLink, "Cover image cannot be null");
    }

    public CaseStudyOverview(boolean spotlight, String title, String overview, String coverImageLink) {
        System.out.println("Most args");
        this.spotlight = Assert.assertNull(spotlight, "Spotlight cannot be null");
        this.title = Assert.assertNull(title, "Title cannot be null");
        this.overview = Assert.assertNull(overview, "Overview cannot be null");
        this.coverImageLink = Assert.assertNull(coverImageLink, "Cover image cannot be null");
    }

    public CaseStudyOverview() {
        System.out.println("No args");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNull(id, "ID cannot be null");
    }

    public boolean getSpotlight() {
        return spotlight;
    }

    public void setSpotlight(boolean spotlight) {
        this.spotlight = Assert.assertNull(spotlight, "Spotlight cannot be null");

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Assert.assertNull(title, "");
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
