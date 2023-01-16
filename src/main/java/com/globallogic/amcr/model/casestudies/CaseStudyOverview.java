package com.globallogic.amcr.model.casestudies;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("unused")
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
        setId(id);
        setSpotlight(spotlight);
        setTitle(title);
        setOverview(overview);
        setCoverImageLink(coverImageLink);
    }

    public CaseStudyOverview() {
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
        this.title = Assert.assertNull(title, "Title cannot be null");
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = Assert.assertNull(overview, "Overview cannot be null");
    }

    public String getCoverImageLink() {
        return coverImageLink;
    }

    public void setCoverImageLink(String coverImageLink) {
        this.coverImageLink = Assert.assertNull(coverImageLink, "Cover image link cannot be null");
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
        return "CaseStudyOverview{" + "title='" + title + '\'' + ", overview='" + overview + '\'' + ", coverImageLink='" + coverImageLink + '\'' + '}';
    }
}
