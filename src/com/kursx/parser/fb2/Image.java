package com.kursx.parser.fb2;

import org.w3c.dom.Node;

public class Image extends Xmlns {

    public Image() {
        super();
    }

    Image(Node node) {
        super(node.getAttributes().item(0));
    }
}
