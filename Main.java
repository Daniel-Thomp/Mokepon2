import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    static team Player = new team();
    static team Opponent = new team();

    static textAnimator animator = new textAnimator();

    public static void main(String[] args) throws InterruptedException {
        Player.monsters[2].assign("Arboragon", 6);
        Player.monsters[1].assign("Aquabirb", 40);
        Player.monsters[1].xp = 9999;

        Player.monsters[1].Move1 = "Will-O-Wisp";
        // Player.monsters[1].specialAttack = 99999;
        // Player.monsters[1].speed = 50;

        Opponent.monsters[1].assign("Arboragon", 25);
        // Opponent.monsters[1].attack = 0;
        // Opponent.monsters[1].specialAttack = 0;
        // Opponent.monsters[1].speed = 75;
        Opponent.monsters[1].maxHP = 105;
        Opponent.monsters[1].currentHP = 105;

        Player.add2Bag("Mokeball", 1);
        Player.add2Bag("Potion", 1);

        level.topExit = "town1";
        new screen();
        // battle.startBattle();

    }

    public static double typeChart(int attack, int type1, int type2) {
        Scanner fileScanner = fileReader("files//typeChart.csv");
        for (int i = 0; i < attack; i++) {
            fileScanner.nextLine();
        }
        for (int i = 0; i < type1; i++) {
            fileScanner.next();
        }
        Double matchUp1 = fileScanner.nextDouble();
        fileScanner.close();
        if (type2 == -1) {
            return matchUp1;
        } else {
            Scanner fileScanner2 = fileReader("files//typeChart.csv");
            for (int i = 0; i < attack; i++) {
                fileScanner2.nextLine();
            }
            for (int i = 0; i < type2; i++) {
                fileScanner2.next();
            }
            Double matchUp2 = fileScanner2.nextDouble();
            fileScanner2.close();

            return matchUp1 * matchUp2;
        }
    }

    public static Scanner fileReader(String filename) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new BufferedReader(new FileReader(filename)));
        } catch (FileNotFoundException e) {
            System.out.println(filename + " not found");
        }

        fileScanner.useDelimiter("[\\r\\n,]+");

        return fileScanner;
    }

    public static void printMoves() {
        Scanner fileScanner = Main.fileReader("files//Stats.txt");
        String currentLine = "";
        String nextLine = "";
        String prevLine = "";
        int counter = 0;
        boolean duplicate = false;

        String[] moves = new String[999];
        while (fileScanner.hasNext()) {
            prevLine = currentLine;
            currentLine = nextLine;
            nextLine = fileScanner.nextLine();
            if (currentLine.contains(":") == false && (nextLine.contains("LVL") || prevLine.contains("LVL"))) {
                duplicate = false;
                int j = 0;
                while (moves[j] != null) {
                    if (moves[j].contains(currentLine)) {
                        duplicate = true;
                    }
                    j++;

                }
                if (duplicate == false) {
                    moves[counter] = currentLine;
                    counter++;
                }

            }

        }
        int n = 0;
        while (moves[n] != null) {
            n++;
        }
        duplicate = true;
        while (duplicate = true && n >= 0) {
            duplicate = false;
            int i = 0;
            while (moves[i + 1] != null) {
                if ((int) moves[i].charAt(0) > (int) moves[i + 1].charAt(0)) {
                    String temp = moves[i];
                    moves[i] = moves[i + 1];
                    moves[i + 1] = temp;
                    duplicate = true;
                }
                i++;
            }
            n--;
        }
        counter = 0;

        while (moves[counter] != null) {
            System.out.println(moves[counter]);
            counter++;
        }
        System.out.println("there are currently " + counter + " moves in the game");

    }

}
