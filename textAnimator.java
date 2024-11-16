import java.util.concurrent.*;
import javax.swing.SwingUtilities;

public class textAnimator {
    private String text;
    private String message = "";
    private int i = 0;
    private ScheduledExecutorService executor;

    public void text(String text) {
        this.text = text;
        this.message = "";
        this.i = 0;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new TextAni(), 0, 25, TimeUnit.MILLISECONDS);
    }

    private class TextAni implements Runnable {
        public void run() {
            if (i < text.length()) {
                message += text.charAt(i);
                i++;
                
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        screen.text.setText(message);
                    }
                });
            } else {
                executor.shutdown();
            }
        }
    }
}
