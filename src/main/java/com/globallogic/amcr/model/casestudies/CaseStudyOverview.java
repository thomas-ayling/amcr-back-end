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
    private UUID coverImageId;
    private transient String coverImageLink;

    public CaseStudyOverview(UUID id, boolean spotlight, String title, String overview, UUID coverImageId) {
        setId(id);
        setSpotlight(spotlight);
        setTitle(title);
        setOverview(overview);
        setCoverImageId(coverImageId);
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
        this.title = Assert.assertNotNull(title, "Title cannot be null");
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = Assert.assertNotNull(overview, "Overview cannot be null");
    }

    public UUID getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(UUID coverImageId) {
        this.coverImageId = Assert.assertNotNull(coverImageId, "Cover image link cannot be null");
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
        return getTitle().equals(that.getTitle()) && getOverview().equals(that.getOverview()) && getCoverImageId().equals(that.getCoverImageId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getOverview(), getCoverImageId());
    }

    @Override
    public String toString() {
        return "CaseStudyOverview{" + "title='" + title + '\'' + ", overview='" + overview + '\'' + ", coverImageId='" + coverImageId + '\'' + '}';
    }
}
