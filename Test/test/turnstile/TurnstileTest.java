package turnstile;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import turnstile.Turnstile.Action;
import turnstile.Turnstile.EVENT;
import turnstile.Turnstile.STATE;

@Ignore //Junit doenst ignore abstract classes so it tries to run the test in this file
public abstract class TurnstileTest {

    String result;
    Turnstile turnstile;

    @Before
    public void setup() {
        result = "";
        Action alarm = () -> {
            result += "A";
        };
        Action open = () -> {
            result += "O";
        };
        Action close = () -> {
            result += "C";
        };
        Action thankyou = () -> {
            result += "T";
        };
        turnstile = buildTurnStile(STATE.CLOSED, open, alarm, thankyou, close);
    }

    abstract Turnstile buildTurnStile(STATE initial, Action CCO, Action CPC, Action OCO, Action OPC);

    @Test
    public void testStandardUse() {
        turnstile.handleEvent(EVENT.COIN);
        turnstile.handleEvent(EVENT.PASS);
        assertEquals("OC", result);
    }

    @Test
    public void testJumper() {
        turnstile.handleEvent(EVENT.PASS);
        assertEquals("A", result);
    }

    @Test
    public void testGenerousMan() {
        turnstile.handleEvent(EVENT.COIN);
        turnstile.handleEvent(EVENT.COIN);
        turnstile.handleEvent(EVENT.PASS);
        assertEquals("OTC", result);
    }

    @Test
    public void testVeryGenerousMan() {
        for (int i = 1; i <= 5; i++) {
            turnstile.handleEvent(EVENT.COIN);
        }
        turnstile.handleEvent(EVENT.PASS);
        assertEquals("OTTTTC", result);
    }

}
