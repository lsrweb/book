import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BookManager {
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("图书添加成功！");
    }

    public void deleteBook(String id) {
        Book book = findBookById(id);
        if (book != null) {
            books.remove(book);
            System.out.println("图书删除成功！");
        } else {
            System.out.println("未找到指定ID的图书！");
        }
    }

    public void updateBook(Book updatedBook) {
        Book book = findBookById(updatedBook.getId());
        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
            System.out.println("图书更新成功！");
        } else {
            System.out.println("未找到指定ID的图书！");
        }
    }

    public void listBooks() {
        loadBooksFromFile("books.csv");
        if (books.isEmpty()) {
            System.out.println("没有图书信息！");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void saveBooksToFile(String filename) {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)) {
            for (Book book : books) {
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getPrice() + "\n");
            }
            System.out.println("图书数据保存成功！");
        } catch (IOException e) {
            System.out.println("保存图书数据时发生错误：" + e.getMessage());
        }
    }

    public void loadBooksFromFile(String filename) {
        books.clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    double price = Double.parseDouble(parts[3]);  
                    books.add(new Book(id, title, author, price));
                }
            }
            System.out.println("图书数据加载成功！");
        } catch (IOException e) {
            System.out.println("加载图书数据时发生错误：" + e.getMessage());
        }
    }

    private Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
}
