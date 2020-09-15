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
					shootingThread.setDaemon(true);
					shootingThread.start();
				}
			}
		});
		
		// 운석이 2초마다 하나씩 내려오는 스레드
		Thread fallingMeteorThread = new Thread(()->{
			while(true) {
				ImageView meteor = new ImageView(new Image("file:///C:/Users/min%20gyeong%20ho/git/Game1945/Game1945/src/images/meteor.png"));
				meteor.setFitWidth(60);
				meteor.setFitHeight(60);
				meteorX = Math.random()*pane.getPrefWidth();
				meteor.setLayoutX(meteorX);
				meteor.setLayoutY(0);
				Platform.runLater(()->{
					pane.getChildren().add(meteor);
					// meteor가 떨어지는 스레드
					Thread meteorThread = new Thread(()->{
						while(true) {
							Platform.runLater(()->{
								meteor.setLayoutY(meteor.getLayoutY()+10);
								if (meteor.getLayoutY() == pane.getPrefHeight()) {
									pane.getChildren().remove(meteor);
								}
								// meteor와 jet가 부딪히면 jet가 폭발하고 Platform은 종료
								if ((jet.getLayoutX() >= meteor.getLayoutX() && jet.getLayoutX() <= meteor.getLayoutX() + meteor.getFitWidth())
										|| jet.getLayoutX()+jet.getFitWidth() >= meteor.getLayoutX() && jet.getLayoutX()+jet.getFitWidth() <= meteor.getLayoutX() + meteor.getFitWidth()) {
									if ((jet.getLayoutY() >= meteor.getLayoutY() && jet.getLayoutY() <= meteor.getLayoutY() + meteor.getFitHeight())
											|| jet.getLayoutY() + jet.getFitHeight() >= meteor.getLayoutY() && jet.getLayoutY() + jet.getFitHeight() <= meteor.getLayoutY() + meteor.getFitHeight()) {
										jet.setImage(new Image("file:///C:/Users/min%20gyeong%20ho/git/Game1945/Game1945/src/images/bomb.png"));
										Platform.exit();
									}
								}
								
								
							});
							try {
								Thread.sleep(100);
							} catch(Exception e) {
							}
						}
					});
					meteorThread.setDaemon(true);
					meteorThread.start();
					
				});
				try {
					Thread.sleep(1500);
				} catch(Exception e) {
					
				}
			}
		});
		fallingMeteorThread.setDaemon(true);
		fallingMeteorThread.start();
		
		// universe(falling star)
		Thread fallingStarThread = new Thread(()->{
			while(true) {
				starX = Math.random() * pane.getPrefWidth();
				Circle star = new Circle(starX, 0 , 2);
				star.setFill(Color.WHITE);
				Platform.runLater(()->{
					pane.getChildren().add(star);
					Thread starThread = new Thread(()->{
						while(true) {
							Platform.runLater(()->{
								star.setLayoutY(star.getLayoutY()+10);
							});
							try {
								Thread.sleep(50);
							} catch(Exception e) {
							}
						}
					});
					starThread.setDaemon(true);
					starThread.start();
				});
				try {
					Thread.sleep(500);
				} catch(Exception e) {
					
				}
			}
		});
		fallingStarThread.setDaemon(true);
		fallingStarThread.start();
		
		// rocket과 meteor 충돌 쓰레드
//		Thread collisionThread = new Thread(()->{
//			
//		});
//		collisionThread.setDaemon(true);
//		collisionThread.start();
		
		
		// bullet이 meteor를 맞추는 쓰레드
//		Thread hitThread = new Thread(()->{
//			
//		});
//		hitThread.setDaemon(true);
//		hitThread.start();
		
		/*
		 * 해야할 일
		 * 1. 마우스 클릭 시 메테오가 떨어지지만 이것을 그냥 있어도 메테오가 떨어지게끔 -> O
		 * 2. 불릿이 메테오에 닿으면 메테오의 이미지가 bomb으로 바뀜 
		 * 3. 메테오가 rocket에 닿으면 게임 끝
		 */
	}
}
