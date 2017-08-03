package com.kursx.parser.fb2;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Date {

    protected String value;
    protected String date;

    public Date() {
    }

    Date(Node node) {
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("value")) {
                value = attr.getNodeValue();
            }
        }
        date = node.getTextContent();
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}