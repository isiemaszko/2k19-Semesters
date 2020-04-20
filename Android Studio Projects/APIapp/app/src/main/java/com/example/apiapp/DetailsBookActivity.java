package com.example.apiapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsBookActivity extends AppCompatActivity {

    public static final String DETAILS_BOOK_COVER = "pb.edu.pl.DETAILS_BOOK_COVER";
    public static final String DETAILS_BOOK_TITLE = "pb.edu.pl.DETAILS_BOOK_TITLE";
    public static final String DETAILS_BOOK_AUTHOR = "pb.edu.pl.DETAILS_BOOK_AUTHOR";

    private ImageView coverImageView;
    private TextView titleTextView;
    private TextView authorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_details_book);

        coverImageView = findViewById(R.id.img_cover_details);
        titleTextView = findViewById(R.id.book_title_details);
        authorTextView = findViewById(R.id.book_author_details);

        titleTextView.setText(getIntent().getSerializableExtra(DETAILS_BOOK_TITLE).toString());
        authorTextView.setText(getIntent().getSerializableExtra(DETAILS_BOOK_AUTHOR).toString());

        if (getIntent().getSerializableExtra(DETAILS_BOOK_COVER).toString() != null) {
            Picasso.with(coverImageView.getContext())
                    .load(getIntent().getSerializableExtra(DETAILS_BOOK_COVER).toString())
                    .placeholder(R.drawable.ic_book_black_24dp).into(coverImageView);
        } else {
            coverImageView.setImageResource(R.drawable.ic_book_black_24dp);
        }
    }

}
