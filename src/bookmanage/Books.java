package bookmanage;

import java.io.Serializable;

public class Books implements Serializable {
    private String codeBook;
    private String nameBook;
    private String author;
    private String category;
    private String publisher;
    private int year;

    public Books() {
    }

    public Books(String codeBook, String nameBook, String author, String category, String publisher, int year) {
        this.codeBook = codeBook;
        this.nameBook = nameBook;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.year = year;
    }

    public String getCodeBook() {
        return codeBook;
    }

    public void setCodeBook(String codeBook) {
        this.codeBook = codeBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
