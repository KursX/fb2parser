package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Body {

    protected String name;
    protected Title title;
    protected Image image;
    protected List<Section> sections = new ArrayList<>();
    protected List<Epigraph> epigraphs;

    public Body() {
    }

    Body(Node body) {
        NamedNodeMap attrs = body.getAttributes();
        for (int index = 0; index < attrs.getLength(); index++) {
            Node attr = attrs.item(index);
            if (attr.getNodeName().equals("name")) {
                name = attr.getNodeValue();
            }
        }
        NodeList map = body.getChildNodes();
        for (int index = 0; index < map.getLength(); index++) {
            Node node = map.item(index);
            switch (node.getNodeName()) {
                case "section":
                    sections.add(new Section(node));
                    break;
                case "title":
                    title = new Title(node);
                    break;
                case "name":
                    name = node.getTextContent();
                    break;
                case "image":
                    image = new Image(node);
                    break;
                case "epigraph":
                    if (epigraphs == null) epigraphs = new ArrayList<>();
                    epigraphs.add(new Epigraph(node));
                    break;
            }
        }
    }

    @NotNull
    public List<Section> getSections() {
        return sections;
    }

    @Nullable
    public Title getTitle() {
        return title;
    }

    @Nullable
    public List<Epigraph> getEpigraphs() {
        return epigraphs;
    }

    @Nullable
    public Image getImage() {
        return image;
    }

    @Nullable
    public String getName() {
        return name;
    }
}