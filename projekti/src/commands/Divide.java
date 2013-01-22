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
public class Divide implements Command{
    private Polynomial divisor;
    private Polynomial dividend;
    private StringModifier mod;
    private Fraction fraction;
    public Divide(){
        this.mod=new StringModifier();
        this.fraction=new Fraction();
    }
    @Override
    public String commandDescription() {
        return "Takes <i>two polynomials</i> as parameters and divides them, so that the first parameter is the dividend and the second one is the divisior.<br><br>Examples: <br><ul><li>divide(x^2+2x+1,x+1)</li><li>divide(x^2-9x-10,x+1)</li></ul>";
    }

    @Override
    public String execute() {
        Polynomial result = new Polynomial();
        Multiply mul = new Multiply();
        Sum sum = new Sum();
        Polynomial minus = new Polynomial();
        Polynomial temp;
        Term c;
        minus.addTerm(-1,1,0);
        Term a = divisor.termAt(0);
        Term b = dividend.termAt(0);
        if(a.getExponent()==0){
            dividend.divideWithCoeff(a.getCoefficient());
            return dividend.toString();
        }
        if(a.getExponent()>b.getExponent()){
            return "(" + dividend + ")/(" + divisor + ")";
        }
        while(a.getExponent()<=b.getExponent()){
            int div[] = fraction.division(b.getCoefficient()[0], b.getCoefficient()[1], a.getCoefficient()[0], a.getCoefficient()[1]);
            c = new Term(div[0],div[1],b.getExponent()-a.getExponent());
            result.addTerm(c);
            temp=new Polynomial();
            temp.addTerm(c);
            temp=mul.multiplyTwo(temp,divisor);
            temp=mul.multiplyTwo(temp, minus);
            dividend=sum.sum(dividend, temp);
            b=dividend.termAt(0);
        }
        if(dividend.toString().equals("0")){
            return result.toString();
        }else{
            dividend.reduce();
            return result.toString() + " and the remainder is " + dividend.toString();
        }
        
    }
    @Override
    public String operationDescription() {
        return "("+dividend+")/("+divisor+")";
    }

    @Override
    public void handleParameters(String parameters) {
        dividend=mod.turnIntoPolynomial(mod.readParameters(parameters)[0]);
        divisor=mod.turnIntoPolynomial(mod.readParameters(parameters)[1]);
    }
    
}
