package test.classes;

import main.classes.Library;
import main.classes.SearchByType;
import main.classes.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class StudentTest {

    private Library library;
    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    public void setUp() {
        library = new Library();
        student1 = new Student("Mahdi", 1);
        student2 = new Student("Ali", 2);
        student3 = new Student("Hasan", 3);
        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);
    }

    @Test
    public void testSearchStudentsById() {
        ArrayList<Object> ids = new ArrayList<>();
        ids.add(1);
        List<Student> result = library.searchStudents(SearchByType.ID, ids);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(student1));
    }

    @Test
    public void testSearchStudentsByName() {
        ArrayList<Object> names = new ArrayList<>();
        names.add("Ali");
        names.add("Hasan");
        List<Student> result = library.searchStudents(SearchByType.NAME, names);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(student2));
        assertTrue(result.contains(student3));
    }

    @Test
    public void testSearchStudentsByNonExistentId() {
        ArrayList<Object> ids = new ArrayList<>();
        ids.add(50);
        List<Student> result = library.searchStudents(SearchByType.ID, ids);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchStudentsByNonExistentName() {
        ArrayList<Object> names = new ArrayList<>();
        names.add("Mohammad");
        List<Student> result = library.searchStudents(SearchByType.NAME, names);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchStudentsByInvalidTypeTitle() {
        ArrayList<Object> titles = new ArrayList<>();
        titles.add("Some Title");
        List<Student> result = library.searchStudents(SearchByType.TITLE, titles);
        assertNull(result, "The method should return null for TITLE search type.");
    }

    @Test
    public void testSearchStudentsByInvalidTypeAuthor() {
        ArrayList<Object> authors = new ArrayList<>();
        authors.add("Some Author");
        List<Student> result = library.searchStudents(SearchByType.AUTHOR, authors);
        assertNull(result, "The method should return null for AUTHOR search type.");
    }


}
