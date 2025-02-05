package dam.psp.GameCenter;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(initScene());
        primaryStage.setTitle("GameCenter");
        primaryStage.getIcons().add(new Image("/media/app_icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private Scene initScene() {
        try {
            return new Scene(FXMLLoader.load(getClass().getResource("/vistas/frmMainBody.fxml")));
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading app fxml file");
            System.err.println(e.getCause());
            return null;
        }
    }
}
