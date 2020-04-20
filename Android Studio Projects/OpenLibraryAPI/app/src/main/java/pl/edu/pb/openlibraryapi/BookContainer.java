package pl.edu.pb.openlibraryapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Getter
@Setter
public class BookContainer {

    @SerializedName("docx")
    private List<Book> bookList;

    public List<Book> getBookList(){
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

}
