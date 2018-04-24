package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

//http://www.fictionbook.org/index.php/Элемент_annotation
public class Annotation extends IdElement {

    protected String lang;
    protected ArrayList<Element> elements;

    public Annotation() {
    }

    Annotation(Node node) {
        super(node);
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("xml:lang")) {
                lang = attr.getNodeValue();
            }
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            if (elements == null) elements = new ArrayList<>();
            switch (paragraph.getNodeName()) {
                case "p":
                    elements.add(new P(paragraph));
                    break;
                case "poem":
                    elements.add(new Poem(paragraph));
                    break;
                case "cite":
                    elements.add(new Cite(paragraph));
                    break;
                case "subtitle":
                    elements.add(new Subtitle(paragraph));
                    break;
                case "empty-line":
                    elements.add(new EmptyLine());
                    break;
                case "table":
                    elements.add(new Table());
                    break;
            }
        }
    }

    @NotNull
    public ArrayList<Element> getAnnotations() {
        return elements == null ? new ArrayList<Element>() : elements;
    }

    @Nullable
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }
}
