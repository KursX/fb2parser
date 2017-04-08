package com.kursx.parser.fb2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class TitleInfo {

    private String genre;
    private String bookTitle;
    private String date;
    private String lang;
    private String srcLang;
    private List<Author> authors = new ArrayList<>();
    private List<Author> translators = new ArrayList<>();
    private Annotation annotation;
    private CoverPage coverPage;
    private Sequence sequence;

    TitleInfo(Document document) {
        NodeList description = document.getElementsByTagName("title-info");
        for (int item = 0; item < description.getLength(); item++) {
            NodeList map = description.item(item).getChildNodes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                switch (node.getNodeName()) {
                    case "sequence":
                        sequence = new Sequence(node);
                        break;
                    case "coverpage":
                        coverPage = new CoverPage(node);
                        break;
                    case "annotation":
                        this.annotation = new Annotation(node);
                        break;
                    case "date":
                        date = node.getTextContent();
                        break;
                    case "author":
                        NodeList authorsList = node.getChildNodes();
                        authors.add(new Author(authorsList));
                        break;
                    case "translator":
                        NodeList translatorsList = node.getChildNodes();
                        translators.add(new Author(translatorsList));
                        break;
                    case "genre":
                        genre = node.getTextContent();
                        break;
                    case "book-title":
                        bookTitle = node.getTextContent();
                        break;
                    case "lang":
                        lang = node.getTextContent();
                        break;
                    case "src-lang":
                        srcLang = node.getTextContent();
                        break;
                }
            }
        }
    }

    public String getGenre() {
        return genre;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getDate() {
        return date;
    }

    public String getLang() {
        return lang;
    }

    public String getSrcLang() {
        return srcLang;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Author> getTranslators() {
        return translators;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public CoverPage getCoverPage() {
        return coverPage;
    }

    public Sequence getSequence() {
        return sequence;
    }
}
