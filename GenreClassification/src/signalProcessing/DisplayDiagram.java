package signalProcessing;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

//launch FXML containing the plot view for testing
public class DisplayDiagram extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start( Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Display Plot");
		Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("DisplayPlot.fxml"));
	
		Scene myScene = new Scene(myPane);
	//	myScene.getStylesheets().add("/test/control.css");
		primaryStage.setScene(myScene);
		primaryStage.show();
	}
}
