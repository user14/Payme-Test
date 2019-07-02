package com.pooja.payme_test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pooja.payme_test.R;
import com.pooja.payme_test.model.Book;

import java.util.List;

/*
    BookAdapter class to display list of books on recyclerview
 */
public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Book> books;
    public BooksAdapter(List<Book> books)
    {
        this.books = books;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_style_one, parent, false);

        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_style_one, parent, false);
                return new ViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_style_two, parent, false);
                return new ViewHolder(view);

        }
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ViewHolder viewHolder = (ViewHolder)holder;
                viewHolder.bindData(books.get(position));
                break;

            case 2:
                ViewHolder viewHolder2 = (ViewHolder)holder;
                viewHolder2.bindData(books.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 * 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView imageViewBook;
        private TextView textViewTitle;
        private TextView textViewAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewBook = (ImageView)itemView.findViewById(R.id.book_image);
            textViewTitle = (TextView)itemView.findViewById(R.id.book_title);
            textViewAuthor = (TextView)itemView.findViewById(R.id.book_author);
        }

        public void bindData(Book book)
        {
            textViewTitle.setText(book.getTitle());
            textViewAuthor.setText(book.getAuthor());

            // update image icon using Glide lib
            Glide.with(itemView)
                    .load(book.getImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.payme))
                    .into(imageViewBook);


        }
    }


}
