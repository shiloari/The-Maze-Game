package View;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class WinCharDisplay extends Canvas {
    //character win animation frames.
    final static Image CHAR_0 = new Image("char_attack/Satyr_01_Attacking_000.png");
    final static Image CHAR_1 = new Image("char_attack/Satyr_01_Attacking_001.png");
    final static Image CHAR_2 = new Image("char_attack/Satyr_01_Attacking_002.png");
    final static Image CHAR_3 = new Image("char_attack/Satyr_01_Attacking_003.png");
    final static Image CHAR_4 = new Image("char_attack/Satyr_01_Attacking_004.png");
    final static Image CHAR_5 = new Image("char_attack/Satyr_01_Attacking_005.png");
    final static Image CHAR_6 = new Image("char_attack/Satyr_01_Attacking_006.png");
    final static Image CHAR_7 = new Image("char_attack/Satyr_01_Attacking_007.png");
    final static Image CHAR_8 = new Image("char_attack/Satyr_01_Attacking_008.png");
    final static Image CHAR_9 = new Image("char_attack/Satyr_01_Attacking_009.png");
    final static Image CHAR_10 = new Image("char_attack/Satyr_01_Attacking_010.png");
    final static Image CHAR_11 = new Image("char_attack/Satyr_01_Attacking_011.png");

    //animate the attack.
    public void Attack() {
        Timeline AttackTimeline = new Timeline();
        GraphicsContext gc = getGraphicsContext2D();
        double charLayoutWidth=225;
        double charLayoutHeight = 220;
        double charWidth = 400;
        double charHeight = 400;

        AttackTimeline.setCycleCount(4);
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_0, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(200), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_1, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_2, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(400), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_3, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_4, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_5, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(700), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_6, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(800), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_7, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(900), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_8, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_9, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1200), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_10, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1300), e -> {
            gc.clearRect(charLayoutWidth, charLayoutHeight, charWidth, charHeight);
            gc.drawImage(CHAR_11, charLayoutWidth, charLayoutHeight, charWidth, charHeight);
        }));
        AttackTimeline.play();
        AttackTimeline.setDelay(Duration.millis(200));
    }
}
