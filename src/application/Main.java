package application;
	
import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Media media = new Media(new File("hanju-akhiyan-de-vehre-vich-nusrat-fateh-ali-khan-complete-full-version-osa-worldwide-givefastlink.mp4").toURI().toString());
			MediaPlayer mp = new MediaPlayer(media);
			mp.setAutoPlay(true);
			MediaView mv = new MediaView(mp);
			mv.setFitHeight(300);
			mv.setFitWidth(400);
			Group sp = new Group(mv);
			Button bt = new Button("Pause");
			bt.setPrefSize(70,10);
			Button bt1 = new Button("<<");
			bt1.setPrefSize(70,10);
			Button bt2 = new Button(">>");
			bt2.setPrefSize(70,10);
			Slider slider = new Slider();
			slider.setValue(50);
			Slider volumeSlider = new Slider();
			volumeSlider.setValue(50);
			Slider timeSlider = new Slider();
			timeSlider.setValue(0);
			bt.setOnAction(e->{
				String s=(((Button)e.getSource()).getText());
				if(s=="Pause") {
					mp.pause();
					bt.setText("Play");
				}else {
					bt.setText("Pause");
					mp.play();
				}
			});
		
			bt1.setOnAction(e->{
				mp.seek(new Duration(mp.getCurrentTime().toMillis()-10000));
				timeSlider.setValue(mp.getCurrentTime().toMillis()/mp.getTotalDuration().toMillis()*100);
			});
			
			bt2.setOnAction(e->{
				mp.seek(new Duration(mp.getCurrentTime().toMillis()+10000));
				timeSlider.setValue(mp.getCurrentTime().toMillis()/mp.getTotalDuration().toMillis()*100);
			});
			
			slider.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable arg0) {
					mp.setRate((slider.getValue()/100)+.5);
				}
				
			});
			
			volumeSlider.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable arg0) {
					mp.setVolume((volumeSlider.getValue()/100));
				}
				
			});
			
			timeSlider.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable arg0) {
					double time=timeSlider.getValue()*(mp.getTotalDuration().toMillis());
					mp.seek(new Duration(time/100+1000));
				}
				
			});
			
			
			
			FlowPane hbox = new FlowPane(slider,bt1,bt,bt2,volumeSlider,timeSlider);
			hbox.setHgap(10);
			hbox.setVgap(30);
			VBox vbox = new VBox(sp,hbox);	
			vbox.setSpacing(50);
			Scene scene = new Scene(vbox,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
