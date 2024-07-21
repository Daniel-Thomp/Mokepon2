import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;


public class movement {
    static int backgroundx = -640;
    static int backgroundy = -640;

    static int stepCount;
    static boolean running = false;

    static int playerx = 4;
    static int playery = 5;

    public static void walk(String direction){
        
        Timer timer = new Timer(5, event -> {
            if (direction == "up") {
                

                backgroundy = backgroundy + 2;

                if (stepCount == 0 || stepCount == 32 || stepCount == 64) {      

                    screen.player.setIcon(images.player_back);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_back_running);
                    
                } else if (stepCount == 48) {
                    
                    screen.player.setIcon(images.player_back_running2);

                }

            } else if (direction == "down") {
                
                backgroundy = backgroundy - 2;

                if (stepCount == 0 || stepCount == 32 || stepCount == 64) {      

                    screen.player.setIcon(images.player_front);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_front_running);
                    
                } else if (stepCount == 48) {
                    
                    screen.player.setIcon(images.player_front_running2);

                }

            } else if (direction == "left") {
                
                backgroundx = backgroundx + 2;

                if (stepCount == 0 || stepCount == 48) {      

                    screen.player.setIcon(images.player_left);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_left_running);
                    
                } 

            } else if (direction == "right") {
                
                backgroundx = backgroundx -2;
                if (stepCount == 0 || stepCount == 48) {      

                    screen.player.setIcon(images.player_right);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_right_running);
                    
                } 

            }
            
            screen.background.setLocation(backgroundx, backgroundy);
            screen.frame.revalidate();
            screen.frame.repaint();

            if (stepCount < screen.tile) {
                walk(direction);
            } else{
                running = false;
                stepCount = 0;
                if (screen.leveldata[playerx][playery] > 2) {
                    screen.leveldata = level.newLevel(screen.leveldata[playerx][playery]);
                } else if (screen.leveldata[playerx][playery] == 2 && ThreadLocalRandom.current().nextInt(1, 4) == 3) {
                    battle.encounter();
                }
            }
        });
        timer.setRepeats(false);
        timer.start();
        stepCount = stepCount + 2;
    }
}
