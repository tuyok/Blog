package com.tuyongkang.blog.util.Vo;

import java.io.Serializable;

public class EmailAttachmentVo implements Serializable {

    private static final long serialVersionUID = 7316991294294102884L;

    private String fileName;
    private byte[] fileContent;

    public EmailAttachmentVo() {
        super();
    }

    public EmailAttachmentVo(String fileName, byte[] fileContent) {
        super();
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
