package game.weapon;

public class Nerf implements Weapon {
    @Override
    public int bps(){
        return 1;
    }
    @Override
    public int damage(){
        return 10;
    }
    @Override
    public int lifeSelfImpact(){
        return 5;
    }
    @Override
    public int criticalStrike(){
        return 5;
    }
}
