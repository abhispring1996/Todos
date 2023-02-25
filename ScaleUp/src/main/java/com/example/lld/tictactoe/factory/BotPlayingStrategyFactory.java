package com.example.lld.tictactoe.factory;

import com.example.lld.tictactoe.enums.BotDifficultyLevel;
import com.example.lld.tictactoe.strategies.BotPlayingStrategy;
import com.example.lld.tictactoe.strategies.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {


    public BotPlayingStrategy createBotPlayingStrategyOnDiffLevel(BotDifficultyLevel botDifficultyLevel){


        return switch (botDifficultyLevel){
            case EASY,MEDIUM,HARD -> new RandomBotPlayingStrategy();
        };
    }

}
