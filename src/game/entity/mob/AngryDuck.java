package game.entity.mob;

class AngryDuck extends Mob{

    AngryDuck(int level) {
        setLevel(level);
        double ratio = 1 + (float)(level)/2;
        setHp((int)(ratio*25));
        setAttack((int)(ratio*6));
        setCritical((int)(ratio*10));
        setMaxHp((int)(ratio*25));
    }
}
