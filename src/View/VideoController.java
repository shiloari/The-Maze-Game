package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Main.Main.*;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;

public class VideoController implements Initializable {


    public AnchorPane video;
    public Label l_video;
    public Button BackButton;
    public CheckBox cb_fullscreen;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        addAdjustmentsToButton(BackButton,video);
        addAdjustmentsToButton(l_video,video);
        addAdjustmentsToButton(cb_fullscreen,video);
        if(isFullscreen())
            cb_fullscreen.setSelected(true);
        else
            cb_fullscreen.setSelected(false);
        getWindow().fullScreenProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue)
                cb_fullscreen.setSelected(true);
            else
                cb_fullscreen.setSelected(false);
        });
        cb_fullscreen.addEventHandler(MOUSE_CLICKED,event -> {
            changeToFullscreen();
        });
        BackButton.addEventHandler(MOUSE_CLICKED, event -> {
            switchScene("Settings.fxml");
        });
        BackButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(BackButton));
        video.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                BackButton.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                l_video.setLayoutY(468*(newSceneHeight.doubleValue()/1080));
                cb_fullscreen.setLayoutY(681*(newSceneHeight.doubleValue()/1080));
                //l_video.setFont(Font.font(96*(newSceneHeight.doubleValue()/1080)));
            }
        });
        video.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                BackButton.setLayoutX(50*(newSceneWidth.doubleValue()/1920));
                l_video.setLayoutX(796*(newSceneWidth.doubleValue()/1920));
                cb_fullscreen.setLayoutX(723*(newSceneWidth.doubleValue()/1920));
                l_video.setFont(Font.font("Impact",96*(newSceneWidth.doubleValue()/1920)));
                cb_fullscreen.setFont(Font.font("Baskerville Old Face",41*(newSceneWidth.doubleValue()/1920)));
            }
        });
    }


    private void switchScene(String newScense){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(newScense));

        } catch (IOException ignored) {
        }
        switchScenes(root);
    }
}
