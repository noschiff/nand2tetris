public class Parser {
    private String[] inputFile;
    private String current;
    private int currentLine;

    public Parser(String[] inputFile) {
        this.inputFile = inputFile;
        this.currentLine = 0;
        this.current = inputFile[currentLine];
    }

    public void advance() {
        currentLine++;
        current = inputFile[currentLine];
    }

    public boolean hasMoreCommands() {
        return currentLine < inputFile.length - 1;
    }

    public CommandType commandType() {
        if (current.startsWith("@")) {
            return CommandType.A_COMMAND;
        } else if (current.startsWith("(")) {
            return CommandType.L_COMMAND;
        } else {
            return CommandType.C_COMMAND;
        }
    }

    public String symbol() {
        return current.replace("(", "").replace(")", "").replace("@", "");
    }

    public String dest() {
        return current.split("=")[0];
    }

    public String comp() {
        if (current.contains("=")) {
            return current.split("=")[1].split(";")[0];
        }
        return "";
    }

    public String jump() {
        if (current.contains(";")) {
            return current.split(";")[1];
        }
        return "";
    }

}
