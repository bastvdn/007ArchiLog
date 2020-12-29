package game.weapon;

public class SuperLazerGun implements Weapon {
    @Override
    public int bps(){
        return 4;
    }
    @Override
    public int damage(){
        return 30;
    }
    @Override
    public int lifeSelfImpact(){
        return 10;
    }
    @Override
    public int criticalStrike(){
        return 35;
    }
}
