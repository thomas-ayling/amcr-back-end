package com.globallogic.amcr.persistence.model.layoutcomponent;

import java.util.UUID;

public class Layout {

    private UUID id;
    private String elementName;
    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private boolean movable;
    private String page;

    public Layout(String elementName, int xPosition, int yPosition, int width, int height, boolean movable, String page) {
        this.elementName = elementName;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.movable = movable;
        this.page = page;
    }

    public Layout(UUID id, String elementName, int xPosition, int yPosition, int width, int height, boolean movable, String page) {
        this.id = id;
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

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
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

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
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
        return "Layout{" + "id=" + id + ", elementName='" + elementName + '\'' + ", xPosition=" + xPosition + ", yPosition=" + yPosition + ", width=" + width + ", height=" + height + ", movable=" + movable + ", page='" + page + '\'' + '}';
    }
}
