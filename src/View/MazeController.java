package View;

import Main.Main;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import animatefx.animation.FadeIn;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static Main.Main.addAdjustmentsToButton;
import static Main.Main.setButtonPulseEffect;
import static javafx.scene.input.MouseEvent.MOUSE_CLICKED;
import static javafx.scene.input.MouseEvent.MOUSE_ENTERED;
/**Maze controller, controls all actions related to the maze scene.*/
public class MazeController extends Pane implements Observer, IView, Initializable {
    public static MyViewModel viewModel;
    public static String height;
    public static String width;
    public static final Set<String> runningAnimates = new HashSet<>();
    public static boolean isPressed = false;
    public static boolean isSave = false;
    @FXML
    public AnchorPane Mazepane;
    public MazeDisplay mazeDisplayer;
    public PlayerDisplay playerDisplayer;
    public ScrollPane Canvaspane;
    public Button BackButton,b_hint,b_solve,b_save,b_mazesettings;

    //set ViewModel for the controller.
    public void setViewModel(MyViewModel p_viewModel) {
        viewModel = p_viewModel;
    }
    //Clear all before closing.
    private void clearBeforeClose(){
        mazeDisplayer.clearBeforeClose();
        playerDisplayer.clearBeforeClose();
        viewModel.clearBeforeClose();
    }
    /**Implements the observer methods update. Gets notified by the view model*/
    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            switch ((String) arg) {
                //Generate maze.
                case "generateMaze":
                    playerDisplayer.setPlayerTimeLines();
                    playerDisplayer.setCharacterPosition(viewModel.getCharacterPosition());
                    displayMaze(viewModel.getMaze());
                    playerDisplayer.DrawCharacterPosition(viewModel.getCharacterPosition());
                    break;
                //All possible moves: clear current spot, stop idle animation, start walking animation.
                case "moveRight":
                    playerDisplayer.ClearCharacterSpot();
                    playerDisplayer.stopCharIdle();
                    playerDisplayer.animateWalk("RIGHT");
                    break;
                case "moveLeft":
                    playerDisplayer.ClearCharacterSpot();
                    playerDisplayer.stopCharIdle();
                    playerDisplayer.animateWalk("LEFT");
                    break;
                case "moveUp":
                    playerDisplayer.ClearCharacterSpot();
                    playerDisplayer.stopCharIdle();
                    playerDisplayer.animateWalk("UP");
                    break;
                case "moveDown":
                    playerDisplayer.ClearCharacterSpot();
                    playerDisplayer.stopCharIdle();
                    playerDisplayer.animateWalk("DOWN");
                    break;
                //All diagonal moves, built from two moves, one after another.
                case "moveUpRight":
                    playerDisplayer.stopCharIdle();
                    Timeline UpRight = new Timeline();
                    UpRight.setCycleCount(1);
                    UpRight.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosLeft());
                        playerDisplayer.animateWalk("UP");
                    }));
                    UpRight.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosRight());
                        playerDisplayer.animateWalk("RIGHT");
                    }));
                    UpRight.play();
                    break;
                case "moveRightUp":
                    playerDisplayer.stopCharIdle();
                    Timeline RightUp = new Timeline();
                    RightUp.setCycleCount(1);
                    RightUp.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosDown());
                        playerDisplayer.animateWalk("RIGHT");
                    }));
                    RightUp.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosUp());
                        playerDisplayer.animateWalk("UP");
                    }));
                    RightUp.play();
                    break;
                case "moveRightDown":
                    playerDisplayer.stopCharIdle();
                    Timeline RightDown = new Timeline();
                    RightDown.setCycleCount(1);
                    RightDown.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosUp());
                        playerDisplayer.animateWalk("RIGHT");
                    }));
                    RightDown.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosDown());
                        playerDisplayer.animateWalk("DOWN");
                    }));
                    RightDown.play();
                    break;
                case "moveDownRight":
                    playerDisplayer.stopCharIdle();
                    Timeline DownRight = new Timeline();
                    DownRight.setCycleCount(1);
                    DownRight.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosLeft());
                        playerDisplayer.animateWalk("DOWN");
                    }));
                    DownRight.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosRight());
                        playerDisplayer.animateWalk("RIGHT");
                    }));
                    DownRight.play();
                    break;
                case "moveLeftUp":
                    playerDisplayer.stopCharIdle();
                    Timeline LeftUp = new Timeline();
                    LeftUp.setCycleCount(1);
                    LeftUp.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosDown());
                        playerDisplayer.animateWalk("LEFT");
                    }));
                    LeftUp.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosUp());
                        playerDisplayer.animateWalk("UP");
                    }));
                    LeftUp.play();
                    break;
                case "moveUpLeft":
                    playerDisplayer.stopCharIdle();
                    Timeline UpLeft = new Timeline();
                    UpLeft.setCycleCount(1);
                    UpLeft.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosRight());
                        playerDisplayer.animateWalk("UP");
                    }));
                    UpLeft.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosLeft());
                        playerDisplayer.animateWalk("LEFT");
                    }));
                    UpLeft.play();
                    break;
                case "moveLeftDown":
                    playerDisplayer.stopCharIdle();
                    Timeline LeftDown = new Timeline();
                    LeftDown.setCycleCount(1);
                    LeftDown.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosUp());
                        playerDisplayer.animateWalk("LEFT");
                    }));
                    LeftDown.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosDown());
                        playerDisplayer.animateWalk("DOWN");
                    }));
                    LeftDown.play();
                    break;
                case "moveDownLeft":
                    playerDisplayer.stopCharIdle();
                    Timeline DownLeft = new Timeline();
                    DownLeft.setCycleCount(1);
                    DownLeft.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosRight());
                        playerDisplayer.animateWalk("DOWN");
                    }));
                    DownLeft.getKeyFrames().add(new KeyFrame(Duration.millis(650), e -> {
                        viewModel.setCharacterPosition(viewModel.getCharacterPosition().getPosLeft());
                        playerDisplayer.animateWalk("LEFT");
                    }));
                    DownLeft.play();
                    break;
                case "solveMaze":
                    drawSolution(viewModel.currentSol);
                    break;
                case "getHint":
                    drawHint(viewModel.getLastTimeOnPath());
                    break;
                case "HideSolution":
                    HideSolution();
                    break;
                case "win":
                    openWinWindow();
                    break;
                case "saveSucceed":
                    saveGame("Succeed");
                    break;
                case "saveFailed":
                    saveGame("Failed");
                    break;

            }
        }
    }
    //Save the game.
    private void saveGame(String worked){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"", ButtonType.OK);
        alert.getDialogPane().setStyle("-fx-border-color: black;");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.initOwner(Main.getWindow());
        alert.setHeaderText("Save Game");
        if(worked.equals("Succeed")){
            alert.setContentText("Save Succeed");
            isSave = true;}
        else
            alert.setContentText("Something failed : please try again.");
        alert.showAndWait();
    }
    //Hide solution.
    private void HideSolution() {
        playerDisplayer.clearAll();
        playerDisplayer.animateCharIdle();
    }
    //Draw hint.
    private void drawHint(Position LastTimeOnPath) {
        playerDisplayer.DrawHint(LastTimeOnPath);
        //playerDisplayer.HideHint(LastTimeOnPath);
    }

    //Open win window
    public void openWinWindow() {
        Stage winStage = new Stage();
        FXMLLoader winWindowLoader = new FXMLLoader(getClass().getResource("/View/winWindow.fxml"));
        Parent root = null;
        try {
            root = winWindowLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene winScene = new Scene(root,800,600);
        winStage.setScene(winScene);
        winStage.initModality(Modality.APPLICATION_MODAL);
        winStage.setResizable(false);
        winStage.setAlwaysOnTop(true);
        winStage.setOnCloseRequest(e->{
            e.consume();
            if (WinController.Done){
                clearAllbeforeClose();
                clearBeforeClose();
                Main.setMenuMusic();
                switchScene("MainMenu.fxml");
                winStage.close();
            }
        });
        winStage.show();
    }
    //Draw solution.
    public void drawSolution(Solution currentSol) {
        playerDisplayer.drawSolution(currentSol);
    }

    //Display the maze.
    public synchronized void displayMaze(Maze maze) {
        mazeDisplayer.setMaze(maze);
        mazeDisplayer.animateGolemIdle();
        FadeIn FadeInMaze = new FadeIn(mazeDisplayer);
        FadeInMaze.setSpeed(0.5);
        FadeIn FadeInPlayer = new FadeIn(playerDisplayer);
        FadeInMaze.setSpeed(0.5);
        FadeInMaze.play();
        FadeInPlayer.play();
        playerDisplayer.setMazeSize(maze.getMaze().length,maze.getMaze()[0].length);
    }
    //Generate maze.
    public static void generateMaze() {
        int mazeHeight = Integer.valueOf(height);
        int mazeWidth = Integer.valueOf(width);
        viewModel.generateMaze(mazeHeight, mazeWidth);
    }

    public void KeyPressed(KeyEvent keyEvent) {

        if (!runningAnimates.contains("Pressed")) { //Defend animation.
            isSave = false;
            Timeline moveTimeline = new Timeline();
            moveTimeline.setCycleCount(1);
            Mazepane.setDisable(true);
            runningAnimates.add("Pressed");
            //Trigger movement of character by arrow key / NUMPAD pressed.
            moveTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(0), e -> {
                viewModel.moveCharacter(keyEvent.getCode());
                keyEvent.consume();
            }));
            moveTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(595), e -> {}));
            moveTimeline.play();
            Mazepane.setDisable(false);
        }
    }


    public synchronized void KeyReleased(KeyEvent keyEvent){
        if (runningAnimates.contains("Pressed")) {
            runningAnimates.remove("Pressed");
        }
    }


    /**Move character by drag.*/
    public void moveByDrag(MouseEvent mouseEvent) {
        viewModel.moveByDrag(mouseEvent,mazeDisplayer.getWidth(),mazeDisplayer.getHeight());
    }

    /**Alert before exit without saving.*/
    private void areYouSureNoSave(){
        if(isSave){
            isSave = false;
            clearAllbeforeClose();
            clearBeforeClose();
            Main.setMenuMusic();
            switchScene("MainMenu.fxml");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit without saving ?", ButtonType.YES,ButtonType.CANCEL);
            alert.getDialogPane().setStyle("-fx-border-color: black;");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.initOwner(Main.getWindow());
            alert.setHeaderText("Quit Game");
            alert.showAndWait();
            ButtonType selected = alert.getResult();
            if (selected == ButtonType.CANCEL)
                alert.close();
            else if(selected == ButtonType.YES){
                clearAllbeforeClose();
                clearBeforeClose();
                Main.setMenuMusic();
                switchScene("MainMenu.fxml");}
        }
    }
    //Clear all before close.
    private void clearAllbeforeClose(){
        //System.out.println(viewModel.getMaze() + " : " + playerDisplayer.getCharacterPosition());
        if (viewModel.getMaze()!=null){
            mazeDisplayer.stopGolemIdle();
        }
        if (playerDisplayer.getCharacterPosition()!=null){
            playerDisplayer.stopCharIdle();
            playerDisplayer.clearAll();}
    }

    //Save game.
    private void SaveGame(){
        TextInputDialog EnterSaveName = new TextInputDialog();
        EnterSaveName.setHeaderText("Save Game");
        EnterSaveName.setContentText("Enter Name :");
        EnterSaveName.getDialogPane().setStyle("-fx-border-color: black;");
        EnterSaveName.initModality(Modality.APPLICATION_MODAL);
        EnterSaveName.initStyle(StageStyle.UNDECORATED);
        EnterSaveName.initOwner(Main.getWindow());
        Optional<String> result = EnterSaveName.showAndWait();
        result.ifPresent(s -> viewModel.saveMazelocal(s)); //TODO check text validity
    }

    /**Implement the interface Initializable, set the default settings for window the moment of initialize.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeScene();
        //Set all buttons actions.
        BackButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e -> {areYouSureNoSave();});
        BackButton.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(BackButton));
        b_hint.addEventHandler(MOUSE_CLICKED, event -> {playerDisplayer.DrawHint(viewModel.getLastTimeOnPath());mazeDisplayer.redraw();});
        b_hint.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_hint));

        b_solve.addEventHandler(MOUSE_CLICKED, event ->viewModel.solveMaze());
        b_solve.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_solve));

        b_mazesettings.addEventHandler(MOUSE_CLICKED, event ->{
            SettingsController.onPlay = true;
            viewModel.setSolisShown(true);
            HideSolution();
            Main.setMenuMusic();
            switchScene("Settings.fxml");
        });
        b_mazesettings.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_mazesettings));

        b_save.addEventHandler(MOUSE_CLICKED, event -> SaveGame());
        b_save.addEventHandler(MOUSE_ENTERED, e-> setButtonPulseEffect(b_save));

        Mazepane.setOnScroll(this::ScrollHandle);
        //Allow moving with arrows while in zoom in mode.
        Canvaspane.addEventFilter(KeyEvent.ANY,e->{
            if (e.getCode().isArrowKey()){
                KeyPressed(e);
                Canvaspane.setFocusTraversable(true);
            }
        });
    }
    private void initializeScene(){
        HBox.setHgrow(mazeDisplayer, Priority.ALWAYS);
        VBox.setVgrow(mazeDisplayer, Priority.ALWAYS);
        HBox.setHgrow(playerDisplayer, Priority.ALWAYS);
        VBox.setVgrow(playerDisplayer, Priority.ALWAYS);
        addAdjustmentsToButton(Canvaspane,Mazepane);
        addAdjustmentsToButton(BackButton,Mazepane);
        addAdjustmentsToButton(b_save,Mazepane);
        addAdjustmentsToButton(b_hint,Mazepane);
        addAdjustmentsToButton(b_solve,Mazepane);
        addAdjustmentsToButton(b_mazesettings,Mazepane);
        mazeDisplayer.maxHeight(1080);
        mazeDisplayer.maxWidth(1920);
        playerDisplayer.maxHeight(1080);
        playerDisplayer.maxWidth(1920);
        widthPropertyHallan();

    }
    public void widthPropertyHallan(){
        Mazepane.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            Canvaspane.setLayoutY(303*(newSceneHeight.doubleValue()/1080));
            BackButton.setLayoutY(26*(newSceneHeight.doubleValue()/1080));
            b_save.setLayoutY(26*(newSceneHeight.doubleValue()/1080));
            b_hint.setLayoutY(26*(newSceneHeight.doubleValue()/1080));
            b_solve.setLayoutY(26*(newSceneHeight.doubleValue()/1080));
            b_mazesettings.setLayoutY(26*(newSceneHeight.doubleValue()/1080));
            extendWindowAnimation();
        });
        Mazepane.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            Canvaspane.setLayoutX(0);
            BackButton.setLayoutX(170*(newSceneWidth.doubleValue()/1920));
            b_save.setLayoutX(1633*(newSceneWidth.doubleValue()/1920));
            b_hint.setLayoutX(1057*(newSceneWidth.doubleValue()/1920));
            b_solve.setLayoutX(1326*(newSceneWidth.doubleValue()/1920));
            b_mazesettings.setLayoutX(45*(newSceneWidth.doubleValue()/1920));
            extendWindowAnimation();
        });
    }
    /**Adjust animation to window size.*/
    private void extendWindowAnimation(){
        if (viewModel.getMaze()!=null ){
            if(!mazeDisplayer.isTimeLineNull()){
                mazeDisplayer.stopGolemIdle();
                mazeDisplayer.animateGolemIdle();
            }
            mazeDisplayer.redraw();
        }
        if (playerDisplayer.getCharacterPosition()!=null && !playerDisplayer.isTimeLineNull() ){
            playerDisplayer.stopCharIdle();
            playerDisplayer.stopCharWalk();
            playerDisplayer.clearAll();
            playerDisplayer.animateCharIdle();
        }
    }

    /**Scroll handler function*/
    private void ScrollHandle(ScrollEvent event){
        if(!event.isControlDown())
            return;
        double zoom = 1.05;
        double deltaY = event.getDeltaY();
        if(deltaY<0)
            zoom = 0.95;
        //Set bounds for zooming, if out of bound - do nothing.
        if (mazeDisplayer.widthProperty().getValue()*zoom>1650*2.5 ||mazeDisplayer.widthProperty().getValue()*zoom<1650 )
            return;
        if (playerDisplayer.widthProperty().getValue()*zoom>1650*2.5 ||playerDisplayer.widthProperty().getValue()*zoom<1650 )
            return;
        if (mazeDisplayer.heightProperty().getValue()*zoom>668*2.5 ||mazeDisplayer.heightProperty().getValue()*zoom<668 )
            return;
        if (playerDisplayer.heightProperty().getValue()*zoom>668*2.5 ||playerDisplayer.heightProperty().getValue()*zoom<668 )
            return;
        mazeDisplayer.widthProperty().setValue(mazeDisplayer.widthProperty().getValue() * zoom);
        mazeDisplayer.heightProperty().setValue(mazeDisplayer.heightProperty().getValue() * zoom);
        playerDisplayer.widthProperty().setValue(playerDisplayer.widthProperty().getValue() * zoom);
        playerDisplayer.heightProperty().setValue(playerDisplayer.heightProperty().getValue() * zoom);
        extendWindowAnimation();


    }

    //Switch scene gi
    private void switchScene(String newScense){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(newScense));

        } catch (IOException ignored) {
        }
        Main.switchScenes(root);
    }
}
