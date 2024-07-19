package uttt.game;

import uttt.utils.Symbol;

public class MarkImplementation implements MarkInterface {
    private Symbol symbol;
    private int position;

    public MarkImplementation(Symbol s, int j) {
        this.symbol = s;
        this.position = j;

    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void setSymbol(Symbol symbol) {
        if (symbol == null) {
            throw new IllegalArgumentException("Error, symbol cannot be null");
        }
       //if (symbol != Symbol.CROSS || symbol != Symbol.CIRCLE || symbol != Symbol.EMPTY) {
            //throw new IllegalArgumentException("Error  , invalid Symbol");
        //}
        this.symbol = symbol;
    }

}
