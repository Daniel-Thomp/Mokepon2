import javax.swing.*;
import java.awt.*;
import java.io.File;

public class screen extends JFrame {

    public static JFrame frame = new JFrame("Mokepon 2");

    static Font customFont;

    static JLabel animation = new JLabel();
    static JLabel player = new JLabel(images.player_front);
    static JLabel background = new JLabel(images.town_1);
    static JLabel opponent = new JLabel();
    static JLabel user = new JLabel();
    static JLabel box = new JLabel(images.textBox);
    static JLabel infoBox = new JLabel(images.infoBox);
    static JLabel infoBox2 = new JLabel(images.infoBox);
    static JLabel moveBox = new JLabel(images.moveBox);
    static JTextArea text = new JTextArea();
    static JLabel playerName = new JLabel();
    static JLabel opponentName = new JLabel();
    static JLabel playerLevel = new JLabel();
    static JLabel opponentLevel = new JLabel();
    static JLabel weather = new JLabel();
    static JLabel star = new JLabel();

    Dimension playerSize = player.getPreferredSize();

    Image icon = Toolkit.getDefaultToolkit().getImage("Overworld Images//icon.png");

    public void paintComponent(Graphics g) {
    }

    int screenWidth = 654;
    int screenHeight = 613;
    static int tile = 64;

    static int[][] leveldata = level.newLevel("start");

    public screen() {
        frame.add(animation);
        animation.setBounds(0, 0, 640, 576);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("files//font.ttf")).deriveFont(30f);
        } catch (Exception e) {
            System.out.println("File not found");
        }

        text.setForeground(Color.BLACK);
        playerName.setForeground(Color.BLACK);
        playerLevel.setForeground(Color.BLACK);
        opponentName.setForeground(Color.BLACK);
        opponentLevel.setForeground(Color.BLACK);
        battle.battleText1.setForeground(Color.BLACK);
        battle.battleText2.setForeground(Color.BLACK);
        battle.battleText3.setForeground(Color.BLACK);
        battle.battleText4.setForeground(Color.BLACK);
        battle.move1.setForeground(Color.BLACK);
        battle.move2.setForeground(Color.BLACK);
        battle.move3.setForeground(Color.BLACK);
        battle.move4.setForeground(Color.BLACK);
        battle.movePower.setForeground(Color.BLACK);
        battle.moveAccuracy.setForeground(Color.BLACK);
        battle.moveType.setForeground(Color.BLACK);
        text.setFont(customFont);
        playerName.setFont(customFont);
        opponentName.setFont(customFont);
        playerLevel.setFont(customFont);
        opponentLevel.setFont(customFont);
        battle.battleText1.setFont(customFont);
        battle.battleText2.setFont(customFont);
        battle.battleText3.setFont(customFont);
        battle.battleText4.setFont(customFont);
        battle.move1.setFont(customFont);
        battle.move2.setFont(customFont);
        battle.move3.setFont(customFont);
        battle.move4.setFont(customFont);
        battle.moveAccuracy.setFont(customFont);
        battle.movePower.setFont(customFont);
        battle.moveType.setFont(customFont);

        text.setFont(customFont);
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setEditable(false); // Disable editing
        text.setOpaque(false); // Make the background transparent
        text.setFocusable(false); // Disable focusability
        text.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        text.setBorder(null);
        frame.setLayout(null);

        frame.getContentPane().setBackground(Color.BLACK);

        frame.setIconImage(icon);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(switchOut.one);
        frame.add(switchOut.two);
        frame.add(switchOut.three);
        frame.add(switchOut.four);
        frame.add(switchOut.five);
        frame.add(switchOut.six);
        frame.add(switchOut.seven);
        frame.add(switchOut.eight);

        for (int i = 0; i < 5; i++) {
            bag.itemList[i] = new JLabel();
            bag.itemList[i].setFont(screen.customFont);
            screen.frame.add(bag.itemList[i]);
            bag.itemList[i].setBounds(250, 20 + 60 * i, 500, 100);

            bag.quantityList[i] = new JLabel();
            bag.quantityList[i].setFont(screen.customFont);
            screen.frame.add(bag.quantityList[i]);
            bag.quantityList[i].setBounds(500, 50 + 60 * i, 500, 100);
        }

        screen.frame.add(menu.dex);
        screen.frame.add(menu.mons);
        screen.frame.add(menu.bag);
        screen.frame.add(menu.player);
        screen.frame.add(menu.save);
        screen.frame.add(menu.options);
        screen.frame.add(battle.cursorLabel);
        screen.frame.add(menu.menuBox);
        screen.frame.add(screen.box);
        box.setVisible(false);
        screen.box.setBounds(-10, -112, 656, 1200);
        frame.add(star);
        star.setBounds(386, 147, 200, 200);

        frame.add(player);
        player.setBounds(256, 256, playerSize.width, playerSize.height);

        frame.setResizable(false);

        frame.add(background);
        background.setBounds(movement.backgroundx, movement.backgroundy, 2560, 2560);

        frame.addKeyListener(new inputs());
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);

    }

}
