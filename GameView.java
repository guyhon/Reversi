import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameView  {

    private final int SIZE;
    private final int BUTTON_SIZE= 50;

    private Group buttonGroup = new Group();

    private VBox vBox;
    private Game game;
    private GridPane pane;

    private GridPane board;
    private VBox box;
    private HBox box1;
    private Text colors;
    private Text turn;
    private Text winner;
    private palcement[][] gameBoard;

    public  GameView(Game game,Stage window ){
        GridPane board = new GridPane();
        this.box = new VBox();
        this.box1 = new HBox();
        this.pane = board;
        this.SIZE = game.getBOARD_SIZE();
        this.gameBoard  = game.getBoard();
        this.game = game;

        window.setTitle("reversi");

        game.setRedTurn(true);
        game.palcementThatGlow();
        headBox();
        createBoard();
        box.getChildren().addAll(box1,pane);
        window.setScene(new Scene(box));
        window.show();

    }


    public Button createPlacement(palcement palcement, int i, int j){
        Button palcementView = new Button();
        palcementView.setPrefSize(BUTTON_SIZE,BUTTON_SIZE);
        palcementView.setStyle("-fx-background-color: "+ palcement.getColor());
        palcementView.setOnMousePressed(actionEvent ->  {
            endGame();
            game.click(i,j);
            game.stopGlowing();
            game.palcementThatGlow();
            createBoard();
            colors.setText(numberOfColors());
            turn.setText(howTurnIsIt());
           // howTurnIsIt(turn);

        });
        return palcementView;
    }

    public void createBoard() {
        pane.setPrefSize(SIZE*BUTTON_SIZE,SIZE*BUTTON_SIZE);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Button button;
                button = createPlacement(gameBoard[i][j],i,j);
                pane.add(button,i,j);
                GridPane.setMargin(button, new Insets(3));
            }
        }
    }


    public String numberOfColors(){

        return(game.getNumberOfReds()+"  VS  "+game.getNumberOfBlus());
    }

    public String howTurnIsIt(){
        if(game.isRedTurn()) {
            return "red turn   ";
        }
        else {
            return "blue turn   ";
        }
    }

    public void endGame(){
        if(game.boardIsFull()){
            game.setRedTurn(!game.isRedTurn());
            game.palcementThatGlow();
            createBoard();
            turn.setText(howTurnIsIt());
            //howTurnIsIt(turn);
            if(game.boardIsFull()){
                howIsTheWinner();
            }
        }
    }

   public void howIsTheWinner(){
        winner = new Text();
        if(game.getNumberOfReds()>game.getNumberOfBlus())
            winner.setText("the winner is the red");
        else if (game.getNumberOfReds()<game.getNumberOfBlus())
            winner.setText("the winner is the blue");
        else
            winner.setText("tie");
        winner.setStyle("-fx-font: 24 arial;");
       box.getChildren().add(winner);
   }

   public void headBox(){
       colors= colors();
       turn= turns();
       box1.getChildren().addAll(turn,colors);
       box1.setPadding( new Insets(5));


   }

   public Text turns(){
        Text text = new Text(howTurnIsIt());
        text.setStyle("-fx-font: 20 arial; -fx-text-background-color: blue;");
        return text;
   }

    public Text colors(){
        Text text = new Text(numberOfColors());
        text.setStyle("-fx-font: 20 arial;");
        return text;


    }

    //    public void howTurnIsIt(Text text){
//        if(game.isRedTurn()) {
//            text.setText("red turn   ");
//            text.setFill(Color.RED);
//        }
//        else {
//            text.setText("blue turn   ");
//            text.setFill(Color.BLUE);
//        }
//    }


}
