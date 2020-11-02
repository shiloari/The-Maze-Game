package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Main.Main.*;
import static javafx.scene.input.MouseEvent.*;

public class SoundController implements Initializable {


    public AnchorPane sound;
    public Button BackButton,i_sound,i_music;
    public Label l_sound;
    public Slider s_slidesound,s_slidemusic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addAdjustmentsToButton(BackButton,sound);
        addAdjustmentsToButton(l_sound,sound);
        addAdjustmentsToButton(i_sound,sound);
        addAdjustmentsToButton(i_music,sound);
        addAdjustmentsToButton(s_slidemusic,sound);
        addAdjustmentsToButton(s_slidesound,sound);
        slideHandeler(s_slidemusic,music);
        slideHandeler(s_slidesound,sfx);

        BackButton.addEventHandler(MOUSE_CLICKED, event -> {
            Configurations.SetProperty("db.music",Double.toString(music.getVolume()*100),true);
            Configurations.SetProperty("db.sfx",Double.toString(sfx.getVolume()*100),true);
            switchScene("Settings.fxml");});
        BackButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(BackButton));

        sound.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                BackButton.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                l_sound.setLayoutY(468*(newSceneHeight.doubleValue()/1080));
                i_sound.setLayoutY(832*(newSceneHeight.doubleValue()/1080));
                i_music.setLayoutY(714*(newSceneHeight.doubleValue()/1080));
                s_slidesound.setLayoutY(845*(newSceneHeight.doubleValue()/1080));
                s_slidemusic.setLayoutY(733*(newSceneHeight.doubleValue()/1080));
            }
        });
        sound.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                BackButton.setLayoutX(50*(newSceneWidth.doubleValue()/1920));
                l_sound.setLayoutX(796*(newSceneWidth.doubleValue()/1920));
                l_sound.setFont(Font.font("Impact",96*(newSceneWidth.doubleValue()/1920)));
                i_sound.setLayoutX(752*(newSceneWidth.doubleValue()/1920));
                i_music.setLayoutX(751*(newSceneWidth.doubleValue()/1920));
                s_slidesound.setLayoutX(891*(newSceneWidth.doubleValue()/1920));
                s_slidemusic.setLayoutX(891*(newSceneWidth.doubleValue()/1920));
            }
        });
    }

    private void slideHandeler(Slider slider,MediaPlayer sound){
        slider.setMax(100);
        slider.setMin(0);
        if(sound.isMute()){
            setVolume(sound,0.0);
            slider.setValue(0);}
        else
            slider.setValue(getVolume(sound)*100);

        slider.addEventHandler(MouseEvent.MOUSE_PRESSED,event -> {
            slider.addEventHandler(MOUSE_DRAGGED,event1 -> {
                setVolume(sound,slider.getValue()/100);
                if(slider.getValue() != 0)
                    sound.setMute(false);
            });
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

