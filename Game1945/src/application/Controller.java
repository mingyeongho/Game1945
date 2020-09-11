package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable{

	@FXML AnchorPane pane;
	@FXML ImageView jet;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		jet.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.UP) {
					jet.setLayoutY(jet.getLayoutY()-10);
				} else if (event.getCode() == KeyCode.RIGHT) {
					jet.setLayoutX(jet.getLayoutX()+10);
				} else if (event.getCode() == KeyCode.LEFT) {
					jet.setLayoutX(jet.getLayoutX()-10);
				}  else if (event.getCode() == KeyCode.DOWN) {
					jet.setLayoutY(jet.getLayoutY()+10);
				}  
				if (event.getCode() == KeyCode.SPACE) {
					Thread thread = new Thread( ()-> {
						
					});
					thread.start();
				}
			}
		});
		
		
	}
}
