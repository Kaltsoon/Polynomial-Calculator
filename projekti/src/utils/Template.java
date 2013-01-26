/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Sisältää HTML-templateja
 */
public class Template {
    public String PolynomialCalculation(String command, String operationDescription, String solution){
        return "<html><div style='background-color: white; width:428px; '>"+ bigHeading(command) +"<div style='margin-top: 10px; background-color: white; padding: 25px; width: 100%; color:rgb(138,156,168);'><div style='margin-bottom: 10px; width: 100%; background-color: rgb(244,245,247); padding: 15px; font: 11px arial;'><div style='color: rgb(94,104,128); font-size: 13px; wdith: 100%; padding: 5px; border-bottom: 3px solid rgb(187,202,219); margin-bottom: 15px;'>Operation description</div>" + operationDescription + "</div><div style='background-color: rgb(244,245,247); padding: 15px; font: 11px arial; width: 100%;'><div style='color: rgb(94,104,128); font-size: 13px; wdith: 100%; padding: 5px; border-bottom: 3px solid rgb(187,202,219); margin-bottom: 15px;'>Solution</div>" + solution + "</div></div></div><html>";
    }
    public String error(String command){
        return "<html><div style='background-color: white; width: 428px;'>" + bigHeading(command) + "<div style='margin-top: 10px; background-color: white; padding: 25px; color:rgb(138,156,168);'><div style='margin-bottom: 10px; width: 100%; width: 100%; background-color: rgb(244,245,247); padding: 15px; font: 11px arial'><div style='color: rgb(100,43,43); font-size: 13px; wdith: 100%; padding: 5px; border-bottom: 3px solid rgb(187,202,219); margin-bottom: 15px;'>An error has occured!</div>Your input '" + command + "' didn't generate any result. Type 'help' in command-line to see the list of available commands and how to use them.</div></div></div></html>";
    }
    public String bigHeading(String content){
        return "<div style='padding: 10px; font: bold italic 15px arial; background-color:rgb(89,124,164); color: white; width: 100%;'>" + content + "</div>";
    }
    public String welcome(){
        return "<html><div style='background-color: white; width: 428px;'>" + bigHeading("Poly") + "<div style='margin-top: 10px; background-color: white; padding: 25px; color:rgb(138,156,168);'><div style='margin-bottom: 10px; width: 100%; width: 100%; background-color: rgb(244,245,247); padding: 15px; font: 11px arial'><div style='color: rgb(94,104,128); font-size: 13px; wdith: 100%; padding: 5px; border-bottom: 3px solid rgb(187,202,219); margin-bottom: 15px;'>Welcome to use poly!</div>Poly is an application that executes polynomial calculations and draws graphs. Type 'help' in command-line to get more information about Poly.</div></div></div></html>";
    }
    public String box(String heading, String content){
        return  "<div style='margin-bottom: 10px; width: 100%; background-color: rgb(244,245,247); padding: 15px; font: 11px arial; color:rgb(138,156,168);'><div style='color: rgb(94,104,128); font-size: 13px; wdith: 100%; padding: 5px; border-bottom: 3px solid rgb(187,202,219); margin-bottom: 15px;'>"+ heading +"</div>" + content + "</div>";
    }
    public String division(String up, String down){
        return "(" + up + ")/(" + down + ")";
    }
}
