/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import commands.*;
/**
 *
 * @author Kalle
 */
public class KeyInfo implements KeyListener{
    private javax.swing.JTextField jTextField;
    private javax.swing.JLabel jLabel;
    private CommandCollection commandCollection;
    private StringModifier mod;
    private DrawImg draw;
    private javax.swing.JPanel jPanel;
    public KeyInfo(javax.swing.JTextField jTextField,javax.swing.JLabel jLabel, javax.swing.JPanel jPanel, DrawImg draw, CommandCollection commandCollection){
        this.jTextField=jTextField;
        this.jLabel=jLabel;
        this.commandCollection=commandCollection;
        this.jPanel=jPanel;
        this.mod=new StringModifier();
        this.draw=draw;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        List<String> results=searchForMatches(jTextField.getText());
        if(results.size()==1 && e.getKeyCode()==10){
            jTextField.setText(results.get(0));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(jTextField.getText().equals("")){
            return;
        }
        jLabel.setText(stringFromArray(searchForMatches(jTextField.getText())));
    }
    private List<String> searchForMatches(String string){
        List<String> results = new ArrayList<String>();
        if(string.length() <= "help".length() && ("help".substring(0,string.length()).equals(string))){
                    results.add("help");
        }
        for (String string1 : commandCollection.getCommands().keySet()) {   
            if((string.length() <= string1.length() && (string1.substring(0,string.length()).equals(string))) || (string.contains("(") && mod.readTill(string, "(").equals(string1))){              
                results.add(string1);
            }
        }
        return results;
    }
    private String stringFromArray(List<String> list){
        if(list.isEmpty()){
            jPanel.setBackground(new Color(184,86,86));
            jPanel.show();
            draw.setImage("src/gui/icon2_1.png");
            draw.repaint();
            return "<html><span style='font: 11px arial; color: white;'>No match found</span></html>";
        }
        jPanel.setBackground(new Color(148,179,96));
        String result = "";
        jPanel.show();
        draw.setImage("src/gui/icon1_1.png");
        draw.repaint();
        result+="<html><span style='font: 11px arial; color: white;'>";
        for(int i=0; i<list.size(); i++){
            result+=list.get(i);
            if(i<list.size()-1){
                result+=",";
            }
        }
        result+="</span></html>";
        return result;
    }
}
