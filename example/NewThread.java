import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tud.se.httpd.HttpConfig;
import tud.se.httpd.HttpException;
import tud.se.httpd.HttpServer;
import tud.se.httpd.HttpWorker;

public class NewThread implements Runnable {
    private NewServer server;
    private NewWorker worker;
    private HttpConfig config;
    private Boolean flag;

    NewThread(NewServer ns, NewWorker nw, HttpConfig hc) {
        server = ns;
        worker = nw;
        config = hc;
        flag = false;
    }

    @Override public void run() {
        if(flag == true) {
            flag = false;
            server.halt();
            NewServer ns = new NewServer(config, worker);
            ns.updateWorker(worker);
            ns.start();
        }
    }

    public void updateFlag() {
        flag = true;
    }
}
