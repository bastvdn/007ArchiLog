package game.entity.mob;

class Poutine extends Mob{

    Poutine(int level) {
        setLevel(level);
        double ratio = 1 + (float)(level)/2;
        setHp((int)(ratio*20));
        setAttack((int)(ratio*10));
        setCritical((int)(ratio*20));
        setMaxHp((int)(ratio*20));
    }
}
