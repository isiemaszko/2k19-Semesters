package pl.edu.pb.libraryapp;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private BookRepository bookRepository;
    private LiveData<List<Book>> books;

    public ViewModel(@Nullable Application application){
        super(application);
        bookRepository=new BookRepository(application);
        books=bookRepository.findAllBooks();
    }

    public LiveData<List<Book>> findAll(){
        return books;
    }

    public void insert(Book book){
        bookRepository.insert(book);
    }

    public void update(Book book){
        bookRepository.update(book);
    }

    public void delete(Book book){
        bookRepository.delete(book);
    }

}
