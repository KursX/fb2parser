package com.kursx.parser.fb2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class TitleInfo {

    protected ArrayList<String> genre = new ArrayList<>();
//  TODO http://www.fictionbook.org/index.php/Жанры_FictionBook_2.1

    protected ArrayList<String> keywords = new ArrayList<>();
    protected String bookTitle;
    protected String date;
    protected String lang;
    protected String srcLang;
    protected ArrayList<Person> authors = new ArrayList<>();
    protected ArrayList<Person> translators = new ArrayList<>();
    protected Annotation annotation;
    protected ArrayList<Image> coverPage = new ArrayList<>();
    protected Sequence sequence;

    public TitleInfo() {
    }

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
                        NodeList images = node.getChildNodes();
                        for (int image = 0; image < images.getLength(); image++) {
                            if (images.item(image).getNodeName().equals("image")) {
                                coverPage.add(new Image(images.item(image)));
                            }
                        }
                        break;
                    case "elements":
                        this.annotation = new Annotation(node);
                        break;
                    case "date":
                        date = node.getTextContent();
                        break;
                    case "author":
                        authors.add(new Person(node));
                        break;
                    case "translator":
                        translators.add(new Person(node));
                        break;
                    case "keywords":
                        keywords.add(node.getTextContent());
                        break;
                    case "genre":
                        genre.add(node.getTextContent());
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

    public ArrayList<String> getGenres() {
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

    public ArrayList<Person> getAuthors() {
        return authors;
    }

    public ArrayList<Person> getTranslators() {
        return translators;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public ArrayList<Image> getCoverPage() {
        return coverPage;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
