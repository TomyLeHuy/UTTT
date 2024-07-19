package uttt.game;

import uttt.UTTTFactory;
import uttt.utils.Symbol;

public class BoardImplementation implements BoardInterface {
    private MarkInterface[] marks= new MarkInterface[9];

    // constructor
    public BoardImplementation() {
        for (int i = 0; i < 9; i++) {
            this.marks[i] = UTTTFactory.createMark(Symbol.EMPTY, i);
        }
    }

    @Override
    public MarkInterface[] getMarks() {
        return this.marks;
    }

    @Override
    public void setMarks(MarkInterface[] marks) {
        if (this.marks == null || this.marks.length != 9) {
            throw new IllegalArgumentException("invalid marks array");
        }
        this.marks = marks;
    }

    @Override
    public boolean setMarkAt(Symbol symbol, int markIndex) {
        if (isMovePossible(markIndex) == false) {// ob überhaupt möglich was zu placen
            return false;
        }
        if (symbol == null) {// illegal cases
            throw new IllegalArgumentException("sybol cant be null");
        }
        if (markIndex < 0 || markIndex >= 9) {
            throw new IllegalArgumentException("invalid index");
        }
        if (this.marks[markIndex].getSymbol() == Symbol.CROSS||
        this.marks[markIndex].getSymbol() == Symbol.CIRCLE) {// setten
            return false;
        }
        this.marks[markIndex].setSymbol(symbol);
        return true;
    }

    @Override
    public boolean isClosed() {
        // in reihe gewonnen
        if (this.marks[0].getSymbol() != Symbol.EMPTY &&
        this.marks[0].getSymbol() == this.marks[1].getSymbol() &&
        this.marks[1].getSymbol() == this.marks[2].getSymbol()) {
            return true;
        }
        if (this.marks[3].getSymbol() != Symbol.EMPTY &&
        this.marks[3].getSymbol() == this.marks[4].getSymbol() &&
        this.marks[4].getSymbol() == this.marks[5].getSymbol()) {
            return true;
        }
        if (this.marks[6].getSymbol() != Symbol.EMPTY &&
        this.marks[6].getSymbol() == this.marks[7].getSymbol() &&
        this.marks[7].getSymbol() == this.marks[8].getSymbol()) {
            return true;
        }
        // in spalte gewonnen
        if (this.marks[0].getSymbol() != Symbol.EMPTY &&
        this.marks[0].getSymbol() == this.marks[3].getSymbol() &&
        this.marks[3].getSymbol() == this.marks[6].getSymbol()) {
            return true;
        }
        if (this.marks[1].getSymbol() != Symbol.EMPTY &&
        this.marks[1].getSymbol() == this.marks[4].getSymbol() &&
        this.marks[4].getSymbol() == this.marks[7].getSymbol()) {
            return true;
        }
        if (this.marks[2].getSymbol() != Symbol.EMPTY &&
        this.marks[2].getSymbol() == this.marks[5].getSymbol() &&
        this.marks[5].getSymbol() == this.marks[8].getSymbol()) {
            return true;
        }
        // diagonal gewonnen
        if (this.marks[0].getSymbol() != Symbol.EMPTY &&
        this.marks[0].getSymbol() == this.marks[4].getSymbol() &&
        this.marks[4].getSymbol() == this.marks[8].getSymbol()) {
            return true;
        }
        if (this.marks[2].getSymbol() != Symbol.EMPTY &&
        this.marks[2].getSymbol() == this.marks[4].getSymbol() &&
        this.marks[4].getSymbol() == this.marks[6].getSymbol()) {
            return true;
        }

        for (int i = 0; i < 9; i++) {// there is at least one emtpy cell =not closed
            if (this.marks[i].getSymbol() == (Symbol.EMPTY)) {
                return false;
            }
        }
        return true;// all cells are filled

    }

    @Override
    public boolean isMovePossible(int markIndex) {
        if (markIndex < 0 || markIndex >= 9) {
            throw new IllegalArgumentException("invalid Index");
        }
        if (this.marks[markIndex].getSymbol() != Symbol.EMPTY) {// ocupied
            return false;
        }
        if (isClosed() == true) {// wenn closed ist nicht possible
            return false;
        }
        return true;
    }

    @Override
    public Symbol getWinner() {
        // in reihe gewonnen
        if (isClosed()) {
            if (this.marks[0].getSymbol() != Symbol.EMPTY &&
            this.marks[0].getSymbol() == this.marks[1].getSymbol() &&
            this.marks[1].getSymbol() == this.marks[2].getSymbol()) {
                return this.marks[1].getSymbol();
            }
            if (this.marks[3].getSymbol() != Symbol.EMPTY &&
            this.marks[3].getSymbol() == this.marks[4].getSymbol() &&
            this.marks[4].getSymbol() == this.marks[5].getSymbol()) {
                return this.marks[3].getSymbol();
            }
            if (this.marks[6].getSymbol() != Symbol.EMPTY &&
            this.marks[6].getSymbol() == this.marks[7].getSymbol() &&
            this.marks[7].getSymbol() == this.marks[8].getSymbol()) {
                return marks[6].getSymbol();
            }
            // in spalte gewonnen
            if (this.marks[0].getSymbol() != Symbol.EMPTY &&
            this.marks[0].getSymbol() == this.marks[3].getSymbol() &&
            this.marks[3].getSymbol() == this.marks[6].getSymbol()) {
                return this.marks[0].getSymbol();
            }
            if (this.marks[1].getSymbol() != Symbol.EMPTY &&
            this.marks[1].getSymbol() == this.marks[4].getSymbol() &&
            this.marks[4].getSymbol() == this.marks[7].getSymbol()) {
                return marks[1].getSymbol();
            }
            if (this.marks[2].getSymbol() != Symbol.EMPTY &&
            this.marks[2].getSymbol() == this.marks[5].getSymbol() &&
            this.marks[5].getSymbol() == this.marks[8].getSymbol()) {
                return this.marks[2].getSymbol();
            }
            // diagonal gewonnen
            if (this.marks[0].getSymbol() != Symbol.EMPTY &&
            this.marks[0].getSymbol() == this.marks[4].getSymbol() &&
            this.marks[4].getSymbol() == this.marks[8].getSymbol()) {
                return this.marks[0].getSymbol();
            }
            if (this.marks[2].getSymbol() != Symbol.EMPTY &&
            this.marks[2].getSymbol() == this.marks[4].getSymbol() &&
            this.marks[4].getSymbol() == this.marks[6].getSymbol()) {
                return this.marks[2].getSymbol();
            }
        }
        return Symbol.EMPTY;
    }

}
