import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.AnchorPane;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

import java.lang.Object;
import javafx.scene.Node;

import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
//import java.langEnum<Pos>;
import javafx.geometry.Pos;

public class Display {
    private Group root;
    private Canvas canvas;
    private GraphicsContext context;
    private Scene scene;

    private Label scoreDisplay;

    public Display(int width, int height) {
        this.root = new Group();
        this.canvas = new Canvas(width, height);
        this.context = canvas.getGraphicsContext2D();
        this.root.getChildren().add(this.canvas);
        this.scene = new Scene(this.root, width, height);

        AnchorPane ap = new AnchorPane();
        root.getChildren().add(ap);
        ap.setPrefSize(width, height);

        Label orderLabel = new Label("ORDER:");
        orderLabel.setFont(new Font(30));
        ap.getChildren().add(orderLabel);
        ap.setTopAnchor(orderLabel, 10.0);
        ap.setLeftAnchor(orderLabel, 10.0);


        scoreDisplay  = new Label("SCORE");
        scoreDisplay.setFont(new Font(30));
        ap.getChildren().add(scoreDisplay);
        ap.setBottomAnchor(scoreDisplay, 10.0);
        ap.setLeftAnchor(scoreDisplay, 10.0);

    }

    public void setScoreDisplay(String str) { scoreDisplay.setText(str);}
    public void clear() {
        this.context.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }
    public Scene getScene() { return this.scene; } // this is to hook up to the game class
    public Canvas getCanvas() { return this.canvas; } // probably useless
    public GraphicsContext getContext() { return this.context; } // just draw directly onto the context
}
