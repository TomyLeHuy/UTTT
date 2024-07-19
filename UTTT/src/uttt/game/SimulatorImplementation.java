package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Move;
import uttt.utils.Symbol;

public class SimulatorImplementation implements SimulatorInterface {
    private BoardInterface[] boards = new BoardInterface[9];
    private Symbol currentPlayerSymbol;
    private int IndexNextBoard;

    // constructor
    public SimulatorImplementation() {
        currentPlayerSymbol = Symbol.CROSS;
        IndexNextBoard = -1;
        for (int i = 0; i < 9; i++) {
            this.boards[i] = UTTTFactory.createBoard();
        }
    }

    @Override
    public BoardInterface[] getBoards() {
        return this.boards;
    }

    @Override
    public void setBoards(BoardInterface[] boards) {
        if (this.boards == null || this.boards.length != 9) {
            throw new IllegalArgumentException("invalid boards array");
        }
        this.boards = boards;
    }

    @Override
    public Symbol getCurrentPlayerSymbol() {
        return this.currentPlayerSymbol;
    }

    @Override
    public void setCurrentPlayerSymbol(Symbol symbol) {
        this.currentPlayerSymbol = symbol;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int boardIndex, int markIndex) {
        /*
         * if (symbol == null) {
         * throw new IllegalArgumentException("invalid symbol to set");
         * }
         * if (markIndex < 0 || markIndex >= 9 || boardIndex < 0 || boardIndex >= 9) {
         * throw new IllegalArgumentException("invalid indices of Board or Mark");
         * }
         * if (this.boards[boardIndex] == null) {
         * throw new IllegalArgumentException("board is null not valid");
         * }
         * if (this.boards[boardIndex].isClosed() == true) {
         * return false;
         * }
         * if (this.IndexNextBoard != boardIndex && this.IndexNextBoard != -1) {
         * return false;
         * }
         * MarkInterface[] boardMarks = boards[boardIndex].getMarks();
         * if (boardMarks[markIndex].getSymbol() != Symbol.EMPTY) {// cant be placed
         * when alr occupied
         * return false;
         * }
         */
        if (isMovePossible(boardIndex, markIndex)) {
            if (getCurrentPlayerSymbol() != symbol) {
                throw new IllegalArgumentException("wrong player");
            }
            this.boards[boardIndex].setMarkAt(symbol, markIndex);
            if (this.boards[markIndex].isClosed() == true) {// check for next boardIndex
                this.IndexNextBoard = -1;
            } else {
                this.IndexNextBoard = markIndex;
            }
            return true;// erfolgreich setMarkAt
        }
        return false;
        // if(this.currentPlayerSymbol==Symbol.CROSS){
        // setCurrentPlayerSymbol(Symbol.CIRCLE);
        // }else{
        // setCurrentPlayerSymbol(Symbol.CROSS);
        // }
    }

    @Override
    public int getIndexNextBoard() {
        return this.IndexNextBoard;
    }

    @Override
    public void setIndexNextBoard(int index) {
        if (index < 0 || index >= 9) {
            throw new IllegalArgumentException("invalid index to setIndex");
        }
        if (boards[index].isClosed() == true) {//
            this.IndexNextBoard = -1;
            return;
        }
        this.IndexNextBoard = index;

    }

    @Override
    public boolean isGameOver() {
        // in Reihe gewonnen
        if (this.boards[0].getWinner() != Symbol.EMPTY &&
                this.boards[0].getWinner() == this.boards[1].getWinner() &&
                this.boards[1].getWinner() == this.boards[2].getWinner()) {
            return true;
        }
        if (this.boards[3].getWinner() != Symbol.EMPTY &&
                this.boards[3].getWinner() == this.boards[4].getWinner() &&
                this.boards[4].getWinner() == this.boards[5].getWinner()) {
            return true;
        }
        if (this.boards[6].getWinner() != Symbol.EMPTY &&
                this.boards[6].getWinner() == this.boards[7].getWinner() &&
                this.boards[7].getWinner() == this.boards[8].getWinner()) {
            return true;
        }
        // in spalte gewonnen
        if (this.boards[0].getWinner() != Symbol.EMPTY &&
                this.boards[0].getWinner() == this.boards[3].getWinner() &&
                this.boards[3].getWinner() == this.boards[6].getWinner()) {
            return true;
        }
        if (this.boards[1].getWinner() != Symbol.EMPTY &&
                this.boards[1].getWinner() == this.boards[4].getWinner() &&
                this.boards[4].getWinner() == this.boards[7].getWinner()) {
            return true;
        }
        if (this.boards[2].getWinner() != Symbol.EMPTY &&
                this.boards[2].getWinner() == this.boards[5].getWinner() &&
                this.boards[5].getWinner() == this.boards[8].getWinner()) {
            return true;
        }
        // diagonal gewonnen
        if (this.boards[0].getWinner() != Symbol.EMPTY &&
                this.boards[0].getWinner() == this.boards[4].getWinner() &&
                this.boards[4].getWinner() == this.boards[8].getWinner()) {
            return true;
        }
        if (this.boards[2].getWinner() != Symbol.EMPTY &&
                this.boards[2].getWinner() == this.boards[4].getWinner() &&
                this.boards[4].getWinner() == this.boards[6].getWinner()) {
            return true;
        } // if no winner check for validmoves

        for (int i = 0; i < 9; i++) {// if the board is not closed then game is not over
            if (this.boards[i].isClosed() == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isMovePossible(int boardIndex) {
        if (this.IndexNextBoard == -1) {// next board can be freely chosen
            if (boardIndex < 0 || boardIndex >= 9) {
                throw new IllegalArgumentException("invalid boardindex");
            }
            if (this.boards[boardIndex] == null) {
                return false;
            }
            if (this.boards[boardIndex].isClosed() == true) {
                return false;
            } else {
                return true;
            }

        } else {// boardindex has to be ==IndexNextBoard
            if (this.IndexNextBoard != boardIndex) {
                return false;
            }
            if (this.boards[boardIndex].isClosed() == true) {
                return false;
            }
            return true;
        }
    }

    @Override
    public boolean isMovePossible(int boardIndex, int markIndex) {
        if (isMovePossible(boardIndex)) {
            if (markIndex < 0 || markIndex >= 9) {
                throw new IllegalArgumentException("invalid mark indices");
            }
            if (boards[boardIndex].getMarks()[markIndex].getSymbol() == Symbol.EMPTY) {
                return true;
            }
            return false;
        }
        return false;
        /*
         * if (this.IndexNextBoard == -1) {// nextBoard can be freely chosen
         * if (boardIndex < 0 || boardIndex >= 9) {
         * throw new IllegalArgumentException("invalid board indices");
         * }
         * if (markIndex < 0 || markIndex >= 9) {
         * throw new IllegalArgumentException("invalid mark indices");
         * }
         * if (this.boards[boardIndex] == null) {
         * throw new IllegalArgumentException("board is null");
         * }
         * if (this.boards[boardIndex].isClosed() == true) {
         * return false;
         * }
         * if (this.boards[boardIndex].setMarkAt(getCurrentPlayerSymbol(), markIndex) ==
         * false) {
         * return false;
         * }
         * return true;
         * } else {// Boardindex must==IndexNextBoard
         * if (this.IndexNextBoard == boardIndex) {
         * if (this.boards[boardIndex].setMarkAt(getCurrentPlayerSymbol(), markIndex) ==
         * false) {
         * return false;
         * }
         * return true;
         * }
         * return false;
         * }
         */
    }

    @Override
    public Symbol getWinner() {
        // in reihe gewonnen
        if (isGameOver()) {// wenn gameOver checken ob gewinner oder keiner
            if (this.boards[0].getWinner() != Symbol.EMPTY &&
                    this.boards[0].getWinner() == this.boards[1].getWinner() &&
                    this.boards[1].getWinner() == this.boards[2].getWinner()) {
                return this.boards[0].getWinner();
            }
            if (this.boards[3].getWinner() != Symbol.EMPTY &&
                    this.boards[3].getWinner() == this.boards[4].getWinner() &&
                    this.boards[4].getWinner() == this.boards[5].getWinner()) {
                return this.boards[3].getWinner();
            }
            if (this.boards[6].getWinner() != Symbol.EMPTY &&
                    this.boards[6].getWinner() == this.boards[7].getWinner() &&
                    this.boards[7].getWinner() == this.boards[8].getWinner()) {
                return this.boards[6].getWinner();
            }
            // in spalte gewonnen
            if (this.boards[0].getWinner() != Symbol.EMPTY &&
                    this.boards[0].getWinner() == this.boards[3].getWinner() &&
                    this.boards[3].getWinner() == this.boards[6].getWinner()) {
                return this.boards[0].getWinner();
            }

            if (this.boards[1].getWinner() != Symbol.EMPTY &&
                    this.boards[1].getWinner() == this.boards[4].getWinner() &&
                    this.boards[4].getWinner() == this.boards[7].getWinner()) {
                return this.boards[1].getWinner();
            }

            if (this.boards[2].getWinner() != Symbol.EMPTY &&
                    this.boards[2].getWinner() == this.boards[5].getWinner() &&
                    this.boards[5].getWinner() == this.boards[8].getWinner()) {
                return this.boards[2].getWinner();
            }

            // diagonal gewonnen
            if (this.boards[0].getWinner() != Symbol.EMPTY &&
                    this.boards[0].getWinner() == this.boards[4].getWinner() &&
                    this.boards[4].getWinner() == this.boards[8].getWinner()) {
                return this.boards[0].getWinner();
            }

            if (this.boards[2].getWinner() != Symbol.EMPTY &&
                    this.boards[2].getWinner() == this.boards[4].getWinner() &&
                    this.boards[4].getWinner() == this.boards[6].getWinner()) {
                return this.boards[2].getWinner();
            }
            return Symbol.EMPTY;
        }
        return Symbol.EMPTY;
    }

    @Override
    public void run(PlayerInterface playerOne, PlayerInterface playerTwo, UserInterface ui) {
        while (!(isGameOver())) {
            Move playerMove = playerOne.getPlayerMove(this, ui);
            while (this.setMarkAt(playerOne.getSymbol(), playerMove.getBoardIndex(),
                    playerMove.getMarkIndex()) == false) {
                playerMove = playerOne.getPlayerMove(this, ui);
            }
            if (this.currentPlayerSymbol == Symbol.CROSS) {
                setCurrentPlayerSymbol(Symbol.CIRCLE);
            } else {
                setCurrentPlayerSymbol(Symbol.CROSS);
            }
            ui.updateScreen(this);

            if (isGameOver()) {
                break;// winner already
            }

            playerMove = playerTwo.getPlayerMove(this, ui);
            while (this.setMarkAt(playerTwo.getSymbol(), playerMove.getBoardIndex(),
                    playerMove.getMarkIndex()) == false) {
                playerMove = playerTwo.getPlayerMove(this, ui);
            }
            if (this.currentPlayerSymbol == Symbol.CIRCLE) {
                setCurrentPlayerSymbol(Symbol.CROSS);
            } else {
                setCurrentPlayerSymbol(Symbol.CIRCLE);
            }
            ui.updateScreen(this);
        }
        ui.showGameOverScreen(this.getWinner());
    }
}
