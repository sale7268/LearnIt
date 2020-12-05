package edu.csumb.educationalapp;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ReadCommentsActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Comment> commentsList = new ArrayList<>();
    //List<String> comments;
    String objectID;
    ParseObject post = new ParseObject("Post");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comments);

        objectID = ReadPostsActivity.objectID;

        ParseQuery<ParseObject> postQuery = ParseQuery.getQuery("Post");
        //postQuery.whereEqualTo("objectId",objectID);

        // Retrieve the object by id
        postQuery.getInBackground(objectID, new GetCallback<ParseObject>() {
            public void done(ParseObject entity, ParseException e) {
                if (e == null) {
                   post = entity;
                }
            }
        });

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Comment");
        query.whereEqualTo("post",post);

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {
                if (e == null) {
                    for(ParseObject comment : commentList){
                        String content = comment.getString("content");
                        String commentID = comment.getObjectId();
                        Comment commentObj = new Comment(commentID,content);
                        commentsList.add(commentObj);
                    }
                } else {
                    Log.d("comment", "Error: " + e.getMessage());
                }
            }
        });

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
                //saving the post that was clicked's ID to be used in the next activity
                Toast.makeText(ReadCommentsActivity.this, "clicked item:" + i + " " + commentsList.get(i).toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
}