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
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.control.Labeled;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.ImageView;

import javafx.geometry.Pos;


public class Display {
    private Group root;
    private Canvas canvas;
    private GraphicsContext context;
    private Scene scene;

    private Label scoreDisplay;
    private Label bestScoreDisplay;
    private Line vLine;

    private ImageView banner, start;
    private boolean flickerStart;

    private double lineOffSet;

    private double time;

    public Display(int width, int height) {
        this.root = new Group();
        this.canvas = new Canvas(width, height);
        this.context = canvas.getGraphicsContext2D();
        this.root.getChildren().add(this.canvas);
        this.scene = new Scene(this.root, width, height);

        AnchorPane ap = new AnchorPane();
        root.getChildren().add(ap);
        ap.setPrefSize(width, height);

        lineOffSet = 180f;

        banner = new ImageView(Sprites.banner);
        ap.getChildren().add(banner);
        ap.setTopAnchor(banner, 100.0);
        System.out.println((lineOffSet + (width - lineOffSet)/2 - banner.getImage().getWidth()) + "" + "  AND  " + banner.getImage().getWidth());
        ap.setLeftAnchor(banner, 220.0);

        start = new ImageView(Sprites.start);
        ap.getChildren().add(start);
        ap.setLeftAnchor(start, 350.0);
        ap.setBottomAnchor(start, 130.0);
        flickerStart = true;

        Label orderLabel = new Label("ORDER:");
        orderLabel.setFont(new Font(30));
        ap.getChildren().add(orderLabel);
        ap.setTopAnchor(orderLabel, 10.0);
        ap.setLeftAnchor(orderLabel, 10.0);


        scoreDisplay  = new Label("SCORE");
        scoreDisplay.setFont(new Font(30));
        scoreDisplay.setTextAlignment(TextAlignment.CENTER);
        ap.getChildren().add(scoreDisplay);
        ap.setBottomAnchor(scoreDisplay, 300.0);
        ap.setLeftAnchor(scoreDisplay, 10.0);

        bestScoreDisplay = new Label("TOP SCORE: ");
        bestScoreDisplay.setFont(new Font(20));
        bestScoreDisplay.setTextAlignment(TextAlignment.CENTER);
        ap.getChildren().add(bestScoreDisplay);
        ap.setBottomAnchor(bestScoreDisplay, 250.0);
        ap.setLeftAnchor(bestScoreDisplay, 10.0);

        vLine = new Line();
        vLine.setStartX(lineOffSet);
        vLine.setStartY(0f);
        vLine.setEndY(2500f);
        vLine.setEndX(lineOffSet);
        vLine.setStrokeWidth(17.0);
        vLine.setStroke(Color.DARKSALMON);
        root.getChildren().add(vLine);


    }

    public void makeScoreBrown() {
        scoreDisplay.setTextFill(Color.BROWN);
        vLine.setStroke(Color.BROWN);
    }

    public void makeScoreBlack() {
        scoreDisplay.setTextFill(Color.BLACK);
        vLine.setStroke(Color.DARKSALMON);
    }
    public double getLineOffSet() {
        return lineOffSet;
    }

    public void setDemoMode() {
        vLine.setStroke(Color.DARKSALMON);
        banner.setOpacity(100);
        scoreDisplay.setTextFill(Color.BLACK);
        flickerStart = true;
    }

    public void setPlayMode() {
        if(vLine.getStroke() != Color.BROWN)
            vLine.setStroke(Color.BLACK);
        banner.setOpacity(0);
        flickerStart = false;
        start.setOpacity(0);
    }

    public void handleStart(double deltaTime) {
        time += deltaTime;
        if (flickerStart) {
            if((int)time % 2 == 0) {
              start.setOpacity(0);
            } else {
              start.setOpacity(100);
            }
        } else {
            start.setOpacity(0);
        }
    }

    public void setScoreDisplay(String str) { scoreDisplay.setText(str);}
    public void setBestScoreDisplay(String str) { bestScoreDisplay.setText(str);}
    public void clear() {
        this.context.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    public Scene getScene() { return this.scene; } // this is to hook up to the game class
    public Canvas getCanvas() { return this.canvas; } // probably useless
    public GraphicsContext getContext() { return this.context; } // just draw directly onto the context
}
