package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class Poem {

    protected Title title;
    protected List<Epigraph> epigraphs = new ArrayList<>();
    protected String textAuthor;
    protected String date;

//    TODO http://www.fictionbook.org/index.php/Элемент_poem

    public Poem() {
    }

    Poem(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "text-author":
                    textAuthor = paragraph.getTextContent();
                    break;
                case "title":
                    title = new Title(paragraph);
                    break;
                case "epigraph":
                    epigraphs.add(new Epigraph(paragraph));
                    break;
                case "date":
                    date = paragraph.getTextContent();
                    break;
            }
        }
    }

    public Title getTitle() {
        return title;
    }

    public List<Epigraph> getEpigraphs() {
        return epigraphs;
    }

    public String getTextAuthor() {
        return textAuthor;
    }

    public String getDate() {
        return date;
    }

    //    Строфы <stanza>. Одно или более вхождений. Строфа <stanza> - это группа строк стихотворения, отделенная пустым промежутком от остальных строк. Отдельная строка стихотворения помечается тэгом <v>, который должен быть вложен в <stanza>. Кроме того в состав <stanza> могут входить тэги <title> (заголовок) и <subtitle> (подзаголовок).
}
