package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class Title {

    protected ArrayList<P> paragraphs = new ArrayList<>();

    public Title() {
    }

    Title(Node root) {
        NodeList body = root.getChildNodes();
        for (int item = 0; item < body.getLength(); item++) {
            Node node = body.item(item);
            switch (node.getNodeName()) {
                case "p":
                    paragraphs.add(new P(node));
                    break;
            }
        }
    }

    @NotNull
    public ArrayList<P> getParagraphs() {
        return paragraphs;
    }
}
