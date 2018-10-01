package proxyserver;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class ProxyServerWorker implements Runnable {
    private static Logger log = Logger.getLogger(ProxyServerWorker.class);

    protected Socket socket;

    public ProxyServerWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String url;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            url = bufferedReader.readLine();

            /*
            URL u = new URL(url.toString());
            URLConnection uc = u.openConnection();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("Content-type: " + uc.getContentType());
            printWriter.println("Content-encoding: "+ uc.getContentEncoding());
            printWriter.println("Content-length: "+ uc.getContentLength());
            printWriter.close();*/

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            URL u = new URL(url.toString());
            //	InputStream in = u.openStream();
            BufferedReader r = new BufferedReader(
                    new InputStreamReader ( u.openStream()));

            String s;
            while (	(s = r.readLine()) != null)
            {
                printWriter.println(s);
            }
            printWriter.close();


        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
