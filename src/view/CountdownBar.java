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
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import view.buttons.SoundButton;
import view.label.CountdownLabel;
import view.label.LevelLabel;

public class CountdownBar extends HBox {
    private Timeline timeline;
    private Integer minute = 1;
    private Integer second = 59;
    private Integer hundredthsOFSec = 100;
    private LevelLabel levelLabel;
    private CountdownLabel countDownLabel;
    private SoundButton soundButton;


     public CountdownBar(double width, double height){
         super();
         super.setPrefSize(width, height);
         super.setAlignment(Pos.CENTER);
         super.setSpacing(width * 0.15);
         levelLabel = new LevelLabel(width * 0.2 , height) ;
         countDownLabel = new CountdownLabel("REMAINING TIME: 2:00:00", width * 0.45, height);
         soundButton = new SoundButton(height);
         super.getChildren().addAll(levelLabel, countDownLabel, soundButton);
     }

     //set the timer and start the stop clock animation
     public void startCountdown(int minute, int second, int hundredthsOFSec){
         this.minute = minute;
         this.second = second;
         this.hundredthsOFSec = hundredthsOFSec;
         animateCountdown();
     }

     public void changeSoundSetting(){
         soundButton.changeSoundSetting();
     }

     public void animateCountdown(){
         if (timeline != null)
             timeline.stop();
         timeline = new Timeline();
         timeline.setCycleCount(Timeline.INDEFINITE);
         timeline.getKeyFrames().add(getCountDown());
         timeline.playFromStart();
     }

     public void setLevelLabel(int level){
         levelLabel.setText("LEVEL " + level);
     }

//     public void setCountDownLabel(){
//         String level = Controller.getInstance().getLevel();
//         switch (level){
//             case "LEVEL 1":
//                 countDownLabel.setText("REMAINING TIME: 2:00:00");
//                 break;
//             case "LEVEL 2":
//                 countDownLabel.setText("REMAINING TIME: 1:30:00");
//                 break;
//             case "LEVEL 3":
//                 countDownLabel.setText("REMAINING TIME: 1:00:00");
//         }
//     }

    //generate the Keyframe for the countdown clock also set up stop condition for the clock
     private KeyFrame getCountDown(){
         KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.01), e -> {
             hundredthsOFSec--;
             countDownLabel.setTimer(minute, second, hundredthsOFSec);
             if (Controller.getInstance().getPairCounter() == 10){
                 timeline.stop();
                 Controller.getInstance().finish(true, countDownLabel.getTimeScore());
             }
             if (hundredthsOFSec == 0 ){
                 if (minute == 0 && second ==0) {
                     timeline.stop();
                     Controller.getInstance().finish(false, 0);
                 }
                 else if (second == 0 && minute != 0){
                     minute--;
                     second = 60;
                 }
                 else
                     second--;
                 hundredthsOFSec = 100;
             }
         });
         return keyFrame;
     }
}
