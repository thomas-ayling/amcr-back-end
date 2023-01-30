package com.globallogic.amcr.model.attachmentcomponent;

import com.globallogic.amcr.utils.Assert;

public class Content {

    private byte[] content;

    public Content() {}

    public Content(byte[] content) {
        setContent(content);
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = Assert.assertNotNull(content, "Content cannot be null");
    }
}
