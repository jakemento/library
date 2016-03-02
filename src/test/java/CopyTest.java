import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class CopyTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Copy.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Copy firstCopy = new Copy(1, "01/01/2016", "02/01/2016");
    Copy secondCopy = new Copy(1, "01/01/2016", "02/01/2016");
    assertTrue(firstCopy.equals(secondCopy));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Copy firstCopy = new Copy(1, "01/01/2016", "02/01/2016");
    firstCopy.save();
    Copy savedCopy = Copy.all().get(0);
    assertTrue(savedCopy.equals(firstCopy));
  }

  @Test
  public void save_assignsIdToObject() {
    Copy firstCopy = new Copy(1, "01/01/2016", "02/01/2016");
    firstCopy.save();
    Copy savedCopy = Copy.all().get(0);
    assertEquals(firstCopy.getId(), savedCopy.getId());
  }
  @Test
  public void find_findCopyInDatabase_true() {
    Copy firstCopy = new Copy(1, "01/01/2016", "02/01/2016");
    firstCopy.save();
    Copy savedCopy = Copy.find(firstCopy.getId());
    assertTrue(firstCopy.equals(savedCopy));
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
