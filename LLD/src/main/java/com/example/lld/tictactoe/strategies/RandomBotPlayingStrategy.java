package com.example.lld.tictactoe.strategies;

import com.example.lld.tictactoe.models.*;

import java.util.List;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {

        for(List<Cell> cells : board.getBoard()){

            for(Cell cell : cells){

                if(cell.isCellEmpty()){
                    Move make = new Move();
                    make.setCell(cell);
                    make.setPlayer(player);
                    make.setSymbol(player.getSymbol());
                }
            }
        }

        return null;
    }
}
