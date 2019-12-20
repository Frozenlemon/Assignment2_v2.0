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

package view.label;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CountdownLabel extends Label {
    private StringBuilder timer;

    public CountdownLabel(String time, double width, double height){
        super();
        timer = new StringBuilder(time);
        super.setPrefSize(width, height);
        super.setAlignment(Pos.CENTER);
        super.setText(timer.toString());
        super.setFont(Font.font("Verdana",50));
        super.setTextFill(Color.RED);
        super.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        super.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, new Insets(0,0,0,0))));
    }

    //set the timer display if the number are less than 10 then display that number with a 0 in front
    public void setTimer(Integer minute, Integer second, Integer hundredOfSec){
        String string_sec, string_hundredsOfSec;
        if (second < 10)
            string_sec = "0" + second.toString();
        else
            string_sec = second.toString();
        if (hundredOfSec < 10)
            string_hundredsOfSec = "0" + hundredOfSec.toString();
        else
            string_hundredsOfSec = hundredOfSec.toString();
        timer = new StringBuilder("REMAINING TIME: " + minute.toString() + ":" + string_sec + ":" + string_hundredsOfSec);
        super.setText(timer.toString());
    }

    public int getTimeScore(){
        String text = super.getText().substring(16);
        String[] times = text.split(":");
        int result = (Integer.valueOf(times[0]) * 60) + Integer.valueOf(times[1]);
        return result;
    }
}
