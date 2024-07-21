import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class inputs implements KeyListener{

    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

        @Override
    public void keyPressed(KeyEvent e) {
        
        if (movement.running == false) {
            
            if (e.getKeyCode() == KeyEvent.VK_W) {
                
                screen.player.setIcon(images.player_back);
                
                if (screen.leveldata[movement.playerx][movement.playery-1] != 1 && screen.leveldata[movement.playerx][movement.playery-1] != -1) {
                    movement.running = true;
                    movement.playery--;
                    movement.walk("up");
                    
                }
                
                
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                
                screen.player.setIcon(images.player_front);
                if (screen.leveldata[movement.playerx][movement.playery+1] != 1) {                
                    movement.running = true;
                    movement.playery++;
                    movement.walk("down");
                }

            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                
                screen.player.setIcon(images.player_left);
                if (screen.leveldata[movement.playerx-1][movement.playery] != 1) {
                    movement.running = true;
                    movement.playerx--;
                    movement.walk("left");               
                }
                

            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                
                screen.player.setIcon(images.player_right);
                if (screen.leveldata[movement.playerx+1][movement.playery] != 1) {
                    movement.running = true;
                    movement.playerx++;
                    movement.walk("right");
                }
                
                
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
