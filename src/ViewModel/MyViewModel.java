package ViewModel;

import Model.IModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.Observable;
import java.util.Observer;
/**View model - connects between the Model and View, using Observable and Observer interfaces.*/
public class MyViewModel extends Observable implements Observer {
    private IModel model;
    public Solution currentSol=null;
    private Position LastTimeOnPath = null;
    private Position previousPosition = null;
    private Position characterPosition;
    public StringProperty characterPositionRowStr = new SimpleStringProperty("1"); //For Binding
    public StringProperty characterPositionColumnStr = new SimpleStringProperty("1"); //For Binding
    private  boolean SolisShown = false;

    public void setSolisShown(boolean solisShown) {
        SolisShown = solisShown;
    }

    public void clearBeforeClose(){
        model.clearBeforeClose();
        previousPosition = null;
        characterPosition = null;
        LastTimeOnPath = null;
        currentSol = null;
        characterPositionColumnStr = null;
        characterPositionRowStr = null;
    }
    public void setCharacterPosition(Position pos) {
        this.characterPosition = pos;
    }
    public Position getCharacterPosition() {
        return characterPosition;
    }

    public MyViewModel(IModel model) {
        this.model = model;
    }
    public Position getLastTimeOnPath() {
        return model.getLastTimeOnPath();
    }


    @Override
    public void update(Observable o, Object arg) {
        String instruction = "";
        if (o == model) {
            switch ((String) arg) {
                case "generateMaze":
                    characterPosition = model.getCharacterPosition();
                    currentSol=model.getCurrentSol();
                    LastTimeOnPath = model.getLastTimeOnPath();
                    //characterPositionRowStr.set(characterPosition.getRowIndex() + "");
                    //characterPositionColumnStr.set(characterPosition.getColumnIndex() + "");
                    setChanged();
                    notifyObservers("generateMaze");
                    break;

                case "getHint":
                    LastTimeOnPath = model.getLastTimeOnPath();
                    setChanged();
                    notifyObservers("getHint");
                    break;
                case "moveRight":
                    characterPosition = model.getCharacterPosition();
                    //         characterPositionRowStr.set(characterPosition.getRowIndex() + "");
                    // characterPositionColumnStr.set(characterPosition.getColumnIndex() + "");
                    setChanged();
                    notifyObservers("moveRight");
                    break;
                case "moveLeft":
                    characterPosition = model.getCharacterPosition();
                    //characterPositionRowStr.set(characterPosition.getRowIndex() + "");
                    // characterPositionColumnStr.set(characterPosition.getColumnIndex() + "");
                    setChanged();
                    notifyObservers("moveLeft");
                    break;
                case "moveUp":
                    characterPosition = model.getCharacterPosition();
                    //  characterPositionRowStr.set(characterPosition.getRowIndex() + "");
                    //  characterPositionColumnStr.set(characterPosition.getColumnIndex() + "");
                    setChanged();
                    notifyObservers("moveUp");
                    break;
                case "moveDown":
                    characterPosition = model.getCharacterPosition();
                    //  characterPositionRowStr.set(characterPosition.getRowIndex() + "");
                    //  characterPositionColumnStr.set(characterPosition.getColumnIndex() + "");
                    setChanged();
                    notifyObservers("moveDown");
                    break;
                case "moveUpRight":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveUpRight");
                    break;
                case "moveRightUp":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveRightUp");
                    break;
                case "moveDownRight":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveDownRight");
                    break;
                case "moveRightDown":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveRightDown");
                    break;
                case "moveLeftUp":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveLeftUp");
                    break;
                case "moveUpLeft":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveUpLeft");
                    break;
                case "moveDownLeft":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveDownLeft");
                    break;
                case "moveLeftDown":
                    characterPosition = model.getCharacterPosition();
                    setChanged();
                    notifyObservers("moveLeftDown");
                    break;
                case "solveMaze":
                    currentSol=model.getCurrentSol();
                    setChanged();
                    notifyObservers("solveMaze");
                    break;

                case "win":
                   // System.out.println("In view model!!");
                    setChanged();
                    notifyObservers("win");
                    break;
                case "saveSucceed":
                    setChanged();
                    notifyObservers("saveSucceed");
                    break;
                case "saveFailed":
                    setChanged();
                    notifyObservers("saveFailed");
                    break;

            }
        }
    }

    public IModel getModel() {
        return model;
    }
    public void generateMaze(int height, int width){
        model.generateMaze(height, width);
    }
    public void moveCharacter(KeyCode movement){
        model.moveCharacter(movement);
        previousPosition = model.getPreviousPosition();
    }
    public Maze getMaze() {
        return model.getMaze();
    }
    public void solveMaze() {
        Maze maze= getMaze();
       // System.out.println(SolisShown);
        if (!SolisShown){
            SolisShown=true;
            model.solveMaze(maze,false);
        }
        else{
            SolisShown=false;
            setChanged();
            notifyObservers("HideSolution");
        }
    }
    public void sendLoadedMaze(File file) {
        model.getLoadedMaze(file);
    }
    public void moveByDrag(MouseEvent mouseEvent,double canvasSizeX,double canvasSizeY) {
        double cellSizeX=canvasSizeX/getMaze().getMaze().length;
        double cellSizeY=canvasSizeY/getMaze().getMaze()[0].length;
        model.dragMouse(mouseEvent,cellSizeX,cellSizeY);
    }
    public void saveMazelocal(String saveName){
        model.saveMazelocal(saveName);
    }
    public void updateSolver(){model.updateSolver();}
}