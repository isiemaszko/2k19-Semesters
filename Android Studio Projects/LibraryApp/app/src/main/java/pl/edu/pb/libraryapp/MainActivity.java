package pl.edu.pb.libraryapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class MainActivity extends AppCompatActivity {

    private ViewModel bookViewModel;
    private Book ebook;

    public static final int NEW_BOOK_ACTIVITY_REQUEST_CODE=1;
    public static final int EDIT_BOOK_ACTIVITY_REQUEST_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        final BookAdapter adapter=new BookAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookViewModel= ViewModelProviders.of(this).get(ViewModel.class);
        bookViewModel.findAll().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                adapter.setBooks(books);
            }
        });
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton addBookButton = findViewById(R.id.add_button);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EditBookActivity.class);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivityForResult(intent,NEW_BOOK_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==NEW_BOOK_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){
            Book book=new Book(data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_TITLE),
                    data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_AUTHOR));
            bookViewModel.insert(book);
            Snackbar.make(findViewById(R.id.coordinator_layout),getString(R.string.book_added),
                    Snackbar.LENGTH_LONG).show();
        }else if(requestCode==EDIT_BOOK_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK) {
            ebook.setTitle(data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_TITLE));
            ebook.setAuthor(data.getStringExtra(EditBookActivity.EXTRA_EDIT_BOOK_AUTHOR));
            bookViewModel.update(ebook);
        }
        else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG
                ).show();
            }
        }




    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener,  View.OnLongClickListener {
        private TextView bookTitleTextView;
        private TextView bookAuthorTextView;
        private Book book;

        public BookHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.book_list_item,parent,false));
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            bookAuthorTextView=itemView.findViewById(R.id.book_author);
            bookTitleTextView=itemView.findViewById(R.id.book_title);

        }

        public void bind(Book book){
            this.book=book;
            bookTitleTextView.setText(boo.getTitle());
            bookAuthorTextView.setText(book.getAuthor());
        }

        @Override
        public void onClick(View v) {
            ebook=book;
            Intent intent=new Intent(MainActivity.this,EditBookActivity.class);
            intent.putExtra(EditBookActivity.EXTRA_EDIT_BOOK_TITLE, book.getTitle());
            intent.putExtra(EditBookActivity.EXTRA_EDIT_BOOK_AUTHOR, book.getAuthor());

            startActivityForResult(intent,EDIT_BOOK_ACTIVITY_REQUEST_CODE);
        }

        @Override
        public boolean onLongClick(View v){
            bookViewModel.delete(book);
            return true;
        }
    }

    private class BookAdapter extends RecyclerView.Adapter<BookHolder>{

        private List<Book> books;


        @Nullable
        @Override
        public BookHolder onCreateViewHolder(@Nullable ViewGroup parent, int viewType){
            return new BookHolder(getLayoutInflater(),parent);
        }

        @Override
        public void onBindViewHolder(@Nullable BookHolder holder,int position){
            if(books!=null){
                Book book=books.get(position);
                holder.bind(book);
            }else {
                Log.d("MainActivity", "No books");
            }
        }

        @Override
        public int getItemCount() {
            if(books!=null){
                return  books.size();
            }else {
                return 0;
            }
        }

        void setBooks(List<Book> books){
            this.books=books;
            notifyDataSetChanged();
        }

    }

}
