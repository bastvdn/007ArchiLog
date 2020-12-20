package game.armor;

public class Thornmail implements Armor {
    @Override
    public int damageReduced(){
        return 110;
    }
    @Override
    public int pdV(){
        return 20;
    }
    @Override
    public int regen(){
        return 20;
    }
}
