package uttt.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

import uttt.UTTTFactory;
import uttt.game.BoardInterface;
import uttt.game.MarkInterface;
import uttt.utils.Symbol;

public class BoardInterfaceTests {
    BoardInterface board;

    @Before
    public void setUp() throws Exception {
        board = UTTTFactory.createBoard();
    }

    // getMarksTest
    @Test
    public void getMarksTest() {
        MarkInterface[] marks = board.getMarks();
        assertNotNull(marks);
    }

    // setMarksTest
    @Test
    public void setMarksTest() {
        MarkInterface[] marks = new MarkInterface[9];
        if (marks.length > 10 || marks.length < 0) {
            throw new IllegalArgumentException();
        }
        assertNotNull("at least one mark is null", marks);
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.EMPTY, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.EMPTY, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        if (marks[0].getSymbol() != Symbol.CROSS || marks[8].getSymbol() != Symbol.CIRCLE ||
                marks[0].getPosition() != 0 || marks[8].getPosition() != 8) {
            throw new IllegalArgumentException();
        }
        board.setMarks(marks);
        MarkInterface[] getMarks = board.getMarks();
        assertArrayEquals(marks, getMarks);
    }

    // setMarkAt Tests
    @Test
    public void setMarkAtTest() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.EMPTY, 1);// mark can be set here
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.EMPTY, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        boolean markSetAtValid = board.setMarkAt(Symbol.CROSS, 1);
        assertTrue(markSetAtValid);
    }

    @Test
    public void setMarkAtInvalid1() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.EMPTY, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.EMPTY, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        // index out of reach
        assertThrows("index out of reach", IllegalArgumentException.class,
                () -> board.setMarkAt(Symbol.CROSS, -1));
        assertThrows("index out of reach", IllegalArgumentException.class,
                () -> board.setMarkAt(Symbol.CROSS, 9));
        assertThrows("index is already occupied", IllegalArgumentException.class,
                () -> board.setMarkAt(Symbol.CROSS, 8));
        assertThrows("null cannot be a symbol", IllegalArgumentException.class,
                () -> board.setMarkAt(null, 2));
        assertThrows("null cannot be a symbol Empty", IllegalArgumentException.class,
                () -> board.setMarkAt(Symbol.EMPTY, 2));
    }

    @Test
    public void setMarkAtWonBoard() {
        // board already won
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.CROSS, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        assertFalse("board won", board.setMarkAt(Symbol.CIRCLE, 6));
    }

    @Test
    public void setMarkAtComp() {
        // compare symbols if setAt right
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        board.setMarkAt(Symbol.CROSS, 2);
        assertEquals("symbol was not set correctly", marks[2].getSymbol(), Symbol.CROSS);
    }

    // isClosedTest
    @Test
    public void isClosedTest() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);// Cross won
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.CROSS, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        boolean boardClosed = board.isClosed();
        assertTrue(boardClosed);
    }

    @Test
    public void isClosedTest2() {// diagonal win
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);// Cross won
        marks[1] = UTTTFactory.createMark(Symbol.EMPTY, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.CROSS, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.CIRCLE, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CROSS, 8);
        board.setMarks(marks);
        boolean boardClosed = board.isClosed();
        assertTrue(boardClosed);
    }

    @Test
    public void isClosedTestWrong() {
        // no winning Condition
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        boolean boardClosed = board.isClosed();
        assertFalse(boardClosed);
    }

    @Test
    public void isClosedBoardFull() {
        MarkInterface[] marks = new MarkInterface[9];
        // Case 3: Board with all positions filled but no win
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.CIRCLE, 1);
        marks[2] = UTTTFactory.createMark(Symbol.CROSS, 2);
        marks[3] = UTTTFactory.createMark(Symbol.CIRCLE, 3);
        marks[4] = UTTTFactory.createMark(Symbol.CIRCLE, 4);
        marks[5] = UTTTFactory.createMark(Symbol.CROSS, 5);
        marks[6] = UTTTFactory.createMark(Symbol.CROSS, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CROSS, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        assertTrue(board.isClosed());
    }

    @Test
    public void isClosedBoardFull2() {
        MarkInterface[] marks = new MarkInterface[9];
        // Board with all positions filled and a win
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.CROSS, 2);
        marks[3] = UTTTFactory.createMark(Symbol.CROSS, 3);
        marks[4] = UTTTFactory.createMark(Symbol.CIRCLE, 4);
        marks[5] = UTTTFactory.createMark(Symbol.CIRCLE, 5);
        marks[6] = UTTTFactory.createMark(Symbol.CIRCLE, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CROSS, 8);
        board.setMarks(marks);
        assertTrue(board.isClosed());
    }

    // isMovePossibleTest
    @Test
    public void isMovePossibleTest() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        assertEquals(marks.length, 9);
        assertFalse("out of reach", board.isMovePossible(-1));
        assertTrue(board.isMovePossible(2));
    }

    @Test
    public void isMovePossibleInvalid1() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.EMPTY, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.EMPTY, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        assertEquals(marks.length, 9);
        // index out of reach
        assertFalse(board.isMovePossible(9));
    }

    @Test
    public void isMovePossibleInvalid2() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.EMPTY, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.EMPTY, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        // index occupied
        assertFalse(board.isMovePossible(8));
    }

    @Test
    public void isMovePossibleInvalid3() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.CROSS, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        assertEquals(marks.length, 9);
        // board already won
        assertFalse(board.isMovePossible(6));
    }

    // getWinnerTest
    @Test
    public void getWinnerTest() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);// Cross won
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.CROSS, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        Symbol winner = board.getWinner();
        assertEquals(Symbol.CROSS, winner);
    }

    @Test
    public void getWinnerUnfinishedTest() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);// Cross won
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.EMPTY, 2);
        marks[3] = UTTTFactory.createMark(Symbol.EMPTY, 3);
        marks[4] = UTTTFactory.createMark(Symbol.EMPTY, 4);
        marks[5] = UTTTFactory.createMark(Symbol.EMPTY, 5);
        marks[6] = UTTTFactory.createMark(Symbol.EMPTY, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        Symbol winner = board.getWinner();
        assertEquals(Symbol.EMPTY, winner);
    }

    @Test
    public void getWinnerFullBoardTest() {
        MarkInterface[] marks = new MarkInterface[9];
        marks[0] = UTTTFactory.createMark(Symbol.CROSS, 0);// Cross won
        marks[1] = UTTTFactory.createMark(Symbol.CROSS, 1);
        marks[2] = UTTTFactory.createMark(Symbol.CIRCLE, 2);
        marks[3] = UTTTFactory.createMark(Symbol.CIRCLE, 3);
        marks[4] = UTTTFactory.createMark(Symbol.CROSS, 4);
        marks[5] = UTTTFactory.createMark(Symbol.CROSS, 5);
        marks[6] = UTTTFactory.createMark(Symbol.CROSS, 6);
        marks[7] = UTTTFactory.createMark(Symbol.CIRCLE, 7);
        marks[8] = UTTTFactory.createMark(Symbol.CIRCLE, 8);
        board.setMarks(marks);
        Symbol winner = board.getWinner();
        assertEquals(Symbol.EMPTY, winner);
    }
}
