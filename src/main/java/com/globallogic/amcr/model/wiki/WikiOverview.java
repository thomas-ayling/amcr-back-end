package com.globallogic.amcr.model.wiki;

import com.globallogic.amcr.utils.Assert;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;
@SuppressWarnings("unused")
public class WikiOverview {

        private UUID id;
        @NotNull
        private String title;
        @NotNull
        private String overview;
        @NotNull
        private String subImage;
        @NotNull
        private String subTitle;
        @NotNull
        private String subOverview;

        public WikiOverview(UUID id, String title, String overview, String subImage, String subTitle, String subOverview) {
            setId(id);
            setTitle(title);
            setOverview(overview);
            setSubImage(subImage);
            setSubTitle(subTitle);
            setSubOverview(subOverview);
        }


    public WikiOverview() {
        }



//    public WikiPageOverview(UUID id, String title, String overview, String subImage, String subTitle, String subOverview, String diagram) {
//    }

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


        public String getSubImage() {
            return subImage;
        }

        public void setSubImage(String subImage) {
            this.subImage = Assert.assertNotNull(subImage, "Cover image cannot be null");
        }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {

        this.subTitle = Assert.assertNotNull(title, "The Subtitle cannot be null");
    }

    public String getSubOverview() {
        return subOverview;
    }

    public void setSubOverview(String subOverview) {
        this.subOverview = Assert.assertNotNull(subOverview, "Sub-overview cannot be null");
    }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WikiOverview that = (WikiOverview) o;
            return getTitle().equals(that.getTitle()) && getOverview().equals(that.getOverview()) && getSubImage().equals(that.getSubImage());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getTitle(), getOverview(), getSubImage(), getSubTitle(), getSubOverview());
        }

        @Override
        public String toString() {
            return "WikiOverview{" + "title='" + title + '\'' + ", overview='" + overview + '\'' + ", subImage='" + subImage + '\'' + ", subTitle='" + subTitle + '\'' + ", subOverview='" + subOverview + '\'' + '}';
        }
    }


