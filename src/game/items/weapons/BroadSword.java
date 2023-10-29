package game.items.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.UpgradeAction;
import game.capabilities.Ability;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.capabilities.Status;
import game.actions.SellAction;
import game.items.Purchasable;
import game.items.Sellable;
import game.items.Upgradeable;

import java.util.Random;

/**
 * Class representing a Broadsword
 * @author Lim Hung Xuan
 * Modified by: Group6
 */
public class BroadSword extends WeaponItem implements Purchasable, Sellable, FocusActionCapable, Upgradeable {

    private final int UPGRADE_POINT = 10;
    private final int SELLING_PRICE= 100;
    private final int UPGRADE_PRICE = 1000;

    private int specialSkillTurn = 0;

    private int initialHitRate;
    private int upgradeCount = 0;


    /**
     * Constructor for the BroadSword class.
     */
    public BroadSword() {
        super("Broadsword", '1', 110, "slashes", 80);
        this.initialHitRate = 80;
        this.addCapability(Ability.PURCHASABLE);
        this.addCapability(Ability.SELLABLE);
    }


    /**
     * Generates a list of allowable actions for the owner of this BroadSword.
     *
     * @param owner The actor who owns this BroadSword.
     * @return An ActionList containing allowable actions for the owner.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actionList = new ActionList();
        actionList.add(new FocusAction(this));
        return actionList;
    }

    /**
     * Generates a list of allowable actions for the given target and location involving the BroadSword.
     *
     * @param target    The actor being targeted.
     * @param location  The location of the target.
     * @return An ActionList containing allowable actions involving the BroadSword for the given target and location.
     */
    @Override
    public ActionList allowableActions(Actor target, Location location) {
        ActionList actionList = new ActionList();
        if(!target.hasCapability(Status.HOSTILE_TO_ENEMY) && (target.hasCapability(Status.FRIENDLY_TO_ENEMY))) {
            actionList.add(new AttackAction(target, location.toString(), this));
        }
        if (target.hasCapability(Status.TRADER)) {
            actionList.add(new SellAction(this));
        }
        if (target.hasCapability(Status.UPGRADE_ITEMS_WEAPONS)) {
            actionList.add(new UpgradeAction(this));
        }

        return actionList;
    }

    /**
     * Sets the turn count for a special skill associated with the BroadSword.
     *
     * @param turn The number of turns for the special skill to be active.
     */
    public void setSkillTurn(int turn){
        this.specialSkillTurn = turn;
    }

    /**
     * Increases the damage multiplier and hit rate of the BroadSword.
     *
     * @param damageMultiplier The amount to increase the damage multiplier by.
     * @param hitRate          The new hit rate to set.
     */
    @Override
    public void increaseDamageMultiplierAndHitRate(float damageMultiplier,int hitRate){
        this.increaseDamageMultiplier(damageMultiplier);
        this.updateHitRate(hitRate);
    }

    /**
     * Decrements the special skill turn count of the BroadSword.
     */
    public void decrementSkillTurn(){
        this.specialSkillTurn -= 1;
    }

    /**
     * Resets the stats of the BroadSword to their initial values.
     */
    @Override
    public void resetWeaponStat(){
        this.updateHitRate(initialHitRate);
        this.updateDamageMultiplier(1f);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        decrementSkillTurn();
        if ( this.specialSkillTurn == 0){
            resetWeaponStat();
        }
    }

    /**
     * Handles the purchase of the BroadSword by an actor from a seller.
     *
     * @param actor  The actor purchasing the BroadSword.
     * @param seller The seller from whom the BroadSword is being purchased.
     * @return A message indicating the success or failure of the purchase.
     */
    @Override
    public String purchase(Actor actor, Actor seller) {
        Random random = new Random();
        int purchasePrice = getPurchasePrice(seller);

        if (actor.getBalance() >= purchasePrice){
            actor.deductBalance(purchasePrice);
            if (random.nextDouble() <= 0.05){
                return actor + " paid " + getPurchasePrice(seller) + " runes but did not receive " + this;
            }else {
                actor.addItemToInventory(this);
                return actor + " purchased " + this + " for " + purchasePrice+" runes)";
            }
        }else {
            return actor + " failed to purchase " + this + " due to insufficient runes!";
        }
    }

    /**
     * Handles the upgrading of the BroadSword by an actor.
     *
     * @param actor The actor upgrading the BroadSword.
     * @return A message indicating the success or failure of the upgrade.
     */
    @Override
    public String upgrade(Actor actor){
        int upgradePrice = this.getUpgradingPrice();
        if (actor.getBalance() >= upgradePrice) {
            actor.deductBalance(upgradePrice);
            this.upgradeCount += UPGRADE_POINT;
            return "Success! " + actor + "'s " + this + " has been successfully upgraded for " + this.getUpgradingPrice() + " runes.";
        }else {
            return actor + " failed to upgrade " + this + " due to insufficient runes!";
        }
    }

    /**
     * Calculates the damage dealt by the BroadSword, considering its upgrade count.
     *
     * @return The calculated damage value.
     */
    @Override
    public int damage() {
        return (super.damage()+this.upgradeCount);
    }

    /**
     * Retrieves the price for upgrading the BroadSword.
     *
     * @return The price for upgrading the BroadSword.
     */
    @Override
    public int getUpgradingPrice(){
        return this.UPGRADE_PRICE;
    }

    /**
     * Checks if the BroadSword is able to be upgraded.
     *
     * @return true if the BroadSword is upgradable, false otherwise.
     */
    @Override
    public boolean ableToUpgrade(){
        return true;
    }

    /**
     * Calculates the purchase price of the BroadSword from a seller.
     *
     * @param seller The seller offering the BroadSword.
     * @return The calculated purchase price.
     */
    @Override
    public int getPurchasePrice(Actor seller) {
        if (seller.hasCapability(Status.SUSPICIOUS)) {
            return 250;
        }
        return 0;
    }

    /**
     * Handles the selling of the BroadSword by an actor.
     *
     * @param actor The actor selling the BroadSword.
     * @return A message indicating the success or failure of the sale.
     */
    @Override
    public String sell(Actor actor) {
        actor.addBalance(this.SELLING_PRICE);
        actor.removeItemFromInventory(this);
        return actor + " sold " + this + " at its normal price (" + this.SELLING_PRICE +" runes)";
    }

    /**
     * Retrieves the selling price of the BroadSword.
     *
     * @return The selling price of the BroadSword.
     */
    @Override
    public int getSellingPrice() {
        return this.SELLING_PRICE;
    }

}
