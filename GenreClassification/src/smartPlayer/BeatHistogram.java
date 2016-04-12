package smartPlayer;

import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.layout.StackPane;
import javafx.scene.chart.NumberAxis;

//display beat histogram for selected song
public class BeatHistogram extends Application{
	
	
	 @FXML
	 private LineChart<Number, Number> lineChart;
	
	 Song song;
	
	 final ObservableList<XYChart.Series<Number, Number>> lineChartData = FXCollections.observableArrayList();
	 final LineChart.Series<Number, Number> series = new LineChart.Series<Number, Number>();
	
	
	BeatHistogram(Song song)
	{
		this.song = song;
	}

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		 Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		
		// TODO Auto-generated method stub
		primaryStage.setTitle("Beat Histogram-" + song.getGenre() + "-" + song.getName());
		primaryStage.setX(10);
		primaryStage.setY(primaryScreenBounds.getHeight()-600);
		primaryStage.setWidth(primaryScreenBounds.getHeight()/8);
		primaryStage.setWidth(primaryScreenBounds.getWidth()/2);
		
		AnchorPane pane = new AnchorPane();
		pane.setStyle("signalProcessing/plot.css");
	//	pane.setPrefHeight(primaryScreenBounds.getHeight()/8);
	//	pane.setPrefWidth(primaryScreenBounds.getWidth()/16);
		
	//	pane.setMaxHeight(primaryScreenBounds.getHeight()/8);
	//	pane.setMWidth(primaryScreenBounds.getWidth()/16);
		
		NumberAxis bpm  = new NumberAxis();
		bpm.setLabel("Beats per minute");
		
		NumberAxis bstr  = new NumberAxis();
		bstr.setLabel("Beat Strength");
		
		
		lineChart = new  LineChart<Number, Number>(bpm,bstr);
		
	
		
		  double[] histogram = song.getHistogram();
		  
		  
		  // add values to diagram
	        for(int i =0; i<histogram.length; i++)
	        {
	        	if(i<55)
	        		histogram[i] = 0;
	       
			series.getData().add(new XYChart.Data<Number, Number>((double) i, histogram[i]));
			//series.getData().add(new XYChart.Data<Double, Double>((double) i*2+1, new Double(0)));  //-Math.random()*4));    //new Double(0)));
	        }
		
	
	        lineChartData.add(series);
	        lineChart.setData(lineChartData);
	        

	

        
    //    BeatHistogramControl control = new BeatHistogramControl(song);
  //      myScene.setRoot(control);
		
		StackPane root = new StackPane();
		root.getChildren().add(lineChart);
		
		Scene scene = new Scene(root,1080,522);
		scene.getStylesheets().add("smartPlayer/beatHistogram.css");
				
		primaryStage.setScene(scene);
		primaryStage.show();
		
	
		
	}

}
