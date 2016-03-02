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
