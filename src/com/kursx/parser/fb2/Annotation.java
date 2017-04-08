package com.kursx.parser.fb2;

import com.kursx.parser.fb2.P;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Annotation {

    private List<P> annotation = new ArrayList<>();

    Annotation(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "p":
                    annotation.add(new P(paragraph.getTextContent()));
                    break;
            }
        }
    }

    public List<P> getAnnotation() {
        return annotation;
    }
}
