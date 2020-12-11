package edu.csumb.educationalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ReadCommentsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Comment> commentsList = new ArrayList<>();
    String objectID;
    Button addCommentBtn;
    Button returnBtn, btnDeletePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comments);

        objectID = HomePageActivity.objectID;
        addCommentBtn = findViewById(R.id.addCommentBtn);
        returnBtn = findViewById(R.id.return_button);

        ParseQuery<ParseObject> commentQuery = ParseQuery.getQuery("Comment");
        commentQuery.whereEqualTo("postID",objectID);

        try {
            List<ParseObject> results = commentQuery.find();
            for (ParseObject result : results) {
                String content = result.getString("content");
                String createdBy = result.getString("createdBy");

                Comment commentObj = new Comment(createdBy,content,objectID);
                commentsList.add(commentObj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listView=(ListView)findViewById(R.id.listview);

        if(commentsList.isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(ReadCommentsActivity.this);

            String msg = "There are currently no comments to display.";
            builder.setTitle(msg);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,commentsList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,long l) {
            }
        });

        addCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadCommentsActivity.this,CommentActivity.class);
                startActivity(intent);
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadCommentsActivity.this,HomePageActivity.class);
                startActivity(intent);
            }
        });

        btnDeletePost = findViewById(R.id.btnDeletePost);
        btnDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadCommentsActivity.this, DeletePostConfirmActivity.class);
                startActivity(intent);
            }
        });
    }
}