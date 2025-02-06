package dam.psp.GameCenter.Controllers;

import dam.psp.GameCenter.Model.Game;
import dam.psp.GameCenter.Util.ImageTools;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddEditController {

    private Game game;
    
    private Image image;
    
    private String imagex64;
    
    private boolean result;
    
    private boolean editing = false;
    
    @FXML
    private Button btnClose;

    @FXML
    private Button btnMini;

    @FXML
    private HBox hboxTitlebar;

    @FXML
    private ImageView imgImage;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPath;

    @FXML
    void btnAddImagePressed() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpeg", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            image = ImageTools.resizeImageToSquare(new Image(selectedFile.toURI().toString()));
            imgImage.setImage(image);
            imagex64 = ImageTools.loadX64FromImage(image);
        }
    }

    @FXML
    void btnCancelPressed() {
        result = false;
        closeWindow();
    }
    
    @FXML
    void btnSavePressed() {
        result = true;
        if (editing) {
            MainBodyController.games.remove(game);
            MainBodyController.games.add(new Game(imagex64, txtName.getText(), txtPath.getText(), game.isFav()));
        } else {
            MainBodyController.games.add(new Game(imagex64, txtName.getText(), txtPath.getText(), false));
        }
        closeWindow();
    }

    @FXML
    void btnClosePressed() {
        result = false;
        closeWindow();
    }

    @FXML
    void btnPathPressed() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an game executable file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Executable Files", "*.exe", "*.jar", "*.lnk", "*.url"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            txtPath.setText(selectedFile.toURI().toString());
        }
    }

    public void setGame(Game game) {
        this.game = game;
        editing = true;
        setGameData();
    }
    
    private void setGameData() {
        txtName.setText(game.getName());
        txtPath.setText(game.getUrl());
        imagex64 = game.getImage();
        imgImage.setImage(ImageTools.loadImgFromX64(imagex64));
    }

    public boolean getResult() {
        return result;
    }
    
    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
}
