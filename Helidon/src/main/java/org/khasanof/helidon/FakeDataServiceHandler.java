package org.khasanof.helidon;

import io.helidon.http.Status;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

import java.util.Map;

public class FakeDataServiceHandler implements HttpService {

    static final int MAX_COUNT = 1_000;

    private final FakeDataService fakeDataService;

    public FakeDataServiceHandler(FakeDataService fakeDataService) {
        this.fakeDataService = fakeDataService;
    }

    @Override
    public void routing(HttpRules rules) {
        rules.get("/", this::generate);
    }

    void generate(ServerRequest request, ServerResponse response) {
        String rawCount = request.query().first("count").orElse(null);
        if (rawCount == null || rawCount.isBlank()) {
            response.status(Status.BAD_REQUEST_400)
                    .send(Map.of("error", "count request param is required, e.g. /fake-data?count=10"));
            return;
        }

        int count;
        try {
            count = Integer.parseInt(rawCount);
        } catch (NumberFormatException e) {
            response.status(Status.BAD_REQUEST_400)
                    .send(Map.of("error", "count must be an integer"));
            return;
        }

        if (count < 1 || count > MAX_COUNT) {
            response.status(Status.BAD_REQUEST_400)
                    .send(Map.of("error", "count must be between 1 and " + MAX_COUNT));
            return;
        }

        response.send(fakeDataService.generate(count));
    }
}
