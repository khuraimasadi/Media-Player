package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ColSpan extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gp = new GridPane();
		gp.add(new Button("16666666"),0,0);
		gp.add(new Button("2"),1,0);
		gp.add(new Button("3"),2,0);
		gp.add(new Button("5"),3,0);
		gp.add(new Button("4"),0,1);
		gp.add(new Button("566666666"),1,1,3,1);
		Scene scene = new Scene(gp,400,400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
    public static void main(String[]args) {
    	launch(args);
    }
}
