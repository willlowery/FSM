package turnstile;

import turnstile.Turnstile.Action;

public class TurnstileTimeComparision {

    static final int RUN_SIZE = 100;
    static final int NUMBER_OF_RUNS = 1000000;
    static final Action NOOP = new Action() {

        @Override
        public void act() {
     
        }
    };

    public static void main(String[] args) {
        TurnstileSwitchImp switchImp = new TurnstileSwitchImp(Turnstile.STATE.OPEN, NOOP, NOOP, NOOP, NOOP);
        TurnstileTableImp tableImp = new TurnstileTableImp(Turnstile.STATE.OPEN, NOOP, NOOP, NOOP, NOOP);

        long total = 0;
        for (int i = 0; i < NUMBER_OF_RUNS; i++) {
            total += (time(tableImp, RUN_SIZE) - time(switchImp, RUN_SIZE));
        }
        System.out.println(String.format("The average difference between times is %.4f ms.", total / (double) NUMBER_OF_RUNS));
    }

    private static long time(Turnstile turnstile, int sampleSize) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < sampleSize; i++) {
            turnstile.handleEvent(randomEvent());
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static Turnstile.EVENT randomEvent() {
        int event = (int) ((Math.random() * 100) % Turnstile.EVENT.values().length);
        Turnstile.EVENT randomEvent = Turnstile.EVENT.values()[event];
        return randomEvent;
    }
}
