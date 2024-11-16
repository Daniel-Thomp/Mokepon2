import javax.swing.*;

public class switchOut {
    static JLabel one = new JLabel();
    static JLabel two = new JLabel();
    static JLabel three = new JLabel();
    static JLabel four = new JLabel();
    static JLabel five = new JLabel();
    static JLabel six = new JLabel();
    static JLabel seven = new JLabel();
    static JLabel eight = new JLabel();

    static int cursor;

    public static void player(){
        battle.event = "Switch";
        battle.cursor = 1;
        
        battle.battleText1.setVisible(false);
        battle.battleText2.setVisible(false);
        battle.battleText3.setVisible(false);
        battle.battleText4.setVisible(false);

        one.setBounds(425, 210, 1000, 50);
        two.setBounds(425, 240, 1000, 50);
        three.setBounds(425, 270, 1000, 50);
        four.setBounds(425, 300, 1000, 50);
        five.setBounds(425, 330, 1000, 50);
        six.setBounds(425, 360, 1000, 50);

        battle.cursorLabel.setBounds(355,180,100, 100);

        one.setFont(screen.customFont);
        two.setFont(screen.customFont);
        three.setFont(screen.customFont);
        four.setFont(screen.customFont);
        five.setFont(screen.customFont);
        six.setFont(screen.customFont);

        screen.box.setIcon(images.switchBox);
        one.setText(Main.Player.monsters[1].name);
        two.setText(Main.Player.monsters[2].name);
        three.setText(Main.Player.monsters[3].name);
        four.setText(Main.Player.monsters[4].name);
        five.setText(Main.Player.monsters[5].name);
        six.setText(Main.Player.monsters[6].name);
        seven.setText("");
        eight.setText("");
    
    }
    public static void switchMenu (){
        screen.box.setIcon(images.switchBox2);
        battle.event = "switchMenu";
        seven.setBounds(175, 210, 1000, 50);
        eight.setBounds(175, 240, 1000, 50);
        seven.setText("Switch");
        eight.setText("Stats");
        seven.setFont(screen.customFont);
        eight.setFont(screen.customFont);
        cursor = 1;
        battle.cursorLabel.setBounds(105,180,100, 100);

    }
    public static void swap (Mokepon in){
        one.setText("");
        two.setText("");
        three.setText("");
        four.setText("");
        five.setText("");
        six.setText("");
        seven.setText("");
        eight.setText("");
        screen.box.setIcon(images.textBox);
        battle.cursorLabel.setVisible(false);
        Main.animator.text("You withdrew " + Main.Player.inBattle.name);
        animations.Switch();

        Main.Player.inBattle = in;
        switchInfo();
    }
    public static void opponent(){
        
        
    }
    public static void switchInfo (){
        screen.playerName.setText(Main.Player.inBattle.name);
        screen.playerLevel.setText("L:" + Main.Player.inBattle.level);
        screen.opponentName.setText(Main.Opponent.inBattle.name);
        screen.opponentLevel.setText("L:" + Main.Opponent.inBattle.level);
        ImageIcon P = new ImageIcon("battle images//monsters//"+Main.Player.inBattle.name+"_back.png");
        ImageIcon O = new ImageIcon("battle images//monsters//"+Main.Opponent.inBattle.name+"_front.png");
        screen.user.setIcon(P);
        screen.opponent.setIcon(O);
        battle.drawHealthbar();
        
    }
    public static void next(){
        
    }
}
