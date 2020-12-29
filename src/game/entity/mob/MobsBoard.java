package game.entity.mob;
import java.util.ArrayList;
import java.util.List;
import game.weapon.*;
import game.armor.*;

/*
* MobsBoard contains à list of mobs created according to the current level of the game
* each mobs will have an armor and a weapon
* */
public class MobsBoard {

    private List<Mob> mobs = new ArrayList<Mob>();

    /*
    * weapons available at each level
    * */
    private static Weapon[][] weaponBoard = {
            {new Fist(), new Nerf()},
            {new Nerf(), new BBGun()},
            {new BBGun(), new StunGun()},
            {new StunGun(),new SuperLazerGun()},
            {new SuperLazerGun(),new Bazooka()}
    };

    /*
     * armors available at each level
     * */
    private static Armor[][] armorBoard = {
            {new Naked(), new Tshirt()},
            {new Tshirt(),new Coat()},
            {new Coat(),new BulletVest()},
            {new BulletVest(), new MagicCloak()},
            {new InvisibilityCloak(), new Thornmail()}
    };

    /*
     * adjectives available at each level (for fun purpose only, no interaction with the game whatsoever)
     * */

    private static String[][] qualityBoard = {
            {"Baby","Happy","Cute","Not sad", "Curious"},
            {"Geeky", "DJ", "Seductive", "Kinky", "Meticulous"},
            {"Go-muscu", "Pro-hacker", "Serious", "Sneaky","Totally incognito" },
            { "Sasuke", "Mega", "Enraged", "Rudeboy", "Fuckboy"},
            {"Super-angry","The wisest", "Chuck-Norris", "God-like", "Once again that fucking"}
    };

    public Mob getMob(int id){
        return mobs.get(id);
    }

    /*
    * gives the mob a weapon, an armor and a quality
    * */
    void addMob(Mob mob) {

        int MAX_WEAPON = 1;
        int MIN_WEAPON = 0;
        int MAX_ARMOR = 1;
        int MIN_ARMOR = 0;
        int MAX_QUALITY = 4;
        int MIN_QUALITY = 0;

        mob.setWeapon(MobsBoard.weaponBoard[mob.getLevel()][MIN_WEAPON + (int)(Math.random() * ((MAX_WEAPON - MIN_WEAPON)+1))]);
        mob.setArmor(MobsBoard.armorBoard[mob.getLevel()][MIN_ARMOR + (int)(Math.random() * ((MAX_ARMOR - MIN_ARMOR)+1))]);
        mob.setQuality(MobsBoard.qualityBoard[mob.getLevel()][MIN_QUALITY + (int)(Math.random() * ((MAX_QUALITY - MIN_QUALITY)+1))]);
        mobs.add(mob);
    }
}