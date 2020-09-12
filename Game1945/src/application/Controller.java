package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

public class Controller implements Initializable{

	@FXML ImageView jet;
	
	Random random = new Random();
	Image meteor; 
	Circle circle = new Circle(1); // bullet
	
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
					Platform.runLater(()-> {
						circle.setLayoutX(jet.getLayoutX() + jet.getFitWidth()/2);
						circle.setLayoutY(jet.getLayoutY() - 10);
						Thread shootingThread = new Thread(()-> {
							while(true) {
								circle.setLayoutY(circle.getLayoutY() - 10);
								try {
									Thread.sleep(500);
								} catch(Exception e) {
									
								}
								if (circle.getLayoutY() == 0) {
									break;
								}
							}
						});
						shootingThread.start();
					});
				}
			}
		});
		
		// 운석이 떨어지는 쓰레드
//		Thread meteorThread = new Thread(()->{
//			
//			
//		});
//		meteorThread.start();
		
		// 검은 배경에 흰색 점이 떨어지는 쓰레드
//		Thread starThread = new Thread( ()-> {
//			
//			
//		});
//		starThread.start();
	}
}
