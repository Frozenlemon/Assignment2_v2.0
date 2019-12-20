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

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.buttons.DecreaseDifButton;
import view.buttons.IncreaseDifButton;
import view.buttons.QuitButton;
import view.buttons.StartButton;

public class Menu extends VBox {

    private VBox difficultyMenu;
    private VBox quitGame;

    public Menu(double width, double height){
        super();
        super.setPrefSize(width, height);
        difficultyMenu = initDifficultyMenu(width, height*0.8);
        quitGame = initQuitMenu(width, height * 0.2);
        super.getChildren().addAll(difficultyMenu, quitGame);
        super.setSpacing(width*0.1);
    }

    public VBox getDifficultyMenu(){
        return this.difficultyMenu;
    }

    private VBox initDifficultyMenu(double width, double height) {
        VBox difficultyMenu = new VBox();
        difficultyMenu.setPrefSize(width, height);
        difficultyMenu.setAlignment(Pos.CENTER);
        difficultyMenu.setSpacing(20);
        difficultyMenu.getChildren().addAll(new IncreaseDifButton(), new DecreaseDifButton(), new StartButton());
        for (Node node : difficultyMenu.getChildren()) {
            ((Button) node).setPrefSize(width, height / 3);
        }
        return difficultyMenu;
    }

    private VBox initQuitMenu(double width, double height){
        VBox quit = new VBox();
        quit.setPrefSize(width, height );
        quit.setAlignment(Pos.CENTER);
        quit.setSpacing(20);
        quit.getChildren().addAll(new QuitButton());
        ((Button) quit.getChildren().get(0)).setPrefSize(width, height-40);
        return quit;
    }
}
