import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class animations {
    static int counter = 360;
    static ImageIcon img1;
    static ImageIcon img2;
    static boolean grow = false;
    static double loop;
    static int cycle;
    static ImageIcon O;
    static ImageIcon N;
    static String Old;
    static String New;
    static int shakes;
    static ScheduledExecutorService executor;
    static ScheduledExecutorService executor2;
    static boolean captured;


    public static void encounter() {
        int speed = 200;
        Timer timer2 = new Timer(speed, event2 -> {
            screen.animation.setIcon(images.encounter9);
            Timer timer3 = new Timer(speed, event3 -> {
                screen.animation.setIcon(null);
                Timer timer4 = new Timer(speed, event4 -> {
                    screen.animation.setIcon(images.encounter9);
                    Timer timer5 = new Timer(speed, event5 -> {
                        screen.animation.setIcon(null);
                        Timer timer6 = new Timer(speed, event6 -> {
                            screen.animation.setIcon(images.encounter9);
                            Timer timer7 = new Timer(speed, event7 -> {
                                screen.animation.setIcon(null);
                                Timer timer8 = new Timer(speed, event8 -> {
                                    screen.animation.setIcon(images.encounter1);
                                    encounter2();
                                });
                                timer8.setRepeats(false);
                                timer8.start();
                            });
                            timer7.setRepeats(false);
                            timer7.start();
                        });
                        timer6.setRepeats(false);
                        timer6.start();
                    });
                    timer5.setRepeats(false);
                    timer5.start();
                });
                timer4.setRepeats(false);
                timer4.start();
            });
            timer3.setRepeats(false);
            timer3.start();
        });
        timer2.setRepeats(false);
        timer2.start();

    }

    public static void encounter2() {
        int speed2 = 100;

        Timer timer = new Timer(speed2, event -> {
            screen.animation.setIcon(images.encounter2);
            Timer timer2 = new Timer(speed2, event2 -> {
                screen.animation.setIcon(images.encounter3);
                Timer timer3 = new Timer(speed2, event3 -> {
                    screen.animation.setIcon(images.encounter4);
                    Timer timer4 = new Timer(speed2, event4 -> {
                        screen.animation.setIcon(images.encounter5);
                        Timer timer5 = new Timer(speed2, event5 -> {
                            screen.animation.setIcon(images.encounter6);
                            Timer timer6 = new Timer(speed2, event6 -> {
                                screen.animation.setIcon(images.encounter7);
                                Timer timer7 = new Timer(speed2, event7 -> {
                                    screen.animation.setIcon(images.encounter8);
                                    Timer timer8 = new Timer(speed2, event8 -> {
                                        screen.animation.setIcon(images.encounter9);
                                    });
                                    timer8.setRepeats(false);
                                    timer8.start();
                                });
                                timer7.setRepeats(false);
                                timer7.start();
                            });
                            timer6.setRepeats(false);
                            timer6.start();
                        });
                        timer5.setRepeats(false);
                        timer5.start();
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

    static Runnable openingAnimation = new Runnable() {

        public void run() {
            if (counter == 0) {
                Timer timer = new Timer(200, event -> {
                    textAnimator animator = new textAnimator();
                    animator.text("A wild " + Main.Opponent.inBattle.name + " appeared!");
                    screen.playerName.setText(Main.Player.inBattle.name);
                    screen.playerLevel.setText("L:" + Main.Player.inBattle.level);
                    screen.opponentName.setText(Main.Opponent.inBattle.name);
                    screen.opponentLevel.setText("L:" + Main.Opponent.inBattle.level);
                    screen.infoBox.setVisible(true);
                    screen.infoBox2.setVisible(true);
                    battle.Ostatus.setVisible(true);
                    battle.Pstatus.setVisible(true);
                    battle.drawHealthbar();
                    ImageIcon P = new ImageIcon("battle images//monsters//" + Main.Player.inBattle.name + "_back.png");
                    ImageIcon O = new ImageIcon(
                            "battle images//monsters//" + Main.Opponent.inBattle.name + "_front.png");
                    screen.user.setIcon(P);
                    screen.opponent.setIcon(O);
                    battle.platform1.setVisible(true);
                    battle.platform2.setVisible(true);
                    Timer timer2 = new Timer(2000, event2 -> {
                        battle.action();
                    });
                    timer2.setRepeats(false);
                    timer2.start();

                });
                timer.setRepeats(false);
                timer.start();
                battle.executor.close();

            }
            screen.opponent.setBounds(360 - counter, 0, 256, 256);
            screen.user.setBounds(0 + counter, 185, 256, 256);
            counter--;

        }
    };

    public static ImageIcon grey(Image Image) {
        BufferedImage greyImage = null;
        try {
            BufferedImage colorImage = convertToBufferedImage(Image);

            greyImage = new BufferedImage(
                    colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            for (int y = 0; y < colorImage.getHeight(); y++) {
                for (int x = 0; x < colorImage.getWidth(); x++) {
                    int rgba = colorImage.getRGB(x, y);
                    int alpha = (rgba >> 24) & 0xFF;
                    int red = (rgba >> 16) & 0xFF;
                    int green = (rgba >> 8) & 0xFF;
                    int blue = rgba & 0xFF;
                    int grey = (red + green + blue) / 3;
                    int newRgba = (alpha << 24) | (grey << 16) | (grey << 8) | grey;
                    greyImage.setRGB(x, y, newRgba);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageIcon image = new ImageIcon(greyImage);
        return image;
    }

    public static void evolve(String Old, String New) {
        animations.Old = Old;
        animations.New = New;
        int speed = 250;
        O = new ImageIcon("battle images//monsters//" + Old + "_front.png");
        N = new ImageIcon("battle images//monsters//" + New + "_front.png");
        BufferedImage bufferedImage = convertToBufferedImage(O.getImage());
        O = new ImageIcon(centerImageContent(bufferedImage));
        bufferedImage = convertToBufferedImage(N.getImage());
        N = new ImageIcon(centerImageContent(bufferedImage));
        screen.user.setIcon(O);
        screen.user.setLocation(199, 50);
        screen.user.setHorizontalAlignment(JLabel.CENTER);
        screen.user.setVerticalAlignment(JLabel.CENTER);
        screen.animation.setIcon(null);

        Timer timer5 = new Timer(speed, event5 -> {
            Main.animator.text(Old + " is evolving!");
            Timer timer6 = new Timer(1750, event6 -> {
                screen.user.setIcon(grey(O.getImage()));
                Timer timer4 = new Timer(250, event4 -> {

                    BufferedImage bufferedImage2 = convertToBufferedImage(O.getImage());
                    BufferedImage whiteImage = makeImageWhite(bufferedImage2);

                    img1 = new ImageIcon(whiteImage);
                    screen.user.setIcon(img1);
                    BufferedImage bufferedImage3 = convertToBufferedImage(N.getImage());
                    BufferedImage whiteImage2 = makeImageWhite(bufferedImage3);
                    img2 = new ImageIcon(whiteImage2);
                    counter = 511;
                    loop = 1;
                    cycle = 1;

                    Timer timer3 = new Timer(1000, event -> {
                        battle.executor = Executors.newScheduledThreadPool(1);
                        battle.executor.scheduleAtFixedRate(shrink, 0, (int) ((1.0 / 256.0) * 1000),
                                TimeUnit.MILLISECONDS);
                    });
                    timer3.setRepeats(false);
                    timer3.start();

                });
                timer4.setRepeats(false);
                timer4.start();
            });
            timer6.setRepeats(false);
            timer6.start();
        });
        Timer timer = new Timer(speed, event -> {
            screen.frame.getContentPane().setBackground(Color.LIGHT_GRAY);
            Timer timer2 = new Timer(speed, event2 -> {
                screen.frame.getContentPane().setBackground(Color.GRAY);
                Timer timer3 = new Timer(speed, event3 -> {
                    screen.frame.getContentPane().setBackground(Color.DARK_GRAY);
                    Timer timer4 = new Timer(speed, event4 -> {
                        screen.frame.getContentPane().setBackground(Color.BLACK);
                        timer5.setRepeats(false);
                        timer5.start();
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

    public static BufferedImage convertToBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    public static BufferedImage centerImageContent(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int minX = width, minY = height, maxX = 0, maxY = 0;

        // Determine the bounding box of the non-transparent pixels
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgba = image.getRGB(x, y);
                int alpha = (rgba >> 24) & 0xff;
                if (alpha != 0) {
                    if (x < minX)
                        minX = x;
                    if (y < minY)
                        minY = y;
                    if (x > maxX)
                        maxX = x;
                    if (y > maxY)
                        maxY = y;
                }
            }
        }

        // Width and height of the bounding box
        int contentWidth = maxX - minX + 1;
        int contentHeight = maxY - minY + 1;

        // Extract the content
        BufferedImage contentImage = image.getSubimage(minX, minY, contentWidth, contentHeight);

        // Create a new 256x256 image
        BufferedImage centeredImage = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);

        // Calculate the position to center the content
        int centerX = (256 - contentWidth) / 2;
        int centerY = (256 - contentHeight) / 2;

        // Draw the content in the center
        Graphics2D g = centeredImage.createGraphics();
        g.drawImage(contentImage, centerX, centerY, null);
        g.dispose();

        return centeredImage;
    }

    public static BufferedImage makeImageWhite(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage whiteImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgba = image.getRGB(x, y);
                int alpha = (rgba >> 24) & 0xff;

                if (alpha != 0) {
                    whiteImage.setRGB(x, y, new Color(255, 255, 255, alpha).getRGB());
                } else {
                    whiteImage.setRGB(x, y, rgba);
                }
            }
        }
        return whiteImage;
    }

    static Runnable shrink = new Runnable() {
        public void run() {
            try {
                if (counter <= 256) {
                    grow = true;
                    counter = 257;
                    cycle++;
                    if (cycle % 2 == 0) {
                        screen.user.setIcon(img2);
                    } else {
                        screen.user.setIcon(img1);
                    }
                } else if (counter >= 512) {
                    grow = false;
                    loop += 0.5;
                    counter = 512;
                    if (cycle == 40) {
                        Image image = convertToBufferedImage(img2.getImage());
                        Image scaledImage = image.getScaledInstance(256, 256, Image.SCALE_SMOOTH);
                        screen.user.setIcon(new ImageIcon(scaledImage));

                        screen.user.setHorizontalAlignment(JLabel.CENTER);
                        screen.user.setVerticalAlignment(JLabel.CENTER);
                        evolve2();
                        battle.executor.shutdownNow();
                    }
                }
                Image image;
                if (cycle % 2 == 0) {
                    image = convertToBufferedImage(img2.getImage());
                } else {
                    image = convertToBufferedImage(img1.getImage());
                }

                Image scaledImage = image.getScaledInstance(counter - 256, counter - 256, Image.SCALE_SMOOTH);
                screen.user.setIcon(new ImageIcon(scaledImage));

                screen.user.setHorizontalAlignment(JLabel.CENTER);
                screen.user.setVerticalAlignment(JLabel.CENTER);

                if (grow) {
                    counter += loop;
                } else {
                    counter -= loop;
                }
            } catch (Exception e) {

            }

        }
    };

    public static void evolve2() {
        int speed = 250;
        Timer timer = new Timer(1000, event -> {
            screen.user.setIcon(grey(N.getImage()));
            Timer timer7 = new Timer(250, event7 -> {
                screen.user.setIcon(N);
                Timer timer6 = new Timer(speed, event6 -> {
                    screen.frame.getContentPane().setBackground(Color.DARK_GRAY);
                    Timer timer2 = new Timer(speed, event2 -> {
                        screen.frame.getContentPane().setBackground(Color.GRAY);
                        Timer timer3 = new Timer(speed, event3 -> {
                            screen.frame.getContentPane().setBackground(Color.LIGHT_GRAY);
                            Timer timer4 = new Timer(speed, event4 -> {
                                screen.frame.getContentPane().setBackground(Color.WHITE);
                                Timer timer5 = new Timer(speed, event5 -> {
                                    textAnimator animator = new textAnimator();
                                    animator.text("Congratulations! Your " + Old + " evolved into " + New + "!");
                                });
                                timer5.setRepeats(false);
                                timer5.start();
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
                timer6.setRepeats(false);
                timer6.start();
            });
            timer7.setRepeats(false);
            timer7.start();

        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void Switch() {
        grow = false;
        counter = 256;
        O = new ImageIcon("battle images//monsters//" + Main.Player.inBattle.name + "_back.png");
        screen.user.setIcon(O);
        battle.executor = Executors.newScheduledThreadPool(1);
        battle.executor.scheduleAtFixedRate(disappear, 0, (int) ((1.5 / 256.0) * 1000), TimeUnit.MILLISECONDS);
    }

    static Runnable disappear = new Runnable() {
        public void run() {
            try {
                if (counter == 1) {
                    grow = true;
                    O = new ImageIcon("battle images//monsters//" + Main.Player.inBattle.name + "_back.png");
                    Main.animator.text("Go, " + Main.Player.inBattle.name + "!");
                }
                if (counter == 257) {
                    switchOut.next();
                    battle.executor.shutdownNow();
                }
                if (grow == false) {
                    Image image = convertToBufferedImage(O.getImage());

                    Image scaledImage = image.getScaledInstance(counter, counter, Image.SCALE_SMOOTH);
                    screen.user.setIcon(new ImageIcon(scaledImage));

                    screen.user.setHorizontalAlignment(JLabel.CENTER);
                    screen.user.setVerticalAlignment(JLabel.CENTER);
                    counter--;
                } else {
                    Image image = convertToBufferedImage(O.getImage());

                    Image scaledImage = image.getScaledInstance(counter, counter, Image.SCALE_SMOOTH);
                    screen.user.setIcon(new ImageIcon(scaledImage));

                    screen.user.setHorizontalAlignment(JLabel.CENTER);
                    screen.user.setVerticalAlignment(JLabel.CENTER);
                    counter++;
                }

            } catch (Exception e) {
                System.out.println(e);
                battle.executor.shutdownNow();
            }

        }
    };

    public static void capture(int shakes) {
        Escapecounter = 1;

        animations.shakes = shakes;
        bag.ball.setIcon(images.ball);
        counter = 256;
        O = new ImageIcon("battle images//monsters//" + Main.Opponent.inBattle.name + "_front.png");
        BufferedImage img = convertToBufferedImage(O.getImage());
        BufferedImage white = makeImageWhite(img);
        O.setImage(white);
        battle.executor = Executors.newScheduledThreadPool(1);
        battle.executor.scheduleAtFixedRate(Caught, 0, (int) ((1.5 / 256.0) * 1000), TimeUnit.MILLISECONDS);

    }

    static Runnable Caught = new Runnable() {
        public void run() {
            try {
                if (counter == 1) {

                    ball();
                    battle.executor.shutdownNow();
                }
                Image image = convertToBufferedImage(O.getImage());

                Image scaledImage = image.getScaledInstance(counter, counter, Image.SCALE_SMOOTH);
                screen.opponent.setIcon(new ImageIcon(scaledImage));

                screen.opponent.setHorizontalAlignment(JLabel.CENTER);
                screen.opponent.setVerticalAlignment(JLabel.BOTTOM);
                counter--;

            } catch (Exception e) {
                System.out.println(e + " zoinks");

                battle.executor.shutdownNow();
            }

        }
    };

    public static void ball() {
        screen.opponent.setVisible(false);
        if (shakes == 4) {
            shakes = 3;
            captured = true;
        } else {
            captured = false;
        }

        bag.ball.setBounds(456, 200, 100, 100);
        bag.ball.setVisible(true);
        if (shakes > 0) {
            executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(shaking, 1050, 35, TimeUnit.MILLISECONDS);
        } else {
            escapeCheck();
        }

    }

    static Runnable shaking = new Runnable() {
        int counter = 0;

        public void run() {
            try {
                if (counter == 30) {
                    if (shakes == 1) {

                        escapeCheck();
                        executor.shutdownNow();
                    } else {
                        counter = 0;
                        shakes--;
                    }
                } else if (counter == 1) {
                    bag.ball.setIcon(images.ball3);
                } else if (counter == 3 || counter == 9) {
                    bag.ball.setIcon(images.ball);
                } else if (counter == 6) {
                    bag.ball.setIcon(images.ball2);
                } else if (counter > 30) {
                    counter = 0;
                }
                counter++;
            } catch (Exception e) {
                System.out.println(e);
                executor.shutdownNow();
            }
        }
    };
    static int Escapecounter;

    public static void escapeCheck() {
        if (captured) {
            counter = 1;
            battle.executor = Executors.newScheduledThreadPool(1);
            battle.executor.scheduleAtFixedRate(Star, 1050, 1500, TimeUnit.MICROSECONDS);
            Main.animator.text(Main.Opponent.inBattle.name + " was caught!");
        } else {

            screen.opponent.setVisible(true);

            executor2 = Executors.newScheduledThreadPool(1);
            executor2.scheduleAtFixedRate(Escape, 850, (int) ((0.75 / 256.0) * 1000), TimeUnit.MILLISECONDS);
        }
    }

    static Runnable Escape = new Runnable() {
        public void run() {
            try {
                Image image = convertToBufferedImage(O.getImage());
                Image scaledImage = image.getScaledInstance(Escapecounter, Escapecounter, Image.SCALE_SMOOTH);
                screen.opponent.setIcon(new ImageIcon(scaledImage));

                screen.opponent.setHorizontalAlignment(JLabel.CENTER);
                screen.opponent.setVerticalAlignment(JLabel.BOTTOM);

                if (Escapecounter == 256) {

                    bag.ball.setVisible(false);
                    screen.opponent.setIcon(
                            new ImageIcon("battle images//monsters//" + Main.Opponent.inBattle.name + "_front.png"));

                    Main.animator.text(Main.Opponent.inBattle.name + " escaped");

                    Timer timer = new Timer(2000, event -> {

                        move.opponentTurn();
                    });
                    timer.setRepeats(false);
                    timer.start();
                    executor2.shutdownNow();
                }
                Escapecounter++;

            } catch (Exception e) {
                System.out.println(e);
                executor2.shutdownNow();
            }

        }
    };
    static Runnable Star = new Runnable() {

        public void run() {
            try {
                if (counter <= 100) {
                    Image image = convertToBufferedImage(images.star.getImage());

                    Image scaledImage = image.getScaledInstance(counter, counter, Image.SCALE_SMOOTH);
                    screen.star.setIcon(new ImageIcon(scaledImage));

                    screen.star.setHorizontalAlignment(JLabel.CENTER);
                    screen.star.setVerticalAlignment(JLabel.CENTER);

                } else if (counter > 300 && counter < 300 + 500.0) {
                    screen.star.setIcon(new ImageIcon(adjustImageTransparency(
                            convertToBufferedImage(images.star.getImage()), (float) (1.0 - (counter - 300) / 500.0))));
                } else if (counter == 300 + 500.0) {
                    Timer timer = new Timer(bag.delay, event -> {
                        if (Main.Player.add2team(Main.Opponent.inBattle)) {
                            Timer timer2 = new Timer(3000, event2 -> {
                                battle.endBattle();
                            });
                            timer2.setRepeats(false);
                            timer2.start();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    battle.executor.shutdownNow();

                }
                counter++;

            } catch (Exception e) {
                System.out.println(e);
                battle.executor.shutdownNow();
            }

        }
    };

    public static BufferedImage adjustImageTransparency(BufferedImage originalImage, float alphaFactor) {
        // Ensure the image has an alpha channel (transparency)
        BufferedImage imageWithTransparency = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Copy original image into the new image with an alpha channel
        imageWithTransparency.getGraphics().drawImage(originalImage, 0, 0, null);

        // Use RescaleOp to adjust alpha; only the alpha channel (4th band) is scaled
        float[] scales = { 1f, 1f, 1f, alphaFactor }; // RGB unchanged, Alpha adjusted
        float[] offsets = new float[4]; // No offsets

        RescaleOp rescaleOp = new RescaleOp(scales, offsets, null);
        rescaleOp.filter(imageWithTransparency, imageWithTransparency);

        // Return the new image with modified transparency
        return imageWithTransparency;
    }

}
