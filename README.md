# Fb2 parser
## Simple parser of book format fb2 in java objects

Download [Fb2Parser.jar](https://github.com/KursX/fb2parser/raw/master/jar/fb2parser.jar)

Using: 

```java
try {
    FictionBook fb2 = new FictionBook(new File("book.fb2"));
} catch (ParserConfigurationException | IOException | SAXException e) {
	e.printStackTrace();
}
```

Android Gradle: 

```java
dependencies {
	compile files('src/main/libs/fb2parser.jar')
}
```