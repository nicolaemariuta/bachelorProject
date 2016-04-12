/*
 * Copyright (c) 2012, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package smartPlayer;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EmbeddedMediaPlayer extends Application {

 //   private static final String MEDIA_URL ="D:\\facultate\\SpecialWorkspace\\GenreClassification\\decoder\\TheBeatles-ComeTogether1969.mp3";
    Song song;
    SmartPlayerController controller;
   
    
    
    
    EmbeddedMediaPlayer(Song song, SmartPlayerController controller)
    {
    	this.song = song;
    	this.controller = controller;
    
    }
    
    @Override
    public void start(final Stage primaryStage) {
    	Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    	 
    	 
        primaryStage.setTitle(song.getGenre() + "-" + song.getName());
        primaryStage.setX(10);
		primaryStage.setY(10);
        Group root = new Group();
        final Scene scene = new Scene(root, 540, 241);

        // create media player
        
        
        
         File file = new File(song.getFilePath());
        String path = file.toURI().toASCIIString();
        
        
      
        
        
         Media media = new Media (path);
         final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        
        
        
        
        
        
        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        
        mediaPlayer.setOnStopped(new Runnable() {
            public void run() {
            	
            
         
            }
        });
        
        
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                System.out.println("stopped");
                primaryStage.close();
            	controller.playNext(song);
         
            }
        });
    
        
        
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
            	mediaPlayer.stop();
        //        System.out.println("Stage is closing");
            }
        });  
        
        
    }

}
