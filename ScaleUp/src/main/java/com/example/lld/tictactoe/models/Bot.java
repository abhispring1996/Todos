package com.example.lld.tictactoe.models;

import com.example.lld.tictactoe.enums.BotDifficultyLevel;
import com.example.lld.tictactoe.enums.PlayerType;
import com.example.lld.tictactoe.strategies.BotPlayingStrategy;

public class Bot extends Player{

    private BotPlayingStrategy botPlayingStrategy;

    private BotDifficultyLevel botDifficultyLevel;

    public Bot(Symbol symbol ,BotDifficultyLevel botDifficultyLevel){
        super(symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        return this.botPlayingStrategy.makeMove(board,this);
    }
}
