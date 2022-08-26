package com.example.lld.tictactoe.strategies;

import com.example.lld.tictactoe.models.Board;
import com.example.lld.tictactoe.models.Player;

public interface GameWinningStrategy {

    boolean checkIfWon(Board board, Player player);

}
