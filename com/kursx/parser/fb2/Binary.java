package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

//http://www.fictionbook.org/index.php/Элемент_binary
public class Binary extends IdElement {

    protected String contentType;
    protected String binary;

    public Binary() {
    }

    Binary(Node node) {
        super(node);
        binary = node.getTextContent();
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            switch (attr.getNodeName()) {
                case "content-type":
                    contentType = attr.getNodeValue();
                    break;
            }
        }
    }

    public String getContentType() {
        return contentType;
    }

    @NotNull
    public String getBinary() {
        return binary;
    }

    @NotNull
    @Override
    public String getId() {
        return super.getId();
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }
}
