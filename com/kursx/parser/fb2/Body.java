package com.kursx.parser.fb2;

import com.sun.istack.internal.Nullable;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Body {

    protected String name;
    protected Title title;
    protected Image image;
    protected List<Section> sections = new ArrayList<>();
    protected List<Epigraph> epigraphs = new ArrayList<>();

    public Body() {
    }

    Body(Node body) {
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
                    epigraphs.add(new Epigraph(node));
                    break;
            }
        }
    }

    public List<Section> getSections() {
        return sections;
    }

    public
    @Nullable
    Title getTitle() {
        return title;
    }

    public List<Epigraph> getEpigraphs() {
        return epigraphs;
    }

    public
    @Nullable
    Image getImage() {
        return image;
    }

    public
    @Nullable
    String getName() {
        return name;
    }
}