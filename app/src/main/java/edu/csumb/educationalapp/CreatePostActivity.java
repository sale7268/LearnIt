package edu.csumb.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class CreatePostActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;
    Button submitButton;
    Button addStepButton;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String>steps = new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        submitButton = findViewById(R.id.submitBtn);
        addStepButton = findViewById(R.id.addStepBtn);
        listView = (ListView)findViewById(R.id.listView);

        addStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,steps);

        listView.setAdapter(arrayAdapter);

        setUpListViewListener();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty((etTitle.getText()))) {
                    etTitle.setError("Title is required");
                } else if(TextUtils.isEmpty((etContent.getText()))) {
                    etContent.setError("Content is required");
                }else{
                    //saving new Post to the database
                    ParseObject myPost = new ParseObject("Post");

                    myPost.put("title", etTitle.getText().toString().trim());
                    myPost.put("content", etContent.getText().toString().trim());
                    myPost.put("createdBy",ParseUser.getCurrentUser().getUsername());
                    myPost.addAll("steps",steps);

                    myPost.saveInBackground();

                    Toast.makeText(CreatePostActivity.this,"Post was submitted!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreatePostActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void addItem(View view){
        EditText input = findViewById(R.id.etStep);
        String itemText = input.getText().toString();

        if(!itemText.equals("")){
            arrayAdapter.add(itemText);
            //stepsList.add(itemText);
            input.setText("");
        }
        else{
            Toast.makeText(this, "Please Enter Text.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUpListViewListener(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item has been removed.", Toast.LENGTH_SHORT).show();
                steps.remove(i);
                //stepsList.remove(i);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

    }
}