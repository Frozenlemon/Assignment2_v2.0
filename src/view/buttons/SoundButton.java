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

package view.buttons;

import Controller.Controller;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import util.FileIO;

public class SoundButton extends ImageView {

    private Image soundOn, soundOff;
    private boolean isOn;
    private GameMusic gameMusic;

    public SoundButton(double height){
        super();
        soundOn = new Image("file:" + FileIO.getFilePath("sound_on.png"));
        soundOff = new Image("file:" + FileIO.getFilePath("sound_off.png"));
        isOn = true;
        gameMusic = new GameMusic();

        super.setPreserveRatio(true);
        super.setFitHeight(height);

        super.setImage(soundOn);
        super.setOnMouseClicked(event -> {
            Controller.getInstance().changeSoundSetting();
        });
    }

    //flip between 2 image representing "sound off" and "sound on". The selection is base on the current state of the player
    public void changeSoundSetting(){
        if (isOn) {
            super.setImage(soundOff);
            gameMusic.stopMusic();
            isOn = false;

        }
        else {
            super.setImage(soundOn);
            gameMusic.playMusic();
            isOn = true;
        }
    }

    //Music class responsible to play the music.
    //Nested class due to the only class has interaction with the player is the sound button
    private class GameMusic {

        private Media musicFile;
        private final MediaPlayer PLAYER;

        private GameMusic(){
            musicFile = new Media(FileIO.getMusicURI().toString());
            PLAYER = new MediaPlayer(musicFile);
            PLAYER.setAutoPlay(true);
            PLAYER.setVolume(0.2);
            PLAYER.setCycleCount(Timeline.INDEFINITE);
        }

        private void stopMusic(){
            if (PLAYER != null)
                PLAYER.pause();
        }

        private void playMusic(){
            if (PLAYER != null)
                PLAYER.play();
        }
    }
}
