package dam.psp.GameCenter.Controllers;

import dam.psp.GameCenter.Util.ViewStatus;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainBodyController implements Initializable{
    
    public static List<Game> games = new Arr
    
    private ViewStatus viewStatus = ViewStatus.GAMES;

    @FXML
    private Button btnFavs;

    @FXML
    private Button btnGames;

    @FXML
    private Button btnSettings;

    @FXML
    private VBox vboxListBody;

    @FXML
    void btnFavsPressed(ActionEvent event) {

    }

    @FXML
    void btnGamesPressed(ActionEvent event) {

    }

    @FXML
    void btnSettingsPressed(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (new File("games.json").exists()) {
            
        }
    }
    
    

}
