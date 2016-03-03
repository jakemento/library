import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/books", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("books", Book.all());
      model.put("template", "templates/books.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/authors", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("authors", Author.all());
      model.put("template", "templates/authors.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/patrons", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("patrons", Patron.all());
      model.put("template", "templates/patrons.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());








    post("/books", (request, response) -> {
      String title = request.queryParams("title");
      title = title.substring(0, 1).toUpperCase() + title.substring(1);
      Book newBook = new Book(title);
      newBook.save();
      response.redirect("/books");
      return null;
    });

    post("/authors", (request, response) -> {
      String firstName = request.queryParams("firstName");
      firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
      String lastName = request.queryParams("lastName");
      lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
      Author newAuthor = new Author(firstName, lastName);
      newAuthor.save();
      response.redirect("/authors");
      return null;
    });



    post("/patrons", (request, response) -> {
      String patronName = request.queryParams("patronName");
      patronName = patronName.substring(0, 1).toUpperCase() + patronName.substring(1);
      Patron newPatron = new Patron(patronName);
      newPatron.save();
      response.redirect("/patrons");
      return null;
    });
  }
}
