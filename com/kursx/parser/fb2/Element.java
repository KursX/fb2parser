package com.kursx.parser.fb2;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class Element {

    protected String text;

    public Element() {
        text = null;
    }

    public Element(Node p) {
        text = p.getTextContent();
    }

    public Element(String p) {
        text = p;
    }

    public String getText() {
        return text;
    }

    public static String getText(ArrayList<Element> list, String divider) {
        StringBuilder text = new StringBuilder();
        for (Element p : list) {
            text.append(p.getText()).append(divider);
        }
        if (text.length() <= divider.length()) return "";
        return text.substring(0, text.length() - divider.length()).trim();
    }
}
