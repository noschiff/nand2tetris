public class Code {
    public static String dest(String mnemonic) {
        String d1 = "0";
        String d2 = "0";
        String d3 = "0";
        if (mnemonic.contains("A"))
            d1 = "1";
        if (mnemonic.contains("D"))
            d2 = "1";
        if (mnemonic.contains("M"))
            d3 = "1";

        return d1 + d2 + d3;
    }

    public static String comp(String mnemonic) {
        String a = mnemonic.contains("M") ? "1" : "0";

        String general = mnemonic.replace("M", "X").replace("A", "X");

        String c = "000000";
        switch (general) {
            case "0":
                c = "101010";
                break;
            case "1":
                c = "111111";
                break;
            case "-1":
                c = "111010";
                break;
            case "D":
                c = "001100";
                break;
            case "X":
                c = "110000";
                break;
            case "!D":
                c = "001101";
                break;
            case "!X":
                c = "110001";
                break;
            case "-D":
                c = "001111";
                break;
            case "-X":
                c = "110011";
                break;
            case "D+1":
                c = "011111";
                break;
            case "X+1":
                c = "110111";
                break;
            case "D-1":
                c = "001110";
                break;
            case "X-1":
                c = "110010";
                break;
            case "D+X":
                c = "000010";
                break;
            case "D-X":
                c = "010011";
                break;
            case "X-D":
                c = "000111";
                break;
            case "D&X":
                c = "000000";
                break;
            case "D|X":
                c = "010101";
                break;
        }

        return a + c;
    }

    public static String jump(String mnemonic) {
        switch (mnemonic) {
            case "":
                return "000";
            case "JGT":
                return "001";
            case "JEQ":
                return "010";
            case "JGE":
                return "011";
            case "JLT":
                return "100";
            case "JNE":
                return "101";
            case "JLE":
                return "110";
            case "JMP":
                return "111";
            default:
                return "000";
        }
    }

    public static String binary(int value) {
        return String.format("%16s", Integer.toBinaryString(value)).replace(" ", "0");
    }
}