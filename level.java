import java.util.Arrays;
import java.util.Scanner;

import javax.swing.ImageIcon;

public class level {
    static String topExit;
    static String bottomExit;
    static String leftExit;
    static String rightExit;
    static String[][] buildings;
    static int[] exitCoords = new int[2];

    public static int[][] newLevel(String exit) {

        Scanner fileScanner = null;
        String location = null;
        String exitSide = "";
        String entryType = "";

        if (exit == "start") {
            exit = "town1";
        } else if (exit == topExit) {
            movement.backgroundy = (int) (movement.backgroundy - screen.tile * 20.5);
            screen.background.setLocation(movement.backgroundx, movement.backgroundy);
            location = "bottom";
            if (screen.leveldata[movement.playerx - 1][movement.playery] == -10) {
                exitSide = "right";
            } else {
                exitSide = "left";
            }
        } else if (exit == bottomExit) {
            movement.backgroundy = (int) (movement.backgroundy + screen.tile * 20.5);
            screen.background.setLocation(movement.backgroundx, movement.backgroundy);
            location = "top";
            if (screen.leveldata[movement.playerx - 1][movement.playery] == -10) {
                exitSide = "right";
            } else {
                exitSide = "left";
            }
        } else if (exit == leftExit) {
            movement.backgroundx = (int) (movement.backgroundx - screen.tile * 20.5);
            screen.background.setLocation(movement.backgroundx, movement.backgroundy);
            location = "right";
            if (screen.leveldata[movement.playerx][movement.playery - 1] == -10) {
                exitSide = "top";
            } else {
                exitSide = "bottom";
            }
        } else if (exit == rightExit) {
            movement.backgroundx = (int) (movement.backgroundx + screen.tile * 20.5);
            screen.background.setLocation(movement.backgroundx, movement.backgroundy);
            location = "left";
            if (screen.leveldata[movement.playerx][movement.playery - 1] == -10) {
                exitSide = "top";
            } else {
                exitSide = "bottom";
            }
        } else {
            exitCoords[0] = movement.playerx;
            exitCoords[1] = movement.playery + 1;
            entryType = "building entry";
            System.out.println("BUILDING");
        }
        String fileName = "files//" + exit + ".csv";
        screen.background.setIcon(new ImageIcon("Overworld Images//" + exit + ".png"));

        fileScanner = Main.fileReader(fileName);

        int x = fileScanner.nextInt();
        int y = fileScanner.nextInt();

        int[][] leveldata = new int[x][y];
        buildings = new String[x][y];
        topExit = null;
        bottomExit = null;
        leftExit = null;
        rightExit = null;
        if (entryType == "building entry") {
            movement.playerx = fileScanner.nextInt();
            movement.playery = fileScanner.nextInt();
        }

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (!fileScanner.hasNext()) {
                    break;
                }
                String value = fileScanner.next();
                try {
                    leveldata[j][i] = Integer.parseInt(value);
                } catch (Exception e) {
                    System.out.println(e);
                    leveldata[j][i] = -10;
                    if (i == y - 1) {
                        bottomExit = value;
                    } else if (i == 0) {
                        topExit = value;
                    } else if (j == x - 1) {
                        rightExit = value;
                    } else if (j == 0) {
                        leftExit = value;
                    } else {
                        leveldata[j][i] = -20;
                        buildings[j][i] = value;
                    }
                }

            }

        }
        fileScanner.close();
        if (location == "top") {
            movement.playery = 1;
            for (int i = 0; i < x; i++) {
                if (leveldata[i][0] == -10) {
                    if (exitSide == "left") {
                        movement.playerx = i - 1;
                    } else {
                        movement.playerx = i;
                    }
                }
            }
        } else if (location == "bottom") {
            movement.playery = y - 2;
            for (int i = 0; i < x; i++) {
                if (leveldata[i][y - 1] == -10) {
                    if (exitSide == "left") {
                        System.out.println(i);
                        movement.playerx = i - 1;
                    } else {
                        movement.playerx = i;
                    }
                }
            }
        } else if (location == "left") {
            movement.playerx = 1;
            for (int i = 0; i < x; i++) {
                if (leveldata[0][i] == -10) {
                    if (exitSide == "left") {
                        movement.playery = i - 1;
                    } else {
                        movement.playery = i;
                    }
                }
            }
        } else if (location == "right") {
            movement.playerx = x - 2;
            for (int i = 0; i < x; i++) {
                if (leveldata[x - 1][i] == -10) {
                    if (exitSide == "left") {
                        movement.playery = i - 1;
                    } else {
                        movement.playery = i;
                    }
                }
            }
        }
        int[][] rotatedArray = rotate90DegreesClockwise(leveldata);
        String formattedArray = Arrays.deepToString(rotatedArray)
                .replace("], [", "\n ")// Replace ", [" with line breaks
                .replace("[[", "") // Remove the outer brackets
                .replace("]]", "")
                .replace(" ", "");

        System.out.println(formattedArray);
        // for (int i = 0; i < leveldata.length - 1; i++) {
        // for (int j = 0; j < leveldata[i].length - 1; j++) {
        // System.out.print(leveldata[j][i] + ",");
        // }
        // System.out.println();
        // }

        return leveldata;
    }

    public static int[][] rotate90DegreesClockwise(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        // Create a new array with reversed dimensions
        int[][] rotated = new int[cols][rows];

        // Perform the rotation
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotated[col][rows - 1 - row] = array[row][col];
            }
        }

        return rotated;
    }
}
