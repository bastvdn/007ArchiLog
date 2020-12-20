package game.armor;

public class InvisibilityCloak implements Armor{
    @Override
    public int damageReduced(){
        return 75;
    }
    @Override
    public int pdV(){
        return 20;
    }
    @Override
    public int regen(){
        return 40;
    }
}
