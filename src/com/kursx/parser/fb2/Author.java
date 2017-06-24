package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Author {

    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected String nickname;
    protected String email;

    public Author() {
    }

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
                case "email":
                    email = author.getTextContent();
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

    public String getName() {
        return (firstName == null ? "" : firstName) + " "
                + (middleName == null ? "" : middleName) + " "
                + (lastName == null ? "" : lastName);
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }
}
