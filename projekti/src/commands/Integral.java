/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import domain.Polynomial;
import utils.Fraction;
import utils.StringModifier;
import gui.*;
/**
 * Laskee määrittelemättömiä ja määrättäjy integraaleja
 */
public class Integral implements Command{
    private Polynomial polynomial;
    private Fraction fraction;
    private StringModifier mod;
    private boolean indefinite;
    private int interval[];
    public Integral(){
        this.fraction=new Fraction();
        this.mod=new StringModifier();
        this.indefinite=true;
        this.interval = new int[2];
    }
    @Override
    public String commandDescription() {
        return "For indefinite integral, takes as parameter <i>a single polynomial</i> and integrates it. For definite integral, takes as parameters <i>the interval</i> (from and to value) and <i>the polynomial</i> and presents the definite integral as result.<br><br>Examples:<br><ul><li>integral(0,4,-2x^2+4x-5) (definite integral)</li><li>integral(-2x^2+4x-5) (indefinite integral)</li></ul>";
    }

    @Override
    public String execute() {
        Polynomial integrated = integrate(this.polynomial);
        integrated.reduce();
        List<Polynomial> pols = new ArrayList<Polynomial>();
        pols.add(integrated);
        if(indefinite==false){
            int b[] = integrated.valueAt(interval[1]);
            int a[] = fraction.multiplication(integrated.valueAt(interval[0])[0], integrated.valueAt(interval[0])[1], -1, 1);
            int sum[] = fraction.sum(b[0], b[1], a[0], a[1]);
            SwingUtilities.invokeLater(new GraphGUI(pols));
            return mod.bestForm(sum) + " (&asymp;" + fraction.turnToDecimal(sum[0], sum[1]) + ")";
        }else{
            SwingUtilities.invokeLater(new GraphGUI(pols));
            return integrated.toString();
        }       
    }
    @Override
    public String operationDescription() {
        if(indefinite==true){
            return "Indefinite integral of " + polynomial; 
        }else{
            return "Definite integral of " + polynomial + " between " + interval[0] + " and " + interval[1];
        }
    }

    @Override
    public void handleParameters(String parameters) {
        polynomial=new Polynomial();
        interval = new int[2];
        indefinite=true;
        String param[] = mod.readParameters(parameters);
        if(param.length>1){
            indefinite=false;
            interval[0]=Integer.parseInt(param[0]);
            interval[1]=Integer.parseInt(param[1]);
            polynomial=mod.turnIntoPolynomial(param[2]);
        }else{
            polynomial=mod.turnIntoPolynomial(param[0]);
        }
    }
/**
 * Integroi polynomin
 * @param polynomial-olio
 * @param integroitu polynomial-olio
 */
    private Polynomial integrate(Polynomial polynomial){
        Polynomial result = new Polynomial();
        for(int i=0; i<polynomial.termNumber(); i++){
            int mul[] = new int[2];
            if(polynomial.termAt(i).getExponent()!=0){
                mul = fraction.multiplication(polynomial.termAt(i).getCoefficient()[0], polynomial.termAt(i).getCoefficient()[1], 1, polynomial.termAt(i).getExponent()+1);
                
            }else{
                mul[0]=polynomial.termAt(i).getCoefficient()[0];
                mul[1]=polynomial.termAt(i).getCoefficient()[1];
            }
            result.addTerm(mul[0], mul[1], polynomial.termAt(i).getExponent()+1);
        }
        return result;
    }
}
