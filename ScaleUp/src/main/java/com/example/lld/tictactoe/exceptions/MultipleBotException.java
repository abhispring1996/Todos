package com.example.lld.tictactoe.exceptions;

public class MultipleBotException extends Exception{

    public MultipleBotException(){
        super("Multiple Bots are not allowed");
    }
}
