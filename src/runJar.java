import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class runJar {
    public static void run(String filePath) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("java", "-classpath", filePath, "net.runelite.client.RuneLite");
        Process p = pb.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String s = "";
        while((s = in.readLine()) != null){
            System.out.println(s);
        }
        int status = p.waitFor();
        System.out.println("Exited with status: " + status);
    }
}
