package View;

import Main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Main.Main.addAdjustmentsToButton;
import static Main.Main.setButtonPulseEffect;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;

public class SettingsController implements Initializable {


    public AnchorPane settings;
    public Button BackButton,b_video,b_sound,b_controls,b_about;
    public static boolean onPlay = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addAdjustmentsToButton(BackButton,settings);
        addAdjustmentsToButton(b_video,settings);
        addAdjustmentsToButton(b_sound,settings);
        addAdjustmentsToButton(b_controls,settings);
        addAdjustmentsToButton(b_about,settings);

        b_video.addEventHandler(MOUSE_CLICKED, event -> switchScene("Video.fxml"));
        b_video.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_video));

        b_sound.addEventHandler(MOUSE_CLICKED, event -> switchScene("Sound.fxml"));
        b_sound.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_sound));

        b_controls.addEventHandler(MOUSE_CLICKED, event -> switchScene("Controls.fxml"));
        b_controls.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_controls));

        b_about.addEventHandler(MOUSE_CLICKED, event -> switchScene("About.fxml"));
        b_about.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_about));

        BackButton.addEventHandler(MOUSE_CLICKED, event -> {
            if(onPlay) {
                onPlay = false;
                Main.setMazeMusic();
                Main.SwitchToMazeScene();}
            else {
                switchScene("MainMenu.fxml");
            }
        });
        BackButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(BackButton));


        settings.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                BackButton.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                b_video.setLayoutY(540*(newSceneHeight.doubleValue()/1080));
                b_sound.setLayoutY(649*(newSceneHeight.doubleValue()/1080));
                b_controls.setLayoutY(769*(newSceneHeight.doubleValue()/1080));
                b_about.setLayoutY(884*(newSceneHeight.doubleValue()/1080));
            }
        });
        settings.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                BackButton.setLayoutX(50*(newSceneWidth.doubleValue()/1920));
                b_video.setLayoutX(876*(newSceneWidth.doubleValue()/1920));
                b_sound.setLayoutX(862*(newSceneWidth.doubleValue()/1920));
                b_controls.setLayoutX(807*(newSceneWidth.doubleValue()/1920));
                b_about.setLayoutX(855*(newSceneWidth.doubleValue()/1920));
            }
        });
    }
    private void switchScene(String newScense){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(newScense));

        } catch (IOException ignored) {
        }
        Main.switchScenes(root);
    }
}
