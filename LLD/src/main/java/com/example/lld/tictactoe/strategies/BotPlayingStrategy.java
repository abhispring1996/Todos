package com.example.lld.tictactoe.strategies;

import com.example.lld.tictactoe.models.Board;
import com.example.lld.tictactoe.models.Move;
import com.example.lld.tictactoe.models.Player;

public interface BotPlayingStrategy {

    Move makeMove(Board board, Player player);
}
