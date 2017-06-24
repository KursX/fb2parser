package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Annotation {

    protected List<P> annotation = new ArrayList<>();
//  TODO  http://www.fictionbook.org/index.php/Элемент_annotation


    public Annotation() {
    }

    Annotation(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "p":
                    annotation.add(new P(paragraph));
                    break;
            }
        }
    }

    public List<P> getAnnotations() {
        return annotation;
    }
}
