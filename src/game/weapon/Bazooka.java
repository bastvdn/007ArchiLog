package game.weapon;

public class Bazooka  implements Weapon{
    @Override
    public int bps(){return 5;}
    @Override
    public int damage(){
        return 45;
    }
    @Override
    public int lifeSelfImpact(){
        return -5;
    }
    @Override
    public int criticalStrike(){
        return 25;
    }
}
