package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.IOException;



public class UI {
    Game game;
    String fullString = "";
    int aniSize;
    int idAnn;
    List<String> borderWindow = new ArrayList<String>();
    List<String> announcement= new ArrayList<String>();
    List<String> announcementList= new ArrayList<String>(10);
    List<String> ennemy = new ArrayList<String>();
    List<String> animations = new ArrayList<String>();
    List<String> player = new ArrayList<String>();
    List<String> actions = new ArrayList<String>();
    List<String> actionList = new ArrayList<String>();

    public int getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(int playerAction) {
        this.playerAction = playerAction;
    }

    int playerAction = 0;

    int width = 100;
    int height = 20;
    String mode;

    public UI(Game game0) throws InterruptedException {
        game = game0;

        resetBorderDisplay();
        resetEnnDisplay();
        resetAnnouncementDisplay();
        resetAnimationsDisplay();
        resetPlaDisplay();
        resetActionDisplay();
        /*
        updateEnnDisplay();
        updatePlaDisplay();
        */
        //display();

    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

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

    public void resetBorderDisplay(){
        String str = "  ┌";
        for (int c = 0; c < this.width; c++) {
            str += "─";

        }
        str += "┐ ";
        borderWindow.add(str);

        for (int c = 0; c < this.height; c++) {
            String str0 = " *│";
            for (int v = 0; v < this.width; v++) {
                str0 += " ";
            }
            str0 += "│*";
            borderWindow.add(str0);
        }

        String str2 = "  └";
        for (int c = 0; c < this.width; c++) {
            str2 += "─";
        }
        str2 += "┘ ";
        borderWindow.add(str2);


    }

    public void resetEnnDisplay(){
        String strEnn0 = "┌";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strEnn0 += "─";
        }
        strEnn0 += "┐";
        String strEnn1 = "│";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strEnn1 += " ";
        }
        strEnn1 += "│";
        String strEnn2 = "│";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strEnn2 += " ";
        }
        strEnn2 += "│";

        String strEnn3 = "└";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strEnn3 += "─";
        }
        strEnn3 += "┘";

        ennemy.add(0,strEnn0);
        ennemy.add(1,strEnn1);
        ennemy.add(2,strEnn2);
        ennemy.add(3,strEnn3);
    }

    public void resetPlaDisplay(){
        String strPla0 = "┌";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strPla0 += "─";
        }
        strPla0 += "┐";
        String strPla1 = "│";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strPla1 += " ";
        }
        strPla1 += "│";
        String strPla2 = "│";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strPla2 += " ";
        }
        strPla2 += "│";

        String strPla3 = "└";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strPla3 += "─";
        }
        strPla3 += "┘";

        player.add(0,strPla0);
        player.add(1,strPla1);
        player.add(2,strPla2);
        player.add(3,strPla3);
    }

    public void resetAnimationsDisplay(){
        this.aniSize = this.height - 15;
        for (int v = 0; v < this.aniSize; v++) {
            String strAni0 = "";
            for (int c = 0; c < (this.width/2); c++) {
                strAni0 += " ";
            }
            animations.add(v,strAni0);
        }



    }

    public void resetAnnouncementDisplay(){
        String strAnn0 = "┌";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strAnn0 += "─";
        }
        strAnn0 += "┐";
        announcement.add(strAnn0);

        for (int v = 0; v < (this.height-2) - 7; v++) {
            String strAnn1 = "│";
            for (int c = 0; c < (this.width/2)-2; c++) {
                strAnn1 += " ";

            }
            strAnn1 += "│";
            announcement.add(strAnn1);
        }
        String strAnn2 = "└";
        for (int c = 0; c < (this.width/2)-2; c++) {
            strAnn2 += "─";
        }
        strAnn2 += "┘";
        announcement.add(strAnn2);




    }

    public void resetActionDisplay(){
        this.actionList.add(0,"Nothing");
        this.actionList.add(1,"Attack");
        this.actionList.add(2,"Defend");
        this.actionList.add(3,"Reload");



        String strAct0 = "┌";
        for (int c = 0; c < this.width-2; c++) {
            strAct0 += "─";
        }
        strAct0 += "┐";
        actions.add(strAct0);

        for (int v = 0; v < (this.height-(this.aniSize + 8))-2 ; v++) {
            String strAct1 = "│";
            for (int c = 0; c < this.width-2; c++) {
                strAct1 += " ";

            }
            strAct1 += "│";
            actions.add(strAct1);
        }
        String strAct2 = "└";
        for (int c = 0; c < this.width-2; c++) {
            strAct2 += "─";
        }
        strAct2 += "┘";
        actions.add(strAct2);
    }

    public void updateEnnDisplay(){
        String bullets = "bullets : ";
        if (this.game.turns == 1){
            bullets += Integer.toString(this.game.mob.getAmmo());


        } else {
            bullets += "?";
        }

        String stat = this.game.mob.getClass().getSimpleName() + "  " + Integer.toString(this.game.mob.getHp()) + "/"+ Integer.toString(this.game.mob.getMaxHp())
                + "  " + bullets;
        this.ennemy.set(1,"│"+String.format("%"+ Integer.toString((this.width/2)-2)+ "s",stat)+"│");
        //██░

        String str = "";


        int max = ((this.width/2)-2)/2;
        float ratio = (float)this.game.mob.getHp()/this.game.mob.getMaxHp();
        int cur = (int) (ratio * max);
        int mis = max - cur;
        for (int c = 0; c < cur; c++) {
            str += "█";

        }
        for (int c = 0; c < mis; c++) {
            str += "░";

        }

        this.ennemy.set(2,"│"+String.format("%"+ Integer.toString((this.width/2)-2)+ "s",str)+"│");

    }

    public void updatePlaDisplay(){

        String stat = this.game.player.getName()+ "  " + Integer.toString(this.game.player.getHp()) + "/"+ Integer.toString(this.game.player.getMaxHp());
        this.player.set(1,"│"+String.format("%-"+ Integer.toString((this.width/2)-2)+ "s",stat)+"│");
        //██░

        String str = "";


        int max = ((this.width/2)-2)/2;
        float ratio = (float)this.game.player.getHp()/this.game.player.getMaxHp();
        int cur = (int) (ratio * max);
        int mis = max - cur;
        for (int c = 0; c < cur; c++) {
            str += "█";

        }
        for (int c = 0; c < mis; c++) {
            str += "░";

        }

        this.player.set(2,"│"+String.format("%-"+ Integer.toString((this.width/2)-2)+ "s",str)+"│");

    }

    public void updateAnimation(int ennemyAction){
        String ally = this.actionList.get(this.playerAction);
        String enn = this.actionList.get(ennemyAction);

        String tot = "          YOU "+ ally+"       ENNEMY "+ enn;
        this.animations.set(2, String.format("%-"+ Integer.toString((this.width/2))+ "s",tot));


    }

    public void loading(){

        int lr = this.width/8;

        this.animations.set(2,"│"+String.format("%"+ Integer.toString(lr)+"s","\uD83D\uDEE1️")+"│");
        resetDisplay();
        wait(1000);
        this.animations.set(2,"│"+String.format("%"+ Integer.toString(lr*2)+"s","░")+"│");
        resetDisplay();
        wait(1000);
        this.animations.set(2,"│"+String.format("%"+ Integer.toString(lr*3)+"s","░")+"│");
        resetDisplay();
    }

    public void updateAnnouncementDisplay(){
        for(int i = 0; i < (this.height-2) - 7 && i<=announcementList.size()-1; i++){

            this.announcement.set(11-i,"│"+String.format("%-"+ Integer.toString((this.width/2)-2)+ "s",this.announcementList.get(announcementList.size()-(i+1)))+"│");

        }





    }

    public void announce(String s){


        if(s.equals("mobInfo")){
            String str = game.mob.getQuality()+ " " +game.mob.getClass().getSimpleName() + " : "+ game.mob.getHp() + "/"+ game.mob.getMaxHp() +
                    "HP";
            String str1 = "bullets: " + game.mob.getAmmo() + "   attaque: "+ game.mob.getAttack();
            this.announcementList.add("you encounter a NEW ENNEMY");

            this.announcementList.add("↓   ↓   ↓   ↓   ↓   ↓   ↓ ");
            this.announcementList.add(str);
            this.announcementList.add(str1);
            this.announcementList.add("");


            idAnn += 2;
        }

        if(s.equals("mobDead")){
            String str = "YOU KILLED "+ game.mob.getQuality()+ " " +game.mob.getClass().getSimpleName();
            String str1 = "bullets: " + game.mob.getAmmo() + "   attaque: "+ game.mob.getAttack();



            this.announcementList.add(str);

            this.announcementList.add("");


            idAnn += 2;
        }

        if (s.equals("dropGun")){

            String str = "You Droped : " + game.mob.getWeapon().getClass().getSimpleName() + " Do you want it?" ;

            String str1 = "current weapon : " +game.player.getWeapon().getClass().getSimpleName() + "--> Additionnal damages :" + game.player.getWeapon().damage();

            String str2 = " Bullet cost :" + game.player.getWeapon().bps() +" LifeSteal :" + game.player.getWeapon().lifeSelfImpact();

            String str3= game.mob.getWeapon().getClass().getSimpleName()  + "--> Additionnal damages :" + game.mob.getWeapon().damage();

            String str4 = " Bullet cost :" + game.mob.getWeapon().bps() +" LifeSteal :" + game.mob.getWeapon().lifeSelfImpact();

            //mob.getInfoWeapon();
            String str5 = "                            Y/N";
            this.announcementList.add(str);
            this.announcementList.add(str1);
            this.announcementList.add(str2);
            this.announcementList.add("");
            this.announcementList.add(str3);
            this.announcementList.add(str4);
            this.announcementList.add(str5);



        }
        if (s.equals("dropArmor")){

            String str = "You Droped : " + game.mob.getArmor().getClass().getSimpleName() + " Do you want it?" ;

            String str1 = "current armor : " +game.player.getArmor().getClass().getSimpleName() + "--> damage reduction :" + game.player.getArmor().damageReduced();

            String str2 = " added maxHP :" + game.player.getArmor().pdV() + " regeneration :" + game.player.getArmor().regen();

            String str3= game.mob.getArmor().getClass().getSimpleName()  + "--> damage reduction :" + game.mob.getArmor().damageReduced();

            String str4 = " added maxHP :" + game.mob.getArmor().pdV() + " regeneration :" + game.mob.getArmor().regen();

            //mob.getInfoWeapon();
            String str5 = "                            Y/N";
            this.announcementList.add(str);
            this.announcementList.add(str1);
            this.announcementList.add(str2);
            this.announcementList.add("");
            this.announcementList.add(str3);
            this.announcementList.add(str4);
            this.announcementList.add(str5);



        }
        if(s.equals("upgrade")){
            String str0 ="    -----------UPGRADE MENU-----------";

            String str1 = "1 -> +10maxHp, 2 -> +5 attack, , 3 -> +3 bullets";
            this.announcementList.add(str0);
            this.announcementList.add(str1);
            this.announcementList.add("");



        }

        if (s.equals("plaDead")){
            String str0 = "----------------------------";
            String str1 ="          YOU DIED";
            String str2 = "----------------------------";
            this.announcementList.add("");
            this.announcementList.add(str0);
            this.announcementList.add(str1);
            this.announcementList.add(str2);

            this.announcementList.add("");


        }
        updateAnnouncementDisplay();


    }

    public void updateActDisplay(){
        actions.set(2,"│"+String.format("%-"+ Integer.toString(this.width-2)+ "s","ATTACK")+"│");
        actions.set(3,"│"+String.format("%-"+ Integer.toString(this.width-2)+ "s","DEFEND")+"│");
        actions.set(4,"│"+String.format("%-"+ Integer.toString(this.width-2)+ "s","RELOAD")+"│");

    }



    public void resetDisplay(){

        fullString = "";

        for (int c = 0; c < this.height+2; c++) {
            if(c == 0){
                fullString += this.borderWindow.get(c);
                fullString += "\n";
                //System.out.println(this.borderWindow.get(0));

            }

            else if(c  < 5 && c >0){
                fullString += this.borderWindow.get(c).substring(0,3);
                fullString += this.ennemy.get(c-1);
                fullString += this.announcement.get(c-1);
                fullString += this.borderWindow.get(c).substring(this.width+3);
                fullString += "\n";

                //System.out.println(this.borderWindow.get(0));

            }
            else if(c >= 5 && c < this.aniSize + 5) {
                fullString += this.borderWindow.get(c).substring(0, 3);
                fullString += this.animations.get(c - 5);
                fullString += this.announcement.get(c-1);
                fullString += this.borderWindow.get(c).substring(this.width + 3);
                fullString += "\n";

                //System.out.println(this.borderWindow.get(0));

            } else if(c >= this.aniSize + 5 && c <this.aniSize + 9) {
                fullString += this.borderWindow.get(c).substring(0,3);
                fullString += this.player.get(c-(this.aniSize + 5));
                fullString += this.announcement.get(c-1);
                fullString += this.borderWindow.get(c).substring(this.width+3);
                fullString += "\n";



            } else if (c != this.height+1){
                fullString += this.borderWindow.get(c).substring(0,3);

                fullString += this.actions.get(c-(this.aniSize + 9));
                fullString += this.borderWindow.get(c).substring(this.width+3);
                fullString += "\n";
            } else {
                fullString += this.borderWindow.get(c);
                fullString += "\n";
            }
            /*


             */



        }
        display();





    }

    public void display(){
        clearConsole();
        System.out.println(fullString);

    }

}

