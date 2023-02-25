package com.example.lld.tictactoe.models;

public class Cell {

    private int row;

    private int column;

    private Symbol symbol;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public boolean isCellEmpty(){
        return symbol== null;
    }

    public void clearCell() {
        this.symbol = null;
    }

    public Symbol getSymbol(){
        return symbol;
    }
}
