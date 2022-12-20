package com.globallogic.amcr.pagecontent.model;


public class Markdown {

    private int id;

    private String content;

    public Markdown(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Markdown() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
