package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Section {

    protected String id;
    protected Image image;
    protected Annotation annotation;
    protected List<Epigraph> epigraphs;
    protected List<Section> sections;
    protected List<Element> elements = new ArrayList<>();
    protected List<Title> title;

    public Section() {
    }

    Section(Node root) {
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
                case "title":
                    if (title == null) title = new ArrayList<>();
                    title.add(new Title(node));
                    break;
                case "annotation":
                    annotation = new Annotation(node);
                    break;
                case "image":
                    if (elements.isEmpty()) {
                        image = new Image(node);
                    } else {
                        elements.add(new P(new Image(node)));
                    }
                    break;
                case "epigraph":
                    if (epigraphs == null) epigraphs = new ArrayList<>();
                    epigraphs.add(new Epigraph(node));
                    break;
                case "section":
                    if (sections == null) sections = new ArrayList<>();
                    sections.add(new Section(node));
                    break;
                case "poem":
                    elements.add(new Poem(node));
                    break;
                case "subtitle":
                    elements.add(new Subtitle(node));
                    break;
                case "p":
                    elements.add(new P(node));
                    break;
                case "empty-line":
                    elements.add(new EmptyLine());
                    break;
                case "cite":
                    elements.add(new Cite(node));
                    break;
            }
        }
    }

    public @Nullable String getId() {
        return id;
    }

    public @Nullable List<Title> getTitle() {
        return title;
    }

    public @NotNull List<Section> getSections() {
        return sections;
    }

    public @NotNull List<Element> getElements() {
        return elements;
    }

    public @Nullable Image getImage() {
        return image;
    }

    public @NotNull List<Epigraph> getEpigraph() {
        return epigraphs;
    }

    public @Nullable Annotation getAnnotation() {
        return annotation;
    }

    public @NotNull List<Epigraph> getEpigraphs() {
        return epigraphs;
    }

    public String getTitleString(String divider) {
        String value = "";
        List<Element> list = new ArrayList<>();
        for (Title title1 : title) {
            list.addAll(title1.getParagraphs());
        }
        return Element.getText(list, divider);
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        String data = getTitleString(". ");
        if (elements.size() > 0) {
            data += " p: " + elements.size();
        }
        if (sections.size() > 0) {
            data += " section: " + sections.size();
        }
        return data.trim();
    }
}
