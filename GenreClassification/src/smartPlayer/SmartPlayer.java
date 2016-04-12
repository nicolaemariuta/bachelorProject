package smartPlayer;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//launch smartplayer GUI from SmartPlayer.fxml
public class SmartPlayer extends Application {

	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		 Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// TODO Auto-generated method stub
		primaryStage.setTitle("SmartPlayer");
		primaryStage.setX(primaryScreenBounds.getWidth()-500);
		primaryStage.setY(0);
	//	primaryStage.setHeight(primaryScreenBounds.getHeight());
		
		Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("SMartPlayer.fxml"));
	
		Scene myScene = new Scene(myPane);
	//	myScene.getStylesheets().add("/smartPlayer/main.css");  
	//	myScene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
		primaryStage.setScene(myScene);
		primaryStage.show();
		
	}

}
