package edu.csumb.educationalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ReadPostsActivity extends AppCompatActivity {

    ArrayList<Post> postsList = new ArrayList<>();
    ListView listView;
    static String objectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_posts);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        query.setLimit(10); // limit to at most 10 results

        try {
            List<ParseObject> results = query.find();
            for (ParseObject result : results) {
                String title = result.getString("title");
                String content = result.getString("content");
                String createdBy = result.getString("createdBy");
                String objectId = result.getObjectId();

                Post post = new Post(objectId,title,content,createdBy);
                postsList.add(post);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        listView=(ListView)findViewById(R.id.listview);

        if(postsList.isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(ReadPostsActivity.this);

            String msg = "There are currently no posts to display.";
            builder.setTitle(msg);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, postsList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,long l) {
                //saving the post that was clicked's ID to be used in the next activity
                objectID = postsList.get(i).getObjectId();
                //Toast.makeText(ReadPostsActivity.this, "clicked item:" + i + " " + postsList.get(i).toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ReadPostsActivity.this, ReadCommentsActivity.class);
                startActivity(intent);
            }
        });
    }
}

