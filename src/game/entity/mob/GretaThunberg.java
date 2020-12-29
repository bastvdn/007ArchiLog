package game.entity.mob;

class GretaThunberg extends Mob{

    GretaThunberg(int level) {
        setLevel(level);
        double ratio = 1 + (float)(level)/2;
        setHp((int)(ratio*20));
        setAttack((int)(ratio*8));
        setCritical((int)(ratio*10));
        setMaxHp((int)(ratio*20));
    }
}
