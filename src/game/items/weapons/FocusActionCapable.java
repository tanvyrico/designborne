package game.items.weapons;

/**
 * An interface representing items or objects capable of focus actions, which allow the adjustment of weapon statistics
 * such as damage multiplier and hit rate.
 */
public interface FocusActionCapable {

    /**
     * Sets the number of turns for which the focus action will be active.
     *
     * @param turn The number of turns for which the focus action will be active.
     */
    void setSkillTurn(int turn);

    /**
     * Increases the damage multiplier and hit rate for the weapon, typically used during focus actions.
     *
     * @param damageMultiplier The multiplier by which damage will be increased.
     * @param hitRate          The increase in hit rate.
     */
    void increaseDamageMultiplierAndHitRate(float damageMultiplier,int hitRate);

    /**
     * Resets the weapon statistics to their default values after the focus action expires or is canceled.
     */
    void resetWeaponStat();
}
