package pp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        new RSA().setPQ(11,17);
        Parent root = FXMLLoader.load(getClass().getResource("Ventana.fxml"));
        primaryStage.setTitle("Cifrado RSA");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}