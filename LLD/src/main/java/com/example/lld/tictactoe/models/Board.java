package com.example.lld.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int dimension;

    private List<List<Cell>> board;

    public Board(int dimension){

        this.board = new ArrayList<>();

        for(int i=1;i<=dimension;i++){
            board.add(new ArrayList<>(dimension));
        }
    }

    public int getDimension() {
        return dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public Cell getCell(int row,int col){
        return board.get(row).get(col);
    }
}
