package com.globallogic.amcr.attachmentcomponent.persistence.model;

import java.util.Objects;

public class MediaData {
    private int height;
    private int width;

    public MediaData() {

    }

    public MediaData(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaData mediaData = (MediaData) o;
        return height == mediaData.height && width == mediaData.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    @Override
    public String toString() {
        return "MediaData{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}
