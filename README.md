# fb2parser
Easy parser fb2 files to java objects

download jar/fb2parser.jar

Using:

try {
    FB2 fb2 = new FB2(new File("book.fb2"));
} catch (ParserConfigurationException | IOException | SAXException e) {
    e.printStackTrace();
}
