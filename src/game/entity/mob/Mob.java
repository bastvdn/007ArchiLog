package game.entity.mob;

import game.entity.Entity;
/*
 * Mob is an entity who won't be playable, his action will be determined with a small AI
 * they also got an armor and a weapon, as all the entity does
 *
 * */
public abstract class Mob extends Entity {

    public Mob() {
    }
    /*
    * return the action of the mob according to several enemy and mob stats
    *
    * */
    public int rng(Entity player) {

        if (player.getAmmo()<player.getWeapon().bps() && getAmmo()>=getWeapon().bps()) {                     //if both got no bullets the mob reloads
            return chooseAction(175, 0, 100);
        }

        if (getAmmo()<getWeapon().bps()){
            if (player.getAmmo()<player.getWeapon().bps()){                     //if both got no bullets the mob reloads
                return 3;
            }
            return chooseAction(0,125,175);
        }
        if (getAmmo()>(2*getWeapon().bps())){                                   //if the mob has enough bullets to hit twice he will be more likely to attack

            return chooseAction(100,50,25);
        }
        if (player.getAmmo()<getAmmo()){                                        //if the mob has more bullets than the player he will be more likely to attack
            return chooseAction(150,100,100);
        }

        return chooseAction(100,100,100);

    }
    /*
    * chooseAction : return 1 (attack) , 2 (defend) , 3 (reload) according to the ratio between the three inputs (attaque, defense, recharge)
    *
    * */
    private int chooseAction(int attaque,int defense, int recharge){

        int min = 1;
        int rangeAtt = attaque - min + 1;
        int rangeDef = defense - min + 1;
        int rangeRech = recharge - min + 1;
        float chanceAtt = min + (int)(Math.random() * rangeAtt);
        float chanceDef = min + (int)(Math.random() * rangeDef);
        float chanceRech = min + (int)(Math.random() * rangeRech);
        if ((chanceAtt > chanceDef) && (chanceAtt > chanceRech)){
            setState("Attacking");
            return 1;
        }
        if ((chanceDef > chanceRech) && (chanceDef > chanceAtt)){
            setState("Defending");
            return 2;
        }
        if ((chanceRech > chanceDef) && (chanceRech > chanceAtt)){
            setState("Reloading");
            return 3;
        }
        else{
            return 3;
        }
    }

    /* getInfoMob: gives the stats of the mob
     * */
    public String getInfoMob(){
        return getQuality()+ " " +getClass().getSimpleName() + " : "+ getHp() + "/"+ getMaxHp() +
                "HP     bullets: " + getAmmo() + "   attaque: "+ getAttack();

    }


}
