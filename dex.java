import java.util.HashMap;
import java.util.Scanner;

public class dex {
    static HashMap<String, Integer> dex = new HashMap<>();

    public static void register(String Mon) {
        int dexNum = 1;
        Scanner fileScanner = Main.fileReader("files//Stats.txt");
        String line = fileScanner.nextLine();
        while (!line.contains(Mon)) {
            if (line.contains("Type:")) {
                dexNum++;
            }
            line = fileScanner.nextLine();
        }
        dex.put(Mon, dexNum);
    }
}
