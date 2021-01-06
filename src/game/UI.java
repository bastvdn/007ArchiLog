package game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UI {
    Game game;
    String fullString = "";
    int aniSize;
    List<String> borderWindow = new ArrayList<String>();
    List<String> announcement= new ArrayList<String>();
    List<String> ennemy = new ArrayList<String>();
    List<String> animations = new ArrayList<String>();
    List<String> player = new ArrayList<String>();
    List<String> actions = new ArrayList<String>();

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

        ennemy.add(strEnn0);
        ennemy.add(strEnn1);
        ennemy.add(strEnn2);
        ennemy.add(strEnn3);
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

        player.add(strPla0);
        player.add(strPla1);
        player.add(strPla2);
        player.add(strPla3);
    }

    public void resetAnimationsDisplay(){
        this.aniSize = this.height - 15;
        for (int v = 0; v < this.aniSize; v++) {
            String strAni0 = "";
            for (int c = 0; c < (this.width/2); c++) {
                strAni0 += " ";
            }
            animations.add(strAni0);
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

        String stat = this.game.mob.getClass().getSimpleName() + "  " + Integer.toString(this.game.mob.getHp()) + "/"+ Integer.toString(this.game.mob.getMaxHp());
        this.ennemy.set(1,"│"+String.format("%-"+ Integer.toString((this.width/2)-2)+ "s",stat)+"│");
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

        this.ennemy.set(2,"│"+String.format("%-"+ Integer.toString((this.width/2)-2)+ "s",str)+"│");

    }

    public void updatePlaDisplay(){

        String stat = this.game.player.getName()+ "  " + Integer.toString(this.game.player.getHp()) + "/"+ Integer.toString(this.game.player.getMaxHp());
        this.player.set(1,"│"+String.format("%"+ Integer.toString((this.width/2)-2)+ "s",stat)+"│");
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

        this.player.set(2,"│"+String.format("%"+ Integer.toString((this.width/2)-2)+ "s",str)+"│");

    }

    public void updateActDisplay(){
        actions.set(2,"│"+String.format("%-"+ Integer.toString(this.width-2)+ "s","ATTACK")+"│");
        actions.set(3,"│"+String.format("%-"+ Integer.toString(this.width-2)+ "s","DEFEND")+"│");
        actions.set(4,"│"+String.format("%-"+ Integer.toString(this.width-2)+ "s","RELOAD")+"│");

    }



    public void display(){
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
        System.out.println(fullString);





    }

}
