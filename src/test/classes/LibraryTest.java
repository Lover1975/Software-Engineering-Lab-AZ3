package test.classes;

import main.classes.Book;
import main.classes.Library;
import main.classes.SearchByType;
import main.classes.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private Book book;
    private Student student;

    private Book book1, book2, book3, book4, book5;
    private Student student1, student2, student3, student4;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book = new Book("A Tale of Two Cities", "Charles Dickens", 1);
        library.addBook(book);
        student = new Student("Ali", 2);
        library.addStudent(student);

        book1 = new Book("Book-1", "Author-1", 10);
        book2 = new Book("Book-2", "Author-2", 11);
        book3 = new Book("Book-3", "Author-3", 12);
        book4 = new Book("Book-4", "Author-1", 13);
        book5 = new Book("Not-owned-book", "Author-1", 15);

        student1 = new Student("Alice", 10);
        student2 = new Student("Bob", 11);
        student3 = new Student("John", 12);
        student4 = new Student("Not-registered-student", 13);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        library.addStudent(student1);
        library.addStudent(student2);
        library.addStudent(student3);
    }

    @Test
    public void shouldNotLendBookToNonRegisteredStudent() {
        Student not_a_member = new Student("Mahdi", 1);
        assertFalse(library.lendBook(book, not_a_member));
        assertTrue(library.getBooks().contains(book));
    }

    @Test
    public void shouldReturnBookSuccessfully() {
        library.lendBook(book, student);
        assertTrue(library.returnBook(book, student));
        assertTrue(library.getBooks().contains(book)); // Book should be in the library
        assertFalse(student.hasBook(book)); // Student should not have the book anymore
    }

    @Test
    public void shouldNotReturnBookOrStudentIfNull() {
        assertFalse(library.returnBook(null, student));
        assertFalse(library.returnBook(book, null));
    }

    @Test
    public void testLendBookNotRegistered() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertFalse(library.lendBook(book5, student1));
        assertTrue(outContent.toString().contains("!! Book Not-owned-book not registered."));

        System.setOut(System.out);
    }

    @Test
    public void testReturnBookStudentNotRegistered() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertFalse(library.returnBook(book1, student4));
        assertTrue(outContent.toString().contains("!! Student Not-registered-student not registered."));

        System.setOut(System.out);
    }

    @Test
    public void testReturnBookStudentDoesNotHaveBook() {
        library.lendBook(book1, student1);
        library.returnBook(book1, student1);  // return the book

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertFalse(library.returnBook(book1, student1));
        assertTrue(outContent.toString().contains("!! Alice doesn't have the book."));

        System.setOut(System.out);
    }

    @Test
    public void testSearchStudentsInvalidKeyTypeForID() {
        ArrayList<Object> keys = new ArrayList<>(Arrays.asList("invalidKey"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertNull(library.searchStudents(SearchByType.ID, keys));
        assertTrue(outContent.toString().contains("Invalid key type for ID search. Keys must be Integers."));

        System.setOut(System.out);
    }

    @Test
    public void testSearchStudentsInvalidKeyTypeForName() {
        ArrayList<Object> keys = new ArrayList<>(Arrays.asList(123));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNull(library.searchStudents(SearchByType.NAME, keys));
        assertTrue(outContent.toString().contains("Invalid key type for NAME search. Keys must be Strings."));
        System.setOut(System.out);
    }

    @Test
    public void testDisplayBooks() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        library.displayBooks();
        String expectedOutput = "Available books in library:" + System.lineSeparator() +
                "A Tale of Two Cities by Charles Dickens" + System.lineSeparator() +
                "Book-1 by Author-1" + System.lineSeparator() +
                "Book-2 by Author-2" + System.lineSeparator() +
                "Book-3 by Author-3" + System.lineSeparator() +
                "Book-4 by Author-1" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testDisplayStudents() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        library.displayStudents();
        String expectedOutput = "Registered students:" + System.lineSeparator() +
                "Ali|2" + System.lineSeparator() +
                "Alice|10" + System.lineSeparator() +
                "Bob|11" + System.lineSeparator() +
                "John|12" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }


}
