package game.entity.mob;

class Zombie extends Mob {

    Zombie(int level) {
        setLevel(level);
        double ratio = 1 + (float)(level)/2;
        setHp((int)(ratio*30));
        setAttack((int)(ratio*4));
        setCritical((int)(ratio*15));
        setMaxHp((int)(ratio*30));
        }
}
