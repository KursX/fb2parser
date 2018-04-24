package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;

import com.sun.istack.internal.Nullable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

//http://www.fictionbook.org/index.php/Элемент_cite
public class Cite extends Element {

    protected String id;
    protected String lang;
    protected ArrayList<Element> elements;
    protected ArrayList<TextAuthor> textAuthor;

    public Cite() {
    }

    Cite(Node node) {
        NamedNodeMap attrs = node.getAttributes();
        for (int index = 0; index < attrs.getLength(); index++) {
            Node attr = attrs.item(index);
            if (attr.getNodeName().equals("id")) {
                id = attr.getNodeValue();
            }
            if (attr.getNodeName().equals("xml:lang")) {
                lang = attr.getNodeValue();
            }
        }

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "text-author":
                    if (textAuthor == null) textAuthor = new ArrayList<>();
                    textAuthor.add(new TextAuthor(paragraph));
                    break;
                case "poem":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new Poem(paragraph));
                    break;
                case "subtitle":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new Subtitle(paragraph));
                    break;
                case "p":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new P(paragraph));
                    break;
                case "empty-line":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new EmptyLine());
                    break;
            }
        }
    }

    @NotNull
    public ArrayList<TextAuthor> getTextAuthor() {
        return textAuthor == null ? new ArrayList<TextAuthor>() : textAuthor;
    }

    public void setTextAuthor(ArrayList<TextAuthor> textAuthor) {
        this.textAuthor = textAuthor;
    }


    @NotNull
    public ArrayList<Element> getElements() {
        return elements == null ? new ArrayList<Element>() : elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String getText() {
        ArrayList<Element> list = new ArrayList<>(getElements());
        if (textAuthor != null) list.addAll(textAuthor);
        return Element.getText(list, "\n");
    }

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getLang() {
        return lang;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
