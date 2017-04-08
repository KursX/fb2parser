package com.kursx.parser.fb2;

import org.w3c.dom.Document;

public class Description {

    private TitleInfo titleInfo;
    private DocumentInfo documentInfo;
    private PublishInfo publishInfo;

    Description(Document doc) {
        titleInfo = new TitleInfo(doc);
        documentInfo = new DocumentInfo(doc);
        publishInfo = new PublishInfo(doc);
    }

    public DocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    public TitleInfo getTitleInfo() {
        return titleInfo;
    }

    public PublishInfo getPublishInfo() {
        return publishInfo;
    }
}
