package com.example.lld.tictactoe.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Move {

    private Cell cell;
    private Player player;
    private Symbol symbol;

}
