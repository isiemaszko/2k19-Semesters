package pl.edu.pb.libraryapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Getter
@Setter
@Entity (tableName = "book")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public Book(String title, String author) {
        this.title=title;
        this.author=author;
    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
