package tictactoe.strategies.winningstrategies;

import sun.jvm.hotspot.debugger.cdbg.Sym;
import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Symbol;
import tictactoe.strategies.WinningStrategy;

import java.util.HashMap;
import java.util.Map;

public class DiagnolWinningStrategy implements WinningStrategy {
    private Map<Symbol, Integer> leftDiagCount = new HashMap<>();
    private Map<Symbol, Integer> rightDiagCount = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        // Check for left diag
        if(row == col) {
            if(!leftDiagCount.containsKey((symbol))) {
                leftDiagCount.put(symbol, 0);
            }

            leftDiagCount.put(symbol, leftDiagCount.get(symbol) + 1);
        }

        // check for right diag
        if(row + col == board.getSize() - 1) {
            if(!rightDiagCount.containsKey(symbol)) {
                rightDiagCount.put(symbol, 0);
            }

            rightDiagCount.put(symbol, rightDiagCount.get(symbol) + 1);
        }

        if(row == col && leftDiagCount.get(symbol) == board.getSize()) {
            return true;
        }

        if((row + col == board.getSize() - 1) && rightDiagCount.get(symbol) == board.getSize()) {
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col) {
            leftDiagCount.put(
                    symbol,
                    leftDiagCount.get(symbol) - 1
            );
        }

        if(row + col == board.getSize() - 1) {
            rightDiagCount.put(
                    symbol,
                    rightDiagCount.get(symbol) - 1
            );
        }
    }
}
