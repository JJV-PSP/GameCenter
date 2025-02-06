package dam.psp.GameCenter.Controllers;

import dam.psp.GameCenter.Model.Game;
import dam.psp.GameCenter.Util.DataManager;
import dam.psp.GameCenter.Util.ViewStatus;
import static dam.psp.GameCenter.Util.ViewStatus.GAMES;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class MainBodyController implements Initializable{
    
    public static List<Game> games = new ArrayList<>();
    
    private static final File DATA_FILE = new File("games.json");
    
    private ViewStatus viewStatus = ViewStatus.GAMES;
    
    private static List<Button> buttons = new ArrayList<>();
    
    private Stage stage;
    
    private double xOffset = 0;
    
    private double yOffset = 0;

    @FXML
    private Button btnClose;
    
    @FXML
    private Button btnMini;
    
    @FXML
    private Button btnFavs;

    @FXML
    private Button btnGames;
    
    @FXML
    private Button btnAdd;

    @FXML
    private VBox vboxListBody;
    
    @FXML
    private HBox hboxTitlebar;
    
    @FXML
    void btnClosePressed() {
        stage.close();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    @FXML
    void btnMiniPressed() {
        stage.setIconified(true);
    }


    @FXML
    void btnGamesPressed() {
        if (viewStatus != ViewStatus.GAMES) {
            viewStatus = ViewStatus.GAMES;
            buttonSelected(btnGames);
            drawList();
        }
    }
    
    @FXML
    void btnFavsPressed() {
        if (viewStatus != ViewStatus.FAVS) {
            viewStatus = ViewStatus.FAVS;
            buttonSelected(btnFavs);
            drawList();
        }
    }
    
    @FXML
    void btnAddPressed() {
        launchAddEditWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        buttons.add(btnGames);
        buttons.add(btnFavs);
        buttons.add(btnAdd);
        
        if (DATA_FILE.exists()) {
            games.addAll(DataManager.readGamesFromFile());
        }
        
        btnGamesPressed();
        
        drawList();
    }
    
    public void initDragStage() {
        hboxTitlebar.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        hboxTitlebar.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
    
    private void buttonSelected(Button button) {
        resetButtonsStyle();
        button.getStyleClass().clear();
        button.getStyleClass().add("background-body-color");
        button.getStyleClass().add("toolbar-button");
    }
    
    public static void resetButtonsStyle(){
        for(Button button : buttons) {
            button.getStyleClass().clear();
            button.getStyleClass().add("toolbar-color");
            button.getStyleClass().add("toolbar-button");
        }
    }
    
    private List<Game> filterFavGames() {
        List<Game> favGames = new ArrayList<>();
        for (Game game : games) {
            if (game.isFav()) favGames.add(game);
        }
        return favGames;
    }
    
    private void clearBody() {
        vboxListBody.getChildren().clear();
    }
    
    public void drawList() {
        switch (viewStatus) {
            case GAMES -> {
                fillGamesBody(games);
            }
            case FAVS -> {
                fillGamesBody(filterFavGames());
            }
        }
    }
    
    private void fillGamesBody(List<Game> gamesLoaded) {
        clearBody();
        if (gamesLoaded != null) {
            for (Game game : gamesLoaded) {
                vboxListBody.getChildren().add(createGameCard(game));
            }
        }
    }
    
    private Parent createGameCard(Game game) {
        try {
            FXMLLoader fXMLloader = new FXMLLoader(getClass().getResource("/vistas/frmGameCard.fxml"));
            Parent parent = fXMLloader.load();
            GameCardController gcController = fXMLloader.getController();
            gcController.setGame(game);
            gcController.setMbController(this);
            return parent;
        } catch(IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading game card fxml file");
            System.err.println(e.getCause());
            return null;
        }        
    }
    
    private void launchAddEditWindow() {
        try {
            Stage addEditStage = new Stage();
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/vistas/frmAddEdit.fxml"));
            addEditStage.setScene(new Scene(fXMLLoader.load()));
            addEditStage.initStyle(StageStyle.UNDECORATED);
            addEditStage.initModality(Modality.WINDOW_MODAL);
            addEditStage.initOwner(stage);
            AddEditController aeController = fXMLLoader.getController();
            aeController.initDragStage();
            addEditStage.showAndWait();
            if (aeController.getResult()) {
                if (viewStatus == ViewStatus.GAMES) {
                    fillGamesBody(games);
                } else {
                    fillGamesBody(filterFavGames());
                }
            }
        } catch(IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading add edit window fxml file");
            System.err.println(e.getCause());
        }  
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
