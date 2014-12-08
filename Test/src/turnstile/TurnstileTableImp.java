package turnstile;

public class TurnstileTableImp implements Turnstile {

    STATE current;
    Transition[][] transitions;

    public TurnstileTableImp(STATE initial, Action CCO, Action CPC, Action OCO, Action OPC) {
        transitions = new Transition[STATE.values().length][EVENT.values().length];
        current = initial;

        addTransition(STATE.CLOSED, EVENT.COIN, STATE.OPEN, CCO);
        addTransition(STATE.CLOSED, EVENT.PASS, STATE.CLOSED, CPC);
        addTransition(STATE.OPEN, EVENT.COIN, STATE.OPEN, OCO);
        addTransition(STATE.OPEN, EVENT.PASS, STATE.CLOSED, OPC);
    }

    private void addTransition(STATE given, EVENT when, STATE then, Action action) {
        transitions[given.ordinal()][when.ordinal()] = new Transition(this, then, action);
    }

    @Override
    public void handleEvent(EVENT event) {
        transitions[current.ordinal()][event.ordinal()].transition();
    }

    private class Transition {

        STATE toGoTo;
        Action action;
        TurnstileTableImp turnstile;

        public Transition(TurnstileTableImp turnstile, STATE toGoTo, Action action) {
            this.action = action;
            this.toGoTo = toGoTo;
            this.turnstile = turnstile;
        }

        public void transition() {
            action.act();
            turnstile.current = toGoTo;
        }
    }
}
