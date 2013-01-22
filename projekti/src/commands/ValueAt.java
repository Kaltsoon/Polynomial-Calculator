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
    private double x;
    private Polynomial polynomial;
    private StringModifier mod;
    private Fraction frac;
    public ValueAt(){
        mod=new StringModifier();
        frac=new Fraction();
    }
    @Override
    public String commandDescription() {
        return "Takes <i>a polynomial and a point</i> as parameters and presents value of the polynomial at the given point.<br><br>Examples:<br><ul><li>valueAt(x^2+2x-1,2)</li><li>valueAt(2x-2,-4)</li></ul>";
    }

    @Override
    public String execute() {
        String result = polynomial.valueAt(x) + "";
        return result;
    }

    @Override
    public String operationDescription() {
        return "Value of " + polynomial.toString() + " at " + x;
    }

    @Override
    public void handleParameters(String parameters) {
        polynomial=mod.turnIntoPolynomial(mod.readParameters(parameters)[0]);
        if(mod.readParameters(parameters)[1].contains("/")){
            x=frac.turnToDecimal(Integer.parseInt(mod.readParameters(parameters)[1].split("/")[0]), Integer.parseInt(mod.readParameters(parameters)[1].split("/")[1]));
        }else{
            x=Double.parseDouble(mod.readParameters(parameters)[1]);
        }
    }
    
}
