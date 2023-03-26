package ua.mani123.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class WebHookHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {

      exchange.sendResponseHeaders(200, "OK".length());
      exchange.getResponseBody().write("OK".getBytes());

      //Utils.getLogger().info(exchange.getRequestBody().toString());

      //UnmodifiableConfig config = JsonFormat.fancyInstance().createParser().parse(exchange.getRequestBody(), StandardCharsets.UTF_8);
      //Utils.getLogger().info(config.toString());
      //Utils.getLogger().info(config.get("type"));
      //Payment payment = new ObjectConverter().toObject(config, Payment::new);
      //System.out.println("1");
      //System.out.println(payment.toString());
      //System.out.println(payment.getType());
      //Utils.getLogger().info(String.valueOf(payment.getData().getAccount()));
      //Utils.getLogger().info(String.valueOf(payment.getData().getStatementItem().getAmount()));
      //Utils.getLogger().info(String.valueOf(payment.getData().getStatementItem().getBalance()));
    } else {
      exchange.sendResponseHeaders(404, 0);
    }
    exchange.close();
  }
}
