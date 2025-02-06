package dam.psp.GameCenter.Controllers;

import dam.psp.GameCenter.Model.Game;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class GameCard {
    private Game game;
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnFav;

    @FXML
    private Button btnPlay;

    @FXML
    private ImageView imgGame;

    @FXML
    private Label lblGameName;

    @FXML
    private Label lblGameRoute;

    @FXML
    void btnDeletePressed() {
        MainBodyController.games.remove(this.game);
    }

    @FXML
    void btnEditPressed() {
        //TO-DO Agregar vista OGETE OGETE LAS MEJORES PUTAS DE LA ZONA, 
        //NO TE LA PODER PERDER HIJO DE LA REMILL, SI NO ESTÁS ASI, ANDATE A LA CONCHA DE A LORA.
    }

    @FXML
    void btnFavPressed() {
      this.game.setFav(!this.game.isFav());
    }

    @FXML
    void btnPlayPressed() {
         
        ProcessBuilder processBuilder = new ProcessBuilder(game.getUrl());

        try {
            // Iniciar el proceso
            Process process = processBuilder.start();

            // Esperar a que el proceso termine (opcional)
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
    }
    
    public void loadGameData(){
        //TO-DO Setear la imagen de la tarjeta de canción pasandola a abse 64 imgGame.setImage(game);
        lblGameName.setText(game.getName());
        lblGameRoute.setText(game.getUrl());
    
    }
    
    
    
}
