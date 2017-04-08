package com.kursx.parser.fb2;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Binary {

    private String id;
    private String contentType;
    private String binary;

    public Binary(Node node) {
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
