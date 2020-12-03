package com.ashunevich.booktracker;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ashunevich.booktracker.databinding.RecyclerItemBinding;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class BookItemRecyclerView extends RecyclerView.Adapter<BookItemRecyclerView.MyViewHolder> {


    private ArrayList<BookItem> book_list;

    private Context context;
    View view;
    MyViewHolder holder;
    MainActivity.NotesInteractionListener notesInteractionListener;

    public BookItemRecyclerView(Context context,ArrayList<BookItem> data,MainActivity.NotesInteractionListener notesInteractionListener){
        this.context=context;
        this.book_list = data;
        this.notesInteractionListener=notesInteractionListener;
    }
    //This method inflates view present in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        holder = new MyViewHolder(view);
        return holder;
    }

    //Binding the data using get() method of POJO object

    @Override
    public void onBindViewHolder(final BookItemRecyclerView.MyViewHolder holder, int position) {
        final BookItem bookItem = book_list.get(position);
        holder.bind(bookItem);
    }



    //Setting the arraylist
    public void setListContent(ArrayList <BookItem> pad_list) {
        this.book_list = pad_list;
    }

    @Override
    public int getItemCount() {
        return book_list.size();
    }

    //View holder class, where all view components are defined
    class MyViewHolder extends RecyclerView.ViewHolder {
        private RecyclerItemBinding binding;

        public MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void bind (BookItem bookItem){
            binding.setItemBook(bookItem);
            binding.trashBook.setOnClickListener(v -> notesInteractionListener.onNoteDeleted(bookItem.getIdNumber()));
          
        }
    }



}
