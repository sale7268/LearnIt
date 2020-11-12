package edu.csumb.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class ReadPostsActivity extends AppCompatActivity {

    ArrayList<Post> postsList = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_posts);

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Post");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject obj : objects) {
                        String title = obj.getString("title");
                        String content = obj.getString("content");
                        String createdBy = obj.getString("createdBy");

                        Post post = new Post(title,content,createdBy);
                        postsList.add(post);
                    }
                } else {
                    Toast.makeText(ReadPostsActivity.this,"There was an error",Toast.LENGTH_LONG).show();
                }
            }
        });

        listView=(ListView)findViewById(R.id.listview);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, postsList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,long l) {
                Toast.makeText(ReadPostsActivity.this, "clicked item:" + i + " " + postsList.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

