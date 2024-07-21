import java.awt.*;


public class battle {
    
    public static void encounter(){
        screen.background.setIcon(null);
        
        screen.frame.remove(screen.player);
        
        screen.frame.getContentPane().setBackground(Color.WHITE);
        
        screen.frame.add(screen.opponent);
        screen.opponent.setBounds(360, 0, 256, 256);
        screen.frame.add(screen.textBox);
        screen.textBox.setBounds(11,410,616,156);
        screen.frame.add(screen.infoBox);
        screen.frame.add(screen.infoBox2);
        screen.infoBox.setBounds(0, 0, 312, 92);
        screen.infoBox2.setBounds(328,300,312,92);

        
        
    }
    
}
