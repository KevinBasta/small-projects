import java.io.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;
import javafx.scene.layout.Background;
import javafx.collections.ObservableList;
import javafx.scene.layout.BackgroundImage;
import javafx.animation.TranslateTransition;


public class Javafx extends Application { 
   static String[][] wordsArray;     
   static Text[] textArray = new Text[12]; 
   static VBox vboxOne = new VBox(43);
   static VBox vboxTwo = new VBox(43);
   static Pane paneTurns = new Pane();
   static final int intRounds = 20;
   static int intTurns = 0; 
   static Pane paneImgaes = new Pane();
   static ImageView Person1View;
   static ImageView Person2View;
   static ImageView Person3View;
   static ImageView pdPerson1;
   static ImageView pdPerson2;
   static ImageView pdPerson3;

   /** 
    *  The method that launches Javafx
    */
   public static void main(String args[]) throws Exception{    
      Main.main();
      launch(args);     
   }   
   


   /** 
    *  The main Javafx method that gets gui started
    */
   @Override     
   public void start(Stage primaryStage) throws Exception {
      // Home screen scene
      StackPane homeStackPane = new StackPane(); 
      Image bgHomeImage = new Image(new FileInputStream("./src/images/dokihomescreen.jpeg"));
      BackgroundImage homebackgroundimage = new BackgroundImage(bgHomeImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
      homeStackPane.setBackground(new Background(homebackgroundimage));

      Text text = new Text();
      text.setText("PLAY");
      text.setLayoutX(460);
      text.setLayoutY(460);

      Pane paneText = new Pane();
      paneText.getChildren().add(0, text);
      
      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
         @Override 
         public void handle(MouseEvent e) { 
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER); 
            vboxOne.setMaxSize(200, 520);
            vboxOne.setMinSize(200, 520);
            vboxOne.getStyleClass().add("vboxOne");
            vboxTwo.setMaxSize(200, 520);
            vboxTwo.setMinSize(200, 520);
            vboxTwo.getStyleClass().add("vboxTwo");
         
            try {
               addImages();
            } catch (FileNotFoundException e2) {
               e2.printStackTrace();
            }

            newTextPopulate(primaryStage);

            gridPane.add(vboxOne, 0, 0, 1, 1); 
            gridPane.add(vboxTwo, 1, 0, 1, 1); 
            StackPane stackPane = new StackPane(); 

            try {
               Image bgImage = new Image(new FileInputStream("./src/images/dokibackground.png"));
               BackgroundImage backgroundimage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0, true, true, false, false));
               stackPane.setBackground(new Background(backgroundimage));
            } catch (FileNotFoundException e1) {
               e1.printStackTrace();
            }
            

            ObservableList list = stackPane.getChildren(); 
            list.addAll(paneTurns, paneImgaes, gridPane);                
            Scene scene = new Scene(stackPane, 1000, 600); 
            scene.getStylesheets().add("Style.css");
            primaryStage.setScene(scene);     
         }
      };  

      text.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
      text.getStyleClass().add("text");
      ObservableList list = homeStackPane.getChildren(); 
      list.addAll(paneText); 

      Scene titleScreen = new Scene(homeStackPane, 1000, 600); 
      titleScreen.getStylesheets().add("Style.css");
      primaryStage.setScene(titleScreen);
      primaryStage.setTitle("doki"); 
      primaryStage.setResizable(false);
      primaryStage.show(); 
   }    
   


   /** 
    *  A method for getting a new words array 
    */
   public void getNewWords() {
      wordsArray = Main.newWordsArray();
   }



   /** 
    *  A method that populates new text into 2 vbox
    *  Works with the newEventListener method
    */
   public void newTextPopulate(Stage primaryStage) {
      intTurns += 1;

      if (intTurns < (intRounds+1)) {
         Text turnsText = new Text();
         turnsText.setText(String.valueOf(intTurns + "/" + intRounds));
         turnsText.getStyleClass().add("textTwo");
         turnsText.setLayoutX(645);
         turnsText.setLayoutY(80);
         paneTurns.getChildren().clear();
         paneTurns.getChildren().add(turnsText);
         
         getNewWords();
         vboxOne.getChildren().clear();
         vboxTwo.getChildren().clear();

         for (int i = 0; i < 12; i++) {
            Text text = new Text();
            final int t = i;
            text.setText(wordsArray[1][i]);

            newEventListener(text, t, primaryStage);

            textArray[i] = text;
            if (i < 6) {
               vboxOne.getChildren().add(text);
            } else {
               vboxTwo.getChildren().add(text);
            }
         }
      } else {
         Text btntext = new Text();
         btntext.setText("Click me to go to results!");
         btntext.getStyleClass().add("text");
         vboxOne.getChildren().clear();
         vboxTwo.getChildren().clear();
         vboxOne.getChildren().add(btntext);

         EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
            @Override 
            public void handle(MouseEvent e) { 
               resultsScene(primaryStage);
            } 
         };   
         btntext.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
      }
   }



   /** 
    *  A method that adds event listeners on text
    *  Works with the newTextPopulate method
    */
   public void newEventListener(Text text, int t, Stage primaryStage) {
      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
         @Override 
         public void handle(MouseEvent e) { 
            System.out.println(wordsArray[0][t] + " " + wordsArray[1][t] + " clicked."); 
            for (int i = 0; i < 3; i++) {
               if (wordsArray[0][t].equalsIgnoreCase(Main.accessPerson(i).getPersonName().substring(0, 1))) {
                  imageAnimation(i, 2);
               }
            }
            newTextPopulate(primaryStage);
         } 
      };   
      text.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
      text.getStyleClass().add("text");
   }
    
   

   /** 
    *  A method that populates character images
    */
   public void addImages() throws FileNotFoundException {
      Image Person1 = new Image(new FileInputStream("./src/images/" + Main.accessPerson(0).getFileName()), 120, 120, true, false);
      Person1View = new ImageView(Person1);
      Person1View.setX(10);
      Person1View.setY(450);

      Image Person2 = new Image(new FileInputStream("./src/images/" + Main.accessPerson(1).getFileName()), 120, 120, true, false);
      Person2View = new ImageView(Person2);
      Person2View.setX(100);
      Person2View.setY(450);

      Image Person3 = new Image(new FileInputStream("./src/images/" + Main.accessPerson(2).getFileName()), 120, 120, true, false);
      Person3View = new ImageView(Person3);
      Person3View.setX(200);
      Person3View.setY(450);

      paneImgaes.getChildren().add(0, Person1View);
      paneImgaes.getChildren().add(1, Person2View);
      paneImgaes.getChildren().add(2, Person3View);
   }



   /** 
    *  A method for animating the images jumping
    */
   public void imageAnimation(int id, int cycleCount) {
      TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300)); 
      if (id == 0) {
         translateTransition.setNode(Person1View); 
         Main.accessPerson(id).addToScore();
      } else if (id == 1) {
         translateTransition.setNode(Person2View); 
         Main.accessPerson(id).addToScore();
      } else if (id == 2) {
         translateTransition.setNode(Person3View); 
         Main.accessPerson(id).addToScore();
      } else if (id == 3) {
         translateTransition.setNode(pdPerson1); 
      } else if (id == 4) {
         translateTransition.setNode(pdPerson2); 
      } else if (id == 5) {
         translateTransition.setNode(pdPerson3); 
      }
      translateTransition.setByY(-100); 
      translateTransition.setCycleCount(cycleCount); 
      translateTransition.setAutoReverse(true); 
      translateTransition.play();
   }



   /** 
    *  A method that gives the resultes 
    *  and sets it as the scene
    */
   public void resultsScene(Stage primaryStage) {
      Pane pane = new Pane();
      Pane paneCharacters = new Pane();
      Pane paneText = new Pane();

      Text textPerson1 = new Text();
      textPerson1.setText(Main.accessPerson(0).getPersonName() + ": " + String.valueOf(Main.accessPerson(0).getScore() + "/" + intRounds));
      textPerson1.getStyleClass().add("textTwoB");
      Text textPerson2 = new Text();
      textPerson2.setText(Main.accessPerson(1).getPersonName() + ": " + String.valueOf(Main.accessPerson(1).getScore() + "/" + intRounds));
      textPerson2.getStyleClass().add("textTwoB");
      Text textPerson3 = new Text();
      textPerson3.setText(Main.accessPerson(2).getPersonName() + ": " + String.valueOf(Main.accessPerson(2).getScore() + "/" + intRounds));
      textPerson3.getStyleClass().add("textTwoB");

      paneText.getChildren().add(0, textPerson1);
      paneText.getChildren().add(1, textPerson2);
      paneText.getChildren().add(2, textPerson3);

      try {
         Image podium = new Image(new FileInputStream("./src/images/pd.png"), 600, 600, true, false);
         ImageView podiumView = new ImageView(podium);
         podiumView.setX(200);
         podiumView.setY(200);  

         pane.getChildren().add(0, podiumView); 
         Image Person1 = new Image(new FileInputStream("./src/images/" + Main.accessPerson(0).getFileName()), 120, 120, true, false);
         pdPerson1 = new ImageView(Person1);
         Image Person2 = new Image(new FileInputStream("./src/images/" + Main.accessPerson(1).getFileName()), 120, 120, true, false);
         pdPerson2 = new ImageView(Person2);
         Image Person3 = new Image(new FileInputStream("./src/images/" + Main.accessPerson(2).getFileName()), 120, 120, true, false);
         pdPerson3 = new ImageView(Person3);

         paneCharacters.getChildren().add(0, pdPerson1);
         paneCharacters.getChildren().add(1, pdPerson2);
         paneCharacters.getChildren().add(2, pdPerson3);

         if (Main.accessPerson(0).getScore() >= Main.accessPerson(1).getScore() && Main.accessPerson(0).getScore() >= Main.accessPerson(2).getScore()) {
            pdPerson1.setX(450);
            pdPerson1.setY(150);
            textPerson1.setLayoutX(450);
            textPerson1.setLayoutY(300);
            imageAnimation(3, 50);
            if (Main.accessPerson(1).getScore() >= Main.accessPerson(2).getScore()) {
               pdPerson2.setX(270);
               pdPerson2.setY(185);
               textPerson2.setLayoutX(270);
               textPerson2.setLayoutY(340);
               pdPerson3.setX(625);
               pdPerson3.setY(215);
               textPerson3.setLayoutX(625);
               textPerson3.setLayoutY(365);
            } else {
               pdPerson3.setX(270);
               pdPerson3.setY(185);
               textPerson3.setLayoutX(270);
               textPerson3.setLayoutY(340);
               pdPerson2.setX(625);
               pdPerson2.setY(215);
               textPerson2.setLayoutX(625);
               textPerson2.setLayoutY(365);
            }
         } else if (Main.accessPerson(1).getScore() >= Main.accessPerson(0).getScore() && Main.accessPerson(1).getScore() >= Main.accessPerson(2).getScore()) {
            pdPerson2.setX(450);
            pdPerson2.setY(150);
            textPerson2.setLayoutX(450);
            textPerson2.setLayoutY(300);
            imageAnimation(4, 50);
            if (Main.accessPerson(0).getScore() >= Main.accessPerson(2).getScore()) {
               pdPerson1.setX(270);
               pdPerson1.setY(185);
               textPerson1.setLayoutX(270);
               textPerson1.setLayoutY(340);
               pdPerson3.setX(625);
               pdPerson3.setY(215);
               textPerson3.setLayoutX(625);
               textPerson3.setLayoutY(365);
            } else {
               pdPerson3.setX(270);
               pdPerson3.setY(185);
               textPerson3.setLayoutX(270);
               textPerson3.setLayoutY(340);
               pdPerson1.setX(625);
               pdPerson1.setY(215);
               textPerson1.setLayoutX(625);
               textPerson1.setLayoutY(365);
            }
         } else {
            pdPerson3.setX(450);
            pdPerson3.setY(150);
            textPerson3.setLayoutX(450);
            textPerson3.setLayoutY(300);
            imageAnimation(5, 50);
            if (Main.accessPerson(0).getScore() >= Main.accessPerson(1).getScore()) {
               pdPerson1.setX(270);
               pdPerson1.setY(185);
               textPerson1.setLayoutX(270);
               textPerson1.setLayoutY(340);
               pdPerson2.setX(625);
               pdPerson2.setY(215);
               textPerson2.setLayoutX(625);
               textPerson2.setLayoutY(365);
            } else {
               pdPerson2.setX(270);
               pdPerson2.setY(185);
               textPerson2.setLayoutX(270);
               textPerson2.setLayoutY(340);
               pdPerson1.setX(625);
               pdPerson1.setY(215);
               textPerson1.setLayoutX(625);
               textPerson1.setLayoutY(365);
            }
         }

      } catch (FileNotFoundException e1) {
         e1.printStackTrace();
      }
      
      StackPane stackPane = new StackPane(); 

      try {
         Image backImage = new Image(new FileInputStream("./src/images/Club.png"));
          BackgroundImage bgImg = new BackgroundImage(backImage, BackgroundRepeat.NO_REPEAT, 
                                                                     BackgroundRepeat.NO_REPEAT, 
                                                                     BackgroundPosition.DEFAULT, 
                                                                     new BackgroundSize(1.0, 1.0, true, true, false, false));
         Background background = new Background(bgImg);
         stackPane.setBackground(background);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
     
      ObservableList list = stackPane.getChildren(); 
      list.addAll(pane, paneCharacters, paneText); 

      Scene resultsScene = new Scene(stackPane, 1000, 600); 
      resultsScene.getStylesheets().add("Style.css");
      primaryStage.setScene(resultsScene);
   }


} 