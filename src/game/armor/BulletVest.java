package game.armor;

public class BulletVest implements Armor{
    @Override
    public int damageReduced(){
        return 60;
    }
    @Override
    public int pdV(){
        return 10;
    }
    @Override
    public int regen(){
        return 15;
    }
}
