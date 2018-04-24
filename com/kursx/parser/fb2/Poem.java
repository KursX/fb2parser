package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Poem extends Element {

    protected Title title;
    protected ArrayList<Epigraph> epigraphs;
    protected ArrayList<Stanza> stanza = new ArrayList<>();
    protected String textAuthor;
    protected String date;

    public Poem() {
    }

    Poem(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "text-author":
                    textAuthor = paragraph.getTextContent();
                    break;
                case "title":
                    title = new Title(paragraph);
                    break;
                case "epigraph":
                    if (epigraphs == null) epigraphs = new ArrayList<>();
                    epigraphs.add(new Epigraph(paragraph));
                    break;
                case "date":
                    date = paragraph.getTextContent();
                    break;
                case "stanza":
                    stanza.add(new Stanza(paragraph));
                    break;
            }
        }
    }

    public Title getTitle() {
        return title;
    }

    public ArrayList<Epigraph> getEpigraphs() {
        return epigraphs;
    }

    public String getTextAuthor() {
        return textAuthor;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String getText() {
        ArrayList<Element> list = new ArrayList<>();
        if (title != null) list.addAll(title.getParagraphs());
        for (Stanza stanza1 : stanza) {
            if (stanza1.getTitle() != null) {
                for (Title title1 : stanza1.getTitle()) {
                    if (title1 != null) list.addAll(title1.getParagraphs());
                }
            }
            list.addAll(stanza1.getStanza());
        }
        return Element.getText(list, "\n");
    }
}
