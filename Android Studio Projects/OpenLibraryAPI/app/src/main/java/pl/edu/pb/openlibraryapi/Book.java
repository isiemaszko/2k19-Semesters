package pl.edu.pb.openlibraryapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Getter
@Setter
public class Book {
    @SerializedName("title")
    private String title;
    @SerializedName("author_name")
    private List<String> authors;
    @SerializedName("cover_i")
    private String cover;

    public String getTitle(){
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor(){
        return authors;
    }
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }


    public String getCover(){
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

}
