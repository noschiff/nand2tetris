import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("assembly", "asm"));
        int r = fileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            try {
                HackAssembler hackAssembler = new HackAssembler(fileChooser.getSelectedFile());
                hackAssembler.assemble();

                int response = JOptionPane.showConfirmDialog(null, "Do you want to write the output to a file?", "Output Options", JOptionPane.YES_NO_OPTION);
                if (response == 0) {
                    JFileChooser fileChooser1 = new JFileChooser();
                    fileChooser1.setAcceptAllFileFilterUsed(false);
                    fileChooser1.setFileFilter(new FileNameExtensionFilter("hack", "hack"));
                    fileChooser1.setSelectedFile(new File(hackAssembler.getSuggestedOutputPath()));
                    int r1 = fileChooser1.showSaveDialog(null);
                    if (r1 == JFileChooser.APPROVE_OPTION) {
                        String fileLocation = hackAssembler.print(fileChooser1.getSelectedFile());
                        JOptionPane.showMessageDialog(null, "Printed to: " + fileLocation);
                    }
                }

                response = JOptionPane.showConfirmDialog(null, "Do you want to see the output?", "Output Options", JOptionPane.YES_NO_OPTION);
                if (response == 0) {
                    String out = hackAssembler.getOut().toString().replace(", ", "\n").replace("[", "").replace("]", "");
                    JTextArea textArea = new JTextArea(out);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(300, 400));
                    textArea.setEditable(false);
                    JOptionPane.showMessageDialog(null, scrollPane, "Output", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Invalid File");
                System.exit(1);
            }
        }
    }
}
