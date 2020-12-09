package edu.csumb.educationalapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import static edu.csumb.educationalapp.HomePageActivity.objectID;

public class DeletePostConfirmActivity extends AppCompatActivity {

    private Button btnConfirm, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post_confirm);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeletePost();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(DeletePostConfirmActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DeletePostConfirmActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void DeletePost(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.getInBackground(objectID, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground();
                    ParseQuery<ParseObject> commentQuery = ParseQuery.getQuery("Comment");
                    commentQuery.whereEqualTo("postID",objectID);
                    try {
                        List<ParseObject> results = commentQuery.find();
                        for (ParseObject result : results) {
                            result.deleteInBackground();
                        }
                    } catch (ParseException f) {
                        f.printStackTrace();
                    }

                    Toast.makeText(DeletePostConfirmActivity.this, "Post Deleted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(DeletePostConfirmActivity.this, HomePageActivity.class);
                    startActivity(intent);

                } else {
                    Log.d("Post", "Request failed.");
                }
            }
        });
    }
}