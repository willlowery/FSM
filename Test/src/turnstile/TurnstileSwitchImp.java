package turnstile;

import static turnstile.Turnstile.STATE.CLOSED;
import static turnstile.Turnstile.STATE.OPEN;

public class TurnstileSwitchImp implements Turnstile {

    STATE current;
    Action CCO, CPC, OCO, OPC;

    public TurnstileSwitchImp(STATE initial, Action CCO, Action CPC, Action OCO, Action OPC) {
        this.CCO = CCO;
        this.CPC = CPC;
        this.OCO = OCO;
        this.OPC = OPC;
        current = initial;
    }

    public void handleEvent(EVENT event) {
        switch (current) {
            case OPEN:
                switch (event) {
                    case COIN:
                        OCO.act();
                        current = OPEN;
                        break;
                    case PASS:
                        OPC.act();
                        current = CLOSED;
                        break;
                }
                break;
            case CLOSED:
                switch (event) {
                    case COIN:
                        CCO.act();
                        current = OPEN;
                        break;
                    case PASS:
                        CPC.act();
                        current = CLOSED;
                        break;
                }
                break;
        }

    }

}
