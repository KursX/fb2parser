package com.kursx.parser.fb2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class TitleInfo {

    protected List<String> genre = new ArrayList<>();
//  TODO http://www.fictionbook.org/index.php/Жанры_FictionBook_2.1

    protected List<String> keywords = new ArrayList<>();
    protected String bookTitle;
    protected String date;
    protected String lang;
    protected String srcLang;
    protected List<Author> authors = new ArrayList<>();
    protected List<Author> translators = new ArrayList<>();
    protected Annotation annotation;
    protected List<Image> coverPage = new ArrayList<>();
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

    public List<String> getGenres() {
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

    public List<Image> getCoverPage() {
        return coverPage;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public List<String> getGenre() {
        return genre;
    }

    public List<String> getKeywords() {
        return keywords;
    }
}
