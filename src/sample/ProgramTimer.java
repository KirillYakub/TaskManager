package sample;

import java.util.Timer;
import java.util.TimerTask;

public class ProgramTimer {
    private static int programTact = 0;

    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            incTact();
        }
    };

    public static int getProgramTact() { return programTact; }

    public static void incTact() {
        programTact++;
        MemoryScheduler.release();
    }

    public void start() {
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }
}