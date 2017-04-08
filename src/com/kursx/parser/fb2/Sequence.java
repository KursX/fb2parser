package com.kursx.parser.fb2;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Sequence {

    private String name;

    Sequence(Node node) {
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("name")) {
                name = attr.getNodeValue();
            }
        }
    }

    public String getName() {
        return name;
    }
}
