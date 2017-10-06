package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Cite extends Element {

    protected List<TextAuthor> textAuthor;
    protected List<Element> elements = new ArrayList<>();

    public Cite() {
    }

    Cite(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "text-author":
                    if (textAuthor == null) textAuthor = new ArrayList<>();
                    textAuthor.add(new TextAuthor(paragraph));
                    break;
                case "poem":
                    elements.add(new Poem(paragraph));
                    break;
                case "subtitle":
                    elements.add(new Subtitle(paragraph));
                    break;
                case "p":
                    elements.add(new P(paragraph));
                    break;
                case "empty-line":
                    elements.add(new EmptyLine());
                    break;
            }
        }
    }

    public List<TextAuthor> getTextAuthor() {
        return textAuthor;
    }

    public void setTextAuthor(List<TextAuthor> textAuthor) {
        this.textAuthor = textAuthor;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String getText() {
        List<Element> list = new ArrayList<>(elements);
        if (textAuthor != null) list.addAll(textAuthor);
        return Element.getText(list, "\n");
    }
}
