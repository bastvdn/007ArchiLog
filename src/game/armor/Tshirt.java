package game.armor;

public class Tshirt implements Armor{
    @Override
    public int damageReduced(){
        return 15;
    }
    @Override
    public int pdV(){
        return 10;
    }
    @Override
    public int regen(){
        return 1;
    }
}
