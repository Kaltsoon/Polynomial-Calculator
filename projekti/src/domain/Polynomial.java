/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Fraction;

/**
 *
 * @author Kalle
 */
public class Polynomial {
    private int scale=1;
    private Fraction fraction = new Fraction();
    private List<Term> polynomial = new ArrayList<Term>();
    public void addTerm(int numenator, int denominator, int exponent){
        polynomial.add(new Term(numenator,denominator,exponent));
        Collections.sort(polynomial);
    }
    public void addTerm(Term term){
        polynomial.add(term);
    }
    public void setScale(int a){
        this.scale=a;
    }
    public Term termAt(int index){
        return polynomial.get(index);
    }
    public Term getTermOfDegree(int degree){
        for (Term term : polynomial) {
            if(term.getExponent()==degree){
                return term;
            }
        }
        return new Term(0,1,0);
    }
    public Polynomial clone(){
        Polynomial clone = new Polynomial();
        for (Term term : polynomial) {
            clone.addTerm(term);
        }
        return clone;
    }
    public void reduce(){
        List<Term> util = polynomial;
        for(int i=0; i<util.size(); i++){          
            Term current = util.get(i);
            if(current.getExponent()<0 || current.getCoefficient()[0]==0 || current.getCoefficient()[1]==0){
                util.remove(i);
                continue;
            }
            for(int n=0; n<util.size(); n++){
                if(i==n){
                    continue;
                }
                if(current.getExponent()==util.get(n).getExponent()){
                    int sum[] = fraction.sum(current.getCoefficient()[0], current.getCoefficient()[1], util.get(n).getCoefficient()[0], util.get(n).getCoefficient()[1]);
                    util.set(i, new Term(sum[0],sum[1],current.getExponent()));
                    util.remove(n);
                    continue;
                }
            }
        }
        removeZeros();
    }
    private void removeZeros(){
        for(int i=0; i<polynomial.size(); i++){
            Term current = polynomial.get(i);
            if(current.getCoefficient()[0]==0){
                polynomial.remove(i);
                continue;
            }
        }
    }
    public void divideWithCoeff(int coeff[]){
        List<Term> result = new ArrayList<Term>();
        for (Term term : polynomial) {
            int div[] = fraction.division(term.getCoefficient()[0], term.getCoefficient()[1], coeff[0], coeff[1]);
            result.add(new Term(div[0],div[1],term.getExponent()));
        }
        polynomial=result;
    }
    public int getScale(){
        return scale;
    }
    public String toString(){
        if(polynomial.size()==0){
            return "0";
        }
        String polString="";
        for(int i=0; i<polynomial.size(); i++){
            if(i!=0 && polynomial.get(i).getCoefficient()[0]>0){
                polString+="+";
            }
            polString+=polynomial.get(i).toString();
        }
        return polString;
    }
    public int highestDegree(){
        return polynomial.get(0).getExponent();
    }
    public List<Term> getTerms(){
        return polynomial;
    }
    public Polynomial divideWithDegree(int degree){
        Polynomial result = new Polynomial();
        System.out.println(degree);
        for (Term term : polynomial) {
            result.addTerm(term.getCoefficient()[0],term.getCoefficient()[1],term.getExponent()-degree);
        }
        result.reduce();
        return result;
    }
    public int termNumber(){
        return polynomial.size();
    }
    public int lowestDegree(){
        return polynomial.get(polynomial.size()-1).getExponent();
    }
    public double valueAt(double x){
        x=x/scale;
        double y=0;
        for (Term term : polynomial) {
            y+=Math.pow(x, term.getExponent())*fraction.turnToDecimal(term.getCoefficient()[0], term.getCoefficient()[1]);
        }
        return y*scale;
    }
    public int[] valueAt(int x){
        int sum[] = {0,1};
        for (Term term : polynomial) {
            int mul[] = fraction.multiplication((int)Math.pow(x, term.getExponent()), 1, term.getCoefficient()[0], term.getCoefficient()[1]);
            sum=fraction.sum(sum[0], sum[1], mul[0], mul[1]);
        }
        return sum;
    }
}
