package com.example.lld.tictactoe.models;

import com.example.lld.tictactoe.enums.GameStatus;
import com.example.lld.tictactoe.enums.PlayerType;
import com.example.lld.tictactoe.exceptions.MultipleBotException;
import com.example.lld.tictactoe.strategies.GameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Board board;

    private List<Player> players;

    private List<Move> moves;


    private GameStatus gameStatus;

    private List<GameWinningStrategy> gameWinningStrategyList;

    private Player winner;


    private int lastMovedPlayerIndex;

    private Game(){
        this.players = new ArrayList<>();
        this.gameWinningStrategyList = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.lastMovedPlayerIndex=-1;

    }

    public void makeMove(){
        this.lastMovedPlayerIndex+=1;
        // to resolve for negative values
        this.lastMovedPlayerIndex %= this.players.size();

        Player currentPlayer = this.players.get(lastMovedPlayerIndex);
        Move move = currentPlayer.makeMove(this.board);
        this.moves.add(move);

        for(GameWinningStrategy strategy : this.gameWinningStrategyList){

            if(strategy.checkIfWon(this.board,currentPlayer)){
                this.gameStatus = GameStatus.ENDED;
                this.winner = currentPlayer;
                return;
            }

            if(moves.size()==this.board.getDimension()*this.board.getDimension()){
                this.gameStatus = GameStatus.DRAW;
                return;
            }
        }
    }

    public boolean undo(){
        this.lastMovedPlayerIndex-=1;
        this.lastMovedPlayerIndex = (this.lastMovedPlayerIndex + this.players.size())%this.players.size();
        Move lastMove = this.moves.get(this.moves.size()-1);

        Cell cell = lastMove.getCell();
        cell.clearCell();

        this.moves.remove(this.moves.size()-1);
        return true;
    }

    /**
     * Used Builder becoz they are lots of attributes in the class
     * @return
     */
    public static Builder createGame(){
        return new Builder();
    }

    public static final class Builder {

        private List<Player> players;
        private List<GameWinningStrategy> gameWinningStrategyList;
        private int dimension;

        private Builder() {
            this.players = new ArrayList<>();
            this.gameWinningStrategyList = new ArrayList<>();
        }

        public Builder setPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder addPlayers(List<Player> player) {
            this.players.addAll(player);
            return this;
        }

        public Builder setWinningStrategies(GameWinningStrategy gameWinningStrategy) {
            this.gameWinningStrategyList.add(gameWinningStrategy);
            return this;
        }

        public Builder addWinningStrategies(List<GameWinningStrategy> gameWinningStrategy) {
            this.gameWinningStrategyList.addAll(gameWinningStrategy);
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private boolean checkIfSingleBotExists() {
            int count = 0;
            for (Player p : players) {

                if (PlayerType.BOT == p.getPlayerType()) {
                    count++;
                }
            }

            return count <= 1;
        }

        public Game build() throws MultipleBotException {

            if (!checkIfSingleBotExists()) {
                throw new MultipleBotException();
            }

            Game game = new Game();
            game.players.addAll(players);
            game.gameWinningStrategyList.addAll(gameWinningStrategyList);

            Board createBoard = new Board(dimension);
            game.board = createBoard;
            return game;
        }

    }

    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<GameWinningStrategy> getGameWinningStrategyList() {
        return gameWinningStrategyList;
    }

    public Player getWinner() {
        return winner;
    }

    public int getLastMovedPlayerIndex() {
        return lastMovedPlayerIndex;
    }

}

