import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("HOMEPAGE");
  }

  @Test
  public void bookIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add or View Library"));
    fill("#title").with("Narnia");
    submit(".btn");
    assertThat(pageSource()).contains("Narnia");
  }

  @Test
  public void AuthorIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add or View Authors"));
    fill("#firstName").with("C.S");
    fill("#lastName").with("Lewis");
    submit(".btn");
    assertThat(pageSource()).contains("C.S");
    assertThat(pageSource()).contains("Lewis");
  }

  @Test
  public void PatronIsCreatedTest() {
    goTo("http://localhost:4567/");
    click("a", withText("Add or View a Patron"));
    fill("#patronName").with("Jim");
    submit(".btn");
    assertThat(pageSource()).contains("Jim");
  }

  //
  // @Test
  // public void courseIsDisplayedTest() {
  //   Course myCourse = new Course("eco101");
  //   myCourse.save();
  //   String categoryPath = String.format("http://localhost:4567/courses/%d", myCourse.getId());
  //   goTo(categoryPath);
  //   assertThat(pageSource()).contains("eco101");
  // }
  //
  // @Test
  // public void allStudentsNameOnCoursePage() {
  //   Course myCourse = new Course("eco101");
  //   myCourse.save();
  //   Student firstStudent = new Student("Jimmy", "01.01.2016");
  //   firstStudent.save();
  //   Student secondStudent = new Student("Jim Bob", "01.01.2016");
  //   secondStudent.save();
  //   String coursePath = String.format("http://localhost:4567/courses/%d", myCourse.getId());
  //   goTo(coursePath);
  //   assertThat(pageSource()).contains("Jimmy");
  //   assertThat(pageSource()).contains("Jim Bob");
  // }
}
