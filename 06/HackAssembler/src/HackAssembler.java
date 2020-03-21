import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HackAssembler {

    private File file;
    private String[] input;
    private SymbolTable symbolTable;
    private ArrayList<String> out;

    public HackAssembler(String fileName) throws FileNotFoundException {
        this(new File(fileName));
    }

    public HackAssembler(File file) throws FileNotFoundException {
        this.file = file;
        this.out = new ArrayList<>();
        this.symbolTable = new SymbolTable();
        read();
    }

    public void assemble() {
        assemble1();
        assemble2();
    }

    private void read() throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            int commentStart = line.indexOf("//");
            if (commentStart > -1) {
                line = line.substring(0, commentStart);
            }
            line = line.trim();
            if (!line.equals("")) {
                lines.add(line);
            }
        }
        input = lines.toArray(new String[0]);
    }

    private void assemble1() {
        Parser parser = new Parser(input);
        int line = 0;

        while (parser.hasMoreCommands()) {
            if (parser.commandType() == CommandType.L_COMMAND) {
                String symbol = parser.symbol();

                if (!symbolTable.contains(symbol)) {
                    symbolTable.addEntry(symbol, line);
                }
            } else {
                line++;
            }
            parser.advance();
        }
    }

    private void assemble2() {
        Parser parser = new Parser(input);
        int n = 16;

        while (parser.hasMoreCommands()) {
            if (parser.commandType() == CommandType.C_COMMAND) {
                out.add("111" + Code.comp(parser.comp()) + Code.dest(parser.dest()) + Code.jump(parser.jump()));
            } else if (parser.commandType() == CommandType.A_COMMAND) {
                String symbol = parser.symbol();
                int value;
                if (!Character.isDigit(symbol.charAt(0))) {
                    if (!symbolTable.contains(symbol)) {
                        symbolTable.addEntry(symbol, n);
                        n++;
                    }
                    value = symbolTable.getAddress(symbol);
                } else {
                    value = Integer.valueOf(symbol);
                }

                out.add(Code.binary(value));
            }
            parser.advance();
        }
    }

//    public String print() {
//        return print(file.getName().replaceAll("\\..*", "") + ".hack");
//    }

    public String getSuggestedOutputPath() {
        String path = file.getAbsolutePath();
        String newName = file.getName().replaceAll("\\..*", "") + ".hack";
        return path.replace(file.getName(), newName);
    }


    public String print(File outputFile) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
            for (String line : out) {
                writer.println(line);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile.getName();
    }

    public ArrayList<String> getOut() {
        return out;
    }

    public static void main(String[] args) {
        HackAssembler hackAssembler;
        try {
            hackAssembler = new HackAssembler("/Users/noschiff/nand2tetris/projects/06/max/Max.asm");
            hackAssembler.assemble();
//            hackAssembler.print();

            System.out.println(hackAssembler.file.getPath());
            System.out.println(hackAssembler.file.getAbsolutePath());
            System.out.println(hackAssembler.file.getName());
        } catch (FileNotFoundException ignored) {
        }
    }

}
