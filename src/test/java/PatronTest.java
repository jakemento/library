import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class PatronTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Patron.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Patron firstPatron = new Patron("Jim");
    Patron secondPatron = new Patron("Jim");
    assertTrue(firstPatron.equals(secondPatron));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Patron firstPatron = new Patron("Jim");
    firstPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertTrue(savedPatron.equals(firstPatron));
  }

  @Test
  public void save_assignsIdToObject() {
    Patron firstPatron = new Patron("Jim");
    firstPatron.save();
    Patron savedPatron = Patron.all().get(0);
    assertEquals(firstPatron.getId(), savedPatron.getId());
  }
  @Test
  public void find_findPatronInDatabase_true() {
    Patron firstPatron = new Patron("Jim");
    firstPatron.save();
    Patron savedPatron = Patron.find(firstPatron.getId());
    assertTrue(firstPatron.equals(savedPatron));
  }

  @Test
  public void addCopy_addsCopyToPatron() {
    Patron myPatron = new Patron("Jimmy");
    myPatron.save();

    Copy myCopy = new Copy(1, "01/01/2016", "02/01/2016");
    myCopy.save();

    myPatron.addCopy(myCopy);
    Copy savedCopy = myPatron.getCopies().get(0);
    assertTrue(myCopy.equals(savedCopy));
  }


  @Test
  public void getCopies_returnsAllCopies_List() {
    Copy myCopy = new Copy(1, "01/01/2016", "02/01/2016");
    myCopy.save();

    Patron myPatron = new Patron("Karin");
    myPatron.save();

    myCopy.addPatron(myPatron);
    List savedPatrons = myCopy.getPatrons();
    assertEquals(savedPatrons.size(), 1);
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
