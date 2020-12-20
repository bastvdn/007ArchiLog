package game.armor;

public class Coat implements Armor{
    @Override
    public int damageReduced(){
        return 50;
    }
    @Override
    public int pdV(){
        return 20;
    }
    @Override
    public int regen(){
        return 10;
    }
}
