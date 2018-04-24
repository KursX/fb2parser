package com.kursx.parser.fb2;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class Description {

    protected TitleInfo titleInfo;
    protected SrcTitleInfo srcTitleInfo;
    protected DocumentInfo documentInfo;
    protected PublishInfo publishInfo;
    protected ArrayList<CustomInfo> customInfo = new ArrayList<>();

    public Description() {
    }

    Description(Document doc) {
        titleInfo = new TitleInfo(doc);
        srcTitleInfo = new SrcTitleInfo(doc);
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

    public SrcTitleInfo getSrcTitleInfo() {
        return srcTitleInfo;
    }

    public ArrayList<CustomInfo> getCustomInfo() {
        return customInfo;
    }
}
