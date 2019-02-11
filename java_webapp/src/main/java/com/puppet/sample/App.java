package com.puppet.sample;
import com.puppet.sample.langs.Polyglot;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.before;

public class App
{

  private static String requestInfoToString(Request request) {
    StringBuilder sb = new StringBuilder();
    sb.append(request.requestMethod());
    sb.append(" " + request.url());
    sb.append(" " + request.body());
    return sb.toString();
  }

  public static void main(String[] args) {

    try {
      Spark.port(Integer.parseInt(System.getProperty("appPort")));
    } catch (Exception e) {
      Spark.port(9999);
    }

    Spark.threadPool(10, 5, 600);

    before((request, response) -> {
        System.out.println(requestInfoToString(request));
    });

    get("/", (request, response) -> {
      response.redirect("/en");
      return null;
    });

    get("/:lang", App::helloWorld, new ThymeleafTemplateEngine());

  }

  public static ModelAndView helloWorld(Request req, Response res) {
    Map<String, Object> params = new HashMap<>();

    App t = new App();
    params.put("version", t.getClass().getPackage().getImplementationVersion() );
    params.put("hostname", t.getServerHostname() );

    Polyglot p = new Polyglot();

    switch(req.params(":lang")) {
      case "en": params.put("lang", p.enMsg());
                 break;
      case "sp": params.put("lang", p.spMsg());
                 break;
      case "zh": params.put("lang", p.zhMsg());
                 break;
      case "ar": params.put("lang", p.arMsg());
                 break;
      case "hi": params.put("lang", p.hiMsg());
                 break;
      default: String msg = "I don't know that language ~> ";
               msg += req.params(":lang");
               params.put("lang", msg); 
    }

    return new ModelAndView(params, "index");
  }

  public static String getServerHostname() {
    try {
      InetAddress ip = InetAddress.getLocalHost();
      return ip.getHostName();
    } catch (UnknownHostException e) {
      return "Unknown Hostname";
    }
  }
}
