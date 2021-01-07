package game;

import game.entity.mob.Mob;
import game.entity.mob.MobsBoard;
import game.entity.mob.MobsBuilder;
import game.entity.player.Player;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

/*
* Game has a fixed player, level which will increment every 3 fights (fightNumber)
* a score which increase when a mob is killed
* */
public class Game {

    protected Player player;
    private int level = 0;
    private int fightNumber = 0;
    private int score = 0;
    protected Mob mob;
    private static Game instance;
    private UI ui;

    static {
        try {
            instance = new Game();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected Game() throws InterruptedException {

        /*
        MobsBuilder mobsBuilder = new MobsBuilder();
        MobsBoard mobs = mobsBuilder.buildMobs(level);
        this.mob = mobs.getMob(0);
        this.mob.setHp(this.mob.getMaxHp()-10);

        this.player = Player.getInstance();
        player.setName("Basty");

        ui = new UI(this);

         */

        System.out.println("Choose your name");
        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();
        this.player = Player.getInstance();
        player.setName(name);
        loop();

    }

    /* Setup the Singleton design Pattern
    * */
    static Game getInstance()
    {
        return instance;
    }
    /* upgrade, allows the player to choose between 3 small upgrades every end of level
     * */
    private void upgrade() {

        loading();
        System.out.println("    -----------UPGRADE MENU-----------");
        player.getInfoPlayer();
        System.out.println("1 -> +10maxHp, 2 -> +5 attack, , 3 -> +3 bullets");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        if (action == 1){
            player.setMaxHp(player.getMaxHp()+10);
        }
        if (action == 2){
            player.setAttack(player.getAttack()+5);
        }
        if (action == 3){
            player.setAmmo(player.getAmmo()+3);
        }
        loading();

        System.out.println("    ----------SUCCESSFUL UPGRADE-----------");
        System.out.println("    ROUND: " + level);
    }

    /* wait: used to delay time
     * */
    private static void wait(int ms){
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /* loading: displays three points on the console, is used to make the action more visible
     * */
    private void loading(){
        System.out.println("                    .");
        wait(300);
        System.out.println("                    .");
        wait(300);
        System.out.println("                    .");
        wait(300);
    }

    /* fightStatus: gives the Hp of the mob and the player
     * */
    private void fightStatus(){
        System.out.print(player.getName() + " :"+ player.getHp() + "/"+ player.getMaxHp() + "HP");
        System.out.println("                        " + mob.getClass().getSimpleName() + " :"   +  mob.getHp() + "/"+ mob.getMaxHp() + "HP");
    }

    /* fight: starts the fight with a mob
     * */
    private void fight(Mob mob) {

        while(player.getHp() > 0 && mob.getHp() > 0)  //while the player and the mob are alive (the whole loop represents one turn)
        {
            System.out.println("        1 ATTACK, 2 DEFEND, 3 RELOAD");
            Scanner scanner = new Scanner(System.in);
            int action = scanner.nextInt(); //the player inputs his action
            //the mob chooses what does he do according to the current player stats
            loading();

            //1 = attack, 2 = protect , 3 = reload
            if (action == 1) {// if the player attacks
                player.setState("Attacking");
                System.out.print("YOU ATTACK");
                mobDoAction();
                player.attack(mob);
            }

            if (action == 2) {
                player.setState("Defending");
                System.out.print("YOU DEFEND");
                mobDoAction();
            }

            if (action == 3) {
                player.setState("Reloading");
                System.out.print("YOU RELOAD");
                mobDoAction();
                player.reload();
            }

            if (action == 4){                                                   // for testing purposes
                mob.setHp(0);
            }
            fightStatus();
            System.out.println("------------------------------------------------------"); // end of one turn
        }
    }

    /*
    * loop : this is the gameplay loop, she keeps turning until the player die or the max level is hit
    *
    *
    * */
    private void mobDoAction(){
        int mobAction = mob.rng(player);

        if (mobAction == 1) {//if both attack they both get hurt
            System.out.println("                        ENEMY ATTACKS");
            mob.attack(player);
        }
        if (mobAction == 2) { //nothing happens and player looses bullets
            System.out.println("                        ENEMY DEFENDS");
        }
        if (mobAction == 3) { //the mob reload and gets attacked
            System.out.println("                        ENEMY RELOADS");
            mob.reload();
        }
    }

    void loop() {
        while (player.getHp() > 0) {


            loading();
            System.out.println("Press enter when you are ready" );
            Scanner starter = new Scanner(System.in);
            String ready = starter.nextLine();
            System.out.println("----------------------GOOD LUCK----------------------" );
            MobsBuilder mobsBuilder = new MobsBuilder();
            MobsBoard mobs = mobsBuilder.buildMobs(level);
            player.getInfoPlayer();
            loading();

            while (fightNumber < 3) {    // every 3 fights

                mob = mobs.getMob(fightNumber);      //we pick one of the mobs in the board
                System.out.println("NEW ENEMY : " + mob.getQuality() + " " + mob.getClass().getSimpleName() + " dressed with " +
                        mob.getArmor().getClass().getSimpleName() + " and armed with " + mob.getWeapon().getClass().getSimpleName());
                loading();
                mob.getInfoMob();
                fight(mob);         //lauches the fight versus the mob

                if (player.getHp() <= 0) {          //if the player dies the game ends
                    System.out.println("---------------YOU DIED---------------");
                    System.out.println(score);

                }
                if (mob.getHp() <= 0) {       //if the mob dies

                    System.out.println(mob.getClass().getSimpleName() + " DEAD");
                    player.regeneration();              //the player regens Hp
                    loading();
                    int drop = (int)(Math.random() * 3);
                    switch(drop) {       //the player has 1/3 chance to drop the enemy weapon and 1/3 chances to drop the enemy armor

                        case 0:
                            System.out.println("???Do you want " + mob.getWeapon().getClass().getSimpleName() + "???");
                            System.out.print("Actual weapon : ");
                            player.getInfoWeapon();
                            System.out.print("New weapon : ");
                            mob.getInfoWeapon();
                            System.out.println("                            y/n");
                            Scanner scanner = new Scanner(System.in);
                            String choice = scanner.nextLine();

                            if (choice.equals("y")) {
                                player.setWeapon(mob.getWeapon());
                                System.out.println("you are equipped with : " + player.getWeapon().getClass().getSimpleName());
                                loading();
                            }
                            break;

                        case 1:
                            System.out.println("???Do you want " + mob.getArmor().getClass().getSimpleName() + "???");
                            System.out.print("Actual armor : ");
                            player.getInfoArmor();
                            System.out.print("New weapon : ");
                            mob.getInfoArmor();
                            System.out.println("                            y/n");
                            Scanner scannerArm = new Scanner(System.in);
                            String choiceArm = scannerArm.nextLine();
                            if (choiceArm.equals("y")) {
                                player.setArmor(mob.getArmor());
                                System.out.println("you are equipped with : " + player.getArmor().getClass().getSimpleName());
                                loading();
                            }
                            break;

                        case 2:
                            break;
                    }
                    player.getInfoPlayer();
                    loading();
                    System.out.println("STILL " + (2 - fightNumber) + " FIGHT BEFORE UPGRADING");
                    fightNumber += 1;    //the player goes to the next fight
                    score += 1;
                    loading();
                }
            }
            level += 1;     //the player goes to the next level, ennemy will be stronger
            fightNumber = 0;
            System.out.println("------------------------------------------------------");
            System.out.println("end of round");
            System.out.println("------------------------------------------------------");
            upgrade();      //the player can upgrade himself
        }
        System.out.println("WELL PLAYED YOU WON");
    }
}

