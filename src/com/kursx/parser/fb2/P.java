package com.kursx.parser.fb2;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class P {

    protected String p;
    protected List<Image> images = new ArrayList<>();
//    TODO <empty-line/>
//    Жирный - <strong>, а курсивный - <emphasis>
//    Для нижних индексов <sub>, а для верхних индексов <sup>
//    Перечеркнутый - <strikethrough>
//    Программный код - <code>
//    <subtitle>* * *</subtitle>

//  <cite>
//  <p>Время - деньги.<p>
//  <text-author>Бенджамин Франклин</text-author>
//  </cite>

//  <p>Об этом вы можете прочитать <a l:href="#n1">здесь</a>.</p>
//  <p>text<a l:href="#n_2" type="note">[2]</a>
    public P() {
    }

    public P(Image image) {
        images.add(image);
    }

    public P(Node p) {
        NodeList nodeList = p.getChildNodes();
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            switch (nodeList.item(index).getNodeName()) {
                case "image":
                    images.add(new Image(node));
                    break;
                case "#text":
                    this.p = p.getTextContent();
                    break;
            }
        }
    }

    public String getP() {
        return p;
    }

    public List<Image> getImages() {
        return images;
    }
}
