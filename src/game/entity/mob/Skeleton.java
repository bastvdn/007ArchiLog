package game.entity.mob;

class Skeleton extends Mob {

    Skeleton(int level) {
        setLevel(level);
        double ratio = 1 + (float)(level)/2;
        setHp((int)(ratio*20));
        setAttack((int)(ratio*5));
        setCritical((int)(ratio*10));
        setMaxHp((int)(ratio*20));
    }
}
