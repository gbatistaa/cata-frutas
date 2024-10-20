package classes;

import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        // Redireciona os dados para o JTextArea
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Move o scroll para o final
    }
}