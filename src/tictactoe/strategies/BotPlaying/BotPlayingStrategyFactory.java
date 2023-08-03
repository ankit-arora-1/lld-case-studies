package tictactoe.strategies.BotPlaying;

import tictactoe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy gtBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        return new EasyBotPlayingStrategy();
    }
}
