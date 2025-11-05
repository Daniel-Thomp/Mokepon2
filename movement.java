import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class movement {
    static int backgroundx = -640;
    static int backgroundy = -640;

    static int speed = 2;
    static int stepCount;
    static boolean moveable = true;

    static int playerx = 4;
    static int playery = 5;

    public static void walk(String direction) {

        Timer timer = new Timer(1, event -> {
            if (direction == "up") {

                backgroundy = backgroundy + speed;

                if (stepCount == 0 || stepCount == 32 || stepCount == 64) {

                    screen.player.setIcon(images.player_back);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_back_running);

                } else if (stepCount == 48) {

                    screen.player.setIcon(images.player_back_running2);

                }

            } else if (direction == "down") {

                backgroundy = backgroundy - speed;

                if (stepCount == 0 || stepCount == 32 || stepCount == 64) {

                    screen.player.setIcon(images.player_front);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_front_running);

                } else if (stepCount == 48) {

                    screen.player.setIcon(images.player_front_running2);

                }

            } else if (direction == "left") {

                backgroundx = backgroundx + speed;

                if (stepCount == 0 || stepCount == 48) {

                    screen.player.setIcon(images.player_left);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_left_running);

                }

            } else if (direction == "right") {

                backgroundx = backgroundx - speed;
                if (stepCount == 0 || stepCount == 48) {

                    screen.player.setIcon(images.player_right);

                } else if (stepCount == 16) {

                    screen.player.setIcon(images.player_right_running);

                }

            }

            screen.background.setLocation(backgroundx, backgroundy);
            // screen.frame.revalidate();
            // screen.frame.repaint();

            if (stepCount < screen.tile) {
                walk(direction);
            } else {
                moveable = true;
                stepCount = 0;
                if (screen.leveldata[playerx][playery] == -10) {
                    System.out.println(direction);
                    // screen.leveldata = level.newLevel(screen.leveldata[playerx][playery]);
                    switch (direction) {
                        case "up":
                            screen.leveldata = level.newLevel(level.topExit);
                            break;
                        case "down":
                            screen.leveldata = level.newLevel(level.bottomExit);
                            break;
                        case "left":
                            screen.leveldata = level.newLevel(level.leftExit);
                            break;
                        case "right":
                            screen.leveldata = level.newLevel(level.rightExit);
                            break;

                    }
                } else if (screen.leveldata[playerx][playery] == -20){
                    level.newLevel(level.buildings[playerx][playery]);
                } else if (screen.leveldata[playerx][playery] == 2 && ThreadLocalRandom.current().nextInt(0, 12) == 1) {
                    Main.Opponent.monsters[1].assign("Glacierroar", 5);
                    moveable = false;
                    battle.startBattle();
                } else if (inputs.held) {
                    switch (direction) {
                        case "up":
                            if (screen.leveldata[movement.playerx][movement.playery - 1] != 1
                                    && screen.leveldata[movement.playerx][movement.playery - 1] != -1) {
                                movement.moveable = false;
                                movement.playery--;
                                movement.walk("up");

                            }
                            break;
                        case "down":
                            if (screen.leveldata[movement.playerx][movement.playery + 1] != 1) {
                                movement.moveable = false;
                                movement.playery++;
                                movement.walk("down");
                            }
                            break;
                        case "left":
                            if (screen.leveldata[movement.playerx - 1][movement.playery] != 1) {
                                movement.moveable = false;
                                movement.playerx--;
                                movement.walk("left");
                            }
                            break;
                        case "right":
                            if (screen.leveldata[movement.playerx + 1][movement.playery] != 1) {
                                movement.moveable = false;
                                movement.playerx++;
                                movement.walk("right");
                            }
                            break;

                    }

                }
            }
        });
        timer.setRepeats(false);
        timer.start();
        stepCount = stepCount + speed;

    }
}
