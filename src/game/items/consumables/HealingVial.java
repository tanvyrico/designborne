package game.items.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UpgradeAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.items.Purchasable;
import game.items.Sellable;
import game.items.Upgradeable;

import java.util.Random;

/**
 * Represents a Healing Vial item in the game, which is consumable, purchasable, and sellable.
 * Consuming a Healing Vial restores an actor's health attribute by a certain percentage.
 * It can be purchased from or sold to trader actors.
 * @author Enrico Tanvy
 * Modified by: Group6
 */
public class HealingVial extends Item implements Consumable, Purchasable, Sellable, Upgradeable {
    private final BaseActorAttributes modifiedAttribute = BaseActorAttributes.HEALTH;
    private final int sellingPrice = 35;
    private final int upgradingPrice = 250;
    private int maxUpgradeTimes = 1;
    private int upgradeTimes = 0;
    private double percentageIncrease;

    /**
     * Constructs a Healing Vial item with the name "Healing vial" and a display character 'a'.
     * It is both purchasable and sellable.
     */
    public HealingVial(){
        super("Healing vial", 'a', true);
        this.addCapability(Ability.PURCHASABLE);
        this.addCapability(Ability.SELLABLE);
        this.percentageIncrease = 0.1;
    }

    public double getPercentageIncrease(){
        return this.percentageIncrease;
    }

    public void setPercentageIncrease(double givenPercentage){
        this.percentageIncrease = givenPercentage;
    }
    /**
     * Consumes the Healing Vial, increasing the actor's health by a percentage of their maximum health.
     *
     * @param actor The actor consuming the Healing Vial.
     * @return A message describing the consumption and its effects.
     */
    @Override
    public String consume(Actor actor) {
        int buffedPoints = (int) (getPercentageIncrease() * actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, buffedPoints);
        actor.removeItemFromInventory(this);
        return actor + " consumes " + this + " and " + this + " restores the " +
                this.modifiedAttribute + " of " + actor + " by " + buffedPoints + " points.";
    }

    /**
     * Returns a list of allowable actions that an owner of this item can perform.
     * In this case, it includes the ability to consume the Healing Vial.
     *
     * @param owner The actor who owns the Healing Vial.
     * @return A list of allowable actions for the owner of the item.
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        ConsumeAction consumeAction = new ConsumeAction(this);
        actionList.add(consumeAction);
        return actionList;
    }

    /**
     * Purchases the Healing Vial from a seller actor, deducting the purchase price from the buyer's balance.
     *
     * @param actor  The actor buying the Healing Vial.
     * @param seller The actor selling the Healing Vial.
     * @return A message describing the purchase and the updated balance of the buyer.
     */
    public String purchase(Actor actor, Actor seller) {
        Random random = new Random();
        int purchasePrice = getPurchasePrice(seller);
        if (random.nextDouble() <= 0.25) {
            purchasePrice = (int) (getPurchasePrice(seller) * 1.5);
        }
        if (actor.getBalance() >= purchasePrice) {
            actor.deductBalance(purchasePrice);
            actor.addItemToInventory(this);
            return actor + " purchased " + this + " for " + purchasePrice + " Runes";
        }else {
            return actor + " failed to purchase " + this + " due to insufficient runes!";
        }
    }

    /**
     * Calculates the purchase price of the Healing Vial.
     * The price is higher if the seller actor has the SUSPICIOUS capability.
     *
     * @param seller The actor selling the Healing Vial.
     * @return The purchase price of the Healing Vial.
     */
    @Override
    public int getPurchasePrice(Actor seller) {
        if (seller.hasCapability(Status.SUSPICIOUS)) {
            return 100;
        }
        return 0;
    }

    /**
     * Sells the Healing Vial to a trader actor, adding the selling price to the seller's balance.
     * There is a chance for the item to be sold at double its normal price.
     *
     * @param actor The actor selling the Healing Vial.
     * @return A message describing the sale and the updated balance of the seller.
     */
    @Override
    public String sell(Actor actor){
        Random random = new Random();
        if (random.nextDouble() <= 0.1) {
            int luckyPrice = this.sellingPrice * 2;
            actor.addBalance(luckyPrice);
            actor.removeItemFromInventory(this);
            return actor + " sold " + this + " at double its normal price (" + luckyPrice + " runes)";
        }else {
            actor.addBalance(this.sellingPrice);
            actor.removeItemFromInventory(this);
            return actor + " sold " + this + " at its normal price (" + this.sellingPrice +" runes)";
        }
    }

    @Override
    public String upgrade(Actor actor){
        int upgradePrice = this.getUpgradingPrice();
        if (actor.getBalance() >= upgradePrice) {
            actor.deductBalance(upgradePrice);
            this.setPercentageIncrease(0.8);
            this.upgradeTimes++;
            return "Success! " + actor + "'s " + this + " has been successfully upgraded for " + this.getUpgradingPrice() + " runes.";
        }else {
            return actor + " failed to upgrade " + this + " due to insufficient runes!";
        }
    }

    @Override
    public int getUpgradingPrice(){
        return this.upgradingPrice;
    }

    @Override
    public boolean ableToUpgrade(){
        if (this.upgradeTimes < this.maxUpgradeTimes){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Retrieves the selling price of the Healing Vial item.
     *
     * @return The selling price of the Healing Vial item.
     */
    @Override
    public int getSellingPrice() {
        return this.sellingPrice;
    }

    /**
     * Returns a list of allowable actions that a trader actor can perform with this item.
     * In this case, it includes the ability to sell the Healing Vial to the trader.
     *
     * @param target   The trader actor interacting with the Healing Vial.
     * @param location The location where the interaction takes place.
     * @return A list of allowable actions for the trader actor.
     */
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }
        if (target.hasCapability(Status.UPGRADE_ITEMS_WEAPONS)) {
            actionList.add(new UpgradeAction(this));
        }
        return actionList;
    }
}
