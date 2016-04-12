package smartPlayer;



import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import np.com.ngopal.control.AutoFillTextBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.AnchorPane;

//Smaprtplayer controller for SmartPlayer.fxml
public class SmartPlayerController implements Initializable {
	
	//fxml components
	@FXML
	ListView<Song> songList;
	
	@FXML
	Button searchButton;
	
	@FXML
	Button beatHistogram;
	
	@FXML
	Button addSong;
	

	
	@FXML
	TextField searchTextField;
	
	@FXML
	AnchorPane anchorPane;
	
	
	Song selectedSong;
	
	EmbeddedMediaPlayer musicPlayer;
	ArrayList<Song> songs;
	KMeansClustering clustering;  
	ArrayList<Song> played;
	
	BeatHistogram beatDiagram;
	
	
	
	public static final ObservableList<String> names = FXCollections.observableArrayList();
	 public static final ObservableList<Song> data = FXCollections.observableArrayList();
	
	
	
	 //initialize components
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        assert songList != null : "fx:id=\"beatHistogram\" was not injected: check your FXML file 'SmartPlayer.fxml'.";
        assert searchButton != null : "fx:id=\"fileChooser\" was not injected: check your FXML file 'SmartPlayer.fxml'.";
        assert searchTextField != null : "fx:id=\"filePath\" was not injected: check your FXML file 'SmartPlayer.fxml'.";
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'SmartPlayer.fxml'.";
        
        //read songs list from file
        songs = Song.readSongsFile("lista_melodii_procesate");
        

        //k means clustering
        clustering = new KMeansClustering(songs);
        final SmartPlayerController controller = this;
        played = new ArrayList<Song>();
        
        
        for(Song song: songs)
        {
        	names.add(song.getName());
        }
        
    
        
        for(Song song: songs)
        {
        	data.add(song);
        }
        
        //fill GUI list with songs names
        songList.setItems(data);
     //   songList.setCellFactory(ComboBoxListCell.forListView(names));
        songList.setEditable(false);
        songList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
  
        //search for songs name containing the string contained in TextField
        searchButton.setOnAction(new EventHandler<ActionEvent>()
        		{

				
					public void handle(ActionEvent e) {
						data.clear();
						for(Song song: songs)
						{
							if(song.getName().contains(searchTextField.getText()))
							{
								data.add(song);
							}
						}
						
						
						songList.setItems(data);
						
					}
        	
        		});
        
       //selection and start playing the song 
        songList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			
			public void handle(MouseEvent event) {
				
				selectedSong = songList.getSelectionModel().getSelectedItem();
				
				
				if(event.getClickCount() == 2)
				{
					System.out.println("Clicked on" + selectedSong.getGenre());
					try {
			//			musicPlayer.stop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  //  	 musicPlayer = new MusicPlayer("D:\\facultate\\SpecialWorkspace\\GenreClassification\\decoder\\05 Fidel.mp3");
			        //   player.launch(args);
			  //  	 musicPlayer.start(new Stage());
			           
					played.add(selectedSong);
					musicPlayer = new EmbeddedMediaPlayer(selectedSong, controller);
					musicPlayer.start(new Stage());
				}
				
			}
		});
        
        
        //button to add a new song; it opens FileChooser
        addSong.setOnAction(new EventHandler<ActionEvent>()
        		{

				
					public void handle(ActionEvent e) {
						
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Choose music file");
						File file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
						
						String filePath = file.getAbsolutePath();
						String fileName = file.getName();
						
						Song newSong = Song.getSongFromFile(filePath, fileName);
						
						if(!songs.contains(newSong))
						{
							songs.add(newSong);
							Song.writeSongsFile("lista_melodii_procesate", songs);
						}
						
						System.out.println(songs.size());
						
						Song.writeSongsFile("lista_melodii_procesate", songs);
					}
        	
        		});
        
     
        //button to show beat histogram
        beatHistogram.setOnAction(new EventHandler<ActionEvent>()
        		{

				
					public void handle(ActionEvent e) {
						
						if(beatDiagram!=null)
						{
							try {
								beatDiagram.stop();

							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						if(selectedSong != null)
						{
						beatDiagram = new BeatHistogram(selectedSong);
						try {
							beatDiagram.start(new Stage());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
					}
        	
        		});
		
	}
	
		//find cosest song vector in cluster and start playing
		public void playNext(Song song)
		{
			System.out.println("Back");
			
			Song nextSong = clustering.getKnearest(song, played);
			played.add(nextSong);
			
			try {
				musicPlayer.stop();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
			SmartPlayerController controller = this;
			System.out.println("nextSong" + nextSong.getName() + " " + nextSong.getGenre());
			musicPlayer = new EmbeddedMediaPlayer(nextSong, controller);
			musicPlayer.start(new Stage());
			
			
			
			
		}

}
