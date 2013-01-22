/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

/**
 *
 * @author Kalle
 */
public interface Command {
    String commandDescription();
    String execute();
    String operationDescription();
    void handleParameters(String parameters);
}
