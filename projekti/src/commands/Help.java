/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.util.Map;
import utils.*;
/**
 * Esittää kaikkien komentojen kuvaukset
 */
public class Help implements Command{
    private CommandCollection commandCollection;
    private Template template;
    public Help(CommandCollection commandCollection){
        this.commandCollection=commandCollection;
        this.template=new Template();
    }
    @Override
    public String commandDescription() {
        return "";
    }

    @Override
    public String execute() {
        String result = "<html><div style='background-color: white; width: 415px'>" + template.bigHeading("help") + "<div style='margin-top: 10px; background-color: white; padding: 25px; width: 100%'>";
        // Command-line window help
        result+=template.box("Command-line window", "Command-line window is the window you're currently looking at. The command-line itself is the uppermost textfield in the command-line window. It's where you type all your commands. Below it, is the 'suggester' which will tell you, if the command your typing is found in the command collection. By pressing enter, when there's only one result in the suggester, the command will be written in the textfield.");
        // Graph window help
        result+=template.box("Graph window","Graph window is the window where all your graphs will be drawn. You can zoom in by pressing '+' -button, zoom out by pressing '-' -button and move around the coordinate system by using your arrow keys. You can get to the graph window by using the 'draw' -command");
        for (String string : commandCollection.getCommands().keySet()) {
            result+=template.box(string, commandCollection.getCommand(string).commandDescription());
        }
        result+="</div></div></html>";
        return result;
    }

    @Override
    public String operationDescription() {
        return "";
    }

    @Override
    public void handleParameters(String parameters) {
    }
    
}
