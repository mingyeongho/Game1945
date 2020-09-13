package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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

//	ImageView meteor;
	
	double meteorX;
	double starX;
	
	
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
					Circle bullet = new Circle(jet.getLayoutX() + 28, jet.getLayoutY() - 10, 5);
					bullet.setFill(Color.RED);
					pane.getChildren().add(bullet);
					Thread shootingThread = new Thread(()-> {
						while(true) {
							Platform.runLater(()-> {
								bullet.setLayoutY(bullet.getLayoutY()-10);
							});
							try {
								Thread.sleep(50);
							} catch(Exception e) {
							}
						}
					});
					shootingThread.start();
				}
			}
		});
		
		// 운석이 2초마다 하나씩 내려오는 스레드
		Thread thread = new Thread(()->{
			while(true) {
				ImageView meteor = new ImageView(new Image("file:///C:/Users/min%20gyeong%20ho/git/Game1945/Game1945/src/images/meteor.png"));
				meteor.setFitWidth(50);
				meteor.setFitHeight(50);
				meteorX = Math.random()*pane.getPrefWidth();
				meteor.setLayoutX(meteorX);
				meteor.setLayoutY(0);
				Platform.runLater(()->{
					pane.getChildren().add(meteor);
					Thread meteorThread = new Thread(()->{
						while(true) {
							Platform.runLater(()->{
								meteor.setLayoutY(meteor.getLayoutY()+10);
								if (meteor.getLayoutY() == pane.getPrefHeight()) {
									pane.getChildren().remove(meteor);
								}
							});
							try {
								Thread.sleep(100);
							} catch(Exception e) {
							}
						}
					});
					meteorThread.start();
				});
				try {
					Thread.sleep(2000);
				} catch(Exception e) {
					
				}
			}
		});
		thread.start();
		
		// 검은 배경에 흰색 점이 떨어지는 쓰레드
//		Thread starThread = new Thread( ()-> {
//			starX = Math.random() * pane.getPrefWidth();
//			Circle star = new Circle(starX, 0 , 2);
//			star.setFill(Color.WHITE);
//			pane.getChildren().add(star);
//			while(true) {
//				Platform.runLater(()->{
//					star.setLayoutY(star.getLayoutY()+10);
//				});
//				try {
//					Thread.sleep(50);
//				} catch(Exception e) {
//				}
//			}
//		});
//		starThread.start();
		
		/*
		 * 해야할 일
		 * 1. 마우스 클릭 시 메테오가 떨어지지만 이것을 그냥 있어도 메테오가 떨어지게끔
		 * 2. 불릿이 메테오에 닿으면 메테오의 이미지가 bomb으로 바뀜
		 * 3. 메테오가 rocket에 닿으면 게임 끝
		 */
	}
}
