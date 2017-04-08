package com.kursx.parser.fb2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Body {

    private List<Section> sections = new ArrayList<>();

    Body(Document doc) {
        NodeList body = doc.getElementsByTagName("body");
        for (int item = 0; item < body.getLength(); item++) {
            NodeList map = body.item(item).getChildNodes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                switch (node.getNodeName()) {
                    case "section":
                        sections.add(new Section(node));
                        break;
                }
            }
        }
    }

    public List<Section> getSections() {
        return sections;
    }
}