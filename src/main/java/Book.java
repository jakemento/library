import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Book {
  private int id;
  private String title;

  public int getId() {
      return id;
    }

    public String getTitle() {
      return title;
    }


    public Book(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object otherBook){
    if (!(otherBook instanceof Book)) {
      return false;
    } else {
      Book newBook = (Book) otherBook;
      return this.getTitle().equals(newBook.getTitle()) &&
             this.getId() == newBook.getId();
    }
  }

  public static List<Book> all() {
    String sql = "SELECT * FROM books";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Book.class);
    }
  }
}
