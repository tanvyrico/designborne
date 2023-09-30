package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actions.*;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;


public class GreatKnife extends WeaponItem implements Sellable, Purchasable {

    private int sellingPrice = 175;
    /**
     * Constructor for the SkilledWeapon class.
     */
    public GreatKnife() {
        super("GreatKnife", '>', 75, "Stab", 70);
    }

    public ActionList allowableActions(Actor target, Location location){
        ActionList actionList = new ActionList();
        if (!target.hasCapability(Status.HOSTILE_TO_ENEMY) && (target.hasCapability(Status.FRIENDLY_TO_ENEMY))){
            actionList.add(new AttackAction(target, location.toString(), this));
            actionList.add(new StabAndStepAction(this,target,location.toString()));
        }
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }
        return actionList;
    }
    @Override
    public String purchase(Actor actor,Actor seller) {
        Random random = new Random();

        int purchasePrice = getPurchasePrice(seller);
        if (random.nextDouble() <= 0.05) {
            purchasePrice = purchasePrice * 3;
        }
        if (actor.getBalance() >= purchasePrice){
            actor.deductBalance(purchasePrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this;
        }
        return actor + " fail to buy GreatKnife for " + purchasePrice + " Runes";
    }

    public int getPurchasePrice(Actor seller){
        if (seller.hasCapability(Status.SUSPICIOUS)) {
            return 300;
        }
        return 0;
    }

    public int getSellingPrice(){
        return this.sellingPrice;
    }

    @Override
    public String sell(Actor actor) {
        Random random = new Random();
        if (random.nextDouble() <= 0.20) {
            if(actor.getBalance() >= this.sellingPrice){
                actor.deductBalance(this.sellingPrice);
            }
            else{
                actor.deductBalance(actor.getBalance());
            }
            actor.removeItemFromInventory(this);
            return sellingPrice + " runes " + " is stolen from the actor ! ";
        }
        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this;
    }

}
