/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import domain.*;
import utils.*;
import gui.GraphGUI;
 /**
 * Laskee polynomin derivaatan
 */
public class Derivative implements Command{
    private Polynomial polynomial;
    private StringModifier mod;
    private Fraction fraction;
    public Derivative(){
        this.mod=new StringModifier();
        this.fraction=new Fraction();
    }
    @Override
    public String commandDescription() {
        return "Takes <i>a single polynomial</i> as a parameter, and differentiates it.<br><br>Examples:<br><ul><li>derivative(x^2+2x+1/2)</li><li>derivative(-3/4x^4+5/6x^3+6x-2)</li></ul>";
    }

    @Override
    public String execute() {
        Polynomial result = derivative(this.polynomial);
        SwingUtilities.invokeLater(new GraphGUI(result));
        return result.toString();
    }
 /**
 * Laskee polynomin derivaatan
 * @param polynomial-olio
 * @return derivoitu polynomial-olio
 */
    public Polynomial derivative(Polynomial polynomial){
        Polynomial result = new Polynomial();
        for(int i=0; i<polynomial.termNumber(); i++){
            Term current = polynomial.termAt(i);
            int coeff[] = fraction.multiplication(current.getCoefficient()[0], current.getCoefficient()[1], current.getExponent(), 1);
            result.addTerm(coeff[0], coeff[1], current.getExponent()-1);
        }
        result.reduce();
        return result;
    }
    @Override
    public String operationDescription() {
        return "Derivative of " + polynomial.toString();
    }

    @Override
    public void handleParameters(String parameters) {
        polynomial=mod.turnIntoPolynomial(mod.readParameters(parameters)[0]);
    }
    
}
