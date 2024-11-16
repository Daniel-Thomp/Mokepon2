import java.util.*;
import javax.swing.Timer;


public class Mokepon {
    String name = "--";

    int maxHP; 
    int attack;
    int specialAttack;
    int defence;
    int specialDefence;
    int speed;
    
    int currentHP = 0;
    int level;
    int xp;

    int attackBoost = 0;
    int specialAttackBoost = 0;
    int defenceBoost = 0;
    int specialDefenceBoost = 0;
    int speedBoost = 0;
    
    String Move1;
    String Move2;
    String Move3;
    String Move4;
    int type1;
    int type2 = -1;

    String move2learn;
    int EvolutionLevel;

    String status = "";
    boolean flinched = false;
    int tailwind = 0;
    public Mokepon() {      
    }

    public void assign(String name,int level){
        this.level = level;
        this.name = name;
        calcStats();
        getMoves();
        currentHP = maxHP;
        status = "";
        flinched = false;
    }
    public void calcStats(){
        Scanner fileScanner = Main.fileReader("files//Stats.txt");
        
        while (fileScanner.nextLine().contains(name) != true) {}

        int BaseHP = fileScanner.nextInt();
        int BaseATK = fileScanner.nextInt();
        int BaseSpecATK = fileScanner.nextInt();
        int BaseDEF = fileScanner.nextInt();
        int BaseSpecDEF = fileScanner.nextInt();
        int BaseSpeed = fileScanner.nextInt();

        maxHP = (int)((2*BaseHP*level)/100+level+10);
        
        attack = (int)((2*BaseATK*level)/100+5);
        specialAttack = (int)((2*BaseSpecATK*level)/100+5);
        defence = (int)((2*BaseDEF*level)/100+5);
        specialDefence = (int)((2*BaseSpecDEF*level)/100+5);
        speed = (int)((2*BaseSpeed*level)/100+5);

        while (fileScanner.nextLine().contains("Type:") != true) {}
        type1 = fileScanner.nextInt();
        try {
            type2 = fileScanner.nextInt();
        } catch (Exception e) {}

        String currentLine = fileScanner.nextLine();
        boolean found = false;
        while (found == false) {
            if (currentLine.contains("Evolution")) {
                EvolutionLevel = fileScanner.nextInt();
                found = true;
            } else if (currentLine.contains("Abilities")) {
                found = true;
                EvolutionLevel = 100;
            } else {
                currentLine = fileScanner.nextLine();    
            }
        }
        
        fileScanner.close();
    }
    public void getMoves(){
        Scanner fileScanner = Main.fileReader("files//Stats.txt");
        while (fileScanner.nextLine().contains(name) != true) {}
        
        int counter = -1;
        int moveCounter = 0;
        int lastLocation = 0;
        while (fileScanner.nextLine().contains("Moves:") != true) {}
        
        String currentLine = fileScanner.nextLine();
        boolean found = false;
        boolean finished = false;

        while (found == false && finished == false) {
            if (currentLine.contains("LVL")) {
                String lvlRemoved = currentLine.replaceAll("L|V|:","");
                if (Integer.parseInt(lvlRemoved.strip()) <= level) {
                    lastLocation = counter;
                    currentLine = fileScanner.nextLine();
                    counter++;
                } else {
                    found = true;
                }
            } else if (currentLine.contains("Abilities")) {
                finished = true;
            } else {
                currentLine = fileScanner.nextLine();
                counter ++;
                moveCounter ++;
            }
        }
        
        
        
        Scanner fileScanner2 = Main.fileReader("files//Stats.txt");
        while (fileScanner2.next().contains(name) != true) {}
        while (fileScanner2.nextLine().contains("Moves:") != true) {}
        if (finished) {
            for (int i = 0; i < lastLocation-4; i++) {
                fileScanner2.nextLine();
            }
            Move1 = fileScanner2.nextLine();
            fileScanner2.nextLine();
            Move2 = fileScanner2.nextLine();
            fileScanner2.nextLine();
            Move3 = fileScanner2.nextLine();
            fileScanner2.nextLine();
            Move4 = fileScanner2.nextLine();
        } else {
            if (moveCounter < 4) {               
                fileScanner2.nextLine();
                Move1 = fileScanner2.nextLine();
                Move2 = fileScanner2.nextLine();
                if (moveCounter == 3) {
                    fileScanner2.nextLine();
                    Move3 = fileScanner2.nextLine();
                }
            } else if (moveCounter == 4) {
                fileScanner2.nextLine();
                Move1 = fileScanner2.nextLine();
                Move2 = fileScanner2.nextLine();
                fileScanner2.nextLine();
                Move3 = fileScanner2.nextLine();
                fileScanner2.nextLine();
                Move4 = fileScanner2.nextLine();
            } else {
                
                for (int i = 0; i < counter-6; i++) {
                    fileScanner2.nextLine();
                }
                Move1 = fileScanner2.nextLine();
                fileScanner2.nextLine();
                Move2 = fileScanner2.nextLine();
                fileScanner2.nextLine();
                Move3 = fileScanner2.nextLine();
                fileScanner2.nextLine();
                Move4 = fileScanner2.nextLine();
            }
        }
        fileScanner.close();
        fileScanner2.close();
        if (Move3 == null) {
            Move3 = "--";
        }
        if (Move4 == null) {
            Move4 = "--";
        }
           
    }

    public void levelup(){
        level++;
        screen.playerLevel.setText("L:" + level);
        Main.animator.text(name + " reached level " + level + "!");
        Scanner fileScanner = Main.fileReader("files//stats.txt");
        boolean searching = true;
        boolean found = false;
        while (fileScanner.nextLine().contains(name) != true) {}
        while (fileScanner.nextLine().contains("Moves:") != true) {}
        while (searching && found == false) {
            String line = fileScanner.nextLine();
            if (line.contains("LVL" + level + ":")) {
                found = true;
            } else if (line.contains("Abilities")) {
                searching = false;
            }
        }
        
        if (found) {
            
            Timer timer = new Timer(2000, event -> {
                move2learn = fileScanner.nextLine();
                if (Move3 == "--") {
                    Move3 = move2learn;
                    Main.animator.text(name + " learned " + move2learn);

                    Timer timer2 = new Timer(2000, event2 -> {
                        battle.endBattle();
                    });
                    timer2.setRepeats(false);
                    timer2.start();

                } else if (Move4 == "--") {
                    Move4 = move2learn;   
                    Main.animator.text(name + " learned " + move2learn);
                    Timer timer2 = new Timer(2000, event2 -> {
                        battle.endBattle();
                    });
                    timer2.setRepeats(false);
                    timer2.start();
                } else {
                    String message = name + " wants to learn " + move2learn.toUpperCase() + "\nwhich move should be replaced?";
                    Main.animator.text(message);
                    
                    Timer timer2 = new Timer(3000, event2 -> {
                        screen.text.setText("");
                        learnMove();
                    });
                    timer2.setRepeats(false);
                    timer2.start();  
                }

                fileScanner.close();
                calcStats();
            });
            timer.setRepeats(false);
            timer.start();
            
        } else {
            Timer timer = new Timer(2000, event -> {
                battle.endBattle();
                fileScanner.close();
                calcStats();
            });
            timer.setRepeats(false);
            timer.start();
        }

    }

    public void learnMove(){
        battle.event = "LearnMove";
        battle.cursor = 1;
        battle.cursorLabel.setVisible(true);

        battle.move1.setVisible(true);
        battle.move2.setVisible(true);
        battle.move3.setVisible(true);
        battle.move4.setVisible(true);

        screen.box.setIcon(images.moveBox);

        battle.move1.setText(Move1);
        battle.move2.setText(Move2);
        battle.move3.setText(Move3);
        battle.move4.setText(Move4);

        battle.moveType.setVisible(true);
        battle.movePower.setVisible(true);
        battle.moveAccuracy.setVisible(true);
        battle.moveCatagory.setVisible(true);    

        battle.cursorLabel.setBounds(155,396,100, 100);
        move.useMove(Move1, "name");
     
    }
    public void replaceMove(int choice){
        battle.moveBeingUsed();
        if (choice == 1) {
            Main.animator.text(name + " forgot " + Move1);
            Move1 = move2learn;
        }   else if (choice == 2) {
            Main.animator.text(name + " forgot " + Move2);
            Move2 = move2learn;
        } else if (choice == 3) {
            Main.animator.text(name + " forgot " + Move3);
            Move3 = move2learn;
        } else if (choice == 4){
            Main.animator.text(name + " forgot " + Move4);
            Move4 = move2learn;
        } else {
            Main.animator.text(name + " gave up on learning " + move2learn);
        }
        Timer timer = new Timer(2000, event -> {
            if (choice <= 4) {
                Main.animator.text(name + " learned " + move2learn.toUpperCase() + "!");
                Timer timer2 = new Timer(2000, event2 -> {
                    battle.endBattle();
                });
                timer2.setRepeats(false);
                timer2.start();
            } else {
                battle.endBattle();
            }
        });
        timer.setRepeats(false);
        timer.start();
        
    }
    public void gainXp(double opponentLevel){
        double amount = ((200*opponentLevel)/5)*Math.pow((2*opponentLevel+10)/(opponentLevel+(double)level+10), 2.5)+1;
        xp = xp + (int)amount;
        Main.animator.text(name + " gained " + (int)amount + "xp points");

        Timer timer = new Timer(2000, event -> {
            if (xp > (int)(Math.pow(level, 3)-Math.pow(level-1.0, 3))) {
                xp = xp-(int)(Math.pow(level, 3)-Math.pow(level-1.0, 3));
                levelup();
            } else {
                battle.endBattle();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    public boolean evolve(){
        boolean evolving = false;
        if (level >= EvolutionLevel) {
            evolving = true;
            String New;
            Scanner fileScanner = Main.fileReader("files//Stats.txt");     
            while (fileScanner.nextLine().contains(name) != true) {}
            while (fileScanner.nextLine().contains("Evolution:") != true) {}
            fileScanner.nextLine();fileScanner.nextLine();
            New = fileScanner.nextLine();
            animations.evolve(name, New);
            name = New;
            assign(name, level);
            
        }
        return evolving;
    }
    public void resetBoosts(){
        attackBoost = 0;
        specialAttackBoost = 0;
        defenceBoost = 0;
        specialDefenceBoost = 0;
        speedBoost = 0;
    }
    
    
    
    
    
    
    
}