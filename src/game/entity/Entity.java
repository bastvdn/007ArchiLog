package game.entity;

import game.armor.Armor;
import game.weapon.Weapon;

/*
* Entity: each opponent in the game is an entity (the player and all of the mobs)
* they got:
* level: the level in which they are in
* hp: the entity health point, when they go to 0 the entity dies
* attack: the base value of health that the enemy will loose when the entity attacks
* ammo: the number of attack charges the entity has, if it goes to zero the entity cannot attack
* maxHp: the maximum health point an entity can get, Hp cannot be over maxHp
* regen: the base value of health the entity will regenerate after 1 fight
* critical: the chance of the entity attack hittting twice (chance of hitting twice= critical/100)
* weapon: the weapon of the entity, boosting the stats
* armor: the armor of the entity, boosting ths stats
* */
public abstract class Entity {
    private String state;
    public String getState() { return state;}
    public void setState(String state) {
        if (state.equals("Defending")||state.equals("Attacking")||state.equals("Reloading")){
            this.state = state;}
    }

    private int level;
    public int getLevel() { return level;}
    public void setLevel(int level) {this.level = level;}

    private String quality;
    public String getQuality() {return quality;}
    public void setQuality(String quality) {this.quality = quality;}

    private int hp;
    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    private int attack;
    public int getAttack() {return attack; }
    public void setAttack(int attack) { this.attack = attack; }

    private int ammo;
    public int getAmmo() {return ammo; }
    public void setAmmo(int ammo) { this.ammo = ammo; }

    private int maxHp;
    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    private int regen;
    public int getRegen() { return regen; }
    public void setRegen(int regen) { this.regen = regen; }

    private int critical;
    protected void setCritical(int critical) { this.critical = critical; }

    private Weapon weapon;
    public Weapon getWeapon() {return weapon;}
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    private Armor armor;
    public Armor getArmor() {return armor; }
    public void setArmor(Armor armor) {this.armor = armor;}

    /*
    *lifeSelfImpact: allows the user to heal or to get hurt when he attacks (according to the weapon he wields)
    * */
    private void lifeSelfImpact(){

        setHp(getHp()+getWeapon().lifeSelfImpact());
        if (getHp()>getMaxHp()){
            setHp(getMaxHp());
        }
    }
    /*
    * user regenerates Hp at the end of every fight (according to the armor he is equipped with and his base stats)
    * */
    public void regeneration(){
        int initHp = getHp();
        int regenTotal = (getArmor().regen()+getRegen());
        if(regenTotal != 0){
            setHp(getHp()+regenTotal);
            if (getHp()>getMaxHp()){
                setHp(getMaxHp());
            }
        }
    }

    /*
    * The entity that attacks lowers the enemy Hp according to his on damages, his weapon damages,
    * his critical chances and the armor and damage reduction of the enemy
    * The entity can also affect his own Hp
    * */
    public void attack(Entity enemy) {
        if (getAmmo()-getWeapon().bps() >= 0){

            setAmmo(getAmmo()-getWeapon().bps());

            if (!enemy.getState().equals("Defending")){
                int crit = 1;                                                                                                   //this is the critical counter
                int initHp = getHp();
                int initEnemyHp = enemy.getHp(); //if the random number between 1 and  100 is inferior to the crit chance, the attack will hit twice
                if ((int)(Math.random() * 101) < (critical+getWeapon().criticalStrike())) {
                    crit = 2;                                                                                           //critical counter goes to 2

                    lifeSelfImpact();                                                                                   //will hit twice if crit
                }
                lifeSelfImpact();
                float baseDamage= (getAttack() + crit*getWeapon().damage());                                            //the crit only works on the weapon damages
                int enemyDamageReduction = enemy.getArmor().damageReduced();
                float damageMultiplicator = (100/(100+(float)enemyDamageReduction));
                float trueDmg= damageMultiplicator*baseDamage;
                int trueDmgInt = (int)trueDmg;
                enemy.setHp(enemy.getHp() - trueDmgInt);                                                                //the enemy Hp are lowered according to : the attacker damages and weapon damages, the armor and damage reduction of the enemy


            }
        }

    }

    /* getInfoWeapon: gives the stats of the weapon
     * */
    public String getInfoWeapon(){

        System.out.println(getWeapon().getClass().getSimpleName() + "---> Additionnal damages :" + getWeapon().damage() +
                " Bullet cost :" + getWeapon().bps() + " LifeSteal :" + getWeapon().lifeSelfImpact());
        return null;
    }

    /* getInfoArmor: displays the stats of the armor
     * */
    public void getInfoArmor(){

        System.out.println(getArmor().getClass().getSimpleName() + "---> Damage raduction :" + getArmor().damageReduced() +
                " added maxHP :" + getArmor().pdV() + " regeneration :" + getArmor().regen());
    }

    /*
    * reload : reloads one bullet
    * */
    public void reload(){
        setAmmo(getAmmo()+1);
    }
}