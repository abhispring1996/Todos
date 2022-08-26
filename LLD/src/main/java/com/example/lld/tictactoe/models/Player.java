package com.example.lld.tictactoe.models;

import com.example.lld.tictactoe.enums.PlayerType;
import lombok.Getter;

@Getter
public abstract class Player {

    private Symbol symbol;
    private PlayerType playerType;

    private String name;

    public Player(){

    }

    public Player(Symbol symbol,PlayerType playerType){
        this.symbol=symbol;
        this.playerType=playerType;
    }

    public abstract Move makeMove(Board board);
}
