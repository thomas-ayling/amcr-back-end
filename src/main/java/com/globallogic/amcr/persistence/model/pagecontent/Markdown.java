package com.globallogic.amcr.persistence.model.pagecontent;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.UUID;

/**
 * Markdown model for Markdown component
 */
public class Markdown {

    private UUID id;
    private int orderNumber;
    private String name;
    private String content;

    /**
     * @param orderNumber the number associated with each entry, which can be used to get the latest one
     * @param name the name of a markdown entry set by the user
     * @param content the text input that is converted into markdown
     */

    public Markdown(UUID id, int orderNumber, String name, String content) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.name = name;
        this.content = content;
    }

    public Markdown() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        Assert.notNull(id, "ID is null");
        this.id = id;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        Assert.notNull(orderNumber, "Order number is null");
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Assert.notNull(name, "Name is null");
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Markdown markdown = (Markdown) o;
        return Objects.equals(id, markdown.id) && Objects.equals(name, markdown.name) && Objects.equals(content, markdown.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content);
    }

    @Override
    public String toString() {
        return "Markdown{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}