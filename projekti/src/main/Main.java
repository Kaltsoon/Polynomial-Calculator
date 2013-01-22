/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.SwingUtilities;
import commands.*;
import gui.CommandLineGUI;
/**
 *
 * @author Kalle
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CommandCollection commandCollection = new CommandCollection();
        commandCollection.addCommand("multiply", new Multiply());
        commandCollection.addCommand("roots", new Roots());
        commandCollection.addCommand("derivative", new Derivative());
        commandCollection.addCommand("integral", new Integral());
        commandCollection.addCommand("divide", new Divide());
        commandCollection.addCommand("draw", new Draw());
        commandCollection.addCommand("sum", new Sum());
        commandCollection.addCommand("valueAt", new ValueAt());
        CommandLineGUI kayttis = new CommandLineGUI(commandCollection);
        SwingUtilities.invokeLater(kayttis);
    }
}
