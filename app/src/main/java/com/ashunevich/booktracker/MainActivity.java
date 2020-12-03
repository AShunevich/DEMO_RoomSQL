package com.ashunevich.booktracker;



import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;


import com.ashunevich.booktracker.databinding.ActivityMainBinding;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainActivityVm> {

    private ActivityMainBinding binding;
    private BookItemRecyclerView bookItemRecyclerView;
    private AlertDialog alertDialog;

    @Inject
    MainActivityVm mainActivityVM;

    @Override
    public int getBindingVariable() {
        return BR.datamodel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityVm getViewModel() {
        return mainActivityVM;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;




    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding= getViewDataBinding();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ButterKnife.bind(this);
        mainActivityVM.mBookRepository.getAllBooks().observe(this,articleModels ->{
            if(articleModels!=null){
                bookItemRecyclerView = new BookItemRecyclerView(this,new ArrayList<>(articleModels),
                        note_id -> mainActivityVM.mBookRepository.deleteBookItme(note_id));
                binding.recyclerView.setAdapter(bookItemRecyclerView);
            }
        });


    }

    @OnClick({R.id.addNewItem})
    public void setViewvClick(View view) {
        if (view.getId() == R.id.addNewItem) {
            additem();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }


    public interface NotesInteractionListener {
         void onNoteDeleted(int note_id);
    }

    private void additem(){
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.newbook_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("New Book");

        Button ok_button=dialogView.findViewById(R.id.applyButton);
        Button cancel_button=dialogView.findViewById(R.id.cancelButton);
        EditText bookTitle=dialogView.findViewById(R.id.bookTitleText);
        EditText bookAuthor=dialogView.findViewById(R.id.bookAuthorText);
        EditText size=dialogView.findViewById(R.id.booksizeText);
        EditText kostyl = dialogView.findViewById(R.id.kostylId);
        Spinner bookType = dialogView.findViewById(R.id.bookTypeSpinner);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.bookstype, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookType.setAdapter(adapter);

        alertDialog = dialogBuilder.create();
        if (alertDialog.getWindow()!=null) {
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        }
            //  thing to get text value of selected spinner item
        String type = String.valueOf(bookType.getSelectedItem());
        kostyl.setText(type);

        ok_button.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(bookTitle.getText()) && !TextUtils.isEmpty(bookAuthor.getText()) &&
                    !TextUtils.isEmpty(size.getText()) && !TextUtils.isEmpty(String.valueOf(size)))
            {
                BookItem bookItem=new BookItem(bookTitle.getText().toString(),bookAuthor.getText().toString(),
                        size.getText().toString(),bookType.getSelectedItem().toString());
                mainActivityVM.mBookRepository.insert(bookItem);
                alertDialog.dismiss();
            }else {
                if (TextUtils.isEmpty(bookAuthor.getText()))
                    bookAuthor.setError("Field can't be empty");
                if (TextUtils.isEmpty(bookTitle.getText()))
                    bookTitle.setError("Field can't be empty");
                if(TextUtils.isEmpty(size.getText()))
                    size.setError("Field can't be empty");
            }
        });
        cancel_button.setOnClickListener(v -> alertDialog.dismiss());
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }
}
