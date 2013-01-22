/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;
import domain.*;
import utils.*;
/**
 *
 * @author Kalle
 */
public class ValueAt implements Command{
    private int x;
    private Polynomial polynomial;
    private StringModifier mod;
    public ValueAt(){
        mod=new StringModifier();
    }
    @Override
    public String commandDescription() {
        return "Takes <i>a polynomial and a point</i> as parameters and presents value of the polynomial at the given point.<br><br>Examples:<br><ul><li>valueAt(x^2+2x-1,2)</li><li>valueAt(2x-2,-4)</li></ul>";
    }

    @Override
    public String execute() {
        String result="";
        result+=mod.bestForm(polynomial.valueAt(x));
        if(result.contains("/")){
            result+=" (&asymp;" + polynomial.valueAt((double)x) + ")";
        }
        return result;
    }

    @Override
    public String operationDescription() {
        return "Value of " + polynomial.toString() + " at " + x;
    }

    @Override
    public void handleParameters(String parameters) {
        polynomial=mod.turnIntoPolynomial(mod.readParameters(parameters)[0]);
        x=Integer.parseInt(mod.readParameters(parameters)[1]);
    }
    
}
