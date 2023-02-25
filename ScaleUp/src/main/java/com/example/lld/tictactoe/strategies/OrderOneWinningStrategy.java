package com.example.lld.tictactoe.strategies;

import com.example.lld.tictactoe.models.Board;
import com.example.lld.tictactoe.models.Player;

public class OrderOneWinningStrategy implements  GameWinningStrategy{
    @Override
    public boolean checkIfWon(Board board, Player player) {
        return false;
    }
}
