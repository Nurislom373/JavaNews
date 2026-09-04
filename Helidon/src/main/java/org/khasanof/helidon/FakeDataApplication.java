package org.khasanof.helidon;

import io.helidon.config.Config;
import io.helidon.logging.common.LogConfig;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

public class FakeDataApplication {

    public static void main(String[] args) {
        LogConfig.configureRuntime();
        Config config = Config.create();

        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(FakeDataApplication::routing)
                .build()
                .start();

        System.out.println("Fake Data API: http://localhost:" + server.port() + "/fake-data?count=5");
    }

    static void routing(HttpRouting.Builder routing) {
        routing.register("/fake-data", new FakeDataServiceHandler(new FakeDataService()));
    }
}
