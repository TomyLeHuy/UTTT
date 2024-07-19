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
import uttt.game.SimulatorInterface;
import uttt.utils.Symbol;

public class SimulatorTest {
    SimulatorInterface simulator;

    @Before
    public void setUp() throws Exception {
        simulator = UTTTFactory.createSimulator();
        simulator.getBoards();
    }

    @Test
    public void getBoardsTest() {
        BoardInterface[] boards = simulator.getBoards();
        for (int i = 0; i > 8; i++) {
            assertNotNull("oneboard is null", boards[i]);
        }
    }

    @Test
    public void setBoardsTest() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        BoardInterface[] getBoards = simulator.getBoards();
        assertArrayEquals("boards noch the same", boards, getBoards);
    }

    @Test
    public void getCurrentPlayerSymbolTest() {
        Symbol getSymbol = simulator.getCurrentPlayerSymbol();
        assertEquals("symbol not the same", Symbol.EMPTY, getSymbol);
    }

    @Test
    public void setCurrentPlayerSymbolTest() {
        Symbol player1 = Symbol.CROSS;
        if (player1 == Symbol.EMPTY) {
            throw new IllegalArgumentException("playersymbol cant be Emtpy");
        }
        simulator.setCurrentPlayerSymbol(player1);
        assertEquals("playerSy,bol and currentsymbol not the same", player1, simulator.getCurrentPlayerSymbol());

    }

    // setMarkAt TestS
    @Test
    public void setMarkAtTestValid() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 2);
        simulator.setIndexNextBoard(2);
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator.setMarkAt(Symbol.CROSS, 2, 0);
        simulator.setIndexNextBoard(0);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertEquals(Symbol.CIRCLE, boards[0].getMarks()[2].getSymbol());
        assertTrue(simulator.setMarkAt(Symbol.CIRCLE, 0, 4));
        // boardindex 0 again
        assertTrue("invalid pos", simulator.setMarkAt(Symbol.CIRCLE, 0, 0));// validplace
        assertThrows("symbol cannot be null", IllegalArgumentException.class,
                () -> simulator.setMarkAt(null, 0, 0));
        // invalid indices
        assertThrows("index out of reach -", IllegalArgumentException.class,
                () -> simulator.setMarkAt(Symbol.CIRCLE, -1, 0));
        assertThrows("index out of reach >8", IllegalArgumentException.class,
                () -> simulator.setMarkAt(Symbol.CIRCLE, 9, 0));
        assertThrows("wrongBoard should be played on board0", IllegalArgumentException.class,
                () -> simulator.setMarkAt(Symbol.CIRCLE, 3, 0));
        assertThrows("index out of reach -", IllegalArgumentException.class,
                () -> simulator.setMarkAt(Symbol.CIRCLE, 0, -1));
        assertThrows("index out of reach >8", IllegalArgumentException.class,
                () -> simulator.setMarkAt(Symbol.CIRCLE, 0, 9));
        assertThrows("wrong current PlayerSymbol", IllegalArgumentException.class,
                () -> simulator.setMarkAt(Symbol.CROSS, 0, 4));
        assertFalse("already occupied", simulator.setMarkAt(Symbol.CIRCLE, 0, 2));
    }

    @Test
    public void setMarkAtTestValid2() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        assertNotNull(boards);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertTrue(simulator.setMarkAt(Symbol.CIRCLE, 0, 0));
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        assertFalse(simulator.setMarkAt(Symbol.CROSS, 0, 0));
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertTrue(simulator.setMarkAt(Symbol.CIRCLE, 0, 1));
    }

    @Test
    public void setMarkAtTestInvalid1() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        // invalid indices
        assertThrows(IllegalArgumentException.class, () -> simulator.setMarkAt(Symbol.CROSS, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> simulator.setMarkAt(Symbol.CROSS, 9, 0));
        assertThrows(IllegalArgumentException.class, () -> simulator.setMarkAt(Symbol.CROSS, 0, -1));
        assertThrows(IllegalArgumentException.class, () -> simulator.setMarkAt(Symbol.CROSS, 0, 9));
        assertThrows(IllegalArgumentException.class, () -> simulator.setMarkAt(Symbol.CIRCLE, 0, 0));
        assertThrows("symbol cannot be null", IllegalArgumentException.class,
                () -> simulator.setMarkAt(null, 0, 0));
    }

    // getnextIndex
    @Test
    public void getIndexNextBoard() {
        int actualBoard = simulator.getIndexNextBoard();
        assertEquals(actualBoard, -1);
    }

    // setIndexNextBoardTest
    @Test
    public void setIndexNextBoardTest1() {
        simulator.setIndexNextBoard(0);
        int actualIndex = simulator.getIndexNextBoard();
        assertEquals(0, actualIndex);
    }

    @Test
    public void setIndexNextBoardInvalidTest() {
        int invalidIndex = -2;
        assertThrows(IllegalArgumentException.class, () -> simulator.setIndexNextBoard(invalidIndex));
    }

    // isGameOverTest
    @Test
    public void isGameOverTest() {
        simulator = UTTTFactory.createSimulator();
        assertNotNull(simulator);
        boolean gameOver = simulator.isGameOver();
        assertNotNull(gameOver);
        assertTrue(gameOver);
    }

    // isMovePossible(boardIndex)
    @Test
    public void isMovePossibleInvalid1() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        boards[0].setMarkAt(Symbol.CROSS, 0);
        boards[0].setMarkAt(Symbol.CIRCLE, 1);
        boards[0].setMarkAt(Symbol.CROSS, 2);
        boards[0].setMarkAt(Symbol.CIRCLE, 3);
        boards[0].setMarkAt(Symbol.CIRCLE, 4);
        boards[0].setMarkAt(Symbol.CROSS, 5);
        boards[0].setMarkAt(Symbol.CROSS, 6);
        boards[0].setMarkAt(Symbol.CROSS, 7);
        boards[0].setMarkAt(Symbol.CIRCLE, 8);
        simulator.setBoards(boards);
        int invalidIndex = -1;
        assertFalse("board is full no move possible", simulator.isMovePossible(0));
        assertThrows("index out of reach -", IllegalArgumentException.class,
                () -> simulator.isMovePossible(invalidIndex));
        assertThrows("index out of reach >8", IllegalArgumentException.class,
                () -> simulator.isMovePossible(10));
    }

    @Test
    public void isMovePossibleGamerule() {
        // nach rules müsste in board 0 playable sein
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 2);
        simulator.setIndexNextBoard(2);
        simulator.setCurrentPlayerSymbol(Symbol.CROSS);
        simulator.setMarkAt(Symbol.CROSS, 2, 0);
        simulator.setIndexNextBoard(0);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        assertTrue(simulator.isMovePossible(simulator.getIndexNextBoard()));
    }

    @Test
    public void isMovePossibleTest() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        boolean validIndex = simulator.isMovePossible(0);
        assertNotNull("move is null", simulator.isMovePossible(0));
        assertTrue("move is invalid", validIndex);
    }

    // isMovePossible(int boardIndex, int markIndex)
    @Test
    public void isMovePossibleTest2() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        assertNotNull("move is null", simulator.isMovePossible(0, 0));
        assertTrue("move is invalid", simulator.isMovePossible(0, 0));
    }

    @Test
    public void isMovePossibleInvalid3() {
        // board is full no move possible
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        boards[0].setMarkAt(Symbol.CROSS, 0);
        boards[0].setMarkAt(Symbol.CIRCLE, 1);
        boards[0].setMarkAt(Symbol.CROSS, 2);
        boards[0].setMarkAt(Symbol.CIRCLE, 3);
        boards[0].setMarkAt(Symbol.CIRCLE, 4);
        boards[0].setMarkAt(Symbol.CROSS, 5);
        boards[0].setMarkAt(Symbol.CROSS, 6);
        boards[0].setMarkAt(Symbol.CROSS, 7);
        boards[0].setMarkAt(Symbol.CIRCLE, 8);
        for (int i = 1; i < 9; i++) {
            boards[i] = boards[0];
        }
        simulator.setBoards(boards);
        assertFalse("board is full", simulator.isMovePossible(0, 0));
    }

    @Test
    public void isMovePossibleInvalid2() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        assertThrows("index out of reach -board", IllegalArgumentException.class,
                () -> simulator.isMovePossible(-1, 0));
        assertThrows("index out of reach -mark", IllegalArgumentException.class, () -> simulator.isMovePossible(0, -1));
        assertThrows("index out of reach >8 mark", IllegalArgumentException.class,
                () -> simulator.isMovePossible(0, 10));
        assertThrows("index out of reach>8 board", IllegalArgumentException.class,
                () -> simulator.isMovePossible(10, 0));

    }

    // getWinnerTest
    @Test
    public void getWinnerNoWinner1() {
        // boards not filled
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        simulator.setBoards(boards);
        simulator.setCurrentPlayerSymbol(Symbol.CIRCLE);
        simulator.setMarkAt(Symbol.CIRCLE, 0, 0);
        assertEquals("winner exists", simulator.getWinner(), Symbol.EMPTY);
    }

    @Test
    public void getWinnerTestWinner() {
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        boards[0].setMarkAt(Symbol.CIRCLE, 0);
        boards[0].setMarkAt(Symbol.CIRCLE, 1);
        boards[0].setMarkAt(Symbol.CIRCLE, 2);
        boards[1].setMarkAt(Symbol.CIRCLE, 0);
        boards[1].setMarkAt(Symbol.CIRCLE, 1);
        boards[1].setMarkAt(Symbol.CIRCLE, 2);
        boards[2].setMarkAt(Symbol.CIRCLE, 0);
        boards[2].setMarkAt(Symbol.CIRCLE, 1);
        boards[2].setMarkAt(Symbol.CIRCLE, 2);
        simulator.setBoards(boards);
        Symbol gameWinner = simulator.getWinner();
        assertEquals("winner doesn exists", Symbol.CIRCLE, gameWinner);
    }

    @Test
    public void getWinnerNoWinner2() {
        // board full
        BoardInterface[] boards = new BoardInterface[9];
        for (int i = 0; i < 9; i++) {
            boards[i] = UTTTFactory.createBoard();
        }
        boards[0].setMarkAt(Symbol.CROSS, 0);
        boards[0].setMarkAt(Symbol.CIRCLE, 1);
        boards[0].setMarkAt(Symbol.CROSS, 2);
        boards[0].setMarkAt(Symbol.CIRCLE, 3);
        boards[0].setMarkAt(Symbol.CIRCLE, 4);
        boards[0].setMarkAt(Symbol.CROSS, 5);
        boards[0].setMarkAt(Symbol.CROSS, 6);
        boards[0].setMarkAt(Symbol.CROSS, 7);
        boards[0].setMarkAt(Symbol.CIRCLE, 8);
        for (int i = 1; i < 9; i++) {
            boards[i] = boards[0];
        }
        simulator.setBoards(boards);
        Symbol noWinner = simulator.getWinner();
        assertEquals(noWinner, Symbol.EMPTY);
    }

}
