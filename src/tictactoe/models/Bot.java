package tictactoe.models;

import tictactoe.strategies.BotPlaying.BotPlayingStrategy;
import tictactoe.strategies.BotPlaying.BotPlayingStrategyFactory;
import tictactoe.strategies.BotPlaying.EasyBotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Long id, String name, Symbol symbol,
               BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        botPlayingStrategy = BotPlayingStrategyFactory
                .gtBotPlayingStrategyForDifficultyLevel(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }

    @Override
    public Move makeMove(Board board) {
        Move botMove = botPlayingStrategy.makeMove(board);
        botMove.setPlayer(this);

        return botMove;
    }
}
