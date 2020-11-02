package View;

import Main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.nio.file.Paths;

public class WinGolemDisplay extends Canvas {
    //All golem dying animation frames.
    final static Image GOLEM_0 = new Image("golem_die/Golem_01_Dying_000.png");
    final static Image GOLEM_1 = new Image("golem_die/Golem_01_Dying_001.png");
    final static Image GOLEM_2 = new Image("golem_die/Golem_01_Dying_002.png");
    final static Image GOLEM_3 = new Image("golem_die/Golem_01_Dying_003.png");
    final static Image GOLEM_4 = new Image("golem_die/Golem_01_Dying_004.png");
    final static Image GOLEM_5 = new Image("golem_die/Golem_01_Dying_005.png");
    final static Image GOLEM_6 = new Image("golem_die/Golem_01_Dying_006.png");
    final static Image GOLEM_7 = new Image("golem_die/Golem_01_Dying_007.png");
    final static Image GOLEM_8 = new Image("golem_die/Golem_01_Dying_008.png");
    final static Image GOLEM_9 = new Image("golem_die/Golem_01_Dying_009.png");
    final static Image GOLEM_10 = new Image("golem_die/Golem_01_Dying_010.png");
    final static Image GOLEM_11 = new Image("golem_die/Golem_01_Dying_011.png");
    final static Image GOLEM_12 = new Image("golem_die/Golem_01_Dying_012.png");
    final static Image GOLEM_13 = new Image("golem_die/Golem_01_Dying_013.png");
    final static Image GOLEM_14 = new Image("golem_die/Golem_01_Dying_014.png");

    //Animate the golem dying.
    public void Dying(){
        Timeline DyingTimeline = new Timeline();
        Media punch_sfx = new Media(Paths.get("resources/PUNCH.mp3").toUri().toString());
        MediaPlayer PunchMP = new MediaPlayer(punch_sfx);
        PunchMP.setVolume(Double.valueOf(Main.Configurations.GetProperty("db.music",true))/400);
        Media Die_sfx = new Media(Paths.get("resources/attack.mp3").toUri().toString());
        MediaPlayer DieMP = new MediaPlayer(Die_sfx);
        DieMP.setVolume(Double.valueOf(Main.Configurations.GetProperty("db.music",true))/400);
        DieMP.setCycleCount(1);
        Media youwin_sfx = new Media(Paths.get("resources/youwin.mp3").toUri().toString());
        MediaPlayer YouWinMP = new MediaPlayer(youwin_sfx);
        YouWinMP.setVolume(Double.valueOf(Main.Configurations.GetProperty("db.music",true))/400);
        DieMP.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                YouWinMP.setStartTime(Duration.millis(1050));
                YouWinMP.play();
                DyingTimeline.stop();

            }
        });
        double golemLayoutWidth=380;
        double golemLayoutHeight = 150;
        double golemWidth = 400;
        double golemHeight = 400;
        GraphicsContext gc = getGraphicsContext2D();

        DyingTimeline.setCycleCount(1);
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_0, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(1200), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_1, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+4);
            PunchMP.play();
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(2500), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_2, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+8);
            PunchMP.stop();
            PunchMP.play();
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(3800), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_3, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
            PunchMP.stop();
            PunchMP.play();
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5100), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_4, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
            PunchMP.stop();
            DieMP.play();
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5150), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_5, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5200), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_6, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5250), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_7, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5300), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_8, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5350), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_9, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5400), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_10, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+12);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5450), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_11, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+16);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5500), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_12, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+16);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5550), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_13, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+16);

        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(5600), e -> {
            gc.clearRect(golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight);
            gc.drawImage(GOLEM_14, golemLayoutWidth, golemLayoutHeight, golemWidth, golemHeight+16);
        }));
        DyingTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(6800), e -> {
        }));
        DyingTimeline.play();
    }
}
