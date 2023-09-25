package game.items.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;


public class GreatKnife extends SkilledWeapon implements Purchasable, Sellable {
    private int sellingPrice = 175;
    private int purchasePrice = 300;

    /**
     * Constructor for the SkilledWeapon class.
     */
    public GreatKnife() {
        super("GreatKnife", '>', 75, "Stab", 70, 5, false);
        addCapability(Status.STAB_AND_STEP);
    }

    @Override
    public String purchase(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.05) {
            purchasePrice = purchasePrice * 3;
        }
        if (actor.getBalance() >= this.purchasePrice){
            actor.deductBalance(this.purchasePrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this;
        }
        return "purchase failed!";
    }

    @Override
    public String sell(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.1) {
            if(actor.getBalance() >= this.sellingPrice){
                actor.deductBalance(this.sellingPrice);
            }
            else{
                actor.deductBalance(actor.getBalance());
            }
        }
        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }

}
