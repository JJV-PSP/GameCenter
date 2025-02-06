package dam.psp.GameCenter;

import dam.psp.GameCenter.Controllers.MainBodyController;
import static dam.psp.GameCenter.Controllers.MainBodyController.games;
import dam.psp.GameCenter.Util.DataManager;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(initScene(primaryStage));
        primaryStage.setTitle("GameCenter");
        primaryStage.getIcons().add(new Image("/media/app_icon.png"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        initSecureClose(primaryStage);
        primaryStage.show();
    }
    
    private Scene initScene(Stage stage) {
        try {
            FXMLLoader fXMLL = new FXMLLoader(getClass().getResource("/vistas/frmMainBody.fxml"));
            Scene scene = new Scene(fXMLL.load());
            MainBodyController mbController = fXMLL.getController();
            mbController.setStage(stage);
            mbController.initDragStage();
            return scene;
        } catch (IOException e) {
            System.err.println("Error in " + this.getClass().toString() + " loading app fxml file");
            System.err.println(e.getCause());
            return null;
        }
    }
    
    private void initSecureClose(Stage stage) {
        stage.setOnCloseRequest(event -> {
            DataManager.writeGamesToFile(games);
        });
    }
}
