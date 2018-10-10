package controllers;

import core.ClientWorker;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainController {
    @FXML
    private TextField inputUrl;

    @FXML
    private Button goButton;

    @FXML
    private TextField proxyServerAddress;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private void handleGoButtonAction(ActionEvent event) {
        Window owner = goButton.getScene().getWindow();

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        StringTokenizer tokenizer = new StringTokenizer(proxyServerAddress.getText(), ":");
        final ClientWorker clientWorker = new ClientWorker(
                tokenizer.nextToken(),
                Integer.parseInt(tokenizer.nextToken()),
                inputUrl.getText());
        threadPool.execute(clientWorker);

        scrollPane.setContent(null);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // if you change the UI, do it here !
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final WebView browser = new WebView();
                WebEngine webEngine = browser.getEngine();
                webEngine.loadContent(clientWorker.getOutput());
                scrollPane.setContent(browser);
            }
        });
    }
}
