import javafx.application.Application;

import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        Game g = new Game(8);
        GameView G = new  GameView(g,stage);


    }
}