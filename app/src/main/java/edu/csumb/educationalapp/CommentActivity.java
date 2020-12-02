package edu.csumb.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText newComment;
    Button CommentButton;
    ListView commentList;
    String objectID;
    ParseObject post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        Resource = findViewById(R.id.tvResource);
        CommentButton = findViewById(R.id.btnComment);
        commentList = findViewById(R.id.commentList);
        newComment = findViewById(R.id.etNewComment);

        objectID = ReadPostsActivity.objectID;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        query.whereEqualTo("objectId", objectID);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (object == null) {
                    Log.d("Post", "Request failed.");
                } else {
                    String title = object.getString("title");
                    String content = object.getString("content");
                    String createdBy = object.getString("createdBy");

                    post = object;

                    tvTitle.setText(title);
                    tvContent.setText(content);
                    Resource.setText("Created by " + createdBy);
                }
            }
        });

        CommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newCommentString = newComment.getText().toString();
                if(newCommentString.equals("")){
                    Toast.makeText(CommentActivity.this, "Empty Comment", Toast.LENGTH_SHORT).show();
                }else {

                    ParseObject myComment = new ParseObject("Comment");
                    myComment.put("content",newCommentString);

                    myComment.put("post", post);

                    myComment.saveInBackground();

                    Toast.makeText(CommentActivity.this, "Comment Submitted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CommentActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}