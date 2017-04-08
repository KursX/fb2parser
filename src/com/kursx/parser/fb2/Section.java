package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Section {

    private List<P> title;
    private List<Section> sections;
    private List<P> paragraphs;

    Section(Node root) {
        NodeList body = root.getChildNodes();
        for (int item = 0; item < body.getLength(); item++) {
            Node node = body.item(item);
            switch (node.getNodeName()) {
                case "title":
                    if (title == null) title = new ArrayList<>();
                    NodeList titleNode = node.getChildNodes();
                    for (int child = 0; child < titleNode.getLength(); child++) {
                        Node titleP = titleNode.item(child);
                        if (titleP.getNodeName().equals("p")) {
                            title.add(new P(titleP.getTextContent()));
                        }
                    }
                    break;
                case "section":
                    if (sections == null) sections = new ArrayList<>();
                    sections.add(new Section(node));
                    break;
                case "p":
                    if (paragraphs == null) paragraphs = new ArrayList<>();
                    paragraphs.add(new P(node.getTextContent()));
                    break;
            }
        }
    }

    public List<P> getTitle() {
        return title;
    }

    public List<Section> getSections() {
        return sections;
    }

    public List<P> getParagraphs() {
        return paragraphs;
    }
}
