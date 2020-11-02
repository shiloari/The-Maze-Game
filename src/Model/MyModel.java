package Model;

import Client.Client;
import Client.IClientStrategy;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import Server.Server;
import Server.ServerStrategyGenerateMaze;
import Server.ServerStrategySolveSearchProblem;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.MazeState;
import algorithms.search.Solution;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;

public class MyModel extends Observable implements IModel {
    private Maze maze;
    private Position characterPosition;
    private Position previousPosition;
    private Position LastTimeOnPath;
    private Server mazeGeneratingServer;
    private Server solveSearchProblemServer;
    private Solution currentSol = null;


    public void clearBeforeClose(){
        maze = null;
        characterPosition = null;
        LastTimeOnPath = null;
        currentSol = null;
    }
    public Position getLastTimeOnPath() {
        return LastTimeOnPath;
    }
    //Generate the maze by asking the generator server.
    public void generateMaze(int height, int width) {
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5400, new IClientStrategy() {
                @Override
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        int[] mazeDimensions = new int[]{height, width};
                        toServer.writeObject(mazeDimensions);
                        toServer.flush();
                        byte[] compressedMaze = ((byte[]) fromServer.readObject());
                        InputStream is = new MyDecompressorInputStream(new ByteArrayInputStream(compressedMaze));
                        Maze mazesize = new MyMazeGenerator().generate(height,width);
                        byte[] decompressedMaze = new byte[mazesize.toByteArray().length*8];
                        is.read(decompressedMaze);
                        maze = new Maze(decompressedMaze);
                        characterPosition = maze.getStartPosition();
                    } catch (Exception var12) {
                        var12.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException var1) {
            var1.printStackTrace();
        }

        solveMaze(maze,true);
        setChanged();
        notifyObservers("generateMaze");

    }
    //Move character by key event.
    public void moveCharacter(KeyCode movement) {
        String Instruction = ""; //final instruction - where to move?
        int width = maze.getMaze().length;
        int height = maze.getMaze()[0].length;
        Position InitPos = new Position(0,0);
        int wall = 1;
        //Switch the movement received to action.
        //for each case, check the moves validity, and if it is, set all variables and notify the view model.
        switch (movement) {
            case UP:
            case NUMPAD8:
                Position UpPos = new Position(getCharacterPosition().getRowIndex()-1,getCharacterPosition().getColumnIndex());
                if (maze.checkValidity(InitPos,width,height,UpPos,wall)) {
                    previousPosition = characterPosition;
                    characterPosition = UpPos;
                    if (PositionInSolution(characterPosition))
                        LastTimeOnPath = characterPosition;
                    Instruction = "moveUp";
                }
                break;
            case DOWN:
            case NUMPAD2:
                Position DownPos = new Position(getCharacterPosition().getRowIndex()+1,getCharacterPosition().getColumnIndex());
                if (maze.checkValidity(InitPos,width,height,DownPos,wall))
                {
                    previousPosition = characterPosition;
                    characterPosition = DownPos;
                    if (PositionInSolution(characterPosition))
                        LastTimeOnPath = characterPosition;
                    Instruction = "moveDown";
                }
                break;
            case RIGHT:
            case NUMPAD6:
                Position RightPos = new Position(getCharacterPosition().getRowIndex(),getCharacterPosition().getColumnIndex()+1);
                if (maze.checkValidity(InitPos,width,height,RightPos,wall))
                {
                    previousPosition = characterPosition;
                    characterPosition = RightPos;
                    if (PositionInSolution(characterPosition))
                        LastTimeOnPath = characterPosition;
                    Instruction = "moveRight";
                }
                break;
            case LEFT:
            case NUMPAD4:
                Position LeftPos = new Position(getCharacterPosition().getRowIndex(),getCharacterPosition().getColumnIndex()-1);
                if (maze.checkValidity(InitPos,width,height,LeftPos,wall))
                {
                    previousPosition = characterPosition;
                    characterPosition = LeftPos;
                    if (PositionInSolution(characterPosition))
                        LastTimeOnPath = characterPosition;
                    Instruction = "moveLeft";
                }
                break;
            case NUMPAD9:
                Position UpRightPos = new Position(getCharacterPosition().getRowIndex()-1,getCharacterPosition().getColumnIndex()+1);
                if (maze.checkValidity(InitPos,width,height,UpRightPos,wall))
                {
                    if (maze.checkValidity(InitPos,width,height,UpRightPos.getPosLeft(),wall))
                        Instruction = "moveUpRight";
                    else if (maze.checkValidity(InitPos,width,height,UpRightPos.getPosDown(),wall))
                        Instruction = "moveRightUp";
                    if (!Instruction.equals("")){
                        previousPosition = characterPosition;
                        characterPosition = UpRightPos;
                        if (PositionInSolution(characterPosition))
                            LastTimeOnPath = characterPosition;
                    }
                }
                break;
            case NUMPAD7:
                Position UpLeftPos = new Position(getCharacterPosition().getRowIndex()-1,getCharacterPosition().getColumnIndex()-1);
                if (maze.checkValidity(InitPos,width,height,UpLeftPos,wall))
                {
                    if (maze.checkValidity(InitPos,width,height,UpLeftPos.getPosRight(),wall))
                        Instruction = "moveUpLeft";
                    else if (maze.checkValidity(InitPos,width,height,UpLeftPos.getPosDown(),wall))
                        Instruction = "moveLeftUp";
                    if (!Instruction.equals("")) {
                        previousPosition = characterPosition;
                        characterPosition = UpLeftPos;
                        if (PositionInSolution(characterPosition))
                            LastTimeOnPath = characterPosition;
                    }

                }
                break;
            case NUMPAD1:
                Position DownLeftPos = new Position(getCharacterPosition().getRowIndex()+1,getCharacterPosition().getColumnIndex()-1);
                if (maze.checkValidity(InitPos,width,height,DownLeftPos,wall))
                {
                    if (maze.checkValidity(InitPos,width,height,DownLeftPos.getPosRight(),wall))
                        Instruction = "moveDownLeft";
                    else if (maze.checkValidity(InitPos,width,height,DownLeftPos.getPosUp(),wall))
                        Instruction = "moveLeftDown";
                    if (!Instruction.equals("")) {
                        previousPosition = characterPosition;
                        characterPosition = DownLeftPos;
                        if (PositionInSolution(characterPosition))
                            LastTimeOnPath = characterPosition;
                    }
                }
                break;
            case NUMPAD3:
                Position DownRightPos = new Position(getCharacterPosition().getRowIndex()+1,getCharacterPosition().getColumnIndex()+1);
                if (maze.checkValidity(InitPos,width,height,DownRightPos,wall))
                {
                    if (maze.checkValidity(InitPos,width,height,DownRightPos.getPosLeft(),wall))
                        Instruction = "moveDownRight";
                    else if (maze.checkValidity(InitPos,width,height,DownRightPos.getPosUp(),wall))
                        Instruction = "moveRightDown";
                    if (!Instruction.equals("")) {
                        previousPosition = characterPosition;
                        characterPosition = DownRightPos;
                        if (PositionInSolution(characterPosition))
                            LastTimeOnPath = characterPosition;
                    }
                }
                break;
        }
        setChanged();
        notifyObservers(Instruction);
        //In case of reaching the goal state.
        if (characterPosition.Equals(maze.getGoalPosition())) {
            setChanged();
            notifyObservers("win");
        }
    }
    public Maze getMaze() {
        return maze;
    }
    public Position getPreviousPosition() {
        return previousPosition;
    }
    public Position getCharacterPosition() {
        return characterPosition;
    }
    public void startServers() {
        mazeGeneratingServer = new Server(5400, 1000, new ServerStrategyGenerateMaze());
        solveSearchProblemServer = new Server(5401, 1000, new ServerStrategySolveSearchProblem());
        mazeGeneratingServer.start();
        solveSearchProblemServer.start();
    }
    public void stopServers() {
        mazeGeneratingServer.stop();
        solveSearchProblemServer.stop();
    }
    public Solution getCurrentSol() {
        return currentSol;
    }

    public synchronized void solveMaze(Maze maze, boolean isGenerate){
        try {
            Client client = new Client(InetAddress.getLocalHost(), 5401, new IClientStrategy() {
                public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
                    try {
                       // System.out.println("Inside the client, isGenerate= " + isGenerate);
                        ObjectOutputStream toServer = new ObjectOutputStream(outToServer);
                        ObjectInputStream fromServer = new ObjectInputStream(inFromServer);
                        toServer.flush();
                        //    maze.print();
                        toServer.writeObject(maze);
                        toServer.flush();
                        currentSol = (Solution) fromServer.readObject();
                        /*System.out.println("I'm here, currsol is: " + currentSol);
                        ArrayList<AState> mazeSolutionSteps = currentSol.getSolutionPath();
                        System.out.println("solution size: " + mazeSolutionSteps.size());
                        for (int i = 0; i < mazeSolutionSteps.size(); ++i) {
                            System.out.println("Inside the for loop");
                            System.out.println(String.format("%s. %s", i, ((AState) mazeSolutionSteps.get(i)).toString()));
                        }*/
                        if (isGenerate)
                            LastTimeOnPath = characterPosition;
                        else{
                            setChanged();
                            notifyObservers("solveMaze");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.communicateWithServer();
        } catch (UnknownHostException e) {
        }
    }
    private boolean PositionInSolution(Position charPos){
        if(currentSol == null) //TODO change here
            return false;
        for (int i=0; i<currentSol.getSolutionPath().size();i++){
            if (charPos.Equals(((MazeState)currentSol.getSolutionPath().get(i)).getPos()))
                return true;
        }
        return false;
    }


    public synchronized void getLoadedMaze(File file) {
        try {
            InputStream in = new MyDecompressorInputStream(new FileInputStream(file));
            BufferedReader br = new BufferedReader(new FileReader(file));
            String finalInput = "";
            String temp = br.readLine();
            while (temp!=null){
                if (temp.equals(""))
                    temp="\n";
                finalInput+=temp;
                temp = br.readLine();
            }
            byte[] loadMaze = new byte[finalInput.length()*finalInput.length()];
            in.read(loadMaze);
            int rows = 0;
            int cols = 0;
            int counter = 0;
            int newSize = 0;
            int j=0;
            for(String s = ""; counter < 3; j += 2){
                if(loadMaze[j] - 48 == 0 && loadMaze[j + 1] - 48 == 0){
                    switch (counter){
                        case 0:
                            rows = Integer.parseInt(Maze.decodeToBinary(s), 2);
                            break;
                        case 1:
                            cols = Integer.parseInt(Maze.decodeToBinary(s), 2);
                            break;
                        case 2:
                            newSize = Integer.parseInt(Maze.decodeToBinary(s), 2);
                            break;
                    }
                    s="";
                    counter++;
                }
                else {
                    s = s + Integer.toString(loadMaze[j] - 48) + Integer.toString(loadMaze[j + 1] - 48);
                }
            }
            byte[] LoadedMaze = new byte[newSize];
            for(int i =0 ; i<newSize;i++){
                LoadedMaze[i]=loadMaze[j+i];
            }
            maze = new Maze(LoadedMaze);
            characterPosition = new Position(rows,cols);
            solveMaze(maze,true);
            setChanged();
            notifyObservers("generateMaze");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**Calculate if the mouse is out of the cell borders, if so, move the character accordingly.*/
    public void dragMouse(MouseEvent e, double cellWight, double cellHight) {
        double upBorder = characterPosition.getRowIndex() * cellHight;
        double leftBorder = characterPosition.getColumnIndex() * cellWight;
        double bottomBorder = (characterPosition.getRowIndex() + 1) * cellHight;
        double rightBorder = (characterPosition.getColumnIndex() + 1) * cellWight;
        // Move Up
        if((e.getY() < upBorder) && ((e.getX() < rightBorder) && (e.getX() > leftBorder)))
            moveCharacter(KeyCode.UP);
        // Move Down
        else if((e.getY() > bottomBorder) && ((e.getX() < rightBorder) && (e.getX() > leftBorder)))
            moveCharacter(KeyCode.DOWN);
        // Move Right
        else if((e.getX() > rightBorder) && ((e.getY() < bottomBorder) && (e.getY() > upBorder)))
            moveCharacter(KeyCode.RIGHT);
        // Move UP-Right
        else if((e.getY() < upBorder) && (e.getX() > rightBorder))
            moveCharacter(KeyCode.NUMPAD9);
        // Move Down-Left
        else if((e.getY() > bottomBorder) && (e.getX() < upBorder))
            moveCharacter(KeyCode.NUMPAD1);
        // Move Up-Left
        else if((e.getY() < upBorder) && (e.getX() < leftBorder))
            moveCharacter(KeyCode.NUMPAD7);
        // Move Down-Right
        else if((e.getY() > bottomBorder) && (e.getX() > rightBorder))
            moveCharacter(KeyCode.NUMPAD3);
        // Move Left
        else if((e.getX() < leftBorder) && ((e.getY() < bottomBorder) && (e.getY() > upBorder)))
            moveCharacter(KeyCode.LEFT);
    }

    @Override
    public void getHint() {
        setChanged();
        notifyObservers("getHint");

    }
    public void updateSolver(){
       // System.out.println("Update Solver");
        solveMaze(maze,true);}
    public void saveMazelocal(String saveName){
        String url = Main.Main.Configurations.GetProperty("db.url",true);
        File save = new File(url + File.separator + saveName + ".maze");
        byte[] cmpMaze = maze.toByteArray();
        StringBuilder s = new StringBuilder(Maze.encodeBinary(Integer.toBinaryString(characterPosition.getRowIndex())));
        s.append("00");
        s.append(Maze.encodeBinary(Integer.toBinaryString(characterPosition.getColumnIndex())));
        s.append("00");
        s.append(Maze.encodeBinary(Integer.toBinaryString(cmpMaze.length)));
        s.append("00");
        byte[] pos = s.toString().getBytes();
        byte[] completedSave = new byte[pos.length+cmpMaze.length];
        for(int i =0; i<completedSave.length;i++){
            if(i<pos.length)
                completedSave[i]=pos[i];
            else
                completedSave[i]= cmpMaze[i-pos.length];
        }
        OutputStream out = null;
        try {
            out = new MyCompressorOutputStream(new FileOutputStream(save));
            out.write(completedSave);
            setChanged();
            notifyObservers("saveSucceed");
        } catch (IOException e) {
            e.printStackTrace();
            setChanged();
            notifyObservers("saveFailed");
        }

    }

}