package com.kursx.parser.fb2;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Epigraph {

    protected String id;
    protected List<P> paragraphs = new ArrayList<>();
    protected String textAuthor;

// TODO http://www.fictionbook.org/index.php/Элемент_text-author

    public Epigraph() {
    }

    Epigraph(Node root) {
        NamedNodeMap map = root.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("id")) {
                id = attr.getNodeValue();
            }
        }
        NodeList body = root.getChildNodes();
        for (int item = 0; item < body.getLength(); item++) {
            Node node = body.item(item);
            switch (node.getNodeName()) {
                case "p":
                    paragraphs.add(new P(node));
                    break;
                case "text-author":
                    textAuthor = node.getTextContent();
                    break;
            }
        }
    }

    public String getId() {
        return id;
    }

    public List<P> getParagraphs() {
        return paragraphs;
    }

    public String getTextAuthor() {
        return textAuthor;
    }
}
