# fb2parser 
## Easy parser fb2 files into java objects 

download jar/fb2parser.jar 

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