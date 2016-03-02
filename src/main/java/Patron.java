import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Patron {
  private int id;
  private String name;
  private int copy_id;


  public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public int getCopyId() {
      return copy_id;
    }


    public Patron(String name) {
      this.name = name;
    }

  @Override
  public boolean equals(Object otherPatron){
    if (!(otherPatron instanceof Patron)) {
      return false;
    } else {
      Patron newPatron = (Patron) otherPatron;
      return this.getName().equals(newPatron.getName()) &&
              this.getCopyId() == newPatron.getCopyId() &&
              this.getId() == newPatron.getId();

    }
  }

  public static List<Patron> all() {
    String sql = "SELECT * FROM patrons";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Patron.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO patrons(name, copy_id) VALUES (:name, :copy_id)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("copy_id", copy_id)
        .executeUpdate()
        .getKey();
    }
  }

  public static Patron find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM patrons where id=:id";
      Patron patron = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Patron.class);
      return patron;
    }
  }

  public void addCopy(Copy copy) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO copies_patrons (copy_id, patron_id) VALUES (:copy_id, :patron_id)";
      con.createQuery(sql)
      .addParameter("patron_id",this.getId())
      .addParameter("copy_id", copy.getId())
      .executeUpdate();
    }
  }


  public List<Copy> getCopies() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT copies.* FROM patrons JOIN copies_patrons ON (patrons.id = copies_patrons.patron_id) JOIN copies ON (copies_patrons.copy_id = copies.id) WHERE patrons.id = :patron_id;";
        return con.createQuery(sql)
        .addParameter("patron_id", this.getId())
        .executeAndFetch(Copy.class);
    }
  }
}
