import java.awt.*;
import java.net.URL;

public class openURL {
    public static void open(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
