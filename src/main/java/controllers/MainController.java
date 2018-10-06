package controllers;

import core.ClientWorker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {
    @FXML
    private TextField inputUrl;

    @FXML
    private Button goButton;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private TextField proxyServerAddress;

    @FXML
    private void handleGoButtonAction(ActionEvent event) {
        Window owner = goButton.getScene().getWindow();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        StringTokenizer tokenizer = new StringTokenizer(proxyServerAddress.getText(), ":");
        ClientWorker clientWorker = new ClientWorker(this.outputTextArea,
                tokenizer.nextToken(),
                Integer.parseInt(tokenizer.nextToken()),
                inputUrl.getText());
        threadPool.execute(clientWorker);
    }
}
