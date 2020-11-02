package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Main.Main.*;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;

public class AboutController implements Initializable {
    @FXML
    public AnchorPane about;
    public Label l_about;
    public Button BackButton;
    public Label firstLine,secondLine,thirdLine,fourthLine,fifthLine;

    /**Implement the interface Initializable, set the default settings for window the moment of initialize.*/
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        addAdjustmentsToButton(BackButton, about);
        addAdjustmentsToButton(l_about, about);
        addAdjustmentsToButton(firstLine,about);
        addAdjustmentsToButton(secondLine,about);
        addAdjustmentsToButton(thirdLine,about);
        addAdjustmentsToButton(fourthLine,about);
        addAdjustmentsToButton(fifthLine,about);
        //back button goes back to settings.
        BackButton.addEventHandler(MOUSE_CLICKED, event -> {

            switchScene("Settings.fxml");
        });
        //Set pulse.
        BackButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(BackButton));
        //Listeners for width and height.
        about.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                BackButton.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                l_about.setLayoutY(468*(newSceneHeight.doubleValue()/1080));
                firstLine.setLayoutY(702*(newSceneHeight.doubleValue()/1080));
                secondLine.setLayoutY(740*(newSceneHeight.doubleValue()/1080));
                thirdLine.setLayoutY(777*(newSceneHeight.doubleValue()/1080));
                fourthLine.setLayoutY(817*(newSceneHeight.doubleValue()/1080));
                fifthLine.setLayoutY(852*(newSceneHeight.doubleValue()/1080));
            }
        });
        about.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                BackButton.setLayoutX(50*(newSceneWidth.doubleValue()/1920));
                l_about.setLayoutX(796*(newSceneWidth.doubleValue()/1920));
                l_about.setFont(Font.font("Impact",96*(newSceneWidth.doubleValue()/1920)));
                firstLine.setLayoutX(597*(newSceneWidth.doubleValue()/1920));
                firstLine.setFont(Font.font("System",24*(newSceneWidth.doubleValue()/1920)));
                secondLine.setLayoutX(531*(newSceneWidth.doubleValue()/1920));
                secondLine.setFont(Font.font("System",24*(newSceneWidth.doubleValue()/1920)));
                thirdLine.setLayoutX(636*(newSceneWidth.doubleValue()/1920));
                thirdLine.setFont(Font.font("System",24*(newSceneWidth.doubleValue()/1920)));
                fourthLine.setLayoutX(657*(newSceneWidth.doubleValue()/1920));
                fourthLine.setFont(Font.font("System",24*(newSceneWidth.doubleValue()/1920)));
                fifthLine.setLayoutX(880*(newSceneWidth.doubleValue()/1920));
                fifthLine.setFont(Font.font("System",24*(newSceneWidth.doubleValue()/1920)));


            }
        });
    }

    /**Switch scene given a fxml file name*/
    private void switchScene(String newScense){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(newScense));

        } catch (IOException ignored) {
        }
        switchScenes(root);
    }
}

