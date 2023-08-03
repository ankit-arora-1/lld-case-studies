package tictactoe;

import tictactoe.controller.GameController;
import tictactoe.models.*;
import tictactoe.strategies.WinningStrategy;
import tictactoe.strategies.winningstrategies.ColWinningStrategy;
import tictactoe.strategies.winningstrategies.DiagnolWinningStrategy;
import tictactoe.strategies.winningstrategies.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int dimensionOfGame = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(1L, "Rishabh", new Symbol('X'), PlayerType.HUMAN)
        );

        players.add(
                new Bot(2L, "GPT", new Symbol('O'), BotDifficultyLevel.EASY)
        );

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new DiagnolWinningStrategy());

        Game game = null;
        try {
            game = gameController.startGame(
                    dimensionOfGame,
                    players,
                    winningStrategies
            );

            while(gameController.checkGameState(game).equals(GameState.IN_PROGRESS)) {
                gameController.printBoard(game);

                System.out.println("Does anyone want to do an undo (y/n");
                String undoAnswer = scanner.next();
                if(undoAnswer.equalsIgnoreCase("y")) {
                    gameController.undo(game);
                    continue;
                }

                gameController.makeMove(game);
            }
        } catch(Exception e) {
            System.out.println("Something went wrong");
        }

        gameController.printBoard(game);
        System.out.println("Game is finished");
        GameState gameState = gameController.checkGameState(game);
        if(gameState.equals(GameState.WIN)) {
            System.out.println("Winner is: " + gameController.getWinner(game));
        } else if(gameState.equals(GameState.DRAW)) {
            System.out.println("Game Drawn");
        }

    }
}
