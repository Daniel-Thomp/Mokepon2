import java.util.Scanner;
import java.io.*;



public class level {
    public static int[][] newLevel(int ID){        
            
            String fileName = null;
            Scanner fileScanner = null;
            switch (ID) {
                case 0:
                fileName = "files//town1.csv";
                    break;
                case 3:
                    screen.background.setIcon(images.town_1);
                    fileName = "files//town1.csv";
                    movement.backgroundy = (int)(movement.backgroundy+screen.tile*20.5);
                    screen.background.setLocation(movement.backgroundx, movement.backgroundy);
                    movement.playery = 1;
                    movement.playerx = 9;
                    break;
                case 4:
                    //player's house
                    break;
                case 5:
                    //rival's house
                    break;
                case 6:
                    //lab
                    break;
                case 7:
                    screen.background.setIcon(images.route_1);
                    fileName = "files//route1.csv";
                    movement.backgroundy = (int)(movement.backgroundy-screen.tile*20.5);
                    screen.background.setLocation(movement.backgroundx, movement.backgroundy);
                    movement.playery = 21;
                    movement.playerx = 4;
                    
                    break;
                case 8:
                    
                    break;
                case 9:
                    
                    break;
                case 10:
                    
                    break;
                case 11:
                    
                    break;
            }
            try {
                fileScanner = new Scanner (new BufferedReader (new FileReader(fileName)));
            } catch (FileNotFoundException e) {
                System.out.println("File missing");
            }
            
            fileScanner.useDelimiter("[\\r\\n,]+");
            
            int x = fileScanner.nextInt();
            int y = fileScanner.nextInt();
            
            int[][] leveldata = new int[x][y];

            for (int i = 0; i < y; i ++){
                for (int j = 0; j < x; j++) {
                    leveldata[j][i] = fileScanner.nextInt();
                }  
                          
            }
            fileScanner.close();
            // for (int i = 0; i < leveldata.length; i++) {
            //     for (int j = 0; j < leveldata.length; j++) {
            //         System.out.print(leveldata[i][j]);
            //     }
            //     System.out.println();
            // }
            
            // screen.background.setIcon(null);
            
            
            screen.frame.revalidate();
            screen.frame.repaint();
            return leveldata;
    }
    
}
