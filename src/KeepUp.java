import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class KeepUp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
        static final String RUNNING = "Keeping Awake...";
        static final String NOT_RUNNING = "Not Keeping Awake...";
    @Override
    public void start(Stage primaryStage) {
        //Create Buttons to Start, Stop and Exit
        final Button startButton = new Button("Start");
        final Button stopButton = new Button("Stop");
        final Label statusLabel = new Label(NOT_RUNNING);

        final KeepAwake app = new KeepAwake();
        final Thread thread = new Thread(app, "KeepAwake");
        startButton.setOnAction(actionEvent -> {
            statusLabel.setText(RUNNING);
            thread.start();
        });
        stopButton.setOnAction(actionEvent -> {
            statusLabel.setText(NOT_RUNNING);
            app.stop();
        });


        final VBox vBox = new VBox(startButton,stopButton, statusLabel);
        Scene scene = new Scene(vBox,200, 200);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setTitle("KeepAwake");
        primaryStage.show();

    }

    public class KeepAwake implements Runnable{
        public boolean exit = false;
            public void run() {
                System.out.println("Running...");
                while (!exit) {
                    try {
                        System.out.println("sleep");
                        sleep(29000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("keypress");

                    final Robot robot;
                    try {
                        robot = new Robot();
                        robot.keyPress(KeyEvent.VK_E);
                        System.out.println("pressed");
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }

                }
            }

            public void stop(){
                System.out.println("Stopping...");
                exit = true;
            }
    }
}
