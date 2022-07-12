package cardgames;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Snap extends CardGame {

    private boolean timesUp;

    @Override
    public abstract void run();

    protected void setup() {
        makeNewDeck();
        shuffleDeck();
    }

    protected boolean raceTimer() {//returns if win or not
        timesUp = false;
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up");
                timesUp = true;
            }
        };
        Timer timer = new Timer();
        String input = "";
        timer.schedule(task, 2 * 1000);
        while (!input.equals("snap") && !timesUp) {
            input = scanner.nextLine();
        }
        timer.cancel();
        return (input.equals("snap") && !timesUp);
    }
}
