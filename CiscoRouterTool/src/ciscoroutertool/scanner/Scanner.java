/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ciscoroutertool.scanner;

import ciscoroutertool.utils.Host;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runs the scan for a host and holds the results
 * @version 0.01ALPHA
 * @author Andrew Johnston
 */
public class Scanner implements Callable<HostReport> {

    public static ArrayList<Rule> rules;
    
    private ArrayList<Rule> matched;
    
    private static final int SSH_PORT = 22;
    private static final String GET_ALL_CONFIG = "";
    
    private Host host;
    public Scanner(Host h) {
        host = h;
    }



    @Override
    public HostReport call() throws Exception {
        BufferedReader reader = getConfigFile();
        String line = null;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        ArrayList<RouterInterface> interfaces = 
                RouterInterfaceManager.getInterfaces(lines);
        HostReport report = getHostReport(interfaces);
        return report;
    }
    
    private BufferedReader getConfigFile() {
        InputStream in = null;
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(
                    host.getUser(),
                    host.getAddress().getHostAddress(),
                    SSH_PORT);
            session.setPassword(host.getPass());
            session.connect();
            //Run the command that gets the config
            ChannelExec exec = (ChannelExec) session.openChannel("exec");
            in = exec.getInputStream();
            exec.setCommand(GET_ALL_CONFIG);
            exec.connect();         
        } catch (JSchException | IOException ex) {
            Logger.getLogger(Scanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new BufferedReader(new InputStreamReader(in));
    }

    private HostReport getHostReport(ArrayList<RouterInterface> interfaces) {
        HostReport report = new HostReport(host);
        for (RouterInterface iface : interfaces) {
            ArrayList<String> lines = iface.getLines();
            for (String line : lines) {
                //Check each rule against each line
                for (Rule r : rules) {
                    if (r.matchesRule(line)) {
                        report.addMatchedRule(r);
                    }
                }
            }
        }
        return report;
    }
}
