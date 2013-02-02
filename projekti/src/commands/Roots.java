/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import domain.*;
import utils.*;
/**
 * Laskee polynomin reaalijuuret, jos ne ovat olemassa
 */
public class Roots implements Command{
    private Polynomial polynomial;
    private Fraction fraction;
    private StringModifier mod;
    private Template template;
    private List<String> roots;
    public Roots(){
        this.fraction=new Fraction();
        this.mod=new StringModifier();
        this.template=new Template();
    }

    @Override
    public String commandDescription() {
        return "Takes <i>a single polynomial</i> as a parameter and presents its real roots as result.<br><br>Examples:<br><ul><li>roots(x^2-100)</li><li>roots(-3x^4+x^2+x+1)</li></ul>";
    }

    @Override
    public String execute() {
        getRoots();
        this.roots=removeDublicates(roots);
        if(roots.size()==0){
            return polynomial + " has no real roots";
        }
        String result="";
        for(int i=0; i<roots.size(); i++){
            result+="x="+roots.get(i);
            if(i<roots.size()-1){
                result+=", or ";
            }
        }
        return result;
    }
 /**
 * Analysoi polynomin ja etsii sen juuret apumetodien kautta
 * @return lista polynomien reaalijuurista
 */
    private List<String> getRoots(){
        if(polynomial.lowestDegree()>0){
            roots.add("0");
            polynomial=polynomial.divideWithDegree(polynomial.lowestDegree());
            getRoots();
        }else if(polynomial.highestDegree()==2){
            secondDegreeRoots();
        }else if(polynomial.highestDegree()==1){
            firstDegreeRoots();
        }else{
            analyseNumeric();
        }
        return roots;
    }
 /**
 * Haarukoi polynomia ja etsii välejä, joista löytyy nollakohta
 */
    private void analyseNumeric(){
        double bounds[] = rootBounds();
        double step = Math.abs((bounds[0]-bounds[1])/(polynomial.highestDegree()*100));
        for(double i=bounds[0]; i<=bounds[1]; i+=step){
            if(hasDifferentSign(polynomial.valueAt(i),polynomial.valueAt(i+step))==true){
                newtonsMethod(i);
            }
        }
    }
 /**
 * Etsii polynomin nollakohdan tuhannesosan tarkkuudella
 * @param alkuarvaus (arvo lähellä nollakohtaa)
 */
    private void newtonsMethod(double x){
        Derivative der = new Derivative();
        Polynomial derivative = der.derivative(polynomial);
        double result=x;
        x=Math.round(x*1000.0)/1000.0;
        double current = x-polynomial.valueAt(x)/derivative.valueAt(x);
        current=Math.round(current*1000.0)/1000.0;
        while(current!=x && current!=0){
            x=current;
            current=x-polynomial.valueAt(x)/derivative.valueAt(x);
            x=Math.round(x*1000.0)/1000.0;
            current=Math.round(current*1000.0)/1000.0;
        }
        roots.add(x + "");
    }
 /**
 * Katsoo, ovatko kaksi lukua eri merkkiset
 * @param kaksi lukua
 * @return palauttaa true, jos luvut ovat eri merkkiset, false muutoin
 */
    private boolean hasDifferentSign(double a, double b){
        if((a<0&&b<0) || (a>0&&b>0)){
            return false;
        }
        return true;
    }
 /**
 * Tutkii, millä välillä polynomin nollakohdat ovat
 * @return palauttaa taulukon, jonka ensimäinen alkio on nollakohdan alaraja ja toinen alkio yläraja
 */
    private double[] rootBounds(){
        double bounds[] = new double[2];
        Polynomial dividedPolynomial = this.polynomial.clone();
        dividedPolynomial.divideWithCoeff(dividedPolynomial.getTermOfDegree(dividedPolynomial.highestDegree()).getCoefficient());
        List<Double> coeffs = new ArrayList<Double>();
        for (Term term : dividedPolynomial.getTerms()) {
            coeffs.add(Math.abs(fraction.turnToDecimal(term.getCoefficient()[0], term.getCoefficient()[1])));
        }
        coeffs=removeDoubleDublicates(coeffs);
        Collections.sort(coeffs);
        double bound = coeffs.get(coeffs.size()-1)+1;
        bounds[0]=bound*-1;
        bounds[1]=bound;
        return bounds;
    }
 /**
 * Tutkii, onko double kokonaisluku
 * @param luku
 * @return palauttaa false, jos double on kokonaisluku, true muutoin
 */
    private boolean doubleHasDecimal(double a){
        if(a==(double)((int)a)){
            return true;
        }  
        return false;
    }
    private boolean sqrtIsInt(int frac[]){
        double sqrt[] = new double[2];
        sqrt[0] = Math.sqrt(frac[0]);
        sqrt[1] = Math.sqrt(frac[1]);
        if((double)((int)sqrt[0])==sqrt[0] && (double)((int)sqrt[1])==sqrt[1]){
            return true;
        }else{
            return false;
        }
    }
 /**
 * Laskee toisen asteen polynomin nollakohdat
 */
    private void secondDegreeRoots() {
        // ax^2+bx+c
        int a[] = polynomial.getTermOfDegree(2).getCoefficient();
        int b[] = polynomial.getTermOfDegree(1).getCoefficient(); 
        int c[] = polynomial.getTermOfDegree(0).getCoefficient();
        // b^2-4ac
        int square[] = fraction.sum(fraction.multiplication(-4,1,fraction.multiplication(a[0], a[1], c[0], c[1])[0],fraction.multiplication(a[0], a[1], c[0], c[1])[1])[0],fraction.multiplication(-4,1,fraction.multiplication(a[0], a[1], c[0], c[1])[0],fraction.multiplication(a[0], a[1], c[0], c[1])[1])[1],fraction.multiplication(b[0], b[1], b[0], b[1])[0],fraction.multiplication(b[0], b[1], b[0], b[1])[1]);
        if(fraction.turnToDecimal(square[0], square[1])<0){
            return;
        }
        if(sqrtIsInt(square)==true){
            int sum[] = fraction.sum((int)Math.sqrt(square[0]), (int)Math.sqrt(square[1]), b[0]*-1, b[1]);
            int div[] = fraction.division(sum[0], sum[1], a[0]*2, a[1]);
            roots.add(mod.bestForm(div));
            sum = fraction.sum(-1*(int)Math.sqrt(square[0]), (int)Math.sqrt(square[1]), b[0]*-1, b[1]);
            div = fraction.division(sum[0], sum[1], a[0]*2, a[1]);
            roots.add(mod.bestForm(div));
        }else{
            roots.add(template.division(mod.bestForm(fraction.multiplication(b[0], b[1], -1, 1)) + "-SQRT(" + mod.bestForm(square) + ")", mod.bestForm(fraction.multiplication(a[0], a[1], 2, 1))));
            if(!mod.bestForm(fraction.multiplication(b[0], b[1], -1, 1)).equals("")){
                roots.add(template.division(mod.bestForm(fraction.multiplication(b[0], b[1], -1, 1)) + "+SQRT(" + mod.bestForm(square) + ")", mod.bestForm(fraction.multiplication(a[0], a[1], 2, 1))));
            }else{
                roots.add(template.division("SQRT(" + mod.bestForm(square) + ")", mod.bestForm(fraction.multiplication(a[0], a[1], 2, 1))));
            }
            
        }
    }
 /**
 * Laskee ensimäisen asteen polynomin nollakohdan
 */
    private void firstDegreeRoots(){
        if(polynomial.termNumber()==1){
            roots.add("0");
            return;
        }
        int div[] = fraction.division(-1*polynomial.termAt(1).getCoefficient()[0], polynomial.termAt(1).getCoefficient()[1], polynomial.termAt(0).getCoefficient()[0], polynomial.termAt(0).getCoefficient()[1]);
        roots.add(mod.bestForm(div));
    }
 /**
 * Poistaa listan dublikaatit
 * @param lista merkkijonoja
 * @return lista merkkijonoja, josta on poistettu dublikaatit
 */
    private List<String> removeDublicates(List<String> list){
        List<String> result = new ArrayList<String>();
        if(list.isEmpty()){
            return result;
        }
        result.add(list.get(0));
        for (String string : list) {
            if(!result.contains(string)){
                result.add(string);
            }
        }
        return result;
    }
    private List<Double> removeDoubleDublicates(List<Double> list){
        List<Double> result = new ArrayList<Double>();
        if(list.isEmpty()){
            return result;
        }
        result.add(list.get(0));
        for (Double a : list) {
            if(!result.contains(a)){
                result.add(a);
            }
        }
        return result;
    }
    @Override
    public String operationDescription() {
        return "Real roots of " + polynomial;
    }

    @Override
    public void handleParameters(String parameters) {
        this.roots=new ArrayList<String>();
        polynomial=mod.turnIntoPolynomial(mod.readParameters(parameters)[0]);
        polynomial.reduce();
    }
}
