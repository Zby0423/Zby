import java.io.*;
import java.util.*;

class Book implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private int stock;
    public Book(String isbn, String title, String author, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }
    public boolean borrowBook() {
        if (stock > 0) {
            stock--;
            return true;
        }
        return false;
    }
    public void returnBook() {
        stock++;
    }
    public String getIsbn() {
        return isbn;
    }
    public int getStock() {
        return stock;
    }
    @Override
    public String toString() {
        return String.format("ISBN：%s，书名：%s，作者：%s，库存：%d",
                isbn, title, author, stock);
    }
}

class BookManager {
    private List<Book> books = new ArrayList<>();
    private static final String FILE_PATH = "books.dat";
    @SuppressWarnings("unchecked")
    public void loadBooksFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                books = (List<Book>) ois.readObject();
                System.out.println("成功从文件加载图书信息，共" + books.size() + "本");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("加载图书失败：" + e.getMessage());
            }
        } else {
            System.out.println("未找到图书文件，将创建新文件");
        }
    }

    public void saveBooksToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(books);
            System.out.println("图书信息已保存到文件");
        } catch (IOException e) {
            System.out.println("保存图书失败：" + e.getMessage());
        }
    }

    public void addBook(Book book) {
        boolean isDuplicate = books.stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()));
        if (isDuplicate) {
            System.out.println("ISBN已存在，添加失败");
            return;
        }
        books.add(book);
        System.out.println("图书添加成功：" + book);
    }
    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("暂无图书信息");
            return;
        }
        books.sort(Comparator.comparing(Book::getIsbn));
        System.out.println("\n=== 所有图书信息（按ISBN升序）===");
        books.forEach(System.out::println);
    }
    public void borrowBook(String isbn) {
        Optional<Book> bookOpt = books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (book.borrowBook()) {
                System.out.println("借书成功：" + book);
            } else {
                System.out.println("借书失败：库存不足");
            }
        } else {
            System.out.println("借书失败：未找到该ISBN的图书");
        }
    }
    public void returnBook(String isbn) {
        Optional<Book> bookOpt = books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst();
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.returnBook();
            System.out.println("还书成功：" + book);
        } else {
            System.out.println("还书失败：未找到该ISBN的图书");
        }
    }
}

public class Z31 {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.loadBooksFromFile();
        manager.addBook(new Book("9787111641247", "Java编程思想", "Bruce Eckel", 5));
        manager.addBook(new Book("9787115546081", "Spring Boot实战", "Craig Walls", 3));
        manager.showAllBooks();
        manager.borrowBook("9787111641247");
        manager.borrowBook("9787111641247");
        manager.borrowBook("9787111641247");
        manager.borrowBook("9787111641247");
        manager.borrowBook("9787111641247");
        manager.borrowBook("9787111641247");
        manager.showAllBooks();
        manager.returnBook("9787111641247");
        manager.showAllBooks();
        manager.saveBooksToFile();
    }
}