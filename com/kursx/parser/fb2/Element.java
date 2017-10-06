package com.kursx.parser.fb2;

import org.w3c.dom.Node;

import java.util.List;

public class Element {

    protected String text;

    public Element() {
        text = null;
    }

    public Element(Node p) {
        text = p.getTextContent();
    }

    public String getText() {
        return text;
    }

    public static String getText(List<Element> list, String divider) {
        String text = "";
        for (Element p : list) {
            text += p.getText() + divider;
        }
        return text.trim();
    }
}
