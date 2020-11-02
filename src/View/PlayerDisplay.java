package View;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.Scanner;

/**Class extends Canvas, displaying the player character.*/
public class PlayerDisplay extends Canvas {
    private Position characterPosition;
    private double rows;
    private double cols;
    private Timeline IdleTimeLine;
    private Timeline WalkRightTimeLine;
    private Timeline WalkLefTimeLine;
    private Timeline WalkUpTimeLine;
    private Timeline WalkDownTimeLine;
    private double playerLayoutX;
    private double playerLayoutY;
    private int HintX = -1;
    private int HintY = -1;
    //character idle animation frames.
    final static Image CHAR_0 = new Image("char_idle/Satyr_01_Idle Blinking_000.png");
    final static Image CHAR_1 = new Image("char_idle/Satyr_01_Idle Blinking_001.png");
    final static Image CHAR_2 = new Image("char_idle/Satyr_01_Idle Blinking_002.png");
    final static Image CHAR_3 = new Image("char_idle/Satyr_01_Idle Blinking_003.png");
    final static Image CHAR_4 = new Image("char_idle/Satyr_01_Idle Blinking_004.png");
    final static Image CHAR_5 = new Image("char_idle/Satyr_01_Idle Blinking_005.png");
    final static Image CHAR_6 = new Image("char_idle/Satyr_01_Idle Blinking_006.png");
    final static Image CHAR_7 = new Image("char_idle/Satyr_01_Idle Blinking_007.png");
    final static Image CHAR_8 = new Image("char_idle/Satyr_01_Idle Blinking_008.png");
    final static Image CHAR_9 = new Image("char_idle/Satyr_01_Idle Blinking_009.png");
    final static Image CHAR_10 = new Image("char_idle/Satyr_01_Idle Blinking_010.png");
    final static Image CHAR_11 = new Image("char_idle/Satyr_01_Idle Blinking_011.png");
    //character walk animation frames.
    final static Image CHAR_0_Walk = new Image("char_walk/Satyr_01_Walking_000.png");
    final static Image CHAR_1_Walk = new Image("char_walk/Satyr_01_Walking_001.png");
    final static Image CHAR_2_Walk = new Image("char_walk/Satyr_01_Walking_002.png");
    final static Image CHAR_3_Walk = new Image("char_walk/Satyr_01_Walking_003.png");
    final static Image CHAR_4_Walk = new Image("char_walk/Satyr_01_Walking_004.png");
    final static Image CHAR_5_Walk = new Image("char_walk/Satyr_01_Walking_005.png");
    final static Image CHAR_6_Walk = new Image("char_walk/Satyr_01_Walking_006.png");
    final static Image CHAR_7_Walk = new Image("char_walk/Satyr_01_Walking_007.png");
    final static Image CHAR_8_Walk = new Image("char_walk/Satyr_01_Walking_008.png");
    final static Image CHAR_9_Walk = new Image("char_walk/Satyr_01_Walking_009.png");
    final static Image CHAR_10_Walk = new Image("char_walk/Satyr_01_Walking_010.png");
    final static Image CHAR_11_Walk = new Image("char_walk/Satyr_01_Walking_011.png");
    final static Image CHAR_12_Walk = new Image("char_walk/Satyr_01_Walking_012.png");
    final static Image CHAR_13_Walk = new Image("char_walk/Satyr_01_Walking_013.png");
    final static Image CHAR_14_Walk = new Image("char_walk/Satyr_01_Walking_014.png");
    final static Image CHAR_15_Walk = new Image("char_walk/Satyr_01_Walking_015.png");
    final static Image CHAR_16_Walk = new Image("char_walk/Satyr_01_Walking_016.png");
    final static Image CHAR_17_Walk = new Image("char_walk/Satyr_01_Walking_017.png");
    //character left walk animation frames.
    final static Image CHAR_0_WalkL = new Image("char_walk/Satyr_01_WalkingR_000.png");
    final static Image CHAR_1_WalkL = new Image("char_walk/Satyr_01_WalkingR_001.png");
    final static Image CHAR_2_WalkL = new Image("char_walk/Satyr_01_WalkingR_002.png");
    final static Image CHAR_3_WalkL = new Image("char_walk/Satyr_01_WalkingR_003.png");
    final static Image CHAR_4_WalkL = new Image("char_walk/Satyr_01_WalkingR_004.png");
    final static Image CHAR_5_WalkL = new Image("char_walk/Satyr_01_WalkingR_005.png");
    final static Image CHAR_6_WalkL = new Image("char_walk/Satyr_01_WalkingR_006.png");
    final static Image CHAR_7_WalkL = new Image("char_walk/Satyr_01_WalkingR_007.png");
    final static Image CHAR_8_WalkL = new Image("char_walk/Satyr_01_WalkingR_008.png");
    final static Image CHAR_9_WalkL = new Image("char_walk/Satyr_01_WalkingR_009.png");
    final static Image CHAR_10_WalkL = new Image("char_walk/Satyr_01_WalkingR_010.png");
    final static Image CHAR_11_WalkL = new Image("char_walk/Satyr_01_WalkingR_011.png");
    final static Image CHAR_12_WalkL = new Image("char_walk/Satyr_01_WalkingR_012.png");
    final static Image CHAR_13_WalkL = new Image("char_walk/Satyr_01_WalkingR_013.png");
    final static Image CHAR_14_WalkL = new Image("char_walk/Satyr_01_WalkingR_014.png");
    final static Image CHAR_15_WalkL = new Image("char_walk/Satyr_01_WalkingR_015.png");
    final static Image CHAR_16_WalkL = new Image("char_walk/Satyr_01_WalkingR_016.png");
    final static Image CHAR_17_WalkL = new Image("char_walk/Satyr_01_WalkingR_017.png");
    //set all timelines
    public void setPlayerTimeLines() {
        IdleTimeLine = new Timeline();
        WalkRightTimeLine = new Timeline();
        WalkLefTimeLine = new Timeline();
        WalkUpTimeLine = new Timeline();
        WalkDownTimeLine = new Timeline();
    }
    //set character layouts.
    public void setPlayerLayouts(double playerLayoutX, double playerLayoutY){
        this.playerLayoutX = playerLayoutX;
        this.playerLayoutY = playerLayoutY;
    }
    //check for nulls.
    public boolean isTimeLineNull(){return IdleTimeLine == null;}
    //reset all before closing.
    public void clearBeforeClose(){
        characterPosition = null;
        rows = 0;
        cols = 0;
        HintX = -1;
        HintY = -1;
        IdleTimeLine.stop();
        WalkRightTimeLine.stop();
        WalkLefTimeLine.stop();
        WalkUpTimeLine.stop();
        WalkDownTimeLine.stop();
        IdleTimeLine = null;
        WalkRightTimeLine = null;
        WalkLefTimeLine = null;
        WalkUpTimeLine = null;
        WalkDownTimeLine = null;
    }
    //character position setter/getter
    public void setCharacterPosition(Position characterPosition) {
        this.characterPosition = characterPosition;
    }
    public Position getCharacterPosition() {
        return characterPosition;
    }

    //Draw character in position, animate idle after.
    public void DrawCharacterPosition(Position characterPosition) {
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        animateCharIdle();
    }
    //animate walk, calls the correct animation by argument direction.
    public synchronized void animateWalk(String direction){
        if ( MazeController.runningAnimates.contains("Running"))
            return;

        stopCharIdle();
        ClearCharacterSpot();
        switch (direction){
            case "UP":
                animateCharWalkUp();
                break;
            case "DOWN":
                animateCharWalkDown();
                break;
            case "RIGHT":
                animateCharWalkRight();
                break;
            case "LEFT":
                animateCharWalkLeft();
                break;
        }

    }
    private void animateCharWalkLeft() {
        MazeController.runningAnimates.add("Running");
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        WalkLefTimeLine.setCycleCount(1);
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_0_WalkL, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(35), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_1_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17), characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17),characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(70), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17), characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_2_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*2, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*2,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(105), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*2, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_3_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*3, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*3,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(140), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*3, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_4_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*4, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*4,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(175), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*4, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_5_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*5, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*5,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(210), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*5, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_6_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*6, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*6,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(245), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*6, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_7_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*7, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*7,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(280), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*7, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_8_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*8, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*8,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(315), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*8, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_9_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*9, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*9,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(350), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*9, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_10_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*10, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*10,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(385), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*10, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_11_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*11, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*11,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(420), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*11, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_12_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*12, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*12,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(455), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*12, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_13_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*13, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*13,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(490), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*13, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_14_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*14, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*14,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(525), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*14, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_15_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*15, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*15,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(560), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*15, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_16_WalkL, characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*16, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * cellWidth - (cellWidth/17)*16,characterPosition.getRowIndex() * cellHeight);
        }));
        WalkLefTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(595), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*16, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_17_WalkL, characterPosition.getColumnIndex() * (cellWidth) - cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            setPlayerLayouts(characterPosition.getColumnIndex() * (cellWidth) - cellWidth,characterPosition.getRowIndex() * cellHeight);;
        }));
        WalkLefTimeLine.play();
        WalkLefTimeLine.setOnFinished(e->{
            gc.clearRect(characterPosition.getColumnIndex() * (cellWidth) - cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            characterPosition = MazeController.viewModel.getCharacterPosition();
            WalkLefTimeLine.stop();
            MazeController.runningAnimates.remove("Running");
            animateCharIdle();
            MazeController.isPressed=false;
        });
    }
    private void animateCharWalkRight() {
        MazeController.runningAnimates.add("Running");
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        WalkRightTimeLine.setCycleCount(1);
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_0_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(35), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_1_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17), characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(70), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17), characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_2_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*2, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(105), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*2, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_3_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*3, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(140), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*3, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_4_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*4, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(175), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*4, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_5_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*5, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(210), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*5, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_6_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*6, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(245), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*6, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_7_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*7, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(280), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*7, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_8_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*8, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(315), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*8, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_9_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*9, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(350), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*9, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_10_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*10, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(385), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*10, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_11_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*11, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(420), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*11, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_12_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*12, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(455), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*12, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_13_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*13, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(490), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*13, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_14_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*14, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(525), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*14, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_15_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*15, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(560), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*15, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_16_Walk, characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*16, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkRightTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(595), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth + (cellWidth/17)*16, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_17_Walk, characterPosition.getColumnIndex() * (cellWidth*2), characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        }));
        WalkRightTimeLine.play();
        WalkRightTimeLine.setOnFinished(e->{
            gc.clearRect(characterPosition.getColumnIndex() * (cellWidth*2), characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            characterPosition = MazeController.viewModel.getCharacterPosition();
            WalkRightTimeLine.stop();
            MazeController.runningAnimates.remove("Running");
            animateCharIdle();
            MazeController.isPressed=false;
        });
    }
    private void animateCharWalkDown() {
        MazeController.runningAnimates.add("Running");
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        WalkDownTimeLine.setCycleCount(1);
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_0_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(35), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_1_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight + (cellHeight/17), cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(70), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight + (cellHeight/17), cellWidth, cellHeight);
            gc.drawImage(CHAR_2_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight + (cellHeight/17)*2, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(105), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*2, cellWidth, cellHeight);
            gc.drawImage(CHAR_3_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*3, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(140), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*3, cellWidth, cellHeight);
            gc.drawImage(CHAR_4_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*4, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(175), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*4, cellWidth, cellHeight);
            gc.drawImage(CHAR_5_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*5, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(210), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*5, cellWidth, cellHeight);
            gc.drawImage(CHAR_6_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*6, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(245), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*6, cellWidth, cellHeight);
            gc.drawImage(CHAR_7_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*7, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(280), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*7, cellWidth, cellHeight);
            gc.drawImage(CHAR_8_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*8, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(315), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*8, cellWidth, cellHeight);
            gc.drawImage(CHAR_9_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*9, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(350), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*9, cellWidth, cellHeight);
            gc.drawImage(CHAR_10_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*10, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(385), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*10, cellWidth, cellHeight);
            gc.drawImage(CHAR_11_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*11, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(420), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*11, cellWidth, cellHeight);
            gc.drawImage(CHAR_12_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*12, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(455), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*12, cellWidth, cellHeight);
            gc.drawImage(CHAR_13_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*13, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(490), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*13, cellWidth, cellHeight);
            gc.drawImage(CHAR_14_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*14, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(525), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*14, cellWidth, cellHeight);
            gc.drawImage(CHAR_15_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*15, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(560), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*15, cellWidth, cellHeight);
            gc.drawImage(CHAR_16_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*16, cellWidth, cellHeight);

        }));
        WalkDownTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(595), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  + (cellHeight/17)*16, cellWidth, cellHeight);
            gc.drawImage(CHAR_17_Walk, characterPosition.getColumnIndex() , characterPosition.getRowIndex() * cellHeight*2, cellWidth, cellHeight);
        }));

        WalkDownTimeLine.play();
        WalkDownTimeLine.setOnFinished(e->{
            gc.clearRect(characterPosition.getColumnIndex() , characterPosition.getRowIndex() * cellHeight*2, cellWidth, cellHeight);
            characterPosition = MazeController.viewModel.getCharacterPosition();
            WalkDownTimeLine.stop();
            MazeController.runningAnimates.remove("Running");
            animateCharIdle();
            MazeController.isPressed=false;
        });
    }
    private void animateCharWalkUp() {
        MazeController.runningAnimates.add("Running");
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        WalkUpTimeLine.setCycleCount(1);
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_0_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(35), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_1_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight - (cellHeight/17), cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(70), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight - (cellHeight/17), cellWidth, cellHeight);
            gc.drawImage(CHAR_2_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight - (cellHeight/17)*2, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(105), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*2, cellWidth, cellHeight);
            gc.drawImage(CHAR_3_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*3, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(140), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*3, cellWidth, cellHeight);
            gc.drawImage(CHAR_4_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*4, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(175), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*4, cellWidth, cellHeight);
            gc.drawImage(CHAR_5_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*5, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(210), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*5, cellWidth, cellHeight);
            gc.drawImage(CHAR_6_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*6, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(245), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*6, cellWidth, cellHeight);
            gc.drawImage(CHAR_7_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*7, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(280), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*7, cellWidth, cellHeight);
            gc.drawImage(CHAR_8_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*8, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(315), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*8, cellWidth, cellHeight);
            gc.drawImage(CHAR_9_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*9, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(350), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*9, cellWidth, cellHeight);
            gc.drawImage(CHAR_10_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*10, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(385), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*10, cellWidth, cellHeight);
            gc.drawImage(CHAR_11_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*11, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(420), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*11, cellWidth, cellHeight);
            gc.drawImage(CHAR_12_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*12, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(455), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*12, cellWidth, cellHeight);
            gc.drawImage(CHAR_13_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*13, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(490), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*13, cellWidth, cellHeight);
            gc.drawImage(CHAR_14_Walk, characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*14, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(525), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*14, cellWidth, cellHeight);
            gc.drawImage(CHAR_15_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*15, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(560), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*15, cellWidth, cellHeight);
            gc.drawImage(CHAR_16_Walk, characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*16, cellWidth, cellHeight);

        }));
        WalkUpTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(595), e -> {
            gc.clearRect(characterPosition.getColumnIndex() * cellWidth , characterPosition.getRowIndex() * cellHeight  - (cellHeight/17)*16, cellWidth, cellHeight);
            gc.drawImage(CHAR_17_Walk, characterPosition.getColumnIndex() , characterPosition.getRowIndex() * (cellHeight-1), cellWidth, cellHeight);
        }));
        WalkUpTimeLine.play();
        WalkUpTimeLine.setOnFinished(e->{
            gc.clearRect(characterPosition.getColumnIndex() , characterPosition.getRowIndex() * (cellHeight-1), cellWidth, cellHeight);
            characterPosition = MazeController.viewModel.getCharacterPosition();
            WalkUpTimeLine.stop();
            MazeController.runningAnimates.remove("Running");
            animateCharIdle();
            MazeController.isPressed=false;
        });

    }
    public void animateCharIdle(){
        if (characterPosition==null)
            return;
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        setPlayerLayouts(characterPosition.getColumnIndex()*cellWidth,characterPosition.getRowIndex()*cellHeight);
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        // gc.drawImage(CHAR_0, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
        IdleTimeLine.setCycleCount(Timeline.INDEFINITE);
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(0), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_0, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(200), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_1, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(300), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_2, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(400), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_3, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(500), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_4, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(600), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_5, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(700), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_6, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(800), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_7, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(900), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_8, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_9, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1200), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_10, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1300), e->{
            gc.clearRect(characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(CHAR_11, characterPosition.getColumnIndex()* cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        IdleTimeLine.play();
        MazeController.isPressed=false;

    }
    public void stopCharIdle(){
        IdleTimeLine.stop();
        IdleTimeLine=null;
        IdleTimeLine = new Timeline();
    }
    public void stopCharWalk(){
        WalkLefTimeLine.stop();
        WalkDownTimeLine.stop();
        WalkRightTimeLine.stop();
        WalkUpTimeLine.stop();
        WalkUpTimeLine=null;
        WalkRightTimeLine=null;
        WalkDownTimeLine=null;
        WalkLefTimeLine=null;
        WalkDownTimeLine = new Timeline();
        WalkRightTimeLine = new Timeline();
        WalkLefTimeLine = new Timeline();
        WalkUpTimeLine = new Timeline();
    }


    public void setMazeSize(int numOfRows, int numOfColumns) {
        rows = numOfRows;
        cols = numOfColumns;
    }

    public void DrawHint(Position ClosestPos){
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        HintX = ClosestPos.getRowIndex();
        HintY = ClosestPos.getColumnIndex();
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.YELLOWGREEN);
        Timeline hintTimeLine = new Timeline();
        hintTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(0), e->{
            gc.clearRect(HintY* cellWidth, HintX * cellHeight, cellWidth, cellHeight);
        }));
        hintTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(100), e->{
            gc.fillRect(HintY * cellWidth, HintX * cellHeight, cellWidth, cellHeight);
        }));
        hintTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(200), e->{
            gc.clearRect(HintY* cellWidth, HintX * cellHeight, cellWidth, cellHeight);
        }));
        hintTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(300), e->{
            gc.fillRect(HintY * cellWidth, HintX * cellHeight, cellWidth, cellHeight);
        }));
        hintTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(400), e->{
            gc.clearRect(HintY* cellWidth, HintX * cellHeight, cellWidth, cellHeight);
        }));
        hintTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(500), e->{
            gc.fillRect(HintY * cellWidth, HintX * cellHeight, cellWidth, cellHeight);
        }));
        hintTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(600), e->{
            gc.clearRect(HintY* cellWidth, HintX * cellHeight, cellWidth, cellHeight);
        }));
        hintTimeLine.setOnFinished(e->{
            hintTimeLine.stop();
        });
        hintTimeLine.play();
    }


    public void clearAll() {
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0,0,canvasWidth,canvasHeight);

    }

    public void ClearCharacterSpot(){
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(characterPosition.getColumnIndex() * cellWidth, characterPosition.getRowIndex() * cellHeight, cellWidth, cellHeight);
    }


    public synchronized void drawSolution(Solution currentSol) {
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / rows;
        double cellWidth = canvasWidth / cols;

        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.YELLOWGREEN);
        for (int i = 1; i < currentSol.getSolutionPath().size()-1; i++) {
            //System.out.println(currentSol.getSolutionPath().get(i));
            String tempStr = currentSol.getSolutionPath().get(i).toString();
            Scanner in = new Scanner(tempStr).useDelimiter("[^0-9]+");
            int x = in.nextInt();
            int y = in.nextInt();
            gc.fillRect(y * cellWidth, x * cellHeight, cellWidth, cellHeight);
        }
    }
}
