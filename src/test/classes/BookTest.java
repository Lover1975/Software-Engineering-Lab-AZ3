package test.classes;

import main.classes.Library;
import main.classes.Book;
import main.classes.SearchByType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;

    @BeforeEach
    public void setUp() {
        library = new Library();
        book1 = new Book("The Little Prince", "Antoine de Saint-Exupery", 1);
        book2 = new Book("The Alchemist", "Paulo Coelho", 2);
        book3 = new Book("And Then There Were None", "Agatha Christie", 3);
        book4 = new Book("The Da Vinci Code", "Dan Brown", 4);
        book5 = new Book("And Then There Were None", "Agatha Not Christie", 5);
        book6 = new Book("Not The Da Vinci Code", "Dan Brown", 6);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);
    }

    @Test
    public void testSearchBooksById() {
        ArrayList<Object> ids = new ArrayList<>();
        ids.add(1);
        ArrayList<Book> result = library.searchBooks(SearchByType.ID, ids);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(book1));
    }

    @Test
    public void testSearchBooksByTitle() {
        ArrayList<Object> titles = new ArrayList<>();
        titles.add("The Alchemist");
        titles.add("And Then There Were None");
        ArrayList<Book> result = library.searchBooks(SearchByType.TITLE, titles);
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(book2));
        assertTrue(result.contains(book3));
    }

    @Test
    public void testSearchBooksByAuthor() {
        ArrayList<Object> authors = new ArrayList<>();
        authors.add("Agatha Christie");
        ArrayList<Book> result = library.searchBooks(SearchByType.AUTHOR, authors);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(book3));
    }

    @Test
    public void testSearchBooksByNonExistentId() {
        ArrayList<Object> ids = new ArrayList<>();
        ids.add(100);
        ArrayList<Book> result = library.searchBooks(SearchByType.ID, ids);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchBooksByNonExistentTitle() {
        ArrayList<Object> titles = new ArrayList<>();
        titles.add("Non Existent Title");
        ArrayList<Book> result = library.searchBooks(SearchByType.TITLE, titles);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSearchBooksByInvalidType() {
        ArrayList<Object> names = new ArrayList<>();
        names.add("Some Name");
        ArrayList<Book> result = library.searchBooks(SearchByType.NAME, names);
        assertNull(result, "The method should return null for NAME search type.");
    }

    @Test
    public void testSearchBooksByTitleWithMultipleResults() {
        ArrayList<Object> titles = new ArrayList<>();
        titles.add("And Then There Were None");
        ArrayList<Book> result = library.searchBooks(SearchByType.TITLE, titles);
        assertNotNull(result);
        assertEquals(2, result.size()); // Expecting two books with the title "Advanced Java"
        assertTrue(result.contains(book3));
        assertTrue(result.contains(book5));
    }

    @Test
    public void testSearchBooksByAuthorWithMultipleResults() {
        ArrayList<Object> authors = new ArrayList<>();
        authors.add("Dan Brown");
        ArrayList<Book> result = library.searchBooks(SearchByType.AUTHOR, authors);
        assertNotNull(result);
        assertEquals(2, result.size()); // Expecting two books by "John Doe"
        assertTrue(result.contains(book4));
        assertTrue(result.contains(book6));
    }
}

