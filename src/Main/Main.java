package Main;

import Model.MyModel;
import Server.Server;
import View.MainMenuController;
import View.MazeController;
import ViewModel.MyViewModel;
import animatefx.animation.Pulse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import static View.MazeController.viewModel;

public class Main extends Application {
    private static Stage window;
    private static Parent mazeWindow;
    public static MediaPlayer music;
    public static MediaPlayer sfx;
    private static Scene mazeScene;
    private static URL path;

    @Override
    public void start(Stage primaryStage) throws Exception {
        addSaveDirtoDocuments();
        setWindowdefualtSettings();
        MyModel model = new MyModel();
        MyViewModel viewModel = new MyViewModel(model);
        model.startServers();
        model.addObserver(viewModel);
        window = primaryStage;
        FXMLLoader mainMenuLoader = new FXMLLoader(getClass().getResource("/View/MainMenu.fxml"));
        Parent root = mainMenuLoader.load();
        window.setMinHeight(500);
        window.setMinWidth(650);
        double windowWidth = Double.valueOf(Configurations.GetProperty("db.width",true));
        double windowHeight = Double.valueOf(Configurations.GetProperty("db.height",true));
        window.setScene(new Scene(root, windowWidth, windowHeight));
        FXMLLoader mazeSceneLoader = new FXMLLoader(getClass().getResource("/View/Maze.fxml"));
        mazeWindow = mazeSceneLoader.load();
        MazeController viewMaze = mazeSceneLoader.getController();
        MainMenuController mainMenuController = mainMenuLoader.getController();
        viewMaze.setViewModel(viewModel);
        viewModel.addObserver(viewMaze);
        viewModel.addObserver(mainMenuController);
        mazeScene = new Scene(mazeWindow, window.getScene().getWidth(), window.getScene().getHeight());
        Media s_sfx = new Media(Paths.get("src/View/sound/Onbutton.wav").toUri().toString());
        sfx = new MediaPlayer(s_sfx);
        setMenuMusic();
        setCurrectVolume("sfx",sfx);
        setCurrectVolume("music",music);
        music.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                music.stop();
                music.play();
                if(music.isMute())
                    music.setMute(true);
            }
        });
        window.setFullScreen(checkIfFullscreen());
        window.show();
        window.setOnCloseRequest(e -> {
            exitSafe();
        });
        window.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                changeToFullscreen();
            else{
                Configurations.SetProperty("db.fullscreen","false",true);
                window.setHeight(600);
                window.setWidth(800);
            }
        });
    }

    public static void setMazeMusic(){
        if (music!=null)
            music.stop();
        Media mazeMusic = new Media(Paths.get("resources/survivor.mp3").toUri().toString());
        music = new MediaPlayer(mazeMusic);
        music.setCycleCount(MediaPlayer.INDEFINITE);
        music.setVolume(Double.valueOf(Configurations.GetProperty("db.music",true))/600);
        music.play();
    }
    public static void setMenuMusic(){
        if (music!=null)
            music.stop();
        Media menuMusic = new Media(Paths.get("src/View/sound/Think.wav").toUri().toString());
        music = new MediaPlayer(menuMusic);
        music.setCycleCount(MediaPlayer.INDEFINITE);
        music.setVolume(Double.valueOf(Configurations.GetProperty("db.music",true))/100);
        music.play();
    }
    private void setCurrectVolume(String value,MediaPlayer mediaPlayer){
        String s = Configurations.GetProperty("db."+value,true);
        if(s.equals("0.0"))
            mediaPlayer.setMute(true);
        else
            mediaPlayer.setVolume(Double.valueOf(s)/100);
    }
    public static Stage getWindow(){return window;}
    public static void main(String[] args) {
        launch(args);
    }
    private void setWindowdefualtSettings(){
        if(Configurations.GetProperty("db.width",true) == null)
            Configurations.SetProperty("db.width","800",true);
        if(Configurations.GetProperty("db.height",true) == null)
            Configurations.SetProperty("db.height","600",true);
        if(Configurations.GetProperty("db.music",true) == null)
            Configurations.SetProperty("db.music","100",true);
        if(Configurations.GetProperty("db.sfx",true) == null)
            Configurations.SetProperty("db.sfx","100",true);
    }
    public static void exitSafe(){
        Configurations.SetProperty("db.width",Double.toString(getWindow().getWidth()),true);
        Configurations.SetProperty("db.height",Double.toString(getWindow().getHeight()),true);
        Configurations.SetProperty("db.music",Double.toString(music.getVolume()*100),true);
        Configurations.SetProperty("db.sfx",Double.toString(sfx.getVolume()*100),true);
        Platform.exit();
        viewModel.getModel().stopServers();
    }
    private void addSaveDirtoDocuments(){
        String data = Configurations.GetProperty("db.url",true);
        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        String path = fw.getDefaultDirectory().getPath()+ File.separator + "TheMazeGame";
        if(data == null || !data.equals(path)){
            Configurations.SetProperty("db.url",path,true);
            File dirMazes = new File(fw.getDefaultDirectory().getPath() + File.separator + "TheMazeGame");
            if (!dirMazes.exists()) {
                dirMazes.mkdir();
            }
        }
    }

    private void addAllsolveAlgorithmToConfig(){
        String isNUll = Configurations.GetProperty("db.allAlgorithms",false);
        if(isNUll == null){
            Configurations.SetProperty("db.allAlgorithms","Depth-first search,Breadth-first search,Best-first search",true);
        }
    }

    public static void SwitchToMazeScene() {
        if (mazeWindow.getScene()==null)
            mazeScene.setRoot(mazeWindow);
        if (mazeWindow != null) {
            if (isFullscreen())
                Configurations.SetProperty("db.fullscreen","true",true);
            else
                Configurations.SetProperty("db.fullscreen","false",true);
            window.setScene(mazeScene);
            if (Configurations.GetProperty("db.fullscreen",true).equals("true"))
                window.setFullScreen(true);
            else{
                window.setWidth(800);
                window.setHeight(600);
            }
        }
    }
    public static void switchScenes(Parent root){
        if (root!=null){
            if(window.isFullScreen()){
                window.getScene().setRoot(root);
                window.setFullScreen(true);
            }
            else {
                window.setScene(new Scene(root, window.getScene().getWidth(), window.getScene().getHeight()));
            }
            window.show();
        }
    }
    public static void addAdjustmentsToButton(Region region, Pane pane){
        HBox.setHgrow(region, Priority.ALWAYS);
        VBox.setVgrow(region, Priority.ALWAYS);
        region.prefHeightProperty().bind(pane.heightProperty().multiply(region.getPrefHeight()/pane.getPrefHeight()));
        region.prefWidthProperty().bind(pane.widthProperty().multiply(region.getPrefWidth()/pane.getPrefWidth()));
    }
    public static void changeToFullscreen(){
        if(window.isFullScreen()){
            window.setFullScreen(false);
            Configurations.SetProperty("db.fullscreen","false",true);
            window.setHeight(600);
            window.setWidth(800);
        }
        else {
            window.setFullScreen(true);
            Configurations.SetProperty("db.fullscreen","true",true);
        }
    }
    private boolean checkIfFullscreen(){
        String fullscreen = Configurations.GetProperty("db.fullscreen",true);
        if(fullscreen == null){
            Configurations.SetProperty("db.fullscreen","false",true);
            return false;
        }
        return fullscreen.equals("true");
    }
    public static boolean isFullscreen(){
        return window.isFullScreen();
    }
    public static void setButtonPulseEffect(Button button){
        sfx.play();
        Pulse pulse = new Pulse(button);
        pulse.setSpeed(2);
        sfx.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                sfx.stop();
            }
        });
        pulse.play();
        /// TODO sfx doent work proper
    }
    public static void setVolume(MediaPlayer mediaPlayer,Double volume){
        mediaPlayer.setVolume(volume);
    }
    public static Double getVolume(MediaPlayer mediaPlayer){
        return mediaPlayer.getVolume();
    }

    public static class Configurations {

        public static void SetProperty(String dbname , String data, boolean isUser) {
            try {
                String path = "resources/userconfig.properties";
                if(!isUser){
                    path = Server.Configuration.class.getResource("serverconfig.properties").getPath();
                }
                File file = new File(path);
                if (!file.exists()) {
                    file.createNewFile();
                }
                Properties prop = new Properties();
                InputStream fileStream = new FileInputStream(file);
                prop.load(fileStream);
                prop.setProperty(dbname, data);
                fileStream.close();
                OutputStream fileStream1 = new FileOutputStream(file);
                prop.store(fileStream1, (String)null);
                fileStream1.close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        }

        public static String  GetProperty(String dbname, boolean isUser) {
            try {
                String path = "resources/userconfig.properties";
                File file = new File(path);
                if (!file.exists()) {
                    throw new FileNotFoundException();
                }
                InputStream fileStream = new FileInputStream(file);
                Properties prop = new Properties();
                prop.load(fileStream);
                String data = prop.getProperty(dbname);
                fileStream.close();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}




