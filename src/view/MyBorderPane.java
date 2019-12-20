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

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import util.FileIO;

public class MyBorderPane extends BorderPane {
    private PictureGrid pictureGrid;
    private CountdownBar countDown;
    private Menu menu;
    private ResultPane resultPane;

    public MyBorderPane(double width, double height){
        super();
        pictureGrid = new PictureGrid(width * 0.9, height * 0.85);
        resultPane = new ResultPane(width * 0.9, height * 0.85);
        countDown = new CountdownBar(width * 0.9, height * 0.15);
        menu = new Menu(width*0.1, height);
        super.setPrefSize(width, height);
        super.setTop(countDown);
        super.setLeft(pictureGrid);
        super.setRight(menu);
        BackgroundImage bg= new BackgroundImage(new Image("file:" + FileIO.getFilePath("background.png"), width, height, true, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        super.setBackground(new Background(bg));
    }

    public PictureGrid getPictureGrid(){
        return this.pictureGrid;
    }

    public ResultPane getResultPane(){
        return this.resultPane;
    }

    public CountdownBar getCountDown(){
        return this.countDown;
    }

    public Menu getMenu(){
        return this.menu;
    }

    //reset the borderPane to be use again
    public void resetBorderPane(){
        resetPictureGrid();
        pictureGrid.setVisible(true);
        menu.getDifficultyMenu().setVisible(false);
    }

    //display the result pane in place of the pictureGrid
    public void showResultScene(){
        menu.setVisible(true);
        super.setLeft(resultPane);
    }

    //generate a new picture grid (randomize) and set the picture grid to the borderPane
    private void resetPictureGrid(){
        pictureGrid = new PictureGrid(super.getWidth() * 0.9, super.getHeight() * 0.85);
        super.setLeft(pictureGrid);
    }
}
