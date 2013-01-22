/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import commands.*;
import utils.*;
/**
 *
 * @author Kalle
 */
public class ButtonInfo implements ActionListener{
    private javax.swing.JTextField jTextField;
    private javax.swing.JLabel jLabel;
    private Template temp;
    private StringModifier mod;
    private CommandCollection commandCollection;
    private Command help;
    public ButtonInfo(javax.swing.JTextField jTextField,javax.swing.JLabel jLabel, CommandCollection commandCollection){
        this.jTextField=jTextField;
        this.jLabel=jLabel;
        this.temp=new Template();
        this.commandCollection=commandCollection;
        this.mod=new StringModifier();
        this.help=new Help(commandCollection);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(jTextField.getText().equals("help")){
            jLabel.setText(help.execute());
            return;
        }
        try{
            Command current = commandCollection.getCommand(mod.readCommand(jTextField.getText()));
            current.handleParameters(jTextField.getText());
            jLabel.setText(temp.PolynomialCalculation(jTextField.getText(),current.operationDescription(), current.execute()));
            }catch(Exception ex){
            jLabel.setText(temp.error(jTextField.getText()));
            }
        jTextField.setText("");
    }
}
