package game.weapon;

/*
 * Each Entity Wields one weapon, which has:
 * bps: represents the number of bullets used per shot
 * damage: the additionnal damages of the weapon
 * lifeSelfImpact: the value that the user heals or damages himself of
 * criticalStrike: increase the chance of criticalStrike
 * */

public interface Weapon {

    int bps();
    int damage();
    int lifeSelfImpact();
    int criticalStrike();
}
