package com.globallogic.amcr.persistence.model.layoutcomponent;

import java.util.UUID;

public class Layout {

    private UUID id;
    private String elementName;
    private Integer xPosition;
    private Integer yPosition;
    private Integer width;
    private Integer height;
    private Boolean movable;
    private String page;


    public Layout(UUID id, String elementName, Integer xPosition, Integer yPosition, Integer width, Integer height, Boolean movable, String page, UUID profileId) {
        this.id = id;
        this.elementName = elementName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.movable = movable;
        this.page = page;

    }

    public Layout(String elementName, Integer xPosition, Integer yPosition, Integer width, Integer height, Boolean movable, String page, UUID profileId) {
        this.elementName = elementName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.movable = movable;
        this.page = page;
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

    public Integer getxPosition() {
        return xPosition;
    }

    public void setxPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public void setyPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getIsMovable() {
        return movable;
    }

    public void setIsMovable(Boolean movable) {
        this.movable = movable;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }




    @Override
    public String toString() {
        return "Layout{" + "id=" + id + ", elementName='" + elementName + '\'' + ", xPosition=" + xPosition + ", yPosition=" + yPosition + ", width=" + width + ", height=" + height + ", movable=" + movable + ", page='" + page + '\'' +'}';
    }
}
