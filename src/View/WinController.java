package View;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
/**Controller for the win window*/
public class WinController implements Initializable {
    public static boolean Done;
    public AnchorPane WinPane;
    @FXML
    WinCharDisplay winCharDisplayer;
    @FXML
    WinGolemDisplay winGolemDisplayer;
    @FXML
    Label lbl_win;
    /**When initializing, set all settings for the scene.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Done = false;
        winCharDisplayer.Attack();
        winGolemDisplayer.Dying();
        lbl_win.setVisible(false);
        FadeTransition labelFade = new FadeTransition(Duration.millis(1000));
        labelFade.setNode(lbl_win);
        labelFade.setFromValue(0.0);
        labelFade.setToValue(1.0);
        labelFade.setCycleCount(1);
        labelFade.setAutoReverse(false);
        Timeline labelTimeline = new Timeline();
        labelTimeline.setCycleCount(1);
        labelTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5600), e -> {
            if (!lbl_win.isVisible()){
                lbl_win.setVisible(true);
                labelFade.playFromStart();}
        }));
        labelTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(8800), e -> {
            Done=true;
            labelTimeline.stop();
        }));
        labelTimeline.play();

    }
}

