import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class inputs implements KeyListener{
    static boolean exists = true;
    static boolean doubleinput;
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

        @Override
    public void keyPressed(KeyEvent e) {
        doubleinput = true;
        if (movement.moveable) {
            
            if (e.getKeyCode() == KeyEvent.VK_W) {
                
                screen.player.setIcon(images.player_back);
                
                if (screen.leveldata[movement.playerx][movement.playery-1] != 1 && screen.leveldata[movement.playerx][movement.playery-1] != -1) {
                    movement.moveable = false;
                    movement.playery--;
                    movement.walk("up");
                    
                }
                
                
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {
                
                screen.player.setIcon(images.player_front);
                if (screen.leveldata[movement.playerx][movement.playery+1] != 1) {                
                    movement.moveable = false;
                    movement.playery++;
                    movement.walk("down");
                }

            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                
                screen.player.setIcon(images.player_left);
                if (screen.leveldata[movement.playerx-1][movement.playery] != 1) {
                    movement.moveable = false;
                    movement.playerx--;
                    movement.walk("left");               
                }
                

            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                
                screen.player.setIcon(images.player_right);
                if (screen.leveldata[movement.playerx+1][movement.playery] != 1) {
                    movement.moveable = false;
                    movement.playerx++;
                    movement.walk("right");
                }
                
                
            }
        }    
        if (battle.event == "bag") {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (battle.cursor>0) {
                    battle.cursor--;    
                } else if (bag.scroll != 0){
                    bag.scroll--;
                    bag.loadBag();
                }
                battle.cursorLabel.setBounds(180,15 + 60*battle.cursor,100, 100);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (battle.cursor<4) {
                    battle.cursor++;    
                } else if (bag.scroll+5<Main.Player.bag.size()){
                    bag.scroll++;
                    bag.loadBag();
                }
                battle.cursorLabel.setBounds(180,15 + 60*battle.cursor,100, 100);
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                bag.closeBag();
                battle.action();
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                bag.useItem();
            }
        }
        if (battle.event == "Switch") {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                battle.action();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (battle.cursor>1) {
                    battle.cursor--;    
                }else{
                    battle.cursor = 6;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (battle.cursor<6) {
                    battle.cursor++;    
                }else{
                    battle.cursor = 1;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_DOWN) {
                
                if (battle.cursor == 1) {
                    battle.cursorLabel.setBounds(355,180,100, 100);
                } else if (battle.cursor == 2) {
                    battle.cursorLabel.setBounds(355,210,100, 100);
                } else if (battle.cursor == 3) {
                    battle.cursorLabel.setBounds(355,240,100, 100);
                } else if (battle.cursor == 4) {
                    battle.cursorLabel.setBounds(355,270,100, 100);
                } else if (battle.cursor == 5) {
                    battle.cursorLabel.setBounds(355,300,100, 100);
                } else if (battle.cursor == 6) {
                    battle.cursorLabel.setBounds(355,330,100, 100);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (Main.Player.monsters[battle.cursor].name != "--") {
                    switchOut.switchMenu();
                    doubleinput = false;
                }           
            }      
        }
        if (battle.event == "switchMenu") {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                switchOut.player();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (switchOut.cursor == 1) {
                    switchOut.cursor = 2;
                } else {
                    switchOut.cursor = 1;
                }
                if (switchOut.cursor == 1) {
                    battle.cursorLabel.setBounds(105,180,100, 100);
                } else if (switchOut.cursor == 2) {
                    battle.cursorLabel.setBounds(105,210,100, 100);
                }
                    
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE && doubleinput) {
                
                if (switchOut.cursor == 1) {
                    if (Main.Player.monsters[battle.cursor] == Main.Player.inBattle) {
                        battle.event = "";
                        Main.animator.text(Main.Player.inBattle.name + " is already in battle");
                        Timer timer = new Timer(1500, event -> {
                            battle.event = "switchMenu";
                            screen.text.setText("");
                        });
                        timer.start();
                        timer.setRepeats(false);
                    } else {
                        battle.event = "";
                        switchOut.swap(Main.Player.monsters[battle.cursor]);
                    }
                }
            }  
        }
        if (battle.event == "MoveSelection") {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                battle.action();
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (battle.cursor>1) {
                    battle.cursor--;    
                }else{
                    battle.cursor = 4;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (battle.cursor<4) {
                    battle.cursor++;    
                }else{
                    battle.cursor = 1;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_DOWN) {
                
                if (battle.cursor == 1) {
                    move.useMove(Main.Player.inBattle.Move1, "name");
                    battle.cursorLabel.setBounds(155,396,100, 100);
                } else if (battle.cursor == 2) {
                    move.useMove(Main.Player.inBattle.Move2, "name");
                    battle.cursorLabel.setBounds(155,426,100, 100);
                } else if (battle.cursor == 3) {
                    move.useMove(Main.Player.inBattle.Move3, "name");
                    battle.cursorLabel.setBounds(155,456,100, 100);
                } else {
                    move.useMove(Main.Player.inBattle.Move4, "name");
                    battle.cursorLabel.setBounds(155,486,100, 100);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE && exists == true) {
                if (battle.cursor == 1) {   
                    move.useMove(Main.Player.inBattle.Move1, "Player");
                } else if (battle.cursor == 2) {
                    move.useMove(Main.Player.inBattle.Move2, "Player");
                } else if (battle.cursor == 3) {
                    move.useMove(Main.Player.inBattle.Move3, "Player");
                } else if (battle.cursor == 4) {
                    move.useMove(Main.Player.inBattle.Move4, "Player");
                }
                
            }      
        }
        if (battle.event == "LearnMove") {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                Main.Player.inBattle.replaceMove(5);
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (battle.cursor>1) {
                    battle.cursor--;    
                }else{
                    battle.cursor = 4;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (battle.cursor<4) {
                    battle.cursor++;    
                }else{
                    battle.cursor = 1;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_DOWN) {
                
                if (battle.cursor == 1) {
                    move.useMove(Main.Player.inBattle.Move1, "name");
                    battle.cursorLabel.setBounds(155,396,100, 100);
                } else if (battle.cursor == 2) {
                    move.useMove(Main.Player.inBattle.Move2, "name");
                    battle.cursorLabel.setBounds(155,426,100, 100);
                } else if (battle.cursor == 3) {
                    move.useMove(Main.Player.inBattle.Move3, "name");
                    battle.cursorLabel.setBounds(155,456,100, 100);
                } else {
                    move.useMove(Main.Player.inBattle.Move4, "name");
                    battle.cursorLabel.setBounds(155,486,100, 100);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                Main.Player.inBattle.replaceMove(battle.cursor);
            }      
        }
        if (battle.event == "Action") {
            if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (battle.cursor>2) {
                    battle.cursor = battle.cursor-2;    
                }else if (battle.cursor<3) {
                    battle.cursor = battle.cursor+2;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (battle.cursor == 2||battle.cursor == 4) {
                    battle.cursor--;    
                } else {
                    battle.cursor++;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (battle.cursor == 1||battle.cursor == 3) {
                    battle.cursor++;    
                } else {
                    battle.cursor--;
                }
                
            }
            if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_DOWN ||e.getKeyCode() == KeyEvent.VK_LEFT ||e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (battle.cursor == 1) {
                    battle.cursorLabel.setBounds(295,396,100, 100);
                } else if (battle.cursor == 2) {
                    battle.cursorLabel.setBounds(435,396,100, 100);
                } else if (battle.cursor == 3) {
                    battle.cursorLabel.setBounds(295,466,100, 100);
                } else {
                    battle.cursorLabel.setBounds(435,466,100, 100);
                }
            }            
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                if (battle.cursor == 1) {
                    battle.moveSelection();
                } else if (battle.cursor == 2) {
                    switchOut.player();
                } else if (battle.cursor == 3) {
                    bag.openBag();
                } else if (battle.cursor == 4) {
                    battle.run();
                }
            }
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
