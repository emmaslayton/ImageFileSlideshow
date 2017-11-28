/** "ImageFileSlideshow"
 *
 *  @author: Emma Slayton
 *  Created: 11/25/17
 *  Last updated: 11/26/17
 *
 *  Project 15; CSC201-004N
 *  Assignment 15.30
 *
 *    Twenty-five slides are stored as image files (slide0.jpg, slide1.jpg. . . slide24.jpg)
 *    in the image directory downloadable along with the source code in the book. The size
 *    of each image is 800 * 600. Write a program that automatically displays the slides
 *    repeatedly. Each slide is shown for two seconds. The slides are displayed in order.
 *    When the last slide finishes, the first slide is redisplayed, and so on. Click to pause
 *    if the animation is currently playing. Click to resume if the animation is currently paused.
 *
 */

import javafx.application.Application;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ImageFileSlideshow extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    // Slide Array
    ImageView[] imgView = new ImageView[25];
    int imgIndex = 0;

    public void start(Stage stage) {

        // Create a slide pane
        Pane slidepane = new Pane();

        // Load slides (25) in order
        for (int i = 0; i < 25; i++) {
            imgView[i] = new ImageView(new Image("image/slides/slide" + i + ".jpg"));
            imgView[i].setFitWidth(800);
            imgView[i].setFitHeight(600);
        }

        // Display first slide
        slidepane.getChildren().add(imgView[imgIndex]);

        EventHandler<ActionEvent> eventHandler = e -> {
            if (imgIndex < 24) {
                // Add next slide
                slidepane.getChildren().remove(imgView[imgIndex]);
                imgIndex++;
                slidepane.getChildren().add(imgView[imgIndex]);
            }
            else if (imgIndex == 24) {
                imgIndex = 0;
                slidepane.getChildren().remove(imgView[24]);
                slidepane.getChildren().add(imgView[imgIndex]);
            }
        };

        // Animation Timeline: Each slide is shown for two seconds
        Timeline animation = new Timeline(new KeyFrame(Duration.seconds(2), eventHandler));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        // Toggle pause and start of animation with mouse click
        slidepane.setOnMouseClicked(e -> {
            if (animation.getStatus() == Animation.Status.PAUSED) {
                animation.play();
            } else {
                animation.pause();
            }
        });

        //Place the slide in a scene size 800 * 600
        Scene scene = new Scene(slidepane, 800, 600);

        //Display the stage
        stage.setTitle("ImageFileSlideshow");
        stage.setScene(scene);
        stage.show();

        //Must request focus after the primary stage is displayed
        slidepane.requestFocus();
    }
}
