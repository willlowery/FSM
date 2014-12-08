package turnstile;

import turnstile.Turnstile.Action;
import turnstile.Turnstile.STATE;

public class TurnstileTableImpTest extends TurnstileTest {

    @Override
    Turnstile buildTurnStile(STATE initial, Action CCO, Action CPC, Action OCO, Action OPC) {
        return new TurnstileTableImp(initial, CCO, CPC, OCO, OPC);
    }

}
