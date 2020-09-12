package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller implements Initializable{

	@FXML AnchorPane pane;
	@FXML ImageView jet;

	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// move the rocket
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
				
				// shootingThread
				if (event.getCode() == KeyCode.SPACE) {
					Circle circle = new Circle(jet.getLayoutX() + 28, jet.getLayoutY() - 10, 5);
					circle.setFill(Color.RED);
					pane.getChildren().add(circle);
					Thread shootingThread = new Thread(()-> {
						while(true) {
							circle.setLayoutY(circle.getLayoutY()-10);
							try {
								Thread.sleep(50);
							} catch(Exception e) {
								
							}
							if (circle.getLayoutY() == 0) {
								pane.getChildren().remove(circle);
								break;
							}
						}
					});
					shootingThread.setDaemon(true);
					shootingThread.start();
				}
			}
		});
		
//		 운석이 떨어지는 쓰레드
		Thread meteorThread = new Thread(()->{
			ImageView imageview = new ImageView(new Image("file:///C:/Users/min%20gyeong%20ho/git/Game1945/Game1945/src/images/meteor.png"));
			imageview.setFitWidth(50);
			imageview.setFitHeight(50);
			imageview.setLayoutX(100);
			imageview.setLayoutY(0);
			pane.getChildren().add(imageview);
			while(true) {
				imageview.setLayoutY(imageview.getLayoutY()+10);
				try {
					Thread.sleep(100);
				} catch(Exception e) {
					
				}
				if (imageview.getLayoutY() == 0) {
					pane.getChildren().remove(imageview);
				}
			}
		});
		meteorThread.start();
		
		// 검은 배경에 흰색 점이 떨어지는 쓰레드
//		Thread starThread = new Thread( ()-> {
//			
//			
//		});
//		starThread.start();
		
	}
}
