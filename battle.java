import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class battle {
    static JLabel battleText1 = new JLabel();
    static JLabel battleText2 = new JLabel();
    static JLabel battleText3 = new JLabel();
    static JLabel battleText4 = new JLabel();
    static JLabel move1 = new JLabel();
    static JLabel move2 = new JLabel();
    static JLabel move3 = new JLabel();
    static JLabel move4 = new JLabel();
    static JLabel movePower = new JLabel();
    static JLabel moveAccuracy = new JLabel();
    static JLabel moveType = new JLabel();
    static JLabel moveCatagory = new JLabel();
    static JLabel cursorLabel = new JLabel(images.cursor);
    static JLabel platform1 = new JLabel(images.platform);
    static JLabel platform2 = new JLabel(images.platform);
    static JLabel Pstatus = new JLabel();
    static JLabel Ostatus = new JLabel();
    static String event = "Overworld";
    static int cursor = 1;
    static int healthCounter;
    static ScheduledExecutorService executor;
    static JLabel opponentHealthbar = new JLabel();
    static JLabel playerHealthbar = new JLabel();
    static double typeEffectiveness;
    static String effect;
    static int effectStrength;
    static String effect2;
    static int effectStrength2;
    static String weather;
    static int weatherCounter;
    static int delay;
    static Mokepon attacker;
    static Mokepon defender;
    static double damage;
    static double attack;
    static double defence;
    static double time;
    static double BPcountP;
    static double BPcountO;
    static int sleepCountP;
    static int sleepCountO;
    static int runAttempts;

    public static void startBattle() {
        Main.Player.inBattle = Main.Player.monsters[1];
        Main.Opponent.inBattle = Main.Opponent.monsters[1];
        animations.encounter();
        Timer timer = new Timer(2500, event -> {
            battle.event = "Battle";
            screen.background.setVisible(false);
            screen.player.setVisible(false);
            movement.moveable = false;

            screen.frame.getContentPane().setBackground(Color.WHITE);
            screen.frame.add(battleText1);
            screen.frame.add(battleText2);
            screen.frame.add(battleText3);
            screen.frame.add(battleText4);
            cursorLabel.setVisible(false);
            screen.frame.add(move1);
            screen.frame.add(move2);
            screen.frame.add(move3);
            screen.frame.add(move4);
            screen.frame.add(movePower);
            screen.frame.add(moveAccuracy);
            screen.frame.add(moveType);
            screen.frame.add(moveCatagory);

            screen.frame.add(screen.text);

            screen.text.setBounds(30, 425, 550, 300);

            screen.box.setVisible(true);
            screen.frame.remove(screen.box);
            screen.frame.add(screen.box);

            screen.frame.add(opponentHealthbar);
            screen.frame.add(playerHealthbar);

            screen.frame.add(screen.playerName);
            screen.frame.add(screen.playerLevel);
            screen.playerName.setBounds(392, 290, 500, 50);
            screen.playerLevel.setBounds(398, 320, 500, 50);

            screen.frame.add(screen.opponentName);
            screen.frame.add(screen.opponentLevel);
            screen.opponentName.setBounds(78, 20, 500, 50);
            screen.opponentLevel.setBounds(84, 50, 500, 50);

            screen.frame.add(Ostatus);
            Ostatus.setBounds(160, 61, 40, 20);
            ImageIcon status = new ImageIcon("battle images//" + Main.Opponent.inBattle.status + ".png");
            Ostatus.setIcon(status);
            status = new ImageIcon("battle images//" + Main.Player.inBattle.status + ".png");
            screen.frame.add(Pstatus);
            Pstatus.setIcon(status);
            Pstatus.setBounds(474, 331, 40, 20);
            Ostatus.setVisible(false);
            Pstatus.setVisible(false);

            screen.frame.add(screen.infoBox);
            screen.frame.add(screen.infoBox2);
            screen.infoBox.setBounds(5, 10, 316, 124);
            screen.infoBox2.setBounds(320, 280, 316, 124);
            screen.infoBox.setVisible(false);
            screen.infoBox2.setVisible(false);

            screen.frame.add(screen.weather);
            screen.weather.setBounds(0, 0, 656, 615);

            ImageIcon userImg = new ImageIcon("battle images//monsters//" + Main.Player.inBattle.name + "_back.png");
            ImageIcon oppImg = new ImageIcon("battle images//monsters//" + Main.Opponent.inBattle.name + "_front.png");

            screen.user.setIcon(animations.grey(userImg.getImage()));
            screen.opponent.setIcon(animations.grey(oppImg.getImage()));
            screen.frame.add(screen.user);
            screen.frame.add(screen.opponent);
            screen.opponent.setVisible(true);
            screen.frame.add(bag.ball);

            screen.frame.add(platform1);
            screen.frame.add(platform2);
            platform1.setBounds(300, 182, 372, 100);
            platform2.setBounds(-60, 380, 372, 100);
            battle.platform1.setVisible(false);
            battle.platform2.setVisible(false);

            battleText1.setText("Fight");
            battleText2.setText("Mons");
            battleText3.setText("Bag");
            battleText4.setText("Run");
            battleText1.setBounds(360, 400, 100, 100);
            battleText2.setBounds(500, 400, 100, 100);
            battleText3.setBounds(360, 470, 100, 100);
            battleText4.setBounds(500, 470, 100, 100);
            battleText1.setVisible(false);
            battleText2.setVisible(false);
            battleText3.setVisible(false);
            battleText4.setVisible(false);

            move1.setBounds(220, 400, 500, 100);
            move2.setBounds(220, 430, 500, 100);
            move3.setBounds(220, 460, 500, 100);
            move4.setBounds(220, 490, 500, 100);

            moveType.setBounds(30, 275, 500, 100);
            movePower.setBounds(30, 305, 500, 100);
            moveAccuracy.setBounds(30, 335, 500, 100);
            moveCatagory.setBounds(190, 301, 500, 100);

            move1.setVisible(false);
            move2.setVisible(false);
            move3.setVisible(false);
            move4.setVisible(false);

            moveType.setVisible(false);
            movePower.setVisible(false);
            moveAccuracy.setVisible(false);
            moveCatagory.setVisible(false);
            executor = Executors.newScheduledThreadPool(1);

            runAttempts = 1;
            Main.Player.inBattle.tailwind = 0;
            Main.Opponent.inBattle.tailwind = 0;

            screen.animation.setIcon(null);

            animations.counter = 360;
            executor.scheduleAtFixedRate(animations.openingAnimation, 0, (int) ((1.0 / 360.0) * 1500.0),
                    TimeUnit.MILLISECONDS);
        });
        timer.setRepeats(false);
        timer.start();

    }

    public static void action() {
        battle.event = "Action";
        screen.text.setText("");
        cursorLabel.setVisible(true);
        screen.box.setIcon(images.battleBox);
        cursor = 1;

        battleText1.setVisible(true);
        battleText2.setVisible(true);
        battleText3.setVisible(true);
        battleText4.setVisible(true);

        move1.setVisible(false);
        move2.setVisible(false);
        move3.setVisible(false);
        move4.setVisible(false);
        moveType.setVisible(false);
        movePower.setVisible(false);
        moveAccuracy.setVisible(false);
        moveCatagory.setVisible(false);

        switchOut.one.setText("");
        switchOut.two.setText("");
        switchOut.three.setText("");
        switchOut.four.setText("");
        switchOut.five.setText("");
        switchOut.six.setText("");

        cursorLabel.setBounds(295, 396, 100, 100);

    }

    public static void moveSelection() {
        battle.event = "MoveSelection";
        cursor = 1;

        battleText1.setVisible(false);
        battleText2.setVisible(false);
        battleText3.setVisible(false);
        battleText4.setVisible(false);

        move1.setVisible(true);
        move2.setVisible(true);
        move3.setVisible(true);
        move4.setVisible(true);

        screen.box.setIcon(images.moveBox);

        move1.setText(Main.Player.inBattle.Move1);
        move2.setText(Main.Player.inBattle.Move2);
        move3.setText(Main.Player.inBattle.Move3);
        move4.setText(Main.Player.inBattle.Move4);

        moveType.setVisible(true);
        movePower.setVisible(true);
        moveAccuracy.setVisible(true);
        moveCatagory.setVisible(true);

        cursorLabel.setBounds(155, 396, 100, 100);
        move.useMove(Main.Player.inBattle.Move1, "name");
    }

    public static void moveBeingUsed() {

        event = "moveBeingUsed";
        screen.box.setIcon(images.textBox);

        move1.setVisible(false);
        move2.setVisible(false);
        move3.setVisible(false);
        move4.setVisible(false);
        moveType.setVisible(false);
        movePower.setVisible(false);
        moveAccuracy.setVisible(false);
        moveCatagory.setVisible(false);
        cursorLabel.setVisible(false);
        battleText1.setVisible(false);
        battleText2.setVisible(false);
        battleText3.setVisible(false);
        battleText4.setVisible(false);

    }

    public static void calcDamage(int power, int type, String user, String category, String effect, int effectStrength,
            String effect2, int effectStrength2, String moveName, int accuracy) {
        battle.effect = effect;
        battle.effectStrength = effectStrength;
        battle.effect2 = effect2;
        battle.effectStrength2 = effectStrength2;

        damage = 0;
        attack = 0;
        defence = 0;
        typeEffectiveness = 1;
        if (user == "Player") {
            attacker = Main.Player.inBattle;
            defender = Main.Opponent.inBattle;

        } else {
            attacker = Main.Opponent.inBattle;
            defender = Main.Player.inBattle;
        }
        delay = 0;
        if (moveable(attacker)) {
            Timer timer = new Timer(delay, event -> {
                Main.animator.text(attacker.name + " used " + moveName);
                if (ThreadLocalRandom.current().nextInt(1, 101) <= accuracy || accuracy == 0) {
                    if (power != 0) {

                        if (category == "Physical") {
                            attack = attacker.attack * statBoosts(attacker, "attack");
                            defence = defender.defence * statBoosts(defender, "defence");
                        } else {
                            attack = attacker.specialAttack * statBoosts(attacker, "specialAttack");
                            defence = defender.specialDefence * statBoosts(defender, "specialDefence");
                        }

                        damage = ((((2 * (double) attacker.level) / 5 + 2) * (double) power
                                * ((double) attack / (double) defence)) / 50) + 2;

                        typeEffectiveness = Main.typeChart(type, defender.type1, defender.type2);

                        damage = damage * multiplier(type);

                        if (damage > defender.currentHP) {
                            damage = defender.currentHP;
                        }

                        healthCounter = 0;
                        double time = 0;
                        if (user == "Player") {
                            healthCounter = Main.Opponent.inBattle.currentHP;

                            Main.Opponent.inBattle.currentHP = Main.Opponent.inBattle.currentHP - (int) damage;
                            time = 2 / damage;
                            executor = Executors.newScheduledThreadPool(1);
                            executor.scheduleAtFixedRate(healthbarDelayUser, 0, (int) (time * 1000),
                                    TimeUnit.MILLISECONDS);

                        } else {
                            healthCounter = Main.Player.inBattle.currentHP;
                            Main.Player.inBattle.currentHP = Main.Player.inBattle.currentHP - (int) damage;
                            time = 2 / damage;
                            executor = Executors.newScheduledThreadPool(1);
                            executor.scheduleAtFixedRate(healthbarDelayOpponent, 0, (int) (time * 1000),
                                    TimeUnit.MILLISECONDS);
                        }
                    }
                    Timer timer2 = new Timer(2000, event2 -> {
                        moveOver();
                    });
                    timer2.setRepeats(false);
                    timer2.start();
                } else {
                    Timer timer2 = new Timer(2000, event2 -> {

                        Main.animator.text("But it missed");

                        Timer timer3 = new Timer(2000, event3 -> {
                            battle.nextMove();
                        });
                        timer3.setRepeats(false);
                        timer3.start();
                    });
                    timer2.setRepeats(false);
                    timer2.start();
                }
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            Timer timer = new Timer(2000, event -> {
                nextMove();
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    public static boolean moveable(Mokepon attacker) {
        boolean moveable = true;
        if (attacker.flinched) {
            Main.animator.text(attacker.name + " flinched and couldn't move!");
            moveable = false;
        } else if (attacker.status == "Para" && ThreadLocalRandom.current().nextInt(0, 4) == 1) {
            Main.animator.text(attacker.name + " is paralyzed! It can't move!");
            moveable = false;
        } else if (attacker.status == "Sleep") {
            if (attacker == Main.Opponent.inBattle) {
                sleepCountO--;
                if (sleepCountO == 0) {
                    Main.animator.text(attacker.name + " woke up!");
                    delay = 2000;
                    attacker.status = "";
                } else {
                    Main.animator.text(attacker.name + " is fast asleep");
                    moveable = false;
                }
            } else {
                sleepCountP--;
                if (sleepCountO == 0) {
                    Main.animator.text(attacker.name + " woke up!");
                } else {
                    Main.animator.text(attacker.name + " is fast asleep");
                    moveable = false;
                }
            }

        } else if (attacker.status == "Freeze") {
            if (ThreadLocalRandom.current().nextInt(0, 5) == 1) {
                Main.animator.text(attacker.name + " thawed out!");
                delay = 2000;
                attacker.status = "";
            } else {
                Main.animator.text(attacker.name + " is frozen solid");
                moveable = false;
            }
        }
        ImageIcon status = new ImageIcon("battle images//" + Main.Opponent.inBattle.status + ".png");
        Ostatus.setIcon(status);
        status = new ImageIcon("battle images//" + Main.Player.inBattle.status + ".png");
        Pstatus.setIcon(status);
        return moveable;
    }

    public static double statBoosts(Mokepon subject, String stat) {
        double boost = 0;
        if (stat == "attack") {
            boost = subject.attackBoost;
        } else if (stat == "specialAttack") {
            boost = subject.specialAttackBoost;
        } else if (stat == "defence") {
            boost = subject.defenceBoost;
        } else if (stat == "specialDefence") {
            boost = subject.specialDefenceBoost;
        } else if (stat == "speed") {
            boost = subject.speedBoost;
        }
        if (boost >= 0) {
            boost = boost + 2;
            boost = boost / 2;
        } else {
            boost = boost * -1;
            boost = boost + 2;
            boost = 2 / boost;
        }
        if (subject.status == "Burn" && stat == "attack") {
            boost = boost / 2;
        } else if (subject.status == "Para" && stat == "speed") {
            boost = boost / 2;
        }
        if (subject.tailwind > 0 && stat == "speed") {
            boost = boost * 2;
        }
        return boost;
    }

    public static double multiplier(int moveType) {
        double multiplier = Main.typeChart(moveType, defender.type1, defender.type2);
        multiplier = multiplier * ((double) (ThreadLocalRandom.current().nextInt(85, 101)) / 100);
        if (moveType == attacker.type1 || moveType == attacker.type2) {
            multiplier = multiplier * 1.5;
        }
        if (weather == "Rain") {
            if (moveType == 2) {
                multiplier = multiplier * 1.5;
            } else if (moveType == 1) {
                multiplier = multiplier * 0.5;
            }
        } else if (weather == "Sun") {
            if (moveType == 1) {
                multiplier = multiplier * 1.5;
            } else if (moveType == 2) {
                multiplier = multiplier * 0.5;
            }
        }

        return multiplier;
    }

    static Runnable healthbarDelayUser = new Runnable() {
        public void run() {
            if (healthCounter == Main.Opponent.inBattle.currentHP) {
                executor.shutdownNow();
            } else if (healthCounter > Main.Opponent.inBattle.currentHP) {
                healthCounter--;
            } else {
                healthCounter++;
            }
            updateHealthbarO(healthCounter);

        }
    };
    static Runnable healthbarDelayOpponent = new Runnable() {
        public void run() {
            if (healthCounter == Main.Player.inBattle.currentHP) {
                executor.shutdownNow();
            } else if (healthCounter > Main.Player.inBattle.currentHP) {
                healthCounter--;
            } else {
                healthCounter++;
            }
            updateHealthbarP(healthCounter);

        }
    };

    public static void moveOver() {
        delay = 2000;
        if (typeEffectiveness > 1) {
            Main.animator.text("It's supereffective!");
        } else if (typeEffectiveness == 0) {
            Main.animator.text("It had no effect");
        } else if (typeEffectiveness != 1) {
            Main.animator.text("It's not very effective");
        } else {
            delay = 0;
        }
        Timer timer = new Timer(delay, event -> {
            effect(effect, effectStrength, attacker, defender);
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void nextMove() {
        if (Main.Player.inBattle.currentHP <= 0 || Main.Opponent.inBattle.currentHP <= 0) {
            if (Main.Player.inBattle.currentHP <= 0) {
                Main.animator.text(Main.Player.inBattle.name + " fainted");
                Main.Player.inBattle.currentHP = 0;
                switchOut.use = "KO";
                switchOut.player();
            }
            if (Main.Opponent.inBattle.currentHP <= 0) {
                Main.animator.text(Main.Opponent.inBattle.name + " fainted");
                Main.Opponent.inBattle.currentHP = 0;
                Timer timer = new Timer(2000, event -> {
                    Main.Player.inBattle.gainXp(Main.Opponent.inBattle.level);
                });
                timer.setRepeats(false);
                timer.start();

                switchOut.use = "KO";
                // switchOut.opponent();

            }

        } else if (move.opponentMoving) {
            move.userTurn();
        } else if (move.playerMoving) {
            move.opponentTurn();
        } else {
            endTurnEvents();
        }
    }

    public static void endTurnEvents() {
        damage = 0;
        time = 0;
        delay = 0;
        Main.Player.inBattle.flinched = false;
        Main.Opponent.inBattle.flinched = false;
        if (Main.Player.inBattle.status == "Burn") {
            damage = Main.Player.inBattle.maxHP / 16;
            Main.animator.text(Main.Player.inBattle.name + " was hurt by its burn");
        } else if (Main.Player.inBattle.status == "Poison") {
            damage = Main.Player.inBattle.maxHP / 8;
            Main.animator.text(Main.Player.inBattle.name + " was hurt by poison");
        } else if (Main.Player.inBattle.status == "BadPoison") {
            damage = Main.Player.inBattle.maxHP * (BPcountP / 16.0);
            Main.animator.text(Main.Player.inBattle.name + " was hurt by poison");
            BPcountP++;
        }
        if (damage > 0) {
            delay = 2000;
            healthCounter = Main.Player.inBattle.currentHP;
            Main.Player.inBattle.currentHP = Main.Player.inBattle.currentHP - (int) damage;
            if (Main.Opponent.inBattle.currentHP < 0) {
                damage = damage - Main.Player.inBattle.currentHP * -1;
                Main.Player.inBattle.currentHP = 0;
            }
            time = 2 / damage;
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(healthbarDelayOpponent, 0, (int) (time * 1000), TimeUnit.MILLISECONDS);
        }
        Timer timer = new Timer(delay, event -> {
            delay = 0;
            if (Main.Opponent.inBattle.status == "Burn") {
                damage = Main.Opponent.inBattle.maxHP / 16;
                Main.animator.text(Main.Opponent.inBattle.name + " was hurt by its burn");
            } else if (Main.Opponent.inBattle.status == "Poison") {
                damage = Main.Opponent.inBattle.maxHP / 8;
                Main.animator.text(Main.Opponent.inBattle.name + " was hurt by poison");
            } else if (Main.Opponent.inBattle.status == "BadPoison") {
                damage = (Main.Opponent.inBattle.maxHP) * (BPcountO / 16.0);
                Main.animator.text(Main.Opponent.inBattle.name + " was hurt by poison");
                BPcountO++;
            }
            if (damage > 0) {
                delay = 2000;
                healthCounter = Main.Opponent.inBattle.currentHP;
                Main.Opponent.inBattle.currentHP = Main.Opponent.inBattle.currentHP - (int) damage;
                if (Main.Opponent.inBattle.currentHP < 0) {
                    damage = damage - Main.Opponent.inBattle.currentHP * -1;
                    Main.Opponent.inBattle.currentHP = 0;
                }
                time = 2 / damage;
                executor = Executors.newScheduledThreadPool(1);
                executor.scheduleAtFixedRate(healthbarDelayUser, 0, (int) (time * 1000), TimeUnit.MILLISECONDS);
            }
            Timer timer2 = new Timer(delay, event2 -> {
                delay = 0;
                weatherCounter--;
                if (weatherCounter == 0) {
                    Main.animator.text("The weather cleared up");
                    weather = null;
                    screen.frame.getContentPane().setBackground(Color.WHITE);
                    screen.weather.setIcon(null);
                    delay = 2000;
                }
                Timer timer3 = new Timer(delay, event3 -> {
                    delay = 0;
                    Main.Player.inBattle.tailwind--;
                    Main.Opponent.inBattle.tailwind--;
                    if (Main.Player.inBattle.tailwind == 0 || Main.Opponent.inBattle.tailwind == 0) {
                        Main.animator.text("The tailwind petered out");
                        delay = 2000;
                    }
                    Timer timer4 = new Timer(delay, event4 -> {
                        endTurn();
                    });
                    timer4.setRepeats(false);
                    timer4.start();
                });
                timer3.setRepeats(false);
                timer3.start();
            });
            timer2.setRepeats(false);
            timer2.start();

        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void endTurn() {
        if (Main.Player.inBattle.currentHP <= 0 || Main.Opponent.inBattle.currentHP <= 0) {
            if (Main.Player.inBattle.currentHP <= 0) {
                Main.animator.text(Main.Player.inBattle.name + " fainted");
                Main.Player.inBattle.currentHP = 0;

                switchOut.use = "KO";
                switchOut.player();
            }
            if (Main.Opponent.inBattle.currentHP <= 0) {
                Main.animator.text(Main.Opponent.inBattle.name + " fainted");
                Main.Opponent.inBattle.currentHP = 0;
                Main.Player.inBattle.gainXp(Main.Opponent.inBattle.level);

                switchOut.use = "KO";
                // switchOut.opponent();
                Timer timer = new Timer(2000, event -> {
                    endBattle();
                });
                timer.setRepeats(false);
                timer.start();
            }

        } else {
            action();
        }
    }

    public static void effect(String effect, int effectStrength, Mokepon user, Mokepon opponent) {

        delay = 2000;
        if (effect == null) {
            delay = 0;
        } else if (effect == "Rain") {
            if (weather != "Rain") {
                weather = "Rain";
                weatherCounter = effectStrength;
                Main.animator.text("It started to rain");
                screen.frame.getContentPane().setBackground(Color.DARK_GRAY);
                screen.weather.setIcon(images.rain);
            } else {
                Main.animator.text("But it failed");
            }
        } else if (effect == "Sun") {
            if (weather != "Sun") {
                weather = "Sun";
                weatherCounter = effectStrength;
                Main.animator.text("The sunlight turned harsh");
                screen.frame.getContentPane().setBackground(Color.WHITE);
                screen.weather.setIcon(images.sun);
            } else {
                Main.animator.text("But it failed");
            }
        } else if (effect == "Recoil") {

            healthCounter = attacker.currentHP;
            double recoil = damage / effectStrength;
            double time = 2 / recoil;
            attacker.currentHP = attacker.currentHP - (int) recoil;
            executor = Executors.newScheduledThreadPool(1);
            Main.animator.text(attacker.name + " was hurt by the recoil");
            if (attacker == Main.Opponent.inBattle) {

                executor.scheduleAtFixedRate(healthbarDelayUser, 0, (int) (time * 1000), TimeUnit.MILLISECONDS);
            } else {

                executor.scheduleAtFixedRate(healthbarDelayOpponent, 0, (int) (time * 1000), TimeUnit.MILLISECONDS);
            }

        } else if ((ThreadLocalRandom.current().nextInt(1, 101)) <= effectStrength || effectStrength == 0) {
            if (opponent.status == "") {
                if (effect == "Para") {
                    if (defender.type1 != 4 || defender.type2 != 4) {
                        Main.animator.text(opponent.name + " was paralyzed");
                        opponent.status = "Para";
                    } else if (effectStrength == 0) {
                        Main.animator.text("But it failed");
                    }
                } else if (effect == "Freeze") {
                    if (defender.type1 != 5 || defender.type2 != 5) {
                        Main.animator.text(opponent.name + " was frozen solid");
                        opponent.status = "Freeze";
                    } else if (effectStrength == 0) {
                        Main.animator.text("But it failed");
                    }
                } else if (effect == "Burn") {
                    if (defender.type1 != 1 || defender.type2 != 1) {
                        Main.animator.text(opponent.name + " was burned");
                        opponent.status = "Burn";
                    } else if (effectStrength == 0) {
                        Main.animator.text("But it failed");
                    }

                } else if (effect == "Sleep") {
                    Main.animator.text(opponent.name + " fell asleep");
                    opponent.status = "Sleep";
                    if (opponent == Main.Opponent.inBattle) {
                        sleepCountO = ThreadLocalRandom.current().nextInt(1, 4);
                    } else {
                        sleepCountP = ThreadLocalRandom.current().nextInt(1, 4);
                    }
                } else if (effect == "Poison") {
                    if (defender.type1 != 7 || defender.type2 != 7 || defender.type1 != 16 || defender.type2 != 16) {
                        Main.animator.text(opponent.name + " was poisoned");
                        opponent.status = "Poison";
                    } else if (effectStrength == 0) {
                        Main.animator.text("But it failed");
                    }
                } else if (effect == "BadlyPoison") {
                    if (defender.type1 != 7 || defender.type2 != 7 || defender.type1 != 16 || defender.type2 != 16) {
                        Main.animator.text(opponent.name + " was badly poisoned");
                        opponent.status = "BadPoison";
                        if (opponent == Main.Opponent.inBattle) {
                            BPcountO = 1;
                        } else {
                            BPcountP = 1;
                        }
                    } else if (effectStrength == 0) {
                        Main.animator.text("But it failed");
                    }
                }
            } else if ((effectStrength == 0) && (effect == "Para" || effect == "Freeze" || effect == "Burn"
                    || effect == "Sleep" || effect == "Poison" || effect == "BadlyPoison")) {
                Main.animator.text("But it failed");
            }
            if (effect == "Confuse") {
                // do a confusion
            } else if (effect == "Tailwind") {

                if (user.tailwind <= 0) {
                    user.tailwind = 5;
                    Main.animator.text(user.name + " set up a tailwind!");
                } else {
                    Main.animator.text("but it failed");
                }

            } else if (effect == "Flinch") {
                if ((user == Main.Player.inBattle && move.playerFirst)
                        || (user == Main.Opponent.inBattle && move.playerFirst == false)) {
                    opponent.flinched = true;
                }
                delay = 0;
            } else if (effect.contains("+") || effect.contains("-")) {
                statChange(user, opponent);
            }
        } else {
            delay = 0;
        }
        ImageIcon status = new ImageIcon("battle images//" + Main.Opponent.inBattle.status + ".png");
        Ostatus.setIcon(status);
        status = new ImageIcon("battle images//" + Main.Player.inBattle.status + ".png");
        Pstatus.setIcon(status);
        if (effect == battle.effect && effectStrength2 != 0) {
            Timer timer = new Timer(delay, event2 -> {
                effect(effect2, effectStrength2, attacker, defender);
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            Timer timer = new Timer(delay, event2 -> {
                nextMove();
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    public static void statChange(Mokepon user, Mokepon opponent) {
        int statInc = 0;
        String message = "";
        for (int i = 0; i < effect.length(); i++) {
            if (effect.charAt(i) == '+') {
                statInc++;
            } else if (effect.charAt(i) == '-') {
                statInc--;
            }
        }
        if (effect.contains("ATK")) {
            if (effect.contains("O")) {
                message = opponent.name + "'s";
                opponent.attackBoost = opponent.attackBoost + statInc;
            } else {
                message = user.name + "'s";
                user.attackBoost = user.attackBoost + statInc;
            }
            message = message + " attack";
        } else if (effect.contains("DEF")) {
            if (effect.contains("O")) {
                message = opponent.name + "'s";
                opponent.defenceBoost = opponent.defenceBoost + statInc;
            } else {
                message = user.name + "'s";
                user.defenceBoost = user.defenceBoost + statInc;
            }
            message = message + " defence";
        } else if (effect.contains("SPA")) {
            if (effect.contains("O")) {
                message = opponent.name + "'s";
                opponent.specialAttackBoost = opponent.specialAttackBoost + statInc;
            } else {
                message = user.name + "'s";
                user.specialAttackBoost = user.specialAttackBoost + statInc;
            }
            message = message + " special attack";
        } else if (effect.contains("SPD")) {
            if (effect.contains("O")) {
                message = opponent.name + "'s";
                opponent.specialDefenceBoost = opponent.specialDefenceBoost + statInc;
            } else {
                message = user.name + "'s";
                user.specialDefenceBoost = user.specialDefenceBoost + statInc;
            }
            message = message + " special defence";
        } else if (effect.contains("SPE")) {
            if (effect.contains("O")) {
                message = opponent.name + "'s";
                opponent.speedBoost = opponent.speedBoost + statInc;
            } else {
                message = user.name + "'s";
                user.speedBoost = user.speedBoost + statInc;
            }
            message = message + " speed";
        }
        if (opponent.attackBoost > 6) {
            opponent.attackBoost = 6;
        } else if (opponent.attackBoost < -6) {
            opponent.attackBoost = -6;
        } else if (opponent.defenceBoost > 6) {
            opponent.defenceBoost = 6;
        } else if (opponent.defenceBoost < -6) {
            opponent.defenceBoost = -6;
        } else if (opponent.specialAttackBoost > 6) {
            opponent.specialAttackBoost = 6;
        } else if (opponent.specialAttackBoost < -6) {
            opponent.specialAttackBoost = -6;
        } else if (opponent.specialDefenceBoost > 6) {
            opponent.specialDefenceBoost = 6;
        } else if (opponent.specialDefenceBoost < -6) {
            opponent.specialDefenceBoost = -6;
        } else if (opponent.speedBoost > 6) {
            opponent.speedBoost = 6;
        } else if (opponent.speedBoost < -6) {
            opponent.speedBoost = -6;
        } else if (user.attackBoost > 6) {
            user.attackBoost = 6;
        } else if (user.attackBoost < -6) {
            user.attackBoost = -6;
        } else if (user.defenceBoost > 6) {
            user.defenceBoost = 6;
        } else if (user.defenceBoost < -6) {
            user.defenceBoost = -6;
        } else if (user.specialAttackBoost > 6) {
            user.specialAttackBoost = 6;
        } else if (user.specialAttackBoost < -6) {
            user.specialAttackBoost = -6;
        } else if (user.specialDefenceBoost > 6) {
            user.specialDefenceBoost = 6;
        } else if (user.specialDefenceBoost < -6) {
            user.specialDefenceBoost = -6;
        } else if (user.speedBoost > 6) {
            user.speedBoost = 6;
        } else if (user.speedBoost < -6) {
            user.speedBoost = -6;
        }

        if (statInc >= 1) {
            message = message + " rose";
        } else if (statInc == 2) {
            message = message + " sharply";
        } else if (statInc >= 3) {
            message = message + " drastically";
        } else if (statInc == -2) {
            message = message + " harshly";
        } else if (statInc <= -3) {
            message = message + " severely";
        }
        if (statInc <= -1) {
            message = message + " fell";
        }
        message = message + "!";
        Main.animator.text(message);

    }

    public static void drawHealthbar() {
        playerHealthbar.setVisible(true);
        opponentHealthbar.setVisible(true);
        int healthPercentO = (int) (((double) Main.Opponent.inBattle.currentHP / (double) Main.Opponent.inBattle.maxHP)
                * 100);
        if (healthPercentO > 50) {
            opponentHealthbar.setIcon(images.green);
        } else if (healthPercentO <= 25) {
            opponentHealthbar.setIcon(images.red);
        } else {
            opponentHealthbar.setIcon(images.yellow);
        }
        opponentHealthbar.setBounds(77, 98, healthPercentO * 2, 8);

        int healthPercentP = (int) (((double) Main.Player.inBattle.currentHP / (double) Main.Player.inBattle.maxHP)
                * 100);
        if (healthPercentP > 50) {
            playerHealthbar.setIcon(images.green);
        } else if (healthPercentP <= 25) {
            playerHealthbar.setIcon(images.red);
        } else {
            playerHealthbar.setIcon(images.yellow);
        }
        playerHealthbar.setBounds(392, 369, healthPercentP * 2, 8);
    }

    public static void updateHealthbarO(int healthCounter) {
        int healthPercent = (int) (((double) healthCounter / (double) Main.Opponent.inBattle.maxHP) * 100);

        if (healthPercent > 50) {

            opponentHealthbar.setIcon(images.green);

        } else if (healthPercent <= 25) {

            opponentHealthbar.setIcon(images.red);

        } else {

            opponentHealthbar.setIcon(images.yellow);

        }
        opponentHealthbar.setBounds(77, 98, healthPercent * 2, 8);
    }

    public static void updateHealthbarP(int healthCounter) {

        int healthPercent = (int) (((double) healthCounter / (double) Main.Player.inBattle.maxHP) * 100);

        if (healthPercent > 50) {

            playerHealthbar.setIcon(images.green);

        } else if (healthPercent <= 25) {

            playerHealthbar.setIcon(images.red);

        } else {
            playerHealthbar.setIcon(images.yellow);

        }
        playerHealthbar.setBounds(392, 369, healthPercent * 2, 8);
    }

    public static void run() {
        moveBeingUsed();
        if (Main.Player.inBattle.speed >= Main.Opponent.inBattle.speed) {
            Main.animator.text("You got away safely!");
            Timer timer = new Timer(2000, event -> {
                endBattle();
            });
            timer.setRepeats(false);
            timer.start();
        } else {
            double escape = (Main.Player.inBattle.speed * 32) / (Main.Opponent.inBattle.speed / 4.0) + 30 * runAttempts;
            if (ThreadLocalRandom.current().nextInt(0, 256) < escape) {
                Main.animator.text("You got away safely!");
                Timer timer = new Timer(2000, event -> {
                    endBattle();
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                Main.animator.text("You couldn't get away!");
                runAttempts++;
                Timer timer = new Timer(2000, event -> {
                    move.opponentTurn();
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    public static void endBattle() {
        if (Main.Player.inBattle.xp > (int) (Math.pow(Main.Player.inBattle.level, 3)
                - Math.pow(Main.Player.inBattle.level - 1.0, 3))) {
            Main.Player.inBattle.xp = Main.Player.inBattle.xp
                    - (int) (Math.pow(Main.Player.inBattle.level, 3) - Math.pow(Main.Player.inBattle.level - 1.0, 3));
            Main.Player.inBattle.levelup();
        } else {
            delay = 0;
            animations.encounter2();
            Timer timer = new Timer(1250, event -> {

                if (Main.Player.inBattle.evolve()) {
                    delay = 20000;
                }
                screen.text.setText("");
                Timer timer2 = new Timer(delay, event2 -> {
                    screen.animation.setIcon(null);
                    battle.event = "Overworld";
                    screen.player.setVisible(true);
                    screen.background.setVisible(true);
                    movement.moveable = true;
                    screen.frame.remove(screen.box);
                    screen.frame.remove(screen.user);
                    screen.frame.getContentPane().setBackground(Color.BLACK);
                    screen.frame.remove(screen.text);
                    screen.text.setText("");
                });
                timer2.setRepeats(false);
                timer2.start();

                Main.Player.inBattle.resetBoosts();
                Main.Opponent.inBattle.resetBoosts();

                screen.frame.remove(battleText1);
                screen.frame.remove(battleText2);
                screen.frame.remove(battleText3);
                screen.frame.remove(battleText4);
                screen.frame.remove(move1);
                screen.frame.remove(move2);
                screen.frame.remove(move3);
                screen.frame.remove(move4);
                screen.frame.remove(movePower);
                screen.frame.remove(moveAccuracy);
                screen.frame.remove(moveType);
                screen.frame.remove(moveCatagory);
                opponentHealthbar.setVisible(false);
                playerHealthbar.setVisible(false);
                screen.frame.remove(screen.playerName);
                screen.frame.remove(screen.playerLevel);
                screen.frame.remove(screen.opponentName);
                screen.frame.remove(screen.opponentLevel);
                screen.frame.remove(screen.infoBox);
                screen.frame.remove(screen.infoBox2);
                screen.frame.remove(screen.weather);
                screen.frame.remove(screen.opponent);
                screen.frame.remove(platform1);
                screen.frame.remove(platform2);
                screen.frame.remove(Ostatus);
                screen.frame.remove(Pstatus);

                bag.ball.setVisible(false);

                screen.playerName.setText("");
                screen.playerLevel.setText("");
                screen.opponentName.setText("");
                screen.opponentLevel.setText("");
                Main.Opponent = new team();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

}
