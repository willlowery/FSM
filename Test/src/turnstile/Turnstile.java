package turnstile;

public interface Turnstile {

    public enum EVENT {
        COIN,
        PASS;
    }

    public enum STATE {
        OPEN,
        CLOSED;
    }

    public interface Action {
        public void act();
    }

    void handleEvent(EVENT event);

}
