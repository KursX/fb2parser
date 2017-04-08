package com.kursx.parser.fb2;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class CoverPage {

    private List<Image> images = new ArrayList<>();

    CoverPage(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node child = nodeList.item(i);
            switch (child.getNodeName()) {
                case "image":
                    NamedNodeMap map = child.getAttributes();
                    for (int index = 0; index < map.getLength(); index++) {
                        Node attr = map.item(index);
                        images.add(new Image(attr));
                    }
                    break;
            }
        }
    }

    public List<Image> getImages() {
        return images;
    }
}