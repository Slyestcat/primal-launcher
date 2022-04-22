import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class updateGame {

    public int buildNumber;
    public String buildVersion;
    public String buttonHover;
    public String button;
    public String getPercentComplete;
    public String status;
    public static Download downloadPrimal;

    public static int getBuild () {
        int buildNumber = 0;

        return buildNumber;
    }

    public static String getVersion () {
        String buildVersion = "LIVE";

        return buildVersion;
    }

    public String getButtonHover () {
        String buttonHover = "/img/download-bar/Play-BTN-hover.png";

        return buttonHover;
    }

    public String getButton () {
        String button = "/img/download-bar/Play-BTN.png";

        return button;
    }

    public static Float getPercentComplete() {

        return null;
    }

    public static String getUpdateStatus () {
        String status = "Primal is up to date";

        return status;
    }

    public void updateJar() throws IOException {


    }
}
