package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Stanza {

    private List<Title> title;
    private List<Element> stanza;

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

    public List<Title> getTitle() {
        return title;
    }

    public void setTitle(List<Title> title) {
        this.title = title;
    }

    public List<Element> getStanza() {
        return stanza;
    }

    public void setStanza(List<Element> stanza) {
        this.stanza = stanza;
    }
}
