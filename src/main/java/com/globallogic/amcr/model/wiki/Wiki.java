package com.globallogic.amcr.model.wiki;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings("unused")
public class Wiki {

    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String overview;

    private UUID[] subImage;
    @NotNull
    private String subTitle;
    @NotNull
    private String subOverview;

    private UUID diagram;

    /**
     * @param id          the id of the object
     * @param title       the title of the wiki page
     * @param overview    general overview of the wiki page
     * @param subImage    image to be associated with the wiki page
     * @param subTitle    secondary title of the wiki page - mainly for what is near the image
     * @param subOverview secondary overview of the wiki page - gives points on what the section is about
     * @param diagram     diagram that speaks to the diagram component
     */
    public Wiki(UUID id, String title, String overview, UUID[] subImage, String subTitle, String subOverview, UUID diagram) {
        setId(id);
        setTitle(title);
        setOverview(overview);
        setSubImage(subImage);
        setSubTitle(subTitle);
        setSubOverview(subOverview);
        setDiagram(diagram);
    }


    public Wiki() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = Assert.assertNotNull(id, "ID cannot be null");
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


    public UUID[] getSubImage() {
        return subImage;
    }

    public void setSubImage(UUID[] subImage) {
        this.subImage = subImage;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {

        this.subTitle = Assert.assertNotNull(subTitle, "The Subtitle cannot be null");
    }

    public String getSubOverview() {
        return subOverview;
    }

    public void setSubOverview(String subOverview) {
        this.subOverview = Assert.assertNotNull(subOverview, "Sub-overview cannot be null");
    }


    public UUID getDiagram() {
        return diagram;
    }

    public void setDiagram(UUID diagram) {
        this.diagram = diagram;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wiki wiki = (Wiki) o;
        return Objects.equals(title, wiki.title) && Objects.equals(overview, wiki.overview) && Objects.equals(subTitle, wiki.subTitle) && Objects.equals(subOverview, wiki.subOverview);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, overview, subTitle, subOverview);
    }

    @Override
    public String toString() {
        return "Wiki{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", subImage=" + Arrays.toString(subImage) +
                ", subTitle='" + subTitle + '\'' +
                ", subOverview='" + subOverview + '\'' +
                ", diagram=" + diagram +
                '}';
    }
}

