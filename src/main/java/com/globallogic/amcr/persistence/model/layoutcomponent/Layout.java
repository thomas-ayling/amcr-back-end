package com.globallogic.amcr.persistence.model.layoutcomponent;

import java.util.UUID;

public class Layout {

    private UUID id;
    private String elementName;
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private Boolean Static;

    private UUID pageId;


    public Layout(UUID id, String elementName, int xPosition, int yPosition, int width, int height, Boolean aStatic, UUID pageId) {
        this.id = id;
        this.elementName = elementName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        Static = aStatic;
        this.pageId = pageId;
    }

    public Layout() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Boolean getStatic() {
        return Static;
    }

    public void setStatic(Boolean Static) {
        this.Static = Static;
    }

    public UUID getPageId() {
        return pageId;
    }

    public void setPageId(UUID pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString() {
        return "Layout{" +
                "id=" + id +
                ", elementName='" + elementName + '\'' +
                ", xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", width=" + width +
                ", height=" + height +
                ", Static=" + Static +
                ", pageId=" + pageId +
                '}';
    }
}
