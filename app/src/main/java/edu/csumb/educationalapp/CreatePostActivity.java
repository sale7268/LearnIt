package edu.csumb.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreatePostActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        submitButton = findViewById(R.id.submitBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty((etTitle.getText()))) {
                    etTitle.setError("Title is required");
                } else if(TextUtils.isEmpty((etContent.getText()))) {
                    etContent.setError("Content is required");
                }else{
                    ParseObject myPost = new ParseObject("Post");

                    myPost.put("title", etTitle.getText().toString().trim());
                    myPost.put("content", etContent.getText().toString().trim());
                    myPost.put("createdBy",ParseUser.getCurrentUser().getUsername());
                    myPost.saveInBackground();
                    Toast.makeText(CreatePostActivity.this,"Post was submitted!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreatePostActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}