package game.weapon;

public class StunGun implements Weapon {
    @Override
    public int bps(){
        return 2;
    }
    @Override
    public int damage(){
        return 15;
    }
    @Override
    public int lifeSelfImpact(){ return 10; }
    @Override
    public int criticalStrike(){
        return 20;
    }
}
