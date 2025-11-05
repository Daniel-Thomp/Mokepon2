import javax.swing.SwingUtilities;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class textAnimator {
    private String text;
    private StringBuilder message = new StringBuilder();
    private int i = 0;
    private ScheduledExecutorService executor;

    public void text(String text) {
        this.text = text;
        this.message.setLength(0);
        this.i = 0;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new TextAni(), 0, 25, TimeUnit.MILLISECONDS);
    }

    private class TextAni implements Runnable {
        public void run() {
            if (i < text.length()) {
                synchronized (message) {
                    message.append(text.charAt(i));
                }
                i++;

                SwingUtilities.invokeLater(() -> {
                    synchronized (message) {
                        screen.text.setText(message.toString());
                    }
                });
            } else {
                executor.shutdown();
            }
        }
    }
}
