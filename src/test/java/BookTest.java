import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BookTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Book.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Book firstBook = new Book("Moby Dick");
    Book secondBook = new Book("Moby Dick");
    assertTrue(firstBook.equals(secondBook));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Book firstBook = new Book("Moby Dick");
    firstBook.save();
    Book savedBook = Book.all().get(0);
    assertTrue(savedBook.equals(firstBook));
  }

  @Test
  public void save_assignsIdToObject() {
    Book firstBook = new Book("Moby Dick");
    firstBook.save();
    Book savedBook = Book.all().get(0);
    assertEquals(firstBook.getId(), savedBook.getId());
  }
  @Test
  public void find_findBookInDatabase_true() {
    Book firstBook = new Book("Moby Dick");
    firstBook.save();
    Book savedBook = Book.find(firstBook.getId());
    assertTrue(firstBook.equals(savedBook));
  }
//
//   @Test
//   public void addCourse_addsCourseToStudent() {
//     Course myCourse = new Course("eco101");
//     myCourse.save();
//
//     Student myStudent = new Student("Jimmy", "01.01.2016");
//     myStudent.save();
//
//     myStudent.addCourse(myCourse);
//     Course savedCourse = myStudent.getCourses().get(0);
//     assertTrue(myCourse.equals(savedCourse));
// }
//
//   @Test
//   public void getCourses_returnsAllCourses_List() {
//     Course myCourse = new Course("eco101");
//     myCourse.save();
//
//     Student myStudent = new Student("Jimmy", "01.01.2016");
//     myStudent.save();
//
//     myStudent.addCourse(myCourse);
//     List savedCourses = myStudent.getCourses();
//     assertEquals(savedCourses.size(), 1);
//   }
//
//   @Test
//   public void delete_deletesAllStudentsAndCourseAssoications() {
//     Course myCourse = new Course("eco101");
//     myCourse.save();
//
//     Student myStudent = new Student("Jimmy", "01.01.2016");
//     myStudent.save();
//
//     myStudent.addCourse(myCourse);
//     myStudent.delete();
//     assertEquals(myCourse.getStudents().size(), 0);
//   }
}
