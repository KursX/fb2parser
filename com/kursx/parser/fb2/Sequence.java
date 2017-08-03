package com.kursx.parser.fb2;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Sequence {

    protected String name;
    protected String number;

    public Sequence() {
    }

    Sequence(Node node) {
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("name")) {
                name = attr.getNodeValue();
            } else if (attr.getNodeName().equals("number")) {
                number = attr.getNodeValue();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
