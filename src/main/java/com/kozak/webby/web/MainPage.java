package com.kozak.webby.web;

import com.kozak.webby.dao.exec.UserDAO;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

public class MainPage {

    public void showAllUsers() {
        port(4567);
        enableDebugScreen();
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        get("/hello", (request, response) -> {
            UserDAO userDAO = new UserDAO();
            Map<String, Object> model = new HashMap<>();
            model.put("users", userDAO.getAll());

            // The wm files are located under the resources directory
            return new ModelAndView(model, "html/hello.vm");
        }, new VelocityTemplateEngine());


    }


}
