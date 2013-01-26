/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.SwingUtilities;
import domain.*;
import gui.GraphGUI;
import utils.*;
/**
 * Laskee haluttujen polynomien summan
 */
public class Sum implements Command{
    private List<Polynomial> polynomials;
    @Override
    public String commandDescription() {
       return "Takes <i> unlimited amount</i> of polynomials and sums them.<br><br>Examples:<br><ul><li>sum(x^2-x+1,-1/2x-5)</li><li>sum(-1/3x^2+5x,1/7x-4,x^2)</li></ul>";
    }

    @Override
    public String execute() {
        Polynomial result = new Polynomial();
        for (int i = 0; i<polynomials.size(); i++) {
            polynomials.get(i).reduce();
            List<Term> terms = polynomials.get(i).getTerms();
            Iterator iterator = terms.iterator();
            while(iterator.hasNext()){
                result.addTerm((Term)iterator.next());
            }
        }
        result.reduce();
        SwingUtilities.invokeLater(new GraphGUI(result));
        return result.toString();
    }
/**
 * Laskee kahden polynomin summan
 * @param kaksi polynomial-oliota
 * @return summattu polynomial-olio
 */
    public Polynomial sum(Polynomial a, Polynomial b){
        Polynomial result = new Polynomial();
        List<Polynomial> polynomials = new ArrayList<Polynomial>();
        polynomials.add(b);
        polynomials.add(a);
        for (int i = 0; i<polynomials.size(); i++) {
            List<Term> terms = polynomials.get(i).getTerms();
            Iterator iterator = terms.iterator();
            while(iterator.hasNext()){
                result.addTerm((Term)iterator.next());
            }
        }
        result.reduce();
        if(result.getTerms().size()==0){
            result.addTerm(0,1,0);
        }
        return result;
    }
    @Override
    public String operationDescription() {
        String result = "";
        for (int i=0; i<polynomials.size(); i++) {
            result+=polynomials.get(i).toString();
            if(i!=polynomials.size()-1){
                result+="+";
            }
        }
        return result;
    }

    @Override
    public void handleParameters(String parameters) {
        polynomials=new ArrayList<Polynomial>();
        StringModifier mod = new StringModifier();
        String param[] = mod.readParameters(parameters);
        for (int i = 0; i < param.length; i++) {
            polynomials.add(mod.turnIntoPolynomial(param[i]));
        }
    }
    
}
