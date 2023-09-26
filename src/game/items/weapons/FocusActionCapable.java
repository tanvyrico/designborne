package game.items.weapons;

public interface FocusActionCapable {

    void setSkillTurn(int turn);

    void increaseDamageMultiplierAndHitRate(float damageMultiplier,int hitRate);

    void resetWeaponStat();
}
