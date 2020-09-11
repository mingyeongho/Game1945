package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable{

	@FXML AnchorPane pane;
	@FXML ImageView jet;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		jet.setOnKeyTyped(event-> {
			switch (event.getCode()) {
				case UP :
					jet.setLayoutY(jet.getLayoutY()-5);
					break;
				case RIGHT :
					jet.setLayoutX(jet.getLayoutX() + 5);
					break;
				case DOWN :
					jet.setLayoutY(jet.getLayoutY()+5);
					break;
				case LEFT :
					jet.setLayoutX(jet.getLayoutX()-5);
					break;
				default:
					break;
			}
			
		});
		
	}
}
