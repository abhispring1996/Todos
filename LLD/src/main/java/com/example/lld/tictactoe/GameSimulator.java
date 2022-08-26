package com.example.lld.tictactoe;

import com.example.lld.tictactoe.controllers.GameController;
import com.example.lld.tictactoe.enums.BotDifficultyLevel;
import com.example.lld.tictactoe.enums.GameStatus;
import com.example.lld.tictactoe.exceptions.MultipleBotException;
import com.example.lld.tictactoe.models.*;
import com.example.lld.tictactoe.strategies.GameWinningStrategy;
import com.example.lld.tictactoe.strategies.OrderOneWinningStrategy;

import java.util.List;

public class GameSimulator {

    public static void main(String[] args) throws MultipleBotException {

        Player p1 = new Human(new Symbol('X'));
        Player p2 = new Bot(new Symbol('0'), BotDifficultyLevel.EASY);

        GameWinningStrategy gameWinningStrategy = new OrderOneWinningStrategy();

        GameController controller = new GameController();
        Game game = controller.createGame(3, List.of(p1,p2),List.of(gameWinningStrategy));

        while(!controller.getGameStatus(game).equals(GameStatus.IN_PROGRESS)){
                controller.displayBoard(game);
                controller.makeMove(game);
        }

    }
}
