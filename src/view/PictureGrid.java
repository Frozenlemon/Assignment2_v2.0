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
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PictureGrid extends Pane {

    private static final int NUM_PER_ROW = 4;
    private static final int NUM_OF_PAIR = 10;
    double width, height;

    public PictureGrid(double x, double y){
        super();
        super.setPrefSize(x, y);
        super.setVisible(false);
        this.width = x;
        this.height = y;

        List<PictureTile> pictureTiles = initTiles();
        Collections.shuffle(pictureTiles);
        setTilesLayout(pictureTiles);
    }

    //Move each tile to the designated position in the grid and add them to the pane
    private void setTilesLayout(List<PictureTile> pictureTiles){
        for (int i = 0; i < pictureTiles.size(); i++){
            int index = i;
            PictureTile pictureTile = pictureTiles.get(i);
            pictureTile.setTranslateX((width/4) * (i % NUM_PER_ROW));
            pictureTile.setTranslateY((height * 0.2) * (i / NUM_PER_ROW));
            getChildren().add(pictureTile);
            pictureTile.setOnMouseClicked(e -> Controller.getInstance().clickOnPic(index));
        }
    }

    //generate number of tiles base on number of pairs needed
    private List<PictureTile> initTiles(){
        List<PictureTile> pictureTiles = new ArrayList<>();
        for (int i = 0; i < NUM_OF_PAIR; i++){
            pictureTiles.add(new PictureTile(i, width * 0.25, height * 0.18));
            pictureTiles.add(new PictureTile(i, width * 0.25,height * 0.18));
        }
        return pictureTiles;
    }


}
