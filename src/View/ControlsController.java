package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Main.Main.*;
import static View.MazeController.viewModel;
import static View.SettingsController.onPlay;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;

public class ControlsController implements Initializable {

    @FXML
    public AnchorPane controls;
    public Label l_controls;
    public Button BackButton, howto;
    public ComboBox cb_algorithm;
    public Label l_algorithm;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        addAdjustmentsToButton(BackButton,controls);
        addAdjustmentsToButton(l_controls,controls);
        addAdjustmentsToButton(cb_algorithm,controls);
        addAdjustmentsToButton(l_algorithm,controls);
        addAdjustmentsToButton(howto,controls);
        addAlgorithmToCell();
        BackButton.addEventHandler(MOUSE_CLICKED, event -> {
            if(!cb_algorithm.getSelectionModel().isEmpty()){
                Configurations.SetProperty("db.algorithm", cb_algorithm.getSelectionModel().getSelectedItem().toString(),true);
                if(onPlay)
                    viewModel.updateSolver()
                            ;}
            switchScene("Settings.fxml");
        });
        BackButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(BackButton));

        controls.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                BackButton.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                l_controls.setLayoutY(468*(newSceneHeight.doubleValue()/1080));
                l_algorithm.setLayoutY(612*(newSceneHeight.doubleValue()/1080));
                cb_algorithm.setLayoutY(676*(newSceneHeight.doubleValue()/1080));
                howto.setLayoutY(761*(newSceneHeight.doubleValue()/1080));
            }
        });
        controls.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                BackButton.setLayoutX(50*(newSceneWidth.doubleValue()/1920));
                l_controls.setLayoutX(796*(newSceneWidth.doubleValue()/1920));
                l_controls.setFont(Font.font("Impact",96*(newSceneWidth.doubleValue()/1920)));
                l_algorithm.setLayoutX(812*(newSceneWidth.doubleValue()/1920));
                l_algorithm.setFont(Font.font("Baskerville Old Face",43*(newSceneWidth.doubleValue()/1920)));
                cb_algorithm.setLayoutX(811*(newSceneWidth.doubleValue()/1920));
                howto.setLayoutX(766*(newSceneWidth.doubleValue()/1920));
            }
        });
    }
    private void addAlgorithmToCell(){//TODO added
        cb_algorithm.setPromptText(Configurations.GetProperty("db.algorithm",true));
        String allAlgo =  Configurations.GetProperty("db.allAlgorithms",true);
        int i =0;
        String s = "";
        String[] all = allAlgo.split(",");
        cb_algorithm.getItems().setAll(all);
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
