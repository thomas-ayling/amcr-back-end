package com.globallogic.amcr.persistence.model.casestudies;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class CaseStudyOverview {
    private UUID id;
    @NotNull
    private boolean spotlight;
    @NotNull
    private String title;
    @NotNull
    private String overview;
    @NotNull
    private String coverImageLink;

    public CaseStudyOverview(UUID id, boolean spotlight, String title, String overview, String coverImageLink) {
        this.id = id;
        this.spotlight = Assert.assertNotNull(spotlight, "Spotlight cannot be null");
        this.title = Assert.assertNotNull(title, "Title cannot be null");
        this.overview = Assert.assertNotNull(overview, "Overview cannot be null");
        this.coverImageLink = Assert.assertNotNull(coverImageLink, "Cover image cannot be null");
    }

    public CaseStudyOverview() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id, "ID cannot be null");
    }

    public boolean getSpotlight() {
        return spotlight;
    }

    public void setSpotlight(boolean spotlight) {
        this.spotlight = Assert.assertNotNull(spotlight, "Spotlight cannot be null");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Assert.assertNotNull(title, "");
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
