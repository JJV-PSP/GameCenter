package dam.psp.GameCenter.Controllers;

import dam.psp.GameCenter.Model.Game;
import dam.psp.GameCenter.Util.ImageTools;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;

public class GameCardController {

    private Game game;
    
    private MainBodyController mbController;
    
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnPlay;

    @FXML
    private ImageView imgFavStar;
    
    @FXML
    private ImageView imgGame;

    @FXML
    private Label lblGameName;

    @FXML
    private Label lblGameRoute;

    @FXML
    void btnDeletePressed() {
        MainBodyController.games.remove(this.game);
        mbController.drawList();
    }

    @FXML
    void btnFavPressed() {
        this.game.setFav(!this.game.isFav());
        changeStarSat();
        mbController.drawList();
    }

    @FXML
    void btnPlayPressed() {
        ProcessBuilder processBuilder = new ProcessBuilder(game.getUrl());
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading process file");
            System.err.println(e.getCause());
        } catch (InterruptedException e) {
            System.err.println("Error in " + this.getClass().toString() + " thread interrupted");
            System.err.println(e.getCause());
        }
    }

    public void setGame(Game game) {
        this.game = game;
        loadGameData();
    }

    public void setMbController(MainBodyController mbController) {
        this.mbController = mbController;
    }

    public void loadGameData() {
        imgGame.setImage(ImageTools.loadImgFromX64(game.getImage()));
        lblGameName.setText(game.getName());
        lblGameRoute.setText(game.getUrl());
        changeStarSat();
    }
    
    public void changeStarSat(){
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setSaturation(game.isFav() ? 1 : -1);
    imgFavStar.setEffect(colorAdjust);
    }

}
