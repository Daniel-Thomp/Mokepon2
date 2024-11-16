import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JLabel;

public class bag {
    static JLabel[] itemList = new JLabel[5];
    static JLabel[] quantityList = new JLabel[5];
    static int scroll;

    static JLabel ball = new JLabel();
    public static void openBag(){
        battle.event = "bag";
        screen.box.setIcon(images.bag);

        battle.battleText1.setVisible(false);
        battle.battleText2.setVisible(false);
        battle.battleText3.setVisible(false);
        battle.battleText4.setVisible(false);
        battle.opponentHealthbar.setVisible(false);
        battle.playerHealthbar.setVisible(false);
        battle.platform1.setVisible(false);
        battle.platform2.setVisible(false);
        screen.opponentLevel.setVisible(false);
        screen.playerLevel.setVisible(false);
        screen.opponentName.setVisible(false);
        screen.playerName.setVisible(false);
        screen.opponent.setVisible(false);
        screen.user.setVisible(false);
        screen.infoBox.setVisible(false);
        screen.infoBox2.setVisible(false);

        battle.cursor = 0;
        battle.cursorLabel.setBounds(180,15,100, 100);
        scroll = 0;
        loadBag();
        
    }
    public static void loadBag(){
        for (int i = 0; i < 5; i++) { 
            if (i+scroll < Main.Player.bag.size()){
                itemList[i].setText(Main.Player.bag.get(i+scroll));
                quantityList[i].setText("x" + Main.Player.bagAmount.get(i+scroll));
            }       
        }
    }
    public static void closeBag(){
        battle.battleText1.setVisible(false);
        battle.battleText2.setVisible(false);
        battle.battleText3.setVisible(false);
        battle.battleText4.setVisible(false);
        battle.opponentHealthbar.setVisible(true);
        battle.playerHealthbar.setVisible(true);
        battle.platform1.setVisible(true);
        battle.platform2.setVisible(true);
        screen.opponentLevel.setVisible(true);
        screen.playerLevel.setVisible(true);
        screen.opponentName.setVisible(true);
        screen.playerName.setVisible(true);
        screen.opponent.setVisible(true);
        screen.user.setVisible(true);
        screen.infoBox.setVisible(true);
        screen.infoBox2.setVisible(true);

        for (int i = 0; i < itemList.length; i++) {
            itemList[i].setText("");
            quantityList[i].setText("");
        }
    }
    public static void useItem(){
        closeBag();
        battle.event = "item";
        battle.cursorLabel.setVisible(false);
        screen.box.setIcon(images.textBox);
        Catch(1);
    }
    public static void Catch(double ballBonus) {
        int statusBonus = 1;
        if (Main.Opponent.inBattle.status != "") {
            statusBonus = 2;
        }
        double catchChance = 4*((Main.Opponent.inBattle.maxHP/Main.Opponent.inBattle.currentHP) * ballBonus * statusBonus)/Main.Opponent.inBattle.level;

        int shakes = 0;
        boolean notEscaped = true;
        for (int i = 0; i < 4; i++) {
            if (notEscaped) {
                double rand = ThreadLocalRandom.current().nextDouble();
                if (rand>Math.pow(catchChance,0.25)) {
                    //escaped
                    notEscaped = false;
                } else {
                    shakes++;
                }
            }
        }
        //get shakes here, 4 = caught
        animations.capture(shakes);
    }
}