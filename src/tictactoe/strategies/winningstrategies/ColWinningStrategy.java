package tictactoe.strategies.winningstrategies;

import sun.jvm.hotspot.debugger.cdbg.Sym;
import tictactoe.models.Board;
import tictactoe.models.Move;
import tictactoe.models.Symbol;
import tictactoe.strategies.WinningStrategy;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {
    private Map<Integer, Map<Symbol, Integer>> countMap = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!countMap.containsKey(col)) {
            countMap.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> colMap = countMap.get(col);
        if(!colMap.containsKey(symbol)) {
            colMap.put(symbol, 0);
        }

        colMap.put(symbol, colMap.get(symbol) + 1);

        if(colMap.get(symbol) == board.getSize()) {
            return true;
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        Map<Symbol, Integer> colMap = countMap.get(col);
        colMap.put(symbol, colMap.get(symbol) - 1);
    }
}
