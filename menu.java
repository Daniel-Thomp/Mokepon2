import javax.swing.JLabel;

public class menu {
    static JLabel menuBox = new JLabel(images.menu);
    static JLabel dex = new JLabel();
    static JLabel mons = new JLabel();
    static JLabel bag = new JLabel();
    static JLabel player = new JLabel();
    static JLabel save = new JLabel();
    static JLabel options = new JLabel();

    public static void openMenu() {
        movement.moveable = false;
        battle.event = "menu";
        battle.cursorLabel.setVisible(true);
        menuBox.setVisible(true);
        menuBox.setBounds(-30, -62, 700, 700);
        battle.cursorLabel.setBounds(370, 7, 100, 100);
        battle.cursor = 0;

        dex.setBounds(450, 10, 200, 100);
        mons.setBounds(450, 85, 200, 100);
        bag.setBounds(450, 160, 200, 100);
        player.setBounds(450, 235, 200, 100);
        save.setBounds(450, 310, 200, 100);
        options.setBounds(450, 385, 200, 100);

        dex.setFont(screen.customFont);
        mons.setFont(screen.customFont);
        bag.setFont(screen.customFont);
        player.setFont(screen.customFont);
        save.setFont(screen.customFont);
        options.setFont(screen.customFont);

        dex.setText("Mokedex");
        mons.setText("Mokepons");
        bag.setText("Bag");
        player.setText("Player");
        save.setText("Save");
        options.setText("Options");
    }

    public static void closeMenu() {
        
        battle.event = "";
        battle.cursorLabel.setVisible(false);
        menuBox.setVisible(false);

        dex.setText("");
        mons.setText("");
        bag.setText("");
        player.setText("");
        save.setText("");
        options.setText("");
    }
}
