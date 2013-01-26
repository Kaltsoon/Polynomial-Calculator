/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;
import java.util.HashMap;
import java.util.Map;
/**
 * Tallentaa komento-rajapinnan toteuttavia olioita
 */
public class CommandCollection {
    private Map<String,Command> commands;
    public CommandCollection(){
        commands=new HashMap<String,Command>();
    }
 /**
 * Tallentaa komento-rajapinnan toteuttavan olion nimellä
 * @param komennon nimi
 * @param komento-rajapinnan toteuttava olio
 */
    public void addCommand(String name, Command command){
        commands.put(name, command);
    }
    public Map<String,Command> getCommands(){
        return commands;
    }
    public Command getCommand(String name){
        return commands.get(name);
    }
}

