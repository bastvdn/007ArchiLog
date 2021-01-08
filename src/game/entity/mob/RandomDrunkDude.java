package game.entity.mob;

class RandomDrunkDude extends Mob {

    RandomDrunkDude(int level) {
        setLevel(level);
        double ratio = 1 + (float)(level)/2;
        setHp((int)(ratio*30));
        setAttack((int)(ratio*7));
        setCritical((int)(ratio*10));
        setMaxHp((int)(ratio*30));
    }
}
