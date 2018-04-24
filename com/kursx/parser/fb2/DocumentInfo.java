package com.kursx.parser.fb2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class DocumentInfo {

    protected ArrayList<Person> authors = new ArrayList<>();
    protected ArrayList<Person> publishers;
    protected String programUsed;
    protected String srcUrl;
    protected String srcOcr;
    protected String email;
    protected String id;
    protected String version;
    protected History history;
    protected Date date;

    public DocumentInfo() {
    }

    DocumentInfo(Document document) {
        NodeList description = document.getElementsByTagName("document-info");
        for (int item = 0; item < description.getLength(); item++) {
            NodeList map = description.item(item).getChildNodes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                switch (node.getNodeName()) {
                    case "author":
                        authors.add(new Person(node));
                        break;
                    case "publisher":
                        if (publishers == null) publishers = new ArrayList<>();
                        publishers.add(new Person(node));
                        break;
                    case "program-used":
                        programUsed = node.getTextContent();
                        break;
                    case "email":
                        email = node.getTextContent();
                        break;
                    case "src-url":
                        srcUrl = node.getTextContent();
                        break;
                    case "src-ocr":
                        srcOcr = node.getTextContent();
                        break;
                    case "id":
                        id = node.getTextContent();
                        break;
                    case "version":
                        version = node.getTextContent();
                        break;
                    case "history":
                        this.history = new History(node);
                        break;
                    case "date":
                        this.date = new Date(node);
                        break;
                }
            }
        }
    }

    public ArrayList<Person> getAuthors() {
        return authors;
    }

    public String getProgramUsed() {
        return programUsed;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public String getSrcOcr() {
        return srcOcr;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public History getHistory() {
        return history;
    }

    public Date getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }
}