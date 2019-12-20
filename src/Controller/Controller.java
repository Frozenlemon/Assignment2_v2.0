/*
RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2019C
  Assessment: Assignment 2
  Author: Minh Le
  ID: s3757324
  Created  date: 20/12/2019
  Last modified: 16/12/2019
  Acknowledgement:
    Credit for all the picture use in the game
    dog picture:    https://www.ravenshuskyhavenandrescue.org/
                    https://www.womansday.com/life/pet-care/g26418410/small-dog-breeds/?slide=1
                    https://www.amazon.in/Sweet-Happy-Fluffy-White-Journal/dp/1544710909
                    https://www.pinterest.at/pin/515802963566662653/
                    https://www.freepik.com/free-psd/portrait-labrador-retriever-dog_3730283.htm
                    https://www.warrenphotographic.co.uk/41135-cute-yellow-labrador-puppy-with-paws-over
                    https://www.k9web.com/breeds/red-nose-pitbull/
                    https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR90Eg3NskmbjmtjlPVk3xSt5gWYX3KzmAh27nzlM13t3FbEAnc&s
                    http://www.vetstreet.com/dogs/bernese-mountain-dog
                    https://www.pinterest.com/jody_rael/dog/ placeholder
    Background picture:
                    https://janesvillevetclinic.com/aaha-coloring-challenge/pawprint-background/
    Volume on and off icon:
                    https://www.iconfinder.com/icons/1489330/audio_dynamic_high_volume_loud_sound_volume_icon
    "You win" and "you lost" picture:
                    http://thebenefitsourcellc.com/2018/02/20/inflation-you-lose/
                    https://www.freepik.com/premium-vector/cartoon-hand-drawn-win-symbol-with-stars-salute_4768343.htm
    Credit for the music use in the game: Athletic Theme - Yoshi's Island by Koji Kondo
*/

package Controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.MyBorderPane;
import view.PictureTile;

import java.awt.*;

public class Controller {

    private static Controller instance;
    private int turnUpCharges = 1;
    private int currentIndex = 0;
    private Integer[] turnUpPicId = {null, null};
    private Integer[] turnUpPicIndex = {null, null};
    private int level = 1;
    private Stage primaryStage;
    private MyBorderPane myBorderPane;
    private int pairCounter;
    private int score = 0;
    private int turnDelay;

    //default constructor for controller
    private Controller(){
        Dimension sceneSize =Toolkit.getDefaultToolkit().getScreenSize();
        double width = sceneSize.getWidth();
        double height = sceneSize.getHeight();
        myBorderPane = new MyBorderPane(width, height);
        primaryStage = new Stage();
        primaryStage.setTitle("Place holder title");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(myBorderPane, width, height));
    }

    //get the controller instance
    public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    //display the custom stage
    public void update(){
        primaryStage.show();
    }

    //handle event when user click on a picture
    public void clickOnPic(int tileLocation){
        if (turnUpCharges != -1) {
            if (currentIndex == 0)
                currentIndex = 1;
            else
                currentIndex = 0;

            //save the info of clicked picture into 2 array
            PictureTile clickedPictureTile = (PictureTile) myBorderPane.getPictureGrid().getChildren().get(tileLocation);
            turnUpPicIndex[currentIndex] = tileLocation;
            turnUpPicId[currentIndex] = clickedPictureTile.getPictureId();

            //check if there are 2 face up picture
            if (turnUpPicId[0] != null && turnUpPicId[1] != null){
                checkMatchedPic(currentIndex);
            }
            else{
                clickedPictureTile.turnPicture(currentIndex);
            }
        }
    }

    public int getPairCounter(){
        return pairCounter;
    }
    public int getTurnDelay(){
        return this.turnDelay;
    }

    //handle event when player click on start button
    public void clickStart(){
        pairCounter = 0;

        // set the delay between a picture turn face up and turn face down
        if (level == 1)
           turnDelay = 3000;
        else if (level == 2)
            turnDelay = 2000;
        else
            turnDelay = 1000;

        //start game counter and reset the picture grid
        myBorderPane.getCountDown().startCountdown(1,59,100);
        myBorderPane.resetBorderPane();
    }

    //handle event when the game ended via player complete 10 picture pair or the timer run out and display the updated score of the player
    public void finish(boolean playerWin, int roundScore){
        myBorderPane.getPictureGrid().setVisible(false);
        myBorderPane.getMenu().getDifficultyMenu().setVisible(true);
        if (playerWin){
            score += roundScore;
            myBorderPane.getResultPane().showResultScene(true, " YOUR CURRENT SCORE: " + score);
        }
        else {
            score -= 30;
            myBorderPane.getResultPane().showResultScene(false, " YOUR CURRENT SCORE: " + score);
        }
        myBorderPane.showResultScene();
    }

    public void increaseLevel(){
        if (level < 3)
            level++;
        myBorderPane.getCountDown().setLevelLabel(level);
    }

    public void decreaseLevel(){
        if (level > 1)
            level--;
        myBorderPane.getCountDown().setLevelLabel(level);
    }

    public void changeSoundSetting(){
        myBorderPane.getCountDown().changeSoundSetting();
    }

    public void addTurnUpCharge(){
        turnUpCharges++;
    }

    public void removeTurnUpCharge(){
        turnUpCharges--;
    }

    public Integer[] getTurnUpPicId(){
        return turnUpPicId;
    }

    public Integer[] getTurnUpPicIndex(){
        return turnUpPicIndex;
    }

    public String getLevel(){
        return "LEVEL " + level;
    }

    //check if the 2 picture turn up is the same.
    // If 2 picture are the same then perform special animation for the 2 cards, if not then set the regular turn animation for the most recent turn up picture
    private void checkMatchedPic(int currentIndex){
        if (turnUpPicId[0] == turnUpPicId[1]){
            PictureTile pictureTile2 = (PictureTile) myBorderPane.getPictureGrid().getChildren().get(turnUpPicIndex[0]);
            PictureTile pictureTile1 = (PictureTile) myBorderPane.getPictureGrid().getChildren().get(turnUpPicIndex[1]);
            pictureTile1.matchedCard();
            pictureTile2.matchedCard();
            for (int i = 0; i < turnUpPicId.length;  i++)
                turnUpPicId[i] = null;
            for (int i = 0; i < turnUpPicIndex.length;  i++)
                turnUpPicIndex[i] = null;
            turnUpCharges = 1;
            pairCounter++;
        }
        else {
            PictureTile pictureTile = (PictureTile) myBorderPane.getPictureGrid().getChildren().get(turnUpPicIndex[currentIndex]);
            pictureTile.turnPicture(currentIndex);
        }
    }
}
