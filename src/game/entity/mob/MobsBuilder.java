package game.entity.mob;

/*
* MobsBuilder will create à mob board of 3 mobs according to the current level of the game
* */
public class MobsBuilder {

    public MobsBoard buildMobs(int level){

        MobsBoard mobs = new MobsBoard();
        int MAX_MOB = 7;
        int MIN_MOB = 1;

        for(int i= 0; i< 3; i++){                                                               //adding the 3 mobs

            switch (MIN_MOB + (int) (Math.random() * ((MAX_MOB - MIN_MOB) + 1))) {
                case 1 -> mobs.addMob(new Zombie(level));
                case 2 -> mobs.addMob(new AngryDuck(level));
                case 3 -> mobs.addMob(new Skeleton(level));
                case 4 -> mobs.addMob(new GretaThunberg(level));
                case 5 -> mobs.addMob(new Poutine(level));
                case 6 -> mobs.addMob(new RandomDrunkDude(level));
                case 7 -> mobs.addMob(new RedHead(level));
            }
        }
        return mobs;
    }
}