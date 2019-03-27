package com.kursx.parser.fb2;

import com.kursx.parser.fb2.fonts.Emphasis;
import com.kursx.parser.fb2.fonts.StrikeThrough;
import com.kursx.parser.fb2.fonts.Strong;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class P extends Element {

    protected ArrayList<Image> images;
    protected ArrayList<Emphasis> emphasis;
    protected ArrayList<Strong> strong;
    protected ArrayList<StrikeThrough> strikeThrough;
//    TODO
//    Для нижних индексов <sub>, а для верхних индексов <sup>
//    Программный код - <code>
//    <subtitle>* * *</subtitle>

//  <cite>
//  <p>Время - деньги.<p>
//  <text-author>Бенджамин Франклин</text-author>
//  </cite>

//  <p>Об этом вы можете прочитать <a l:href="#n1">здесь</a>.</p>
//  <p>text<a l:href="#n_2" type="note">[2]</a>
    public P() {
        super();
    }

    public P(Image image) {
        super();
        if (images == null) images = new ArrayList<>();
        images.add(image);
    }

    public P(Node p) {
        super(p);
        NodeList nodeList = p.getChildNodes();
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            switch (nodeList.item(index).getNodeName()) {
                case "image":
                    if (images == null) images = new ArrayList<>();
                    images.add(new Image(node));
                    break;
                case "strikethrough":
                    if (strikeThrough == null) strikeThrough = new ArrayList<>();
                    strikeThrough.add(new StrikeThrough(node.getTextContent(), p.getTextContent()));
                    break;
                case "strong":
                    if (strong == null) strong = new ArrayList<>();
                    strong.add(new Strong(node.getTextContent(), p.getTextContent()));
                    break;
                case "emphasis":
                    if (emphasis == null) emphasis = new ArrayList<>();
                    emphasis.add(new Emphasis(node.getTextContent(), p.getTextContent()));
                    break;
                case "subtitle":
                    if (emphasis == null) emphasis = new ArrayList<>();
                    emphasis.add(new Emphasis(node.getTextContent(), p.getTextContent()));
                    break;
            }
        }
    }

    public P(String p) {
        super(p);
    }

    public ArrayList<Image> getImages() {
        return images;
    }
}
