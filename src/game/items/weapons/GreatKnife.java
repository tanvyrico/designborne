package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;
import game.actions.*;
import game.items.Purchasable;
import game.items.Sellable;

import java.util.Random;

/**
 * A class representing a Great Knife weapon item in the game.
 * The Great Knife is a powerful melee weapon that can be used to attack and deal damage to enemies.
 * It can also be purchased and sold by actors who possess the capability to trade.
 */
public class GreatKnife extends WeaponItem implements Sellable, Purchasable{

    private int sellingPrice = 175;

    /**
     * Constructor for the GreatKnife class.
     * Initializes the Great Knife with a name, display character, damage points, verb for attack, and hit rate.
     */
    public GreatKnife() {
        super("GreatKnife", '>', 75, "Stab", 70);
    }

    /**
     * Returns a list of allowable actions that the Great Knife allows its owner to perform on other actors.
     * These actions may include attacking, performing a special stab-and-step action, and selling the Great Knife.
     *
     * @param target   The target actor on which the actions can be performed.
     * @param location The location of the target actor.
     * @return An ActionList containing the allowable actions for the Great Knife.
     */
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

    /**
     * Purchases the Great Knife from a seller, deducting the purchase price from the buyer's balance
     * and adding the Great Knife to the buyer's inventory.
     *
     * @param actor  The actor purchasing the Great Knife.
     * @param seller The actor selling the Great Knife.
     * @return A message indicating the purchase or failure to purchase the Great Knife.
     */
    @Override
    public String purchase(Actor actor, Actor seller) {
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

    /**
     * Retrieves the purchase price of the Great Knife.
     * The purchase price may be higher for suspicious sellers.
     *
     * @param seller The actor selling the Great Knife.
     * @return The purchase price of the Great Knife.
     */
    public int getPurchasePrice(Actor seller){
        if (seller.hasCapability(Status.SUSPICIOUS)) {
            return 300;
        }
        return 0;
    }

    /**
     * Retrieves the selling price of the Great Knife.
     *
     * @return The selling price of the Great Knife.
     */
    public int getSellingPrice(){
        return this.sellingPrice;
    }

    /**
     * Sells the Great Knife to an actor, adding its selling price to the actor's balance
     * and removing it from their inventory.
     * There's a chance that the selling price is stolen from the actor.
     *
     * @param actor The actor selling the Great Knife.
     * @return A message indicating the sale of the Great Knife.
     */
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

