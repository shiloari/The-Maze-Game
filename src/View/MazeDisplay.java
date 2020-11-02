package View;

import algorithms.mazeGenerators.Maze;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MazeDisplay extends Canvas {
    private Maze maze;
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameFloor = new SimpleStringProperty();
    private Timeline GolemTimeLine ;
    final static Image Golem1 = new Image("golem_idle/Golem_01_Idle_000.png");
    final static Image Golem2 = new Image("golem_idle/Golem_01_Idle_001.png");
    final static Image Golem3 = new Image("golem_idle/Golem_01_Idle_002.png");
    final static Image Golem4 = new Image("golem_idle/Golem_01_Idle_003.png");
    final static Image Golem5 = new Image("golem_idle/Golem_01_Idle_004.png");
    final static Image Golem6 = new Image("golem_idle/Golem_01_Idle_005.png");
    final static Image Golem7 = new Image("golem_idle/Golem_01_Idle_006.png");
    final static Image Golem8 = new Image("golem_idle/Golem_01_Idle_007.png");
    final static Image Golem9 = new Image("golem_idle/Golem_01_Idle_008.png");
    final static Image Golem10 = new Image("golem_idle/Golem_01_Idle_009.png");
    final static Image Golem11 = new Image("golem_idle/Golem_01_Idle_010.png");
    public void setMaze(Maze maze) {
        this.maze = maze;
        GolemTimeLine = new Timeline();
        redraw();
    }
    public boolean isTimeLineNull(){return GolemTimeLine==null;}
    public void clearBeforeClose(){
        maze = null;
        getGraphicsContext2D().clearRect(0,0,getWidth(),getHeight());
        GolemTimeLine = null;
    }
    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }
    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameFloor() {
        return ImageFileNameFloor.get();
    }
    public void setImageFileNameFloor(String imageFileNameFloor) {
        this.ImageFileNameFloor.set(imageFileNameFloor);
    }
    public void stopGolemIdle(){
        //System.out.println("Stop Golem Idle");
        GolemTimeLine.stop();
        GolemTimeLine=null;
        GolemTimeLine = new Timeline();
        getGraphicsContext2D().clearRect(0,0,getWidth(),getHeight());
        redraw();
    }
    public  void animateGolemIdle(){
        double canvasHeight = getHeight();
        double canvasWidth = getWidth();
        double cellHeight = canvasHeight / maze.getMaze().length;
        double cellWidth = canvasWidth / maze.getMaze()[0].length;
        Image floorImage = null;
        try {
            floorImage = new Image(new FileInputStream(getImageFileNameFloor()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        GraphicsContext gc = getGraphicsContext2D();
        gc.drawImage(Golem1, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
        GolemTimeLine.setCycleCount(Timeline.INDEFINITE);
        Image finalFloorImage = floorImage;
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(200), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem2, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(300), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem3, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(400), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem4, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(500), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem5, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(600), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem6, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(700), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem7, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(800), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem8, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(900), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem9, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1000), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem10, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1100), e->{
            gc.clearRect(maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(finalFloorImage, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);
            gc.drawImage(Golem11, maze.getGoalPosition().getColumnIndex() * cellWidth, maze.getGoalPosition().getRowIndex() * cellHeight, cellWidth, cellHeight);

        }));
        GolemTimeLine.play();
    }

    public void redraw() {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.getMaze().length;
            double cellWidth = canvasWidth / maze.getMaze()[0].length;

            try {
                Image wallImage = new Image(new FileInputStream(getImageFileNameWall()));
                Image floorImage = new Image(new FileInputStream(getImageFileNameFloor()));
                GraphicsContext gc = getGraphicsContext2D();
                gc.clearRect(0, 0, getWidth(), getHeight());
                //Draw Maze
                for (int i = 0; i < maze.getMaze().length; i++) {
                    for (int j = 0; j < maze.getMaze()[0].length; j++) {
                        if (maze.getMaze()[i][j] == 1) {
                            gc.drawImage(wallImage, j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                        }
                        else
                            gc.drawImage(floorImage, j * cellWidth, i * cellHeight, cellWidth, cellHeight);

                    }
                }
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
            }
        }
    }
}