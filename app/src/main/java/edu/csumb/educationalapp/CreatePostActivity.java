package edu.csumb.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class CreatePostActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
    }

    void submitPost(View view){
        if(TextUtils.isEmpty((etTitle.getText()))) {
            etTitle.setError("Title is required");
        } else if(TextUtils.isEmpty((etContent.getText()))) {
            etContent.setError("Content is required");
        }else{
            ParseObject myPost = new ParseObject("Post");
            myPost.put("title",etTitle.getText().toString().trim());
            myPost.put("content",etContent.getText().toString().trim());

            myPost.saveInBackground();

            Toast.makeText(CreatePostActivity.this, "Post was submitted!", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(CreatePostActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}