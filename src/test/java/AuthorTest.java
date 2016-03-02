import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Author.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Author firstAuthor = new Author("john", "smith");
    Author secondAuthor = new Author("john", "smith");
    assertTrue(firstAuthor.equals(secondAuthor));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Author firstAuthor = new Author("john", "smith");
    firstAuthor.save();
    Author savedAuthor = Author.all().get(0);
    assertTrue(savedAuthor.equals(firstAuthor));
  }

  @Test
  public void save_assignsIdToObject() {
    Author firstAuthor = new Author("john", "smith");
    firstAuthor.save();
    Author savedAuthor = Author.all().get(0);
    assertEquals(firstAuthor.getId(), savedAuthor.getId());
  }
  @Test
  public void find_findAuthorInDatabase_true() {
    Author firstAuthor = new Author("john", "smith");
    firstAuthor.save();
    Author savedAuthor = Author.find(firstAuthor.getId());
    assertTrue(firstAuthor.equals(savedAuthor));
  }

  @Test
  public void addBook_addsBookToAuthor() {
    Author myAuthor = new Author("C.S", "Lewis");
    myAuthor.save();

    Book myBook = new Book("Chronicals of Narnia");
    myBook.save();

    myAuthor.addBook(myBook);
    Book savedBook = myAuthor.getBooks().get(0);
    assertTrue(myBook.equals(savedBook));
  }

  @Test
  public void getBooks_returnsAllBooks_List() {
    Author myAuthor = new Author("C.S", "Lewis");
    myAuthor.save();

    Book myBook = new Book("Chronicals of Narnia");
    myBook.save();

    myAuthor.addBook(myBook);
    List savedBooks = myAuthor.getBooks();
    assertEquals(savedBooks.size(), 1);
  }

  @Test
  public void delete_deletesAllBookFromAuthor() {
    Author myAuthor = new Author("C.S.", "Lewis");
    myAuthor.save();

    Book myBook = new Book("Chronicals of Narnia");
    myBook.save();

    myAuthor.addBook(myBook);
    myAuthor.delete();
    assertEquals(myAuthor.getBooks().size(), 0);
  }
}
