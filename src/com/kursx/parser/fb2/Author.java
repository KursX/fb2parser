package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Author {

    private String firstName;
    private String middleName;
    private String lastName;
    private String nickname;

    Author(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node author = nodeList.item(i);
            switch (author.getNodeName()) {
                case "first-name":
                    firstName = author.getTextContent();
                    break;
                case "middle-name":
                    middleName = author.getTextContent();
                    break;
                case "last-name":
                    lastName = author.getTextContent();
                    break;
                case "nickname":
                    nickname = author.getTextContent();
                    break;
            }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }
}
