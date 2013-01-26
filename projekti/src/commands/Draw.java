/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import domain.*;
import utils.StringModifier;
import gui.*;
/**
 * Piirtää halutut polynomit
 */
public class Draw implements Command{
    private List<Polynomial> polynomials;
    private StringModifier mod;
    @Override
    public String commandDescription() {
        return "Takes <i>unlimited amount</i> of polynomials as parameters and draws them in graph window.<br><br>Examples:<br><ul><li>draw(x^2+2x+4)</li><li>draw(x^2,x+1/2)</li><li>draw(x^2+3x-4,x^4-1/3,-3x^2)</li></ul>";
    }

    @Override
    public String execute() {
        SwingUtilities.invokeLater(new GraphGUI(polynomials));
        return "(Graph window)";
    }

    @Override
    public String operationDescription() {
        String result = "";
        if(polynomials.size()==1){
            result+="Graph of ";
        }else{
            result+="Graphs of ";
        }
        for(int i=0; i<polynomials.size(); i++){
            result+=polynomials.get(i).toString();
            if(i!=polynomials.size()-1){
                result+=", ";
            }
        }
        return result;
    }

    @Override
    public void handleParameters(String parameters) {
        polynomials=new ArrayList<Polynomial>();
        mod = new StringModifier();
        String param[] = mod.readParameters(parameters);
        System.out.println(param[0]);
        for(int i=0; i<param.length; i++){
            polynomials.add(mod.turnIntoPolynomial(param[i]));
        }
    }
    
}
