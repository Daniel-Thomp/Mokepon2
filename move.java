import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class move {
    static boolean opponentMoving = false;
    static boolean playerMoving = false;
    static int Upower = 0;
    static int Uaccuracy = 0;
    static int Utype = 0;
    static String Ucategory = null;
    static String Umove = null;
    static String Ueffect = null;
    static int UeffectStrength = 0;
    static String Ueffect2 = null;
    static int UeffectStrength2 = 0;
    static int power = 0;
    static int accuracy = 0;
    static int type = 0;
    static String category = null;
    static String effect = null;
    static int effectStrength = 0;
    static String effect2 = null;
    static int effectStrength2 = 0;
    static int delay;
    static boolean playerFirst;

    public static void useMove(String move, String user) {
        type = -1;
        power = 0;
        accuracy = 0;
        category = null;
        effect = null;
        effectStrength = 0;
        effect2 = null;
        effectStrength2 = 0;
        delay = 2000;
        category = "Status";

        String movefn = move.strip();
        movefn = movefn.replace(" ", "_");
        movefn = movefn.replaceAll("[-+']", "");

        try {
            Class<?> clazz = move.class;
            Method method1 = clazz.getDeclaredMethod(movefn);
            method1.invoke(null);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (effectStrength2 == 0) {
            delay = 0;
        }

        if (user == "Opponent") {
            if (move.contains("--")) {
                opponentTurn();
            } else {
                battle.moveBeingUsed();
                battle.calcDamage(power, type, user, category, effect, effectStrength, effect2, effectStrength2, move,
                        accuracy);
            }

        } else if (user == "Player") {

            if (Main.Player.inBattle.speed * battle.statBoosts(Main.Player.inBattle,
                    "speed") > Main.Opponent.inBattle.speed * battle.statBoosts(Main.Opponent.inBattle, "speed")
                    || (Main.Player.inBattle.speed
                            * battle.statBoosts(Main.Player.inBattle, "speed") == Main.Opponent.inBattle.speed
                                    * battle.statBoosts(Main.Opponent.inBattle, "speed")
                            && ThreadLocalRandom.current().nextInt(1, 3) == 1)) {
                playerMoving = true;
                playerFirst = true;
                battle.moveBeingUsed();
                battle.calcDamage(power, type, user, category, effect, effectStrength, effect2, effectStrength2, move,
                        accuracy);

            } else {
                playerFirst = false;
                opponentMoving = true;
                Upower = power;
                Utype = type;
                Ucategory = category;
                Uaccuracy = accuracy;
                Umove = move;
                Ueffect = effect;
                Ueffect2 = effect2;
                UeffectStrength = effectStrength;
                UeffectStrength2 = effectStrength2;
                opponentTurn();
            }
        }
        if (user == "name") {
            if (power == 0) {
                battle.movePower.setText("Power: --");
            } else {
                battle.movePower.setText("Power: " + String.valueOf(power));
            }
            if (accuracy == 0) {
                battle.moveAccuracy.setText("Accuracy: --");
            } else {
                battle.moveAccuracy.setText("Accuracy: " + String.valueOf(accuracy));
            }
            if (move.contains("--")) {
                inputs.exists = false;
            } else {
                inputs.exists = true;
            }
            ImageIcon cat = new ImageIcon("battle images//" + category + ".png");
            battle.moveCatagory.setIcon(cat);
            switch (type) {
                case 0:
                    battle.moveType.setText("Type: Normal");
                    break;
                case 1:
                    battle.moveType.setText("Type: Fire");
                    break;
                case 2:
                    battle.moveType.setText("Type: Water");
                    break;
                case 3:
                    battle.moveType.setText("Type: Grass");
                    break;
                case 4:
                    battle.moveType.setText("Type: Electric");
                    break;
                case 5:
                    battle.moveType.setText("Type: Ice");
                    break;
                case 6:
                    battle.moveType.setText("Type: Fighting");
                    break;
                case 7:
                    battle.moveType.setText("Type: Poison");
                    break;
                case 8:
                    battle.moveType.setText("Type: Ground");
                    break;
                case 9:
                    battle.moveType.setText("Type: Flying");
                    break;
                case 10:
                    battle.moveType.setText("Type: Psychic");
                    break;
                case 11:
                    battle.moveType.setText("Type: Bug");
                    break;
                case 12:
                    battle.moveType.setText("Type: Rock");
                    break;
                case 13:
                    battle.moveType.setText("Type: Ghost");
                    break;
                case 14:
                    battle.moveType.setText("Type: Dragon");
                    break;
                case 15:
                    battle.moveType.setText("Type: Dark");
                    break;
                case 16:
                    battle.moveType.setText("Type: Steel");
                    break;
                case 17:
                    battle.moveType.setText("Type: Fairy");
                    break;
                case -1:
                    battle.moveType.setText("Type: --");
                    break;

            }

        }
    }

    public static void userTurn() {
        opponentMoving = false;
        if (UeffectStrength2 == 0) {
            delay = 0;
        }
        battle.moveBeingUsed();
        battle.calcDamage(Upower, Utype, "Player", Ucategory, Ueffect, UeffectStrength, Ueffect2, UeffectStrength2,
                Umove, Uaccuracy);
    }

    public static void opponentTurn() {

        playerMoving = false;
        int choice = ThreadLocalRandom.current().nextInt(1, 5);
        if (choice == 1) {
            useMove(Main.Opponent.inBattle.Move1, "Opponent");
        } else if (choice == 2) {
            useMove(Main.Opponent.inBattle.Move2, "Opponent");
        } else if (choice == 3) {
            useMove(Main.Opponent.inBattle.Move3, "Opponent");
        } else {
            useMove(Main.Opponent.inBattle.Move4, "Opponent");
        }
    }

    public static void Gust() {
        power = 40;
        accuracy = 100;
        category = "Special";
        type = 9;
    }

    public static void Water_Gun() {
        power = 40;
        accuracy = 100;
        category = "Special";
        type = 2;
    }

    public static void Whirlwind() {
        // end battle
        type = 9;
    }

    public static void Rain_Dance() {
        effect = "Rain";
        effectStrength = 5;
        type = 2;
    }

    public static void Air_Cutter() {
        power = 60;
        accuracy = 95;
        category = "Special";
        type = 9;
    }

    public static void Aqua_Ring() {
        // heals each turn
        type = 2;
    }

    public static void Hurricane() {
        power = 110;
        accuracy = 70;
        category = "Special";
        type = 9;
        effect = "Confuse";
        effectStrength = 30;
    }

    public static void Surf() {
        power = 90;
        accuracy = 100;
        category = "Special";
        type = 2;
    }

    public static void Tailwind() {
        effect = "Tailwind";
        type = 9;
    }

    public static void Hydro_Pump() {
        power = 110;
        accuracy = 80;
        category = "Special";
        type = 2;
    }

    public static void Roost() {
        // heal
        type = 9;
    }

    public static void Twister() {
        power = 40;
        accuracy = 100;
        category = "Special";
        type = 14;
    }

    public static void Storm_Surge() {
        // chatgpt
    }

    public static void Thunder() {
        power = 110;
        accuracy = 70;
        category = "Special";
        type = 4;
        effect = "Para";
        effectStrength = 30;
    }

    public static void Tempest_Fury() {
        // chatgpt
    }

    public static void Ice_Fang() {
        power = 65;
        accuracy = 95;
        category = "Physical";
        type = 5;
        effect = "Freeze";
        effectStrength = 10;
        effect2 = "Flinch";
        effectStrength2 = 10;
    }

    public static void Ember() {
        power = 40;
        accuracy = 100;
        category = "Special";
        type = 1;
        effect = "Burn";
        effectStrength = 10;
    }

    public static void Roar() {
        // end battle
        type = 0;
    }

    public static void Flame_Charge() {
        power = 50;
        accuracy = 100;
        category = "Physical";
        type = 1;
        effect = "+SPE";
    }

    public static void Bite() {
        power = 60;
        accuracy = 100;
        category = "Physical";
        type = 15;
        effect = "Flinch";
        effectStrength = 30;
    }

    public static void Icicle_Crash() {
        power = 85;
        accuracy = 90;
        category = "Physical";
        type = 5;
        effect = "Flinch";
        effectStrength = 30;
    }

    public static void Fire_Fang() {
        power = 65;
        accuracy = 95;
        category = "Physical";
        type = 1;
        effect = "Burn";
        effectStrength = 10;
        effect2 = "Flinch";
        effectStrength2 = 10;
    }

    public static void Avalanche() {
        power = 60;
        accuracy = 100;
        category = "Physical";
        type = 5;
        // double dmg if hit first
    }

    public static void Crunch() {
        power = 80;
        accuracy = 100;
        category = "Physical";
        type = 15;
        effect = "O-DEF";
        effectStrength = 20;
    }

    public static void Flare_Blitz() {
        power = 120;
        accuracy = 100;
        category = "Physical";
        type = 1;
        effect = "Burn";
        effectStrength = 10;
        effect2 = "Recoil";
        effectStrength2 = 3;
    }

    public static void Blizzard() {
        power = 110;
        accuracy = 70;
        category = "Special";
        type = 5;
        effect = "Freeze";
        effectStrength = 10;
    }

    public static void Fire_Blast() {
        power = 110;
        accuracy = 85;
        category = "Special";
        type = 1;
        effect = "Burn";
        effectStrength = 10;
    }

    public static void Frostburn() {
        // chatgpt
    }

    public static void Earthquake() {
        power = 100;
        accuracy = 100;
        category = "Physical";
        type = 8;
    }

    public static void Glacial_Roar() {
        // chatgpt
    }

    public static void Dragon_Breath() {
        power = 60;
        accuracy = 100;
        category = "Special";
        type = 14;
        effect = "Para";
        effectStrength = 10;
    }

    public static void Leafage() {
        power = 40;
        accuracy = 100;
        category = "Physical";
        type = 3;
    }

    public static void Growth() {
        effect = "+ATK";
        effect2 = "+SPA";
    }

    public static void Vine_Whip() {
        power = 45;
        accuracy = 100;
        category = "Physical";
        type = 3;
    }

    public static void Dragon_Tail() {
        power = 60;
        accuracy = 90;
        category = "Physical";
        type = 14;
        // end battle
    }

    public static void Razor_Leaf() {
        power = 55;
        accuracy = 95;
        category = "Physical";
        type = 3;
    }

    public static void Leech_Seed() {
        // drain health each turn
        type = 3;
    }

    public static void Dragon_Claw() {
        power = 80;
        accuracy = 100;
        category = "Physical";
        type = 14;
    }

    public static void Synthesis() {
        // heal
        type = 3;
    }

    public static void Seed_Bomb() {
        power = 80;
        accuracy = 100;
        category = "Physical";
        type = 3;
    }

    public static void Dragon_Pulse() {
        power = 85;
        accuracy = 100;
        category = "Special";
        type = 14;
    }

    public static void Solar_Beam() {
        power = 120;
        accuracy = 100;
        category = "Special";
        type = 3;
        // 2 turn
    }

    public static void Wood_Hammer() {
        power = 120;
        accuracy = 100;
        category = "Physical";
        type = 3;
        effect = "Recoil";
        effectStrength = 3;
    }

    public static void Natures_Wrath() {
        // chatgpt
    }

    public static void Leer() {
        accuracy = 100;
        type = 0;
        effect = "O-DEF";
    }

    public static void Smog() {
        power = 30;
        accuracy = 70;
        type = 7;
        category = "Special";
        effect = "Poison";
        effectStrength = 40;
    }

    public static void Wing_Attack() {
        power = 60;
        accuracy = 100;
        type = 9;
        category = "Physical";
    }

    public static void Fire_Spin() {
        power = 35;
        accuracy = 85;
        type = 1;
        category = "Special";
        effect = "fire spin";
    }

    public static void Night_Slash() {
        power = 70;
        accuracy = 100;
        type = 15;
        category = "Physical";
    }

    public static void Dark_Pulse() {
        power = 80;
        accuracy = 100;
        type = 15;
        category = "Special";
        effect = "Flinch";
        effectStrength = 20;
    }

    public static void Inferno() {
        power = 100;
        accuracy = 50;
        type = 1;
        category = "Special";
        effect = "Burn";
    }

    public static void Iron_Tail() {
        power = 100;
        accuracy = 75;
        type = 16;
        category = "Physical";
        effect = "O-DEF";
        effectStrength = 30;
    }

    public static void Overheat() {
        power = 130;
        accuracy = 90;
        type = 1;
        category = "Special";
        effect = "--SPA";
    }

    public static void WillOWisp() {
        accuracy = 85;
        type = 1;
        effect = "Burn";
    }

}
