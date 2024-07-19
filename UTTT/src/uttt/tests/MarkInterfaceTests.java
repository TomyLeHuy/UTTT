package uttt.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.utils.Symbol;
import uttt.game.MarkInterface;

public class MarkInterfaceTests {
    MarkInterface mark;

    @Before
    public void setUp() throws Exception {
        mark = UTTTFactory.createMark(Symbol.EMPTY, 0);
    }

    @Test
    public void getSymbolTest() {
        Symbol acutalsymbol = mark.getSymbol();
        assertEquals(Symbol.EMPTY, acutalsymbol);
    }

    @Test
    public void getPositionTest() {
        int actualPos = mark.getPosition();
        assertEquals(0, actualPos);
    }

    @Test
    public void setSymbol() {
        mark.setSymbol(Symbol.CROSS);
        assertEquals("symbols noch equal", Symbol.CROSS, mark.getSymbol());
    }

    @Test
    public void setSymbolInvalid() {
        Symbol invalidSymbol = null;
        assertThrows("symbol cant be null", IllegalArgumentException.class,
                () -> mark.setSymbol(invalidSymbol));
    }

    @Test
    public void setSymbolEmpty() {
        mark = UTTTFactory.createMark(Symbol.EMPTY, 0);
        Symbol invalidSymbol = Symbol.EMPTY;
        assertThrows("symbol to set cant be EMPTY", IllegalArgumentException.class,
                () -> mark.setSymbol(invalidSymbol));
    }
}
