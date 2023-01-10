package com.globallogic.amcr.persistence.payload.contactcomponent;

import com.globallogic.amcr.utils.Assert;

/**
 * Attachment response to download a file from the files database
 */
public class AttachmentResponse {
    private String fileName;
    private String fileType;
    private byte[] data;

    /**
     * @param fileName the name of the file
     * @param fileType the type of the file
     * @param data     the raw binary data of the file to be downloaded
     */

    public AttachmentResponse(String fileName, String fileType, byte[] data) {
        this.fileName = Assert.assertNull(fileName, "File name cannot be null");
        this.fileType = Assert.assertNull(fileType, "File type cannot be null");
        this.data = Assert.assertNull(data, "Data cannot be null");
    }

    public AttachmentResponse() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = Assert.assertNull(fileName, "File name cannot be null");
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = Assert.assertNull(fileType, "File type cannot be null");
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = Assert.assertNull(data, "Data cannot be null");
    }
}
