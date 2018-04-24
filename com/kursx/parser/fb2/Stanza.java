package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Stanza {

    private ArrayList<Title> title;
    private ArrayList<Element> stanza;

    Stanza(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "title":
                    if (title == null) title = new ArrayList<>();
                    title.add(new Title(paragraph));
                    break;
                case "subtitle":
                    if (stanza == null) stanza = new ArrayList<>();
                    stanza.add(new Subtitle(paragraph));
                    break;
                case "v":
                    if (stanza == null) stanza = new ArrayList<>();
                    stanza.add(new V(paragraph));
                    break;
            }
        }
    }

    public ArrayList<Title> getTitle() {
        return title == null ? new ArrayList<Title>() : title;
    }

    public void setTitle(ArrayList<Title> title) {
        this.title = title;
    }

    public ArrayList<Element> getStanza() {
        return stanza == null ? new ArrayList<Element>() : stanza;
    }

    public void setStanza(ArrayList<Element> stanza) {
        this.stanza = stanza;
    }
}
