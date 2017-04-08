import com.kursx.parser.fb2.FB2;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by User on 07.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        try {
            FB2 fb2 = new FB2(new File("src/file.fb2"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
