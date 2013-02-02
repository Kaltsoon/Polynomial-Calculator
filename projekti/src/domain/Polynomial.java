/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import utils.Fraction;

/**
 * Sisältää listan termejä
 */
public class Polynomial {
    private int scale=1;
    private Fraction fraction = new Fraction();
    private List<Term> polynomial = new ArrayList<Term>();
    public void addTerm(int numenator, int denominator, int exponent){
        polynomial.add(new Term(numenator,denominator,exponent));
        Collections.sort(polynomial);
    }
/**
 * Lisää polynomiin termin
 * @param Term-olio
 */
    public void addTerm(Term term){
        polynomial.add(term);
    }
    public void setScale(int a){
        this.scale=a;
    }
/**
 * Etsii termin, indeksilläillä
 * @param indeksi
 * @return Term-olio
 */
    public Term termAt(int index){
        return polynomial.get(index);
    }
/**
 * Etsii termin, joka on tietty astetta
 * @param halutun termin aste
 * @return Term-olio
 */
    public Term getTermOfDegree(int degree){
        for (Term term : polynomial) {
            if(term.getExponent()==degree){
                return term;
            }
        }
        return new Term(0,1,0);
    }
/**
 * Kloonaa polynomin
 * @return polynomial-olio, joka on kyseisen polynomial-olion klooni
 */
    public Polynomial clone(){
        Polynomial clone = new Polynomial();
        for (Term term : polynomial) {
            clone.addTerm(term);
        }
        return clone;
    }
/**
 * Sieventää polynomin
 */
    public void reduce(){
        List<Term> result=new ArrayList<Term>();
        HashMap<Integer,Term> exponents = new HashMap<Integer,Term>();
        for(int i=0; i<polynomial.size(); i++){
            if(!exponents.containsKey(polynomial.get(i).getExponent()) && polynomial.get(i).getExponent()>=0){
                exponents.put(polynomial.get(i).getExponent(), new Term(polynomial.get(i).getCoefficient()[0],polynomial.get(i).getCoefficient()[1],polynomial.get(i).getExponent()));
            }else{
                int sum[] = fraction.sum(exponents.get(polynomial.get(i).getExponent()).getCoefficient()[0], exponents.get(polynomial.get(i).getExponent()).getCoefficient()[1], polynomial.get(i).getCoefficient()[0], polynomial.get(i).getCoefficient()[1]);
                exponents.put(polynomial.get(i).getExponent(),new Term(sum[0],sum[1],polynomial.get(i).getExponent()));
            }
        }
        for (int n : exponents.keySet()) {
            if(exponents.get(n).getCoefficient()[0]!=0){
                result.add(exponents.get(n));
            }
        }
        Collections.sort(result);
        polynomial=result;
    }
/**
 * Jakaa kaikki polynomin termit halutulla kertoimella
 * @param taulukko, jonka esimäinen alkio on kertoimen osoittaja ja toinen nimittäjä
 */
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
    @Override
    public String toString(){
        if(polynomial.isEmpty()){
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
/**
 * Palauttaa polynomin korkeimman asteen
 * @return polynomin aste
 */
    public int highestDegree(){
        return polynomial.get(0).getExponent();
    }
    public List<Term> getTerms(){
        return polynomial;
    }
/**
 * Jakaa polynomin kaikki termit termillä x^degree
 * @param eksponentti
 * @return jaettu polynomia-olio
 */
    public Polynomial divideWithDegree(int degree){
        Polynomial result = new Polynomial();
        for (Term term : polynomial) {
            result.addTerm(term.getCoefficient()[0],term.getCoefficient()[1],term.getExponent()-degree);
        }
        result.reduce();
        return result;
    }
/**
 * Palauttaa polynomin termien määrän
 * @return termien määrä
 */
    public int termNumber(){
        return polynomial.size();
    }
/**
 * Palauttaa polynomin alhaisimman asteen
 * @return alhaisin aste
 */
    public int lowestDegree(){
        return polynomial.get(polynomial.size()-1).getExponent();
    }
/**
 * Palauttaa polynomin arvon kohdassa x
 * @param kohta x
 * @return polynomin arvo kohdassa x (desimaalimuoto)
 */
    public double valueAt(double x){
        x=x/scale;
        double y=0;
        for (Term term : polynomial) {
            y+=Math.pow(x, term.getExponent())*fraction.turnToDecimal(term.getCoefficient()[0], term.getCoefficient()[1]);
        }
        return y*scale;
    }
 /**
 * Palauttaa polynomin arvon kohdassa x
 * @param kohta x
 * @return polynomin arvo kohdassa x (murtolukumuoto)
 */
    public int[] valueAt(int x){
        int sum[] = {0,1};
        for (Term term : polynomial) {
            int mul[] = fraction.multiplication((int)Math.pow(x, term.getExponent()), 1, term.getCoefficient()[0], term.getCoefficient()[1]);
            sum=fraction.sum(sum[0], sum[1], mul[0], mul[1]);
        }
        return sum;
    }
}
