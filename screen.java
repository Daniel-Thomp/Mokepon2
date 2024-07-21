import javax.swing.*;
import java.awt.*;

public class screen extends JFrame {

    public static JFrame frame = new JFrame("Mokepon 2");

    static JLabel player = new JLabel(images.player_front);
    static JLabel background = new JLabel(images.town_1);
    static JLabel opponent = new JLabel(images.opponent);
    static JLabel textBox = new JLabel(images.textBox);
    static JLabel infoBox = new JLabel(images.infoBox);
    static JLabel infoBox2 = new JLabel(images.infoBox);
    

    Dimension playerSize = player.getPreferredSize();

    Image icon = Toolkit.getDefaultToolkit().getImage("Overworld Images//icon.png");  

    public void paintComponent (Graphics g) {
    }

    int screenWidth = 656;
    int screenHeight = 615;
    static int tile = 64;

    static int drawx = 656;
    static int drawy = 615;

    
    static int[][] leveldata = level.newLevel(0);

    
    
    
    
    public screen(){
        

        frame.setLayout(null);
        
        frame.getContentPane().setBackground(Color.BLACK);

        frame.setIconImage(icon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   

        frame.add(player);
        player.setBounds(256,256,playerSize.width,playerSize.height);

        frame.setResizable(false);

        frame.add(background);
        background.setBounds(movement.backgroundx,movement.backgroundy,2560,2560);

        frame.addKeyListener(new inputs());
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
    
        
        

    }
    

    

    


}
