package View;

import Main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static Main.Main.*;


public class HowToPlayController implements Initializable {
    @FXML
    public AnchorPane howtopane;
    public Button b_letsgo;

    /**Implement the interface Initializable, set the default settings for window the moment of initialize.*/
    @Override
    public void initialize(URL location, ResourceBundle resourcs) {
        music.setMute(true);
        addAdjustmentsToButton(b_letsgo,howtopane);
        b_letsgo.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            SwitchToMazeScene();
            Main.setMazeMusic();
            MazeController.generateMaze(); });
        b_letsgo.addEventHandler(MouseEvent.MOUSE_ENTERED,e-> setButtonPulseEffect(b_letsgo));

        howtopane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                b_letsgo.setLayoutY(865*(newSceneHeight.doubleValue()/1080));
            }
        });
        howtopane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                b_letsgo.setLayoutX(684*(newSceneWidth.doubleValue()/1920));
            }
        });
    }
}
