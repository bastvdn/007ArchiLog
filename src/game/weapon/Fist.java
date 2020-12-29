package game.weapon;

import game.armor.Armor;

public class Fist implements Weapon {

    @Override
    public int bps(){
        return 0;
    }
    @Override
    public int damage(){return 5; }
    @Override
    public int lifeSelfImpact(){ return -1; }
    @Override
    public int criticalStrike(){
        return 10;
    }
}
