package game.armor;

public class Naked implements Armor{
    @Override
    public int damageReduced(){
        return 0;
    }
    @Override
    public int pdV(){
        return 0;
    }
    @Override
    public int regen(){
        return 5;
    }
}
