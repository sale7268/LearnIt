package edu.csumb.educationalapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
    }

    public void DeletePost(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.getInBackground(objectID, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground();
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Comment");
                    query.getInBackground(objectID, new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.deleteInBackground();
                            } else {
                                Log.d("Post", "Request failed.");
                            }
                        }
                    });
                } else {
                    Log.d("Post", "Request failed.");
                }
            }
        });
    }
}