package game;

import game.entity.mob.Mob;
import game.entity.mob.MobsBoard;
import game.entity.mob.MobsBuilder;
import game.entity.player.Player;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;



/*
* Game has a fixed player, level which will increment every 3 fights (fightNumber)
* a score which increase when a mob is killed
* */
public class Game{

    protected Player player;   //the unique player
    private int level = 0;      //the level of the game, increasing each 3 fights
    private int fightNumber = 0;
    private int score = 0;
    protected Mob mob;
    private static Game instance;   //Singleton game
    private final UI ui;                  //Creation of the UI
    protected int pointer =0;
    protected int turns = 0;


    /*
    * Singleton generation
    * */
    static {
        try {
            instance = new Game();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*
    * Class Constructor
    * initiate the player and UI, ask for the player name and launch the gameplay loop (loop())
    * */
    private Game() throws InterruptedException {

        ui = new UI(this);
        ui.announce("start");
        ui.resetDisplay();
        //We setup the UI

        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        while (action.length()>20){
            System.out.println("Name too long!");
            action = scanner.nextLine();
        }
        long startTime = System.nanoTime();
        this.player = Player.getInstance();
        player.setName(action);                         //We setup the player name
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in millis: " + elapsedTime/1000000);
        loop();                                         //game loop

    }

    /*
    * Setup the Singleton design Pattern
    * */
    static Game getInstance()
    {
        return instance;
    }

    /*
     * upgrade, allows the player to choose between 3 small upgrades every end of level
     * 1 -> +10maxHp, 2 -> +5 attack, , 3 -> +3 bullets
     *  */
    private void upgrade() {


        ui.resetActionDisplay();
        ui.announce("upgrade");             //setup UI
        ui.resetDisplay();

        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        while (0>=action || action >3){
            ui.announce("errorIn");
            ui.resetDisplay();
            action = scanner.nextInt();

        }

        if (action == 1){
            player.setMaxHp(player.getMaxHp()+10);      //buff the player according to the input
        }
        if (action == 2){
            player.setAttack(player.getAttack()+5);
        }
        if (action == 3){
            player.setAmmo(player.getAmmo()+3);
        }
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

    /*
     * fight: starts the fight with a mob
     * */
    private void fight(Mob mob) {

        while(player.getHp() > 0 && mob.getHp() > 0)  //while the player and the mob are alive (the whole loop represents one turn)
        {
            this.turns += 1;

            // setting up the UI
            ui.updatePlaDisplay();
            ui.updateEnnDisplay();
            ui.updateActDisplay();
            ui.resetDisplay();
            int action;
            Scanner scanner = new Scanner(System.in);
            while (true){
                try {
                    action = scanner.nextInt();
                    if(0<action && action < 5){
                        break;
                    }
                    ui.announce("errorIn");
                }
                catch (Exception e){
                    System.out.println("Bad input str");
                    action = -1;
                    break;

                }
                ui.resetDisplay();

            }

             //the player inputs his action
                                            //the mob chooses what does he do according to the current player stats

            //1 = attack, 2 = protect , 3 = reload
            if (action == 1) {               // if the player attacks
                player.setState("Attacking");
                ui.setPlayerAction(1);      //we modify the display (animation box)
                mobDoAction();              //mob generates an action
                player.attack(mob);         //the player wont deal dmg if the action generated by the mob was Defend
            }

            if (action == 2) {
                player.setState("Defending");
                ui.setPlayerAction(2);
                mobDoAction();
            }

            if (action == 3) {
                player.setState("Reloading");
                ui.setPlayerAction(3);
                mobDoAction();
                player.reload();
            }

            if (action == 4){                                                   // for testing purposes
                mob.setHp(0);           //one shots the mob
            }
            fightStatus();
            ui.updateAnimation();
            ui.updatePlaDisplay();

        }

        wait(500);
        ui.resetDisplay();   // we reset the display each turn
        this.turns = 0;
    }

    /*
    * generates the mob behaviour
    *
    * */
    private void mobDoAction(){
        int mobAction = mob.rng(player);

        if (mobAction == 1) {//if both attack they both get hurt
            ui.setMobAction(1);
            mob.attack(player);
        }
        if (mobAction == 2) { //nothing happens and player looses bullets
            ui.setMobAction(2);
        }
        if (mobAction == 3) { //the mob reload and gets attacked
            ui.setMobAction(3);
            mob.reload();
        }

    }

    /*
     * loop : this is the gameplay loop, she keeps turning until the player die or the max level is hit
     *
     * */
    void loop() {
        while (player.getHp() > 0) {                //while the player is alive
            long startTime = System.nanoTime();
            MobsBuilder mobsBuilder = new MobsBuilder();
            MobsBoard mobs = mobsBuilder.buildMobs(level);     //Generate a mob builder board containing all the mobs
            long timeElapsed = System.nanoTime() - startTime;
            System.out.println("Execution time of mob generation in millis  : " + timeElapsed/1000000);
            while (fightNumber < 3) {    // every 3 fights

                mob = mobs.getMob(fightNumber);      //we pick one of the mobs in the board according to the fight level
                ui.announce("mobInfo");           //setup UI

                fight(mob);                         //launches the fight versus the mob
                //fight ended
                if (player.getHp() <= 0) {          //if the player dies the game ends
                    ui.announce("plaDead");
                    ui.resetEnnDisplay();
                    ui.resetActionDisplay();
                    ui.resetAnimationsDisplay();
                    ui.resetDisplay();
                    wait(1500);
                }

                if (mob.getHp() <= 0) {       //if the mob dies
                    ui.announce("mobDead");

                    wait(1500);
                    ui.resetEnnDisplay();
                    ui.resetActionDisplay();
                    ui.resetAnimationsDisplay();
                    ui.resetDisplay();
                    wait(1500);

                    player.regeneration();              //the player regen Hp
                    ui.updatePlaDisplay();

                    ui.announce("regen");
                    wait(1500);
                    ui.resetDisplay();
                    wait(1500);


                    int drop = (int)(Math.random() * 3);        //item dropping
                    switch(drop) {       //the player has 1/3 chance to drop the enemy weapon and 1/3 chances to drop the enemy armor

                        case 0:
                            ui.announce("dropGun");
                            ui.resetDisplay();

                            Scanner scanner = new Scanner(System.in);
                            String choice = scanner.nextLine();

                            if (choice.equals("y")) {               //he chooses to get the item
                                player.setWeapon(mob.getWeapon());
                            }
                            break;

                        case 1:
                            ui.announce("dropArmor");
                            ui.resetDisplay();

                            Scanner scannerArm = new Scanner(System.in);
                            String choiceArm = scannerArm.nextLine();
                            if (choiceArm.equals("y")) {
                                player.setArmor(mob.getArmor());
                            }
                            break;

                        case 2:
                            break;
                    }       //end of drop

                    fightNumber += 1;    //the player goes to the next fight
                    score += 1;
                    //loading();
                }       //end of fight
            }        //end of 3 fights
            level += 1;     //the player goes to the next level, enemy will be stronger
            fightNumber = 0;
            upgrade();      //the player can upgrade himself
        }
        System.out.println("WELL PLAYED YOU WON");
    }
}

