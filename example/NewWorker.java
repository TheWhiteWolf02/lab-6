import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tud.se.httpd.HttpConfig;
import tud.se.httpd.HttpException;
import tud.se.httpd.HttpServer;
import tud.se.httpd.HttpWorker;

public class NewWorker extends HttpWorker {
    public NewWorker(HttpConfig config) {
        super(config);
    }
}
