package com.kursx.parser.fb2;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Binary {

    protected String id;
    protected String contentType;
    protected String binary;

    public Binary() {
    }

    Binary(Node node) {
        binary = node.getTextContent();
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            switch (attr.getNodeName()) {
                case "id":
                    id = attr.getNodeValue();
                    break;
                case "content-type":
                    contentType = attr.getNodeValue();
                    break;
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getContentType() {
        return contentType;
    }

    public String getBinary() {
        return binary;
    }
}
