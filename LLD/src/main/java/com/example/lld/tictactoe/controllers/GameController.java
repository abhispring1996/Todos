package com.example.lld.tictactoe.controllers;

import com.example.lld.tictactoe.enums.GameStatus;
import com.example.lld.tictactoe.exceptions.MultipleBotException;
import com.example.lld.tictactoe.models.Cell;
import com.example.lld.tictactoe.models.Game;
import com.example.lld.tictactoe.models.Player;
import com.example.lld.tictactoe.strategies.GameWinningStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<GameWinningStrategy> gameWinningStrategyList)
            throws MultipleBotException {
        Game game = Game.createGame()
                .addPlayers(players)
                .addWinningStrategies(gameWinningStrategyList)
                .setDimension(dimension)
                .build();

        return game;
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public boolean undoMove(Game game){
        return game.undo();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }


    public void displayBoard(Game game) {

        for(List<Cell> cells: game.getBoard().getBoard()){

            for(Cell cell : cells) {
                System.out.print("| " + cell.getSymbol().+" |");
            }
            System.out.println("game = " + game);
        }
    }
}
