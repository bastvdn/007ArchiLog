package game.entity.mob;

class  RedHead extends Mob {

    RedHead(int level) {
        setLevel(level);
        double ratio = 1 + (float)(level)/2;
        setHp((int)(ratio*15));
        setAttack((int)(ratio*5));
        setCritical((int)(ratio*10));
        setMaxHp((int)(ratio*15));
    }
}
