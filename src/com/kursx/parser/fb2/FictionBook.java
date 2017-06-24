package com.kursx.parser.fb2;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FictionBook {

    protected Xmlns[] xmlns;
    protected Description description;
    protected List<Body> bodies = new ArrayList<>();
    protected Map<String, Binary> binaries = new HashMap<>();

    public FictionBook() {
    }

    public FictionBook(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputStream inputStream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        String encoding = line.substring(line.indexOf("encoding=\"") + 10, line.length() - 3);
        Document doc = db.parse(new InputSource(new InputStreamReader(inputStream, encoding)));
        initXmlns(doc);
        description = new Description(doc);
        NodeList bodyNodes = doc.getElementsByTagName("body");
        for (int item = 0; item < bodyNodes.getLength(); item++) {
            bodies.add(new Body(bodyNodes.item(item)));
        }
        NodeList binary = doc.getElementsByTagName("binary");
        for (int item = 0; item < binary.getLength(); item++) {
            Binary binary1 = new Binary(binary.item(item));
            binaries.put(binary1.getId().replace("#", ""), binary1);
        }
    }

    protected void setXmlns(List<Node> nodeList) {
        xmlns = new Xmlns[nodeList.size()];
        for (int index = 0; index < nodeList.size(); index++) {
            Node node = nodeList.get(index);
            xmlns[index] = new Xmlns(node);
        }
    }

    protected void initXmlns(Document doc) {
        NodeList fictionBook = doc.getElementsByTagName("FictionBook");
        List<Node> xmlns = new ArrayList<>();
        for (int item = 0; item < fictionBook.getLength(); item++) {
            NamedNodeMap map = fictionBook.item(item).getAttributes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                xmlns.add(node);
            }
        }
        setXmlns(xmlns);
    }

    public List<Author> getAuthors() {
        return description.getDocumentInfo().getAuthors();
    }

    public Xmlns[] getXmlns() {
        return xmlns;
    }

    public Description getDescription() {
        return description;
    }

    public @Nullable Body getBody() {
        return getBody(null);
    }

    public @Nullable Body getNotes() {
        return getBody("notes");
    }

    public @Nullable Body getComments() {
        return getBody("comments");
    }

    private @Nullable Body getBody(String name) {
        for (Body body : bodies) {
            if ((name + "").equals(body.getName() + "")) {
                return body;
            }
        }
        return null;
    }

    public @NotNull Map<String, Binary> getBinaries() {
        return binaries;
    }

    public String getTitle() {
        return description.getTitleInfo().getBookTitle();
    }

    public String getLang() {
        return description.getTitleInfo().getLang();
    }

    public @Nullable Annotation getAnnotation() {
        return description.getTitleInfo().getAnnotation();
    }
}

