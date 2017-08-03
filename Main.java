import com.kursx.parser.fb2.FictionBook;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            FictionBook fictionBook = new FictionBook(new File("file.fb2"));
            System.out.println("Completed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
