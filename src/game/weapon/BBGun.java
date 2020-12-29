package game.weapon;

public class BBGun implements Weapon {
    @Override
    public int bps(){
        return 1;
    }
    @Override
    public int damage(){
        return 20;
    }
    @Override
    public int lifeSelfImpact(){
        return 0;
    }
    @Override
    public int criticalStrike(){
        return 20;
    }
}
