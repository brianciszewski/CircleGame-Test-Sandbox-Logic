import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;
import java.util.stream.IntStream;

public class TestProjectMain {

  public static void main(Stage primaryStage) {

    // numCircles is +1 higher than the desired, prevents genius error assigning values[7]
    int numCircles = 8;
    int numLines = 7;
    int genius = numLines + (-numCircles) + 1;
    final int DIFFICULTY_CONSTANT = 2;
    Random random = new Random();
    int[] values = new int[8];
    values[0] = -random.nextInt(5);
    values[1] = random.nextInt(5);
    values[2] = -random.nextInt(5);
    values[3] = random.nextInt(5);
    values[4] = random.nextInt(5);
    values[5] = -random.nextInt(5);
    values[6] = random.nextInt(5);
    values[7] = 0;
    int sum = IntStream.of(values).sum();
    if (sum < genius) {
      // Winnable if sum >= genius
      values[7] = (DIFFICULTY_CONSTANT + Math.abs(sum));
    } else if (sum == genius) {
      values[7] = DIFFICULTY_CONSTANT;
    } else if (sum > genius) {
      values[7] = 0;
    }
    
    int switchCircle = values[7];
    int switchCircleWith = random.nextInt(7);
    values[7] = values[switchCircleWith];
    values[switchCircleWith] = switchCircle;

    Rectangle rect = new Rectangle(650, 50, 200, 100);
    rect.setFill(Color.TEAL);
    rect.setStroke(Color.BLACK);

    Label startLabel = new Label("Click here to start");
    startLabel.setFont(new Font("Arial", 20));
    startLabel.setStyle("-fx-font-weight: bold");
    startLabel.setTranslateX(660);
    startLabel.setTranslateY(90);

    Rectangle topSide = new Rectangle(0, 0, 1500, 15);
    Rectangle bottomSide = new Rectangle(0, 985, 1500, 1000);
    Rectangle leftSide = new Rectangle(0, 0, 15, 1000);
    Rectangle rightSide = new Rectangle(1485, 0, 1500, 1000);

    ImageView imageWave1 = new ImageView(new Image(
        "http://icons.iconarchive.com/icons/google/noto-emoji-travel-places/256/42699-water-wave-icon.png"));
    ImageView imageWave2 = new ImageView(new Image(
        "http://icons.iconarchive.com/icons/google/noto-emoji-travel-places/256/42699-water-wave-icon.png"));
    ImageView image1 = new ImageView(new Image(
        "https://orig00.deviantart.net/1511/f/2011/203/2/e/funny_gif_by_luckygay-d41b0ds.gif"));
    ImageView image2 = new ImageView(new Image(
        "https://orig00.deviantart.net/1511/f/2011/203/2/e/funny_gif_by_luckygay-d41b0ds.gif"));

    image2.setX(440);
    image2.setY(30);
    image1.setX(910);
    image1.setY(30);

    imageWave1.setX(2);
    imageWave1.setY(737);
    imageWave2.setX(1237);
    imageWave2.setY(737);

    Pane root = new Pane();
    root.getChildren().addAll(topSide, bottomSide, leftSide, rightSide, rect, startLabel);
        // imageWave1, imageWave2, image1, image2);
    root.setBackground(
        new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));

    Scene scene = new Scene(root, 1500, 1000);

    primaryStage.setTitle("Circles?");
    primaryStage.setScene(scene);
    primaryStage.show();

    rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        // set rectangle-start-button to trigger the
        int x = 0;
        String musicFile = "crowned1.mp3"; // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        displayNum(x, primaryStage, values);
      }
    });
    startLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        // set rectangle-start-button to trigger the
        int x = 0;
        displayNum(x, primaryStage, values);
        String musicFile = "crowned1.mp3"; // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
      }
    });
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  public static void displayNum(int x, Stage primaryStage, int[] values) {

    switch (x) {
    
      case 1: {values[0]--; values[2]++;}
      break;
      case 2: {values[1]--;values[2]++;}
      break;
      case 3: {values[2]-= 3;values[0]++;values[1]++;values[3]++;}
      break;
      case 4: {values[3]-=2;values[2]++;values[4]++;}
      break;
      case 5: {values[4]-=2;values[3]++;values[5]++;}
      break;
      case 6: {values[5]-=3;values[4]++;values[6]++;values[7]++;}
      break;
      case 7: {values[6]--;values[5]++;}
      break;
      case 8: {values[7]--;values[5]++;}

    }

    Label label1 = new Label(Integer.toString(values[0]));
    label1.setFont(new Font("Arial", 20));
    label1.setStyle("-fx-font-weight: bold");

    Label label2 = new Label(Integer.toString(values[1]));
    label2.setFont(new Font("Arial", 20));
    label2.setStyle("-fx-font-weight: bold");

    Label label3 = new Label(Integer.toString(values[2]));
    label3.setFont(new Font("Arial", 20));
    label3.setStyle("-fx-font-weight: bold");

    Label label4 = new Label(Integer.toString(values[3]));
    label4.setFont(new Font("Arial", 20));
    label4.setStyle("-fx-font-weight: bold");

    Label label5 = new Label(Integer.toString(values[4]));
    label5.setFont(new Font("Arial", 20));
    label5.setStyle("-fx-font-weight: bold");

    Label label6 = new Label(Integer.toString(values[5]));
    label6.setFont(new Font("Arial", 20));
    label6.setStyle("-fx-font-weight: bold");

    Label label7 = new Label(Integer.toString(values[6]));
    label7.setFont(new Font("Arial", 20));
    label7.setStyle("-fx-font-weight: bold");

    Label label8 = new Label(Integer.toString(values[7]));
    label8.setFont(new Font("Arial", 20));
    label8.setStyle("-fx-font-weight: bold");


    int[] circleXCords = {150, 450, 750, 1050, 1350};
    int[] circleYCords = {200, 890, 600, 400, 800, 700};
    int xShift = 8;
    int yShift = 10;

    label1.setTranslateX(circleXCords[0] - xShift);
    label1.setTranslateY(circleYCords[0] - yShift);

    label2.setTranslateX(circleXCords[0] - xShift);
    label2.setTranslateY(circleYCords[1] - yShift);

    label3.setTranslateX(circleXCords[1] - xShift);
    label3.setTranslateY(circleYCords[2] - yShift);

    label4.setTranslateX(circleXCords[2] - xShift);
    label4.setTranslateY(circleYCords[3] - yShift);

    label5.setTranslateX(circleXCords[3] - xShift);
    label5.setTranslateY(circleYCords[4] - yShift);

    label6.setTranslateX(circleXCords[3] - xShift);
    label6.setTranslateY(circleYCords[3] - yShift);

    label7.setTranslateX(circleXCords[4] - xShift);
    label7.setTranslateY(circleYCords[0] - yShift);

    label8.setTranslateX(circleXCords[4] - xShift);
    label8.setTranslateY(circleYCords[5] - yShift);

    /////////////////////////////////////////////////////////////////////////////////////////////
    // CIRCLE PROPERTIES

    int radius = 50;

    Circle cir1 = new Circle(circleXCords[0], circleYCords[0], radius);
    Circle cir2 = new Circle(circleXCords[0], circleYCords[1], radius);
    Circle cir3 = new Circle(circleXCords[1], circleYCords[2], radius);
    Circle cir4 = new Circle(circleXCords[2], circleYCords[3], radius);
    Circle cir5 = new Circle(circleXCords[3], circleYCords[4], radius);
    Circle cir6 = new Circle(circleXCords[3], circleYCords[3], radius);
    Circle cir7 = new Circle(circleXCords[4], circleYCords[0], radius);
    Circle cir8 = new Circle(circleXCords[4], circleYCords[5], radius);


    if (values[0] > 0) {
      cir1.setFill(Color.LAWNGREEN);
    } else if (values[0] == 0) {
      cir1.setFill(Color.WHITE);
    } else if (values[0] < 0) {
      cir1.setFill(Color.ORANGERED);
    }
    if (values[1] > 0) {
      cir2.setFill(Color.LAWNGREEN);
    } else if (values[1] == 0) {
      cir2.setFill(Color.WHITE);
    } else if (values[1] < 0) {
      cir2.setFill(Color.ORANGERED);
    }
    if (values[2] > 0) {
      cir3.setFill(Color.LAWNGREEN);
    } else if (values[2] == 0) {
      cir3.setFill(Color.WHITE);
    } else if (values[2] < 0) {
      cir3.setFill(Color.ORANGERED);
    }
    if (values[3] > 0) {
      cir4.setFill(Color.LAWNGREEN);
    } else if (values[3] == 0) {
      cir4.setFill(Color.WHITE);
    } else if (values[3] < 0) {
      cir4.setFill(Color.ORANGERED);
    }
    if (values[4] > 0) {
      cir5.setFill(Color.LAWNGREEN);
    } else if (values[4] == 0) {
      cir5.setFill(Color.WHITE);
    } else if (values[4] < 0) {
      cir5.setFill(Color.ORANGERED);
    }
    if (values[5] > 0) {
      cir6.setFill(Color.LAWNGREEN);
    } else if (values[5] == 0) {
      cir6.setFill(Color.WHITE);
    } else if (values[5] < 0) {
      cir6.setFill(Color.ORANGERED);
    }
    if (values[6] > 0) {
      cir7.setFill(Color.LAWNGREEN);
    } else if (values[6] == 0) {
      cir7.setFill(Color.WHITE);
    } else if (values[6] < 0) {
      cir7.setFill(Color.ORANGERED);
    }
    if (values[7] > 0) {
      cir8.setFill(Color.LAWNGREEN);
    } else if (values[7] == 0) {
      cir8.setFill(Color.WHITE);
    } else if (values[7] < 0) {
      cir8.setFill(Color.ORANGERED);
    }

    cir1.setStroke(Color.BLACK);
    cir2.setStroke(Color.BLACK);
    cir3.setStroke(Color.BLACK);
    cir4.setStroke(Color.BLACK);
    cir5.setStroke(Color.BLACK);
    cir6.setStroke(Color.BLACK);
    cir7.setStroke(Color.BLACK);
    cir8.setStroke(Color.BLACK);


    // Trig to find the diagonal corners of circles
    double circleCornerPixels = radius * Math.cos((Math.PI / 4));
    Line line1 =
        new Line(circleXCords[0] + circleCornerPixels, circleYCords[0] + circleCornerPixels,
            circleXCords[1] - circleCornerPixels, circleYCords[2] - circleCornerPixels);
    Line line2 =
        new Line(circleXCords[0] + circleCornerPixels, circleYCords[1] - circleCornerPixels,
            circleXCords[1] - circleCornerPixels, circleYCords[2] + circleCornerPixels);
    Line line3 =
        new Line(circleXCords[1] + circleCornerPixels, circleYCords[2] - circleCornerPixels,
            circleXCords[2] - circleCornerPixels, circleYCords[3] + circleCornerPixels);
    Line line4 =
        new Line(circleXCords[2] + circleCornerPixels, circleYCords[3] + circleCornerPixels,
            circleXCords[3] - circleCornerPixels, circleYCords[4] - circleCornerPixels);
    Line line5 =
        new Line(circleXCords[3] + circleCornerPixels, circleYCords[4] - circleCornerPixels,
            circleXCords[3] - circleCornerPixels, circleYCords[3] + circleCornerPixels);
    Line line6 =
        new Line(circleXCords[3] + circleCornerPixels, circleYCords[3] - circleCornerPixels,
            circleXCords[4] - circleCornerPixels, circleYCords[0] + circleCornerPixels);
    Line line7 =
        new Line(circleXCords[3] + circleCornerPixels, circleYCords[3] + circleCornerPixels,
            circleXCords[4] - circleCornerPixels, circleYCords[5] - circleCornerPixels);


    String musicFile = "DROPFOOD.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    label1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[0] == -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 1;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[0] == -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 1;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    label2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[1] == -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 2;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir2.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent t) {
        if (values[1] == -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 2;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    label3.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[2] <= -2) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 3;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir3.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent t) {
        if (values[2] <= -2) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 3;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    label4.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[3] <= -3) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 4;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir4.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent t) {
        if (values[3] <= -3) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 4;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    label5.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[4] <= -3) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 5;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir5.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent t) {
        if (values[4] <= -3) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 5;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    label6.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[5] <= -2) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 6;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir6.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent t) {
        if (values[5] <= -2) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 6;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    label7.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[6] <= -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 7;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir7.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent t) {
        if (values[6] <= -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 7;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    label8.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent t) {
        if (values[7] <= -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 8;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });
    cir8.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent t) {
        if (values[7] <= -5) {
          String musicFile = "BUZZER.mp3";
          Media sound = new Media(new File(musicFile).toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(sound);
          mediaPlayer.play();
          musicFile = "DROPFOOD.mp3";
        } else {
          int x = 8;
          mediaPlayer.play();
          displayNum(x, primaryStage, values);
        }
      }
    });


    // GIF borders make program too slow to run. Use multiple threads? left/right need inverse x/y.
    /*
     * ImageView imageBorder1 = new ImageView(new Image(
     * "https://thumbs.gfycat.com/AmpleJubilantAfricanfisheagle-max-1mb.gif")); ImageView
     * imageBorder2 = new ImageView(new Image(
     * "https://thumbs.gfycat.com/AmpleJubilantAfricanfisheagle-max-1mb.gif")); ImageView
     * imageBorder3 = new ImageView(new Image(
     * "https://thumbs.gfycat.com/AmpleJubilantAfricanfisheagle-max-1mb.gif")); ImageView
     * imageBorder4 = new ImageView(new Image(
     * "https://thumbs.gfycat.com/AmpleJubilantAfricanfisheagle-max-1mb.gif"));
     * 
     * imageBorder1.setFitHeight(20); imageBorder1.setFitWidth(1500); imageBorder2.setFitHeight(20);
     * imageBorder2.setFitWidth(1500); imageBorder3.setFitHeight(1000);
     * imageBorder3.setFitWidth(20); imageBorder4.setFitHeight(1000); imageBorder4.setFitWidth(20);
     * 
     * imageBorder2.setY(980); imageBorder4.setX(1480);
     */


    Rectangle topSide = new Rectangle(0, 0, 1500, 15);
    Rectangle bottomSide = new Rectangle(0, 985, 1500, 1000);
    Rectangle leftSide = new Rectangle(0, 0, 15, 1000);
    Rectangle rightSide = new Rectangle(1485, 0, 1500, 1000);

    Pane root2 = new Pane();
    root2.setBackground(
        new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
    root2.getChildren().addAll(cir1, cir2, cir3, cir4, cir5, cir6, cir7, cir8, label1, label2,
        label3, label4, label5, label6, label7, label8, topSide, bottomSide, rightSide, leftSide,
        line1, line2, line3, line4, line5, line6, line7);
    Scene scene2 = new Scene(root2, 1500, 1000);
    primaryStage.setScene(scene2);
    primaryStage.show();



    if (values[0] >= 0) {
      if (values[1] >= 0) {
        if (values[2] >= 0) {
          if (values[3] >= 0) {
            if (values[4] >= 0) {
              if (values[5] >= 0) {
                if (values[6] >= 0) {
                  if (values[7] >= 0) {
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////


                    root2.getChildren().clear();
                    ImageView endingImage = new ImageView(new Image(
                        "https://thumbs.gfycat.com/YoungWelllitCaecilian-size_restricted.gif"));
                    endingImage.setFitHeight(1000);
                    endingImage.setFitWidth(1500);
                    Rectangle clickBox = new Rectangle(0, 0, 1500, 1000);
                    clickBox.setFill(Color.TRANSPARENT);

                    musicFile = "Winning Spider Solitaire.mp3";
                    Media music = new Media(new File(musicFile).toURI().toString());
                    MediaPlayer endMusic = new MediaPlayer(music);

                    
                    // only repeats ~5 times, file == 30seconds. 
                    endMusic.setOnReady(new Runnable() {
                      @Override
                      public void run() {
                        endMusic.play();
                      }
                    });


                    Pane root3 = new Pane();
                    root3.setBackground(new Background(
                        new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY)));
                    root3.getChildren().addAll(endingImage, clickBox);
                    Scene scene3 = new Scene(root3, 1500, 1000);
                    primaryStage.setScene(scene3);
                    primaryStage.show();

                    clickBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                      public void handle(MouseEvent t) {
                        Platform.exit();
                      }
                    });

                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////////////////////////////////


                    // endGame(primaryStage);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
