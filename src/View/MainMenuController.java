package View;

import Main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import static Main.Main.*;
import static View.MazeController.viewModel;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;

public class MainMenuController implements Observer,Initializable {
    @FXML
    public AnchorPane pane,insidepane;
    public Button ExitButton,b_newgame,b_scoreboard,b_settings,b_loadgame, MuteButtonMusic,MuteButtonSfx;
    public Pane stamblack;

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            switch ((String) arg) {
                case "generateMaze":
                    SwitchToMazeScene();
                    break;
            }}
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insidepane.prefHeightProperty().bind(pane.heightProperty());
        insidepane.prefWidthProperty().bind(pane.widthProperty());
        addAdjustmentsToButton(ExitButton,pane);
        addAdjustmentsToButton(MuteButtonMusic,pane);
        addAdjustmentsToButton(MuteButtonSfx,pane);
        addAdjustmentsToButton(b_newgame,pane);
        addAdjustmentsToButton(b_loadgame,pane);
        addAdjustmentsToButton(b_scoreboard,pane);
        addAdjustmentsToButton(b_settings,pane);

        b_newgame.addEventHandler(MOUSE_CLICKED, event -> switchScene("SetNewgame.fxml"));

        b_newgame.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_newgame));

        b_loadgame.addEventHandler(MOUSE_CLICKED, event -> LoadGame());
        b_loadgame.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_loadgame));

        b_scoreboard.addEventHandler(MOUSE_CLICKED, event -> comingSoon());
        b_scoreboard.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_scoreboard));

        b_settings.addEventHandler(MOUSE_CLICKED, event -> switchScene("Settings.fxml"));
        b_settings.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_settings));

        ExitButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(ExitButton));
        ExitButton.addEventHandler(MOUSE_CLICKED, e-> exitSafe());

        MuteButtonMusic.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(MuteButtonMusic));
        MuteButtonMusic.addEventHandler(MOUSE_CLICKED, event -> {
            if(music.isMute()) {
                music.setMute(false);
                if(music.getStatus() == MediaPlayer.Status.STOPPED)
                    music.play();
            }
            else
                music.setMute(true);
        });
        MuteButtonSfx.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(MuteButtonSfx));
        MuteButtonSfx.addEventHandler(MOUSE_CLICKED, event -> {
            if(sfx.isMute()) {
                sfx.setMute(false);
                if(sfx.getStatus() == MediaPlayer.Status.STOPPED)
                    sfx.play();
            }
            else
                sfx.setMute(true);
        });
        pane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            ExitButton.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
            MuteButtonMusic.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
            MuteButtonSfx.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
            b_newgame.setLayoutY(476*(newSceneHeight.doubleValue()/1080));
            b_loadgame.setLayoutY(641*(newSceneHeight.doubleValue()/1080));
            b_scoreboard.setLayoutY(774*(newSceneHeight.doubleValue()/1080));
            b_settings.setLayoutY(887*(newSceneHeight.doubleValue()/1080));
        });
        pane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                ExitButton.setLayoutX(50*(newSceneWidth.doubleValue()/1920));
                MuteButtonMusic.setLayoutX(1778*(newSceneWidth.doubleValue()/1920));
                MuteButtonSfx.setLayoutX(1657*(newSceneWidth.doubleValue()/1920));
                b_newgame.setLayoutX(714*(newSceneWidth.doubleValue()/1920));
                b_loadgame.setLayoutX(785*(newSceneWidth.doubleValue()/1920));
                b_scoreboard.setLayoutX(778*(newSceneWidth.doubleValue()/1920));
                b_settings.setLayoutX(830*(newSceneWidth.doubleValue()/1920));
            }
        });
    }

    //Load game.
    private void LoadGame(){
        FileChooser fc = new FileChooser();
        String url = Configurations.GetProperty("db.url",true);//TODO url mybe null
        fc.setInitialDirectory(new File(url));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Maze Files","*.maze"));
        File selectedFile = fc.showOpenDialog(getWindow());
        if(selectedFile != null){
            viewModel.sendLoadedMaze(selectedFile);
            MazeController.isSave = true;
            Main.setMazeMusic();
        }

    }

    //Coming soon alert, for the scoreboard.
    private void comingSoon(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"", ButtonType.OK);
        alert.getDialogPane().setStyle("-fx-border-color: black;");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initOwner(Main.getWindow());
        alert.setHeaderText("Coming Soon !");
        alert.showAndWait();
    }

    //Switch scene given a fxml file name.
    private void switchScene(String newScense){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(newScense));

        } catch (IOException ignored) {
        }
        switchScenes(root);
    }


}
