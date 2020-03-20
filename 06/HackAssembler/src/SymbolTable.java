import java.util.HashMap;

public class SymbolTable {
    private HashMap<String, Integer> symbolTable;

    public SymbolTable() {
        symbolTable = new HashMap<String, Integer>();

        // registers
        for (int i = 0; i < 16; i++)
            this.addEntry("R" + i, i);

        // io pointers
        this.addEntry("KBD", 24576);
        this.addEntry("SCREEN", 16384);

        // other
        this.addEntry("SP", 0);
        this.addEntry("LCL", 1);
        this.addEntry("ARG", 2);
        this.addEntry("THIS", 3);
        this.addEntry("THAT", 4);
    }

    public void addEntry(String symbol, int address) {
        symbolTable.put(symbol, address);
    }

    public boolean contains(String symbol) {
        return symbolTable.containsKey(symbol);
    }

    public int getAddress(String symbol) {
        return symbolTable.get(symbol);
    }
}
