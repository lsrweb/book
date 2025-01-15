import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) {
        BookManager bookManager = new BookManager();
        bookManager.loadBooksFromFile("books.csv");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("欢迎使用图书管理系统");
            System.out.println("1. 添加图书");
            System.out.println("2. 删除图书");
            System.out.println("3. 修改图书");
            System.out.println("4. 查询图书");
            System.out.println("5. 退出系统");
            System.out.print("请选择操作："); 

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("请输入图书ID：");
                    String id = scanner.nextLine();
                    System.out.print("请输入图书名称：");
                    String title = scanner.nextLine();
                    System.out.print("请输入图书作者：");
                    String author = scanner.nextLine();
                    System.out.print("请输入图书价格：");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    Book book = new Book(id, title, author, price);
                    bookManager.addBook(book);
                    break;
                case 2:
                    System.out.print("请输入要删除的图书ID：");
                    String deleteId = scanner.nextLine();
                    bookManager.deleteBook(deleteId);
                    break;
                case 3:
                    System.out.print("请输入要修改的图书ID：");
                    String updateId = scanner.nextLine();
                    System.out.print("请输入新的图书名称："); 
                    String newTitle = scanner.nextLine();
                    System.out.print("请输入新的图书作者：");
                    String newAuthor = scanner.nextLine();
                    System.out.print("请输入新的图书价格：");
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    Book updatedBook = new Book(updateId, newTitle, newAuthor, newPrice);
                    bookManager.updateBook(updatedBook);
                    break;
                case 4:
                    bookManager.listBooks();
                    break;
                case 5:
                    System.out.println("退出系统");
                    bookManager.saveBooksToFile("books.csv");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新选择！");
            }
        }
    }
}
