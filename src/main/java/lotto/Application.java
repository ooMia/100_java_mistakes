package lotto;

import java.util.Random;

public class Application {
    public static void main(String[] args) {
        Game game = new Game(args, new Random());
        game.runGame();
    }
}
