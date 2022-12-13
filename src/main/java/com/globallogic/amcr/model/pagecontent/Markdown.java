package com.globallogic.amcr.model.pagecontent;

import java.util.UUID;

public class Markdown {

    private UUID id;

    private String content;

    public Markdown(String content) {
        this.content = content;
    }

    public Markdown() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
