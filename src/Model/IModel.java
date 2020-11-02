package Model;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.io.File;

public interface IModel {
    void generateMaze(int height, int width);
    void moveCharacter(KeyCode movement);
    Maze getMaze();
    Position getCharacterPosition();
    Position getLastTimeOnPath();
    Position getPreviousPosition();
    Solution getCurrentSol();
    void getLoadedMaze(File file);
    void dragMouse(MouseEvent mouseEvent, double cellSizeX, double cellSizeY);
    void getHint();
    void saveMazelocal(String saveName);
    void clearBeforeClose();
    void solveMaze(Maze maze, boolean isGenerate);
    void stopServers();
    void updateSolver();
}