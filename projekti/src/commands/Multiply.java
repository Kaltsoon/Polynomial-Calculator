/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;
import java.util.ArrayList;
import domain.Polynomial;
import utils.Fraction;
import java.util.List;
import javax.swing.SwingUtilities;
import utils.StringModifier;
import gui.GraphGUI;
/**
 * Kertoo halutut polynomit keskenään
 */
public class Multiply implements Command{
    private List<Polynomial> polynomials;
    private StringModifier mod;
    public Multiply(){
        this.polynomials=new ArrayList<Polynomial>();
        this.mod=new StringModifier();
    }
    @Override
    public String commandDescription() {
        return "Takes <i>unlimited amount</i> of polynomials as parameters and multiplies them.<br><br>Examples:<br><ul><li>multiply(x^2-1/2,2x+2)</li><li>multiply(1/3x^3+2x^2-1,x^2+2,1/5x-1)</li></ul>";
    }
    
    @Override
    public String execute() {
        return multiplication(this.polynomials).toString();
    }
 /**
 * Kertoo listan polynomeja keskenään
 * @param lista polynomeja
 * @return polynomien tulo
 */
    public Polynomial multiplication(List<Polynomial> polynomials){
        Polynomial current = polynomials.get(0);
        for(int i=1; i<polynomials.size(); i++){
            current = multiplyTwo(current,polynomials.get(i));
        }
        current.reduce();
        SwingUtilities.invokeLater(new GraphGUI(current));
        return current;
    }
 /**
 * Kertoo kaksi polynomia keskenään
 * @param kaksi polynomial-oliota
 * @return polynomien tulo
 */
    public Polynomial multiplyTwo(Polynomial n, Polynomial t){
        Fraction fraction = new Fraction();
        Polynomial solution = new Polynomial();
        for(int i=0; i<n.termNumber(); i++){
            for(int j=0; j<t.termNumber(); j++){
                int mul[] = fraction.multiplication(n.termAt(i).getCoefficient()[0], n.termAt(i).getCoefficient()[1], t.termAt(j).getCoefficient()[0], t.termAt(j).getCoefficient()[1]);
                solution.addTerm(mul[0], mul[1], n.termAt(i).getExponent()+t.termAt(j).getExponent());
            }
        }
        solution.reduce();
        return solution;
    }
    @Override
    public String operationDescription() {
        String description="";
        for(int i=0; i<polynomials.size(); i++){
            description+="(" + polynomials.get(i).toString() + ")";
            if(i<polynomials.size()-1){
                description+="*";
            }
        }
        return description;
    }

    @Override
    public void handleParameters(String parameters) {
        polynomials=new ArrayList<Polynomial>();
        String list[] = mod.readParameters(parameters);
        for(int i=0; i<list.length; i++){
            polynomials.add(mod.turnIntoPolynomial(list[i]));
        }
    }
}
