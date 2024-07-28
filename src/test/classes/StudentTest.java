package test.classes;

import main.classes.Library;
import main.classes.Book;
import main.classes.SearchByType;
import main.classes.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class StudentTest {

    private Library library;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        library = new Library();
        student1 = new Student("Mahdi", 1);
        student2 = new Student("Ali", 2);
        student3 = new Student("Hasan", 3);
        student4 = new Student("Mohsen", 4);
        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);
        library.addStudent(student4);
        book1 = new Book("The Alchemist", "Paulo Coelho", 2);
        book2 = new Book("The Da Vinci Code", "Dan Brown", 3);
        student4.addBook(book1);
        student4.addBook(book2);
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

    @Test
    public void testDisplayBooks() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        student4.displayBooks();
        String expectedOutput = "Mohsen|4 has these books:" + System.lineSeparator() +
                "The Alchemist by Paulo Coelho" + System.lineSeparator() +
                "The Da Vinci Code by Dan Brown" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testToString() {
        assertEquals("Mohsen|4", student4.toString());
    }


}
