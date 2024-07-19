package uttt.game;

import uttt.utils.Move;
import uttt.utils.Symbol;

public class PlayerImplentation implements PlayerInterface{
    private Symbol symbol;
    public PlayerImplentation(Symbol s){
        this.symbol=s;
    }
    @Override
    public Symbol getSymbol() {
        // 
        //throw new UnsupportedOperationException("Unimplemented method 'getSymbol'");
        return this.symbol;
    }

    @Override
    public Move getPlayerMove(SimulatorInterface game, UserInterface ui) throws IllegalArgumentException {
        // 
        //throw new UnsupportedOperationException("Unimplemented method 'getPlayerMove'");
        return ui.getUserMove();
    }
    
}
