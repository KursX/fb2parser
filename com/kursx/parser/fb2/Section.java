package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Section extends IdElement {

    protected Image image;
    protected Annotation annotation;
    protected ArrayList<Epigraph> epigraphs;
    protected ArrayList<Section> sections;
    protected ArrayList<Element> elements;
    protected Title title;

    public Section() {
    }

    Section(Node root) {
        super(root);
        NodeList body = root.getChildNodes();
        for (int item = 0; item < body.getLength(); item++) {
            Node node = body.item(item);
            switch (node.getNodeName()) {
                case "title":
                    title = new Title(node);
                    break;
                case "elements":
                    annotation = new Annotation(node);
                    break;
                case "image":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new P(new Image(node)));
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
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new Poem(node));
                    break;
                case "subtitle":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new Subtitle(node));
                    break;
                case "p":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new P(node));
                    break;
                case "empty-line":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new EmptyLine());
                    break;
                case "cite":
                    if (elements == null) elements = new ArrayList<>();
                    elements.add(new Cite(node));
                    break;
            }
        }
    }

    @NotNull
    public Title getTitle() {
        return title;
    }

    @NotNull
    public ArrayList<Section> getSections() {
        return sections == null ? new ArrayList<Section>() : sections;
    }

    @NotNull
    public ArrayList<Element> getElements() {
        return elements == null ? new ArrayList<Element>() : elements;
    }

    @Nullable
    public Image getImage() {
        return image;
    }

    @Nullable
    public Annotation getAnnotation() {
        return annotation;
    }

    @NotNull
    public ArrayList<Epigraph> getEpigraphs() {
        return epigraphs == null ? new ArrayList<Epigraph>() : epigraphs;
    }

    public String getTitleString(String innerDivider, String outerDivider) {
        if (title == null) return "";
        StringBuilder builder = new StringBuilder();
        ArrayList<Element> list = new ArrayList<>(title.getParagraphs());
        builder.append(Element.getText(list, innerDivider)).append(outerDivider);
        return builder.substring(0, builder.length() - outerDivider.length());
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public void setEpigraphs(ArrayList<Epigraph> epigraphs) {
        this.epigraphs = epigraphs;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String toString() {
        String data = getTitleString(". ", "\n");
        if (!getElements().isEmpty()) {
            data += " p: " + elements.size();
        }
        if (!getSections().isEmpty()) {
            data += " section: " + sections.size();
        }
        return data.trim();
    }
}
