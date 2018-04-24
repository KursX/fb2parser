package com.kursx.parser.fb2;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Epigraph extends IdElement {

    protected ArrayList<Element> elements = new ArrayList<>();
    protected ArrayList<TextAuthor> textAuthor = new ArrayList<>();

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
                case "text-author":
                    if (textAuthor == null) textAuthor = new ArrayList<>();
                    textAuthor.add(new TextAuthor(node));
                    break;
                case "poem":
                    elements.add(new Poem(node));
                    break;
                case "cite":
                    elements.add(new Cite(node));
                    break;
                case "p":
                    elements.add(new P(node));
                    break;
            }
        }
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public ArrayList<TextAuthor> getTextAuthor() {
        return textAuthor;
    }

    public void setTextAuthor(ArrayList<TextAuthor> textAuthor) {
        this.textAuthor = textAuthor;
    }
}
