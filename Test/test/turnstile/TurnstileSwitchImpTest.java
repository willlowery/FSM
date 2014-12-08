package turnstile;

import turnstile.Turnstile.Action;
import turnstile.Turnstile.STATE;

public class TurnstileSwitchImpTest extends TurnstileTest {

    @Override
    Turnstile buildTurnStile(STATE initial, Action CCO, Action CPC, Action OCO, Action OPC) {
        return new TurnstileSwitchImp(initial, CCO, CPC, OCO, OPC);
    }


}
