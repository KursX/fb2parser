package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Title {

    protected List<P> paragraphs = new ArrayList<>();

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
    public List<P> getParagraphs() {
        return paragraphs;
    }
}
