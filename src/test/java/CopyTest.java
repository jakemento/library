import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Arrays;

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

  @Test
  public void addPatron_addsPatronToCopy() {
    Copy myCopy = new Copy(1, "01/01/2016", "02/01/2016");
    myCopy.save();

    Patron myPatron = new Patron("Jimmy");
    myPatron.save();

    myCopy.addPatron(myPatron);
    Patron savedPatron = myCopy.getPatrons().get(0);
    assertTrue(myPatron.equals(savedPatron));
  }


    @Test
   public void getPatrons_returnsAllPatrons_List() {
     Copy myCopy = new Copy(1, "01/01/2016", "02/01/2016");
     myCopy.save();

     Patron myPatron = new Patron("Karin");
     myPatron.save();

     myCopy.addPatron(myPatron);
     List savedPatrons = myCopy.getPatrons();
     assertEquals(savedPatrons.size(), 1);
   }

  @Test
  public void delete_deleteCopy() {
    Copy testCopy = new Copy(1, "01/01/2016", "02/01/2016");
    testCopy.save();
    testCopy.delete();
    assertEquals(0, Copy.all().size());
  }

  @Test
  public void update_updatesCopyCheckoutDate() {
    Copy myCopy = new Copy(1, "01/01/2016", "02/01/2016");
    myCopy.save();
    myCopy.update("02/01/2016", "03/01/2016");
    Copy savedCopy = Copy.find(myCopy.getId());
    assertTrue(myCopy.equals(savedCopy));
    assertEquals(myCopy.getCheckoutDate(), "02/01/2016");
    assertEquals(myCopy.getDueDate(), "03/01/2016");
  }
}
