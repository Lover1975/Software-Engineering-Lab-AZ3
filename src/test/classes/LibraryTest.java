package test.classes;

import main.classes.Book;
import main.classes.Library;
import main.classes.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private Book book;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book = new Book("A Tale of Two Cities", "Charles Dickens", 1);
        library.addBook(book);
    }

    @Test
    public void shouldNotLendBookToNonRegisteredStudent() {
        Student not_a_member = new Student("Mahdi", 1);
        assertFalse(library.lendBook(book, not_a_member));
        assertTrue(library.getBooks().contains(book));
    }
}
