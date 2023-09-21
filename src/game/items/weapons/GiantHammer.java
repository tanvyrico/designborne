package game.items.weapons;

import game.Status;

public class GiantHammer extends SkilledWeapon{
    /**
     * Constructor for the SkilledWeapon class.
     */
    public GiantHammer() {
        super("Giant Hammer",'P', 160, "slams",90,0, false);
        addCapability(Status.AOE_POSSIBLE);
    }


}
