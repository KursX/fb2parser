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
    protected Title title;
    protected Image image;
    protected Annotation annotation;
    protected List<Epigraph> epigraphs = new ArrayList<>();
    protected List<Section> sections = new ArrayList<>();
    protected List<P> paragraphs = new ArrayList<>();

//    <section id="litres_trial_promo">

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
                case "annotation":
                    annotation = new Annotation(node);
                    break;
                case "image":
                    paragraphs.add(new P(new Image(node)));
                    break;
                case "epigraph":
                    epigraphs.add(new Epigraph(node));
                    break;
                case "title":
                    title = new Title(node);
                    break;
                case "section":
                    sections.add(new Section(node));
                    break;
                case "p":
                    paragraphs.add(new P(node));
                    break;
            }
        }
    }

    public @Nullable String getId() {
        return id;
    }

    public @Nullable Title getTitle() {
        return title;
    }

    public @NotNull List<Section> getSections() {
        return sections;
    }

    public @NotNull List<P> getParagraphs() {
        return paragraphs;
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
        if (paragraphs.size() == 0) return value;
        for (P p : paragraphs) {
            value += p.getP() + divider;
        }
        return value.substring(0, value.length() - divider.length());
    }
}
