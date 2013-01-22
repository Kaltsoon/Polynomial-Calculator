/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import domain.Polynomial;
import java.util.ArrayList;

/**
 *
 * @author Kalle
 */
public class StringModifier {
    public Polynomial turnIntoPolynomial(String polynomial){
        Polynomial pol = new Polynomial();
        ArrayList<String> terms = divideIntoTerms(polynomial);
        for (String term : terms) {
            int numenator = termCoefficient(term)[0];
            int denominator = termCoefficient(term)[1];
            int exponent = termExponent(term);
            pol.addTerm(numenator, denominator, exponent);
        }
        return pol;
    }
    public String bestForm(int fraction[]){
        if(fraction[1]==1 && fraction[0]!=0){
            return fraction[0] + "";
        }else if(fraction[0]==0 || fraction[1]==0){
            return "";
        }else if(fraction[1]<0){
            int f[] = {fraction[0]*-1,fraction[1]*-1};
            return bestForm(f);
        }else{
            return fraction[0] + "/" + fraction[1];
        }
    }
    private ArrayList<String> divideIntoTerms(String polynomi){
        int start=0;
        int end=1;
        ArrayList<String> termit = new ArrayList<String>();
        while(end<polynomi.length()-1){
            if((polynomi.charAt(end)+"").equals("+") || (polynomi.charAt(end)+"").equals("-")){
                termit.add(polynomi.substring(start,end));
                start=end;
            }  
            end++;
        }
        termit.add(polynomi.substring(start,polynomi.length()));
        return termit;
    }
    public String readCommand(String string){
        return readTill(string,"(");
    }
    private int[] termCoefficient(String term){
        String firstChar = term.charAt(0) + "";
        if(!term.contains("x") && !term.contains("/")){
                int result[]={Integer.parseInt(term),1};
                return result;
        }
        if(!term.contains("/")){
            if(firstChar.equals("x") || term.substring(0,2).equals("+x")){
                int result[] = {1,1};
                return result;
            }else if(term.substring(0,2).equals("-x")){
                int result[] = {-1,1};
                return result;
            }
            else if(!term.contains("x")){
                int result[] = {Integer.parseInt(term),1};
                return result;
            }
            else{
                int result[] = {Integer.parseInt(term.split("x")[0]),1};
                return result;
            }
        }else{
            String kerroinOsa;
            String osoittaja;
            String nimittaja;           
            if(!term.contains("x")){
                osoittaja = term.split("/")[1];
                nimittaja = term.split("/")[0];
                int palautus[] = {Integer.parseInt(nimittaja), Integer.parseInt(osoittaja)};
                return palautus;
            }
            kerroinOsa = term.split("x")[0];
            osoittaja = kerroinOsa.split("/")[1];
            nimittaja = kerroinOsa.split("/")[0];
            int palautus[] = {Integer.parseInt(nimittaja), Integer.parseInt(osoittaja)};
            return palautus;
        }
    }
    private int termExponent(String termi){
        char n = termi.charAt(termi.length()-1);
        String merkki = ""+n;
        String eksponentti="";
        if(termi.lastIndexOf("x")==-1){
            return 0;
        }
        else if(merkki.equals("x")){
            return 1;
        }
        else{
            for(int i=termi.lastIndexOf("^")+1; i<termi.length(); i++){
                n=termi.charAt(i);
                merkki=""+n;
                eksponentti+=merkki;
            }
            return Integer.parseInt(eksponentti);
        }
    }
    public String readTill(String string, String character){
        char n = string.charAt(0);
        String t = "" + n;
        String result = "";
        int stage=1;
        while(!t.equals(character)){
            result+=t;
            n=string.charAt(stage);
            t=""+n;
            stage++;
        }
        return result;
    }
    public String readFromTo(String string, String start, String end){
        int stage = string.indexOf(start)+1;
        char n = string.charAt(stage);
        String t = "" + n;
        String tulos = "";
        while(!t.equals(end)){          
            n=string.charAt(stage);
            t=""+n;
            if(t.equals(end)){
                break;
            }
            tulos+=t;
            stage++;
        }
        return tulos;
    }
    public String[] readParameters(String string){
            String parameters=readFromTo(string,"(",")");
            return parameters.split(",");
    }
}
