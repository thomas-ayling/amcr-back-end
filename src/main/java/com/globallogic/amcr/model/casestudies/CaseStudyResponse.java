package com.globallogic.amcr.model.casestudies;

import com.globallogic.amcr.utils.Assert;

import java.util.List;

public class CaseStudyResponse extends CaseStudy {
    private List<String> attachmentLinks;
    private String coverImageLink;

    public CaseStudyResponse(CaseStudy caseStudy, List<String> attachmentLinks, String coverImageLink) {
        super(caseStudy.getId(), caseStudy.getSpotlight(), caseStudy.getTitle(), caseStudy.getOverview(), caseStudy.getCoverImageId(), caseStudy.getBody(), caseStudy.getAttachmentIds());
        this.attachmentLinks = attachmentLinks;
        this.coverImageLink = coverImageLink;
    }

    public List<String> getAttachmentLinks() {
        return attachmentLinks;
    }

    public void setAttachmentLinks(List<String> attachmentLinks) {
        this.attachmentLinks = Assert.assertNotNull(attachmentLinks, "Attachment links cannot be null");
    }

    public String getCoverImageLink() {
        return coverImageLink;
    }

    public void setCoverImageLink(String coverImageLink) {
        this.coverImageLink = Assert.assertNotNull(coverImageLink, "Cover image link cannot be null");
    }
}
