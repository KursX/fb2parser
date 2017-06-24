package com.kursx.parser.fb2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PublishInfo {

    protected String bookName;
    protected String city;
    protected String year;
    protected String publisher;
    protected String isbn;
    protected Sequence sequence;

    public PublishInfo() {
    }

    PublishInfo(Document document) {
        NodeList description = document.getElementsByTagName("publish-info");
        for (int item = 0; item < description.getLength(); item++) {
            NodeList map = description.item(item).getChildNodes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                switch (node.getNodeName()) {
                    case "book-name":
                        bookName = node.getTextContent();
                        break;
                    case "city":
                        city = node.getTextContent();
                        break;
                    case "year":
                        year = node.getTextContent();
                        break;
                    case "isbn":
                        isbn = node.getTextContent();
                        break;
                    case "publisher":
                        publisher = node.getTextContent();
                        break;
                    case "sequence":
                        sequence = new Sequence(node);
                        break;
                }
            }
        }
    }

    public String getBookName() {
        return bookName;
    }

    public String getCity() {
        return city;
    }

    public String getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public String getIsbn() {
        return isbn;
    }
}
