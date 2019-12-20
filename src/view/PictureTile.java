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

package view;

import Controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PictureTile extends StackPane {
    private MyImageView faceUp, faceDown;
    private int pictureId;
    private Timeline timeline;
    private int transitionCounter = 0;
    private boolean inTransit, isTurnUp;

    public PictureTile(int index, double x, double height){
        super.setPrefSize(x, height);
        inTransit = false;
        isTurnUp = false;
        this.pictureId = index;
        faceDown = new MyImageView(height, "placeholder");
        faceUp = new MyImageView(height, index + "");
        faceUp.setScaleX(0);
        setAlignment(Pos.CENTER);
        getChildren().addAll(faceDown, faceUp);
    }

    public int getPictureId(){
        return this.pictureId;
    }

    //start special turning animation when the card are matched with another
    public void matchedCard(){
        if (timeline != null)
            timeline.stop();
        inTransit = false;
        transitionCounter = 0;
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(getMatchKeyFrame());
        timeline.playFromStart();
    }

    //start the regular turning animation
    public void turnPicture(int index){
        if (!inTransit) {
            if (timeline != null)
                timeline.stop();
            transitionCounter = 0;
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(getTurnKeyFrame(index));
            timeline.playFromStart();
        }
    }

    //Special KeyFrame animation for when this card are matched with another card. The card are 1st turn up and then disappear
    //from the player screen
    private KeyFrame getMatchKeyFrame(){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), e -> {
            inTransit  = true;
            transitionCounter++;
            if (!isTurnUp){
                hidePicture(faceDown, faceUp);
                isTurnUp = true;
            }
            if (transitionCounter == 30) {
                super.setVisible(false);
                timeline.stop();
            }
        });
        return keyFrame;
    }

    //Generate the KeyFrame for each card. The card are 1st turn face up and then wait for a certain duration base on setting in the controller
    // after the duration the card is then turn face down again
    private KeyFrame getTurnKeyFrame(int index){
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), e->{
            inTransit = true;
            transitionCounter++;
            if (transitionCounter == 1) {
                hidePicture(faceDown, faceUp);
                isTurnUp = true;
                Controller.getInstance().removeTurnUpCharge();
            }
            //get input from controller for how long the wait will be. the current setup each card are running at 20 frame per second
            else if (transitionCounter == Controller.getInstance().getTurnDelay() / 50){
                hidePicture(faceUp, faceDown);
                isTurnUp = false;
                inTransit = false;
                Controller.getInstance().addTurnUpCharge();
                Controller.getInstance().getTurnUpPicId()[index] = null;
                Controller.getInstance().getTurnUpPicIndex()[index] = null;
                timeline.stop();
            }
        });
        return keyFrame;
    }

    //scale animation imitating the flipping animation. this part hiding the side currently facing up
    private void hidePicture(MyImageView picBefore, MyImageView picAfter){
        ScaleTransition transition = new ScaleTransition(Duration.millis(200), picBefore);
        transition.setFromX(1);
        transition.setToX(0);
        transition.setOnFinished(e -> showPicture(picAfter));
        transition.play();
    }

    //the second part of the scale animation. This part show the size that were facing down
    private void showPicture(MyImageView picture){
        picture.setScaleX(0);
        ScaleTransition transition = new ScaleTransition(Duration.millis(200), picture);
        transition.setFromX(0);
        transition.setToX(1);
        transition.play();
    }
}
