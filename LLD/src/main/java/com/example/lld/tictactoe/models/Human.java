package com.example.lld.tictactoe.models;

import com.example.lld.tictactoe.enums.PlayerType;

import java.util.Scanner;

public class Human extends Player{
    public Human(Symbol symbol){
        super(symbol,PlayerType.HUMAN);
    }


    @Override
    public Move makeMove(Board board) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the row where you want to start");
        int row = scanner.nextInt();
        System.out.println("Enter the column where you want to start");
        int col = scanner.nextInt();

        Move move = new Move();
        move.setCell(board.getCell(row-1,col-1));
        move.setPlayer(this);
        move.setSymbol(this.getSymbol());

        return move;
    }
}
