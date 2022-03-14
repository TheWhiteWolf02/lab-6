import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tud.se.httpd.HttpConfig;
import tud.se.httpd.HttpException;
import tud.se.httpd.HttpServer;
import tud.se.httpd.HttpWorker;

public class NewServer extends HttpServer {
    private HttpConfig httpConfig;
    private NewWorker workerCopy;
    public NewServer(HttpConfig config, NewWorker nw) {
        super(config);
        httpConfig = config;
        workerCopy = nw;
    }

    @Override protected void afterHandlingRequest() {
        if(workerCopy.getRequestNum() % 10 == 0) {
            System.out.println("new worker assigned");
            NewWorker nw = new NewWorker(httpConfig);
            workerCopy = nw;
            setWorker(nw);
        }

        if(getReceivedRequests() % 15 == 0) {
            System.out.println("new thread started");
            NewThread newThread = new NewThread(this, workerCopy, httpConfig);
            newThread.updateFlag();
            Thread thread = new Thread(newThread);
            thread.start();
        }
    }

    public void updateWorker(NewWorker nw) {
        workerCopy = nw;
        setWorker(nw);
    }
}
