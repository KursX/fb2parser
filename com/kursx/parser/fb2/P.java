package com.kursx.parser.fb2;

import com.kursx.parser.fb2.fonts.Emphasis;
import com.kursx.parser.fb2.fonts.StrikeThrough;
import com.kursx.parser.fb2.fonts.Strong;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class P {

    protected String p;

    protected List<Image> images;
    protected List<Emphasis> emphasis;
    protected List<Strong> strong;
    protected List<StrikeThrough> strikeThrough;
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
    }

    public P(Image image) {
        if (images == null) images = new ArrayList<>();
        images.add(image);
    }

    public P(Node p) {
        this.p = p.getTextContent();
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
