package View;

import Main.Main;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static Main.Main.*;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;

public class SetNewgameController implements Initializable {
    @FXML
    public AnchorPane newgame,insidepane;
    public Button BackButton,MuteButtonMusic,b_startgame,MuteButtonSfx;
    public Pane stamblack;
    public ComboBox<String> cb_algorithm;
    public Label l_mazesize, l_algorithm;
    public TextField tf_width,tf_height;

    public String getWidth(){return tf_width.getText();}
    public String getHeight(){return tf_height.getText();}
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        insidepane.prefHeightProperty().bind(newgame.heightProperty());
        insidepane.prefWidthProperty().bind(newgame.widthProperty());
        addAdjustmentsToButton(b_startgame,newgame);
        addAdjustmentsToButton(BackButton, newgame);
        addAdjustmentsToButton(MuteButtonMusic,newgame);
        addAdjustmentsToButton(MuteButtonSfx,newgame);
        addAdjustmentsToButton(cb_algorithm,newgame);
        addAdjustmentsToButton(l_mazesize,newgame);
        addAdjustmentsToButton(l_algorithm,newgame);
        addAdjustmentsToButton(tf_width,newgame);
        addAdjustmentsToButton(tf_height,newgame);

        //TODO cell/text size of combo box
        addAlgorithmToCell();

        b_startgame.addEventHandler(MOUSE_CLICKED, event -> StartGame());
        b_startgame.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_startgame));
        BackButton.addEventHandler(MOUSE_CLICKED, event -> switchScene("MainMenu.fxml"));
        BackButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(BackButton));
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

        newgame.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                BackButton.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                MuteButtonMusic.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                MuteButtonSfx.setLayoutY(32*(newSceneHeight.doubleValue()/1080));
                b_startgame.setLayoutY(847*(newSceneHeight.doubleValue()/1080));
                l_mazesize.setLayoutY(457*(newSceneHeight.doubleValue()/1080));
                l_algorithm.setLayoutY(655*(newSceneHeight.doubleValue()/1080));
                tf_width.setLayoutY(578*(newSceneHeight.doubleValue()/1080));
                tf_height.setLayoutY(578*(newSceneHeight.doubleValue()/1080));
                cb_algorithm.setLayoutY(750*(newSceneHeight.doubleValue()/1080));
            }
        });
        newgame.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                BackButton.setLayoutX(50*(newSceneWidth.doubleValue()/1920));
                MuteButtonMusic.setLayoutX(1778*(newSceneWidth.doubleValue()/1920));
                MuteButtonSfx.setLayoutX(1657*(newSceneWidth.doubleValue()/1920));
                b_startgame.setLayoutX(731*(newSceneWidth.doubleValue()/1920));
                l_mazesize.setLayoutX(860*(newSceneWidth.doubleValue()/1920));
                l_mazesize.setFont(Font.font("Baskerville Old Face",47*(newSceneWidth.doubleValue()/1920)));
                l_algorithm.setLayoutX(807*(newSceneWidth.doubleValue()/1920));
                l_algorithm.setFont(Font.font("Baskerville Old Face",43*(newSceneWidth.doubleValue()/1920)));
                tf_width.setLayoutX(730*(newSceneWidth.doubleValue()/1920));
                tf_width.setFont(Font.font(15*(newSceneWidth.doubleValue()/1920)));
                tf_height.setLayoutX(1000*(newSceneWidth.doubleValue()/1920));
                tf_height.setFont(Font.font(15*(newSceneWidth.doubleValue()/1920)));
                cb_algorithm.setLayoutX(810*(newSceneWidth.doubleValue()/1920));
            }
        });
    }
    private void addAlgorithmToCell(){//TODO added
        cb_algorithm.setPromptText("Choose Algorithm");
        String allAlgo =  Configurations.GetProperty("db.allAlgorithms",true);
        String[] all = allAlgo.split(",");
        cb_algorithm.getItems().setAll(all);
    }
    private void setAllButtonsDisable(boolean bool){
        b_startgame.setDisable(bool);
        MuteButtonSfx.setDisable(bool);
        MuteButtonMusic.setDisable(bool);
        BackButton.setDisable(bool);
        cb_algorithm.setDisable(bool);
        tf_height.setDisable(bool);
        tf_width.setDisable(bool);
    }
    private void StartGame(){
        setAllButtonsDisable(true);
        if(!CheckSizeMaze()){
            setAllButtonsDisable(false);
            return;}
        if(cb_algorithm.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please choose Algorithm.", ButtonType.OK);
            alert.getDialogPane().setStyle("-fx-border-color: black;");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initOwner(getWindow());
            alert.setHeaderText("Searching algorithm was not selected!");
            alert.showAndWait();
            Main.setMenuMusic();
            setAllButtonsDisable(false);
            return;}
        Configurations.SetProperty("db.algorithm", cb_algorithm.getSelectionModel().getSelectedItem(),true);
        Media sound = new Media(Paths.get("src/View/sound/Evil_crack.wav").toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.setOnError(() -> System.out.println("Error : " + mediaPlayer.getError().toString()));
        FadeTransition FadeOutScene = new FadeTransition(Duration.millis(500),newgame);
        FadeOutScene.setFromValue(1);
        FadeOutScene.setToValue(0);
        FadeTransition FadeOutScene1 = new FadeTransition(Duration.millis(500),newgame);
        FadeOutScene1.setFromValue(0);
        FadeOutScene1.setToValue(1);
        mediaPlayer.play();
        FadeOutScene.play();
        FadeOutScene.setOnFinished(e -> { stamblack.setVisible(true);;FadeOutScene1.play();});
        FadeOutScene1.setOnFinished(e -> {
            stamblack.setVisible(false);
            setAllButtonsDisable(false);
            switchScene("HowtoPlay.fxml");
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
    private boolean CheckSizeMaze() {
        int heigth = 0;
        int width = 0;
        try {
            heigth = Integer.valueOf(tf_height.getText());
            width = Integer.valueOf(tf_width.getText());
        }catch (Exception e){

        }
        if (!(heigth > 1 && width > 1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please choose valid maze dimensions.", ButtonType.OK);
            alert.getDialogPane().setStyle("-fx-border-color: black;");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initOwner(getWindow());
            alert.setHeaderText("Invalid maze dimensions were selected!");
            alert.showAndWait();
            Main.setMenuMusic();
            return false;
        }
        else {
            MazeController.height = tf_height.getText();
            MazeController.width = tf_width.getText();
            return true;
        }
    }

}
