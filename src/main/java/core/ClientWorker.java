package core;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWorker implements Runnable {
    private static Logger log = Logger.getLogger(ClientWorker.class);

    @FXML
    private TextArea outputTextArea;

    private Socket socket;
    private String proxyServerUrl;
    private Integer proxyServerPort;
    private String url;

    private String output;

    public ClientWorker(TextArea outputTextArea, String proxyServerUrl, Integer proxyServerPort, String url) {
        this.outputTextArea = outputTextArea;
        this.proxyServerUrl = proxyServerUrl;
        this.proxyServerPort = proxyServerPort;
        this.url = url;
        try {
            this.socket = new Socket(proxyServerUrl, proxyServerPort);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(this.url);

            //flushes the stream
            out.flush();

            String feed = null;
            StringBuilder sb = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //read from socket input stream (responses from server)
            while ((feed = in.readLine()) != null) {
                sb.append(feed);
                sb.append("\n");
            }
            //settings server response to GUI
            outputTextArea.setText(sb.toString());
            this.setOutput(sb.toString());
            return;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
