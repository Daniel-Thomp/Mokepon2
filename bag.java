import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.Timer;

public class bag {
    static JLabel[] itemList = new JLabel[5];
    static JLabel[] quantityList = new JLabel[5];
    static int scroll;
    static int delay = 0;
    static ScheduledExecutorService executor;

    static JLabel ball = new JLabel();

    public static void openBag() {
        battle.event = "bag";
        screen.box.setVisible(true);
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
        battle.cursorLabel.setVisible(true);

        battle.cursor = 0;
        battle.cursorLabel.setBounds(180, 15, 100, 100);
        scroll = 0;
        loadBag();

    }

    public static void loadBag() {
        for (int i = 0; i < 5; i++) {
            if (i + scroll < Main.Player.bag.size()) {
                itemList[i].setText(Main.Player.bag.get(i + scroll));
                quantityList[i].setText("x" + Main.Player.bagAmount.get(i + scroll));
            }
        }
    }

    public static void closeBag() {
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

    public static void useItem() {
        battle.event = "item";
        String item = Main.Player.bag.get(battle.cursor + scroll);
        System.out.println(item);
        closeBag();
        battle.cursorLabel.setVisible(false);
        screen.box.setIcon(images.textBox);
        Main.animator.text("You used a " + item);

        Timer timer = new Timer(2000, event -> {
            try {
                Class<?> clazz = bag.class;
                Method method1 = clazz.getDeclaredMethod(item);
                method1.invoke(null);
            } catch (Exception e) {

                System.out.println(e);
            }

        });
        timer.setRepeats(false);
        timer.start();

    }

    public static void Catch(double ballBonus) {
        delay = 0;
        int statusBonus = 1;
        if (Main.Opponent.inBattle.status != "") {
            statusBonus = 2;
        }
        double catchChance = 4
                * ((Main.Opponent.inBattle.maxHP / Main.Opponent.inBattle.currentHP) * ballBonus * statusBonus)
                / Main.Opponent.inBattle.level;

        int shakes = 0;
        boolean notEscaped = true;
        for (int i = 0; i < 4; i++) {
            if (notEscaped) {
                double rand = ThreadLocalRandom.current().nextDouble();
                if (rand > Math.pow(catchChance, 0.25)) {
                    // escaped
                    notEscaped = false;
                } else {
                    shakes++;
                }
            }
        }
        if (shakes == 4 && !dex.dex.containsKey(Main.Opponent.inBattle.name)) {
            delay = 2000;
            Timer timer = new Timer(6750, event -> {
                Main.animator.text(Main.Opponent.inBattle.name + "Was registered to the Mokedex!");
                dex.register(Main.Opponent.inBattle.name);
            });
            timer.setRepeats(false);
            timer.start();
        }
        // get shakes here, 4 = caught
        animations.capture(shakes);
    }

    public static void heal(int healing) {
        battle.healthCounter = Main.Player.inBattle.currentHP;
        if (Main.Player.inBattle.currentHP + healing > Main.Player.inBattle.maxHP) {
            healing = Main.Player.inBattle.maxHP - Main.Player.inBattle.currentHP;
        }
        Main.Player.inBattle.currentHP += healing;
        Main.animator.text(Main.Player.inBattle.name + "'s health was restored!");
        battle.executor = Executors.newScheduledThreadPool(1);
        battle.executor.scheduleAtFixedRate(battle.healthbarDelayOpponent, 0, (int) ((2.0 / healing) * 1000),
                TimeUnit.MILLISECONDS);

        Timer timer = new Timer(3000, event -> {
            move.opponentTurn();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void Mokeball() {
        Catch(1);
    }

    public static void Potion() {
        heal(20);
    }
}