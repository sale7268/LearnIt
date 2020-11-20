package edu.csumb.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class CommentActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvContent;
    TextView Resource;
    Button Comment;
    ListView commentList;
    String objectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        Resource = findViewById(R.id.tvResource);
        Comment = findViewById(R.id.btnComment);
        commentList = findViewById(R.id.commentList);

        objectID = ReadPostsActivity.objectID;

        ParseQuery<ParseObject> query = ParseQuery.getQuery(objectID);
        // Retrieve the object by id
        query.getInBackground(objectID, new GetCallback<ParseObject>() {
            public void done(ParseObject entity, ParseException e) {
                if (e == null) {
                    tvTitle.setText(entity.getString("title"));
                    tvContent.setText(entity.getString("content"));
                    Resource.setText(entity.getString("createdBy"));
                    // Update the fields we want to
//                    entity.put("myCustomKey1Name", "My new value");
//                    entity.put("myCustomKey2Name", 999);
                    // All other fields will remain the same
//                    entity.saveInBackground();
                }
            }
        });
    }
}