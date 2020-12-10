package edu.csumb.educationalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    ArrayList<Post> postsList = new ArrayList<>();
    ListView listView;
    static String objectID;
    ImageView userEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        try {
            //Retrieve the Post that already has the steps
            List<ParseObject> results = query.find();

            for (ParseObject result : results) {
                String title = result.getString("title");
                String content = result.getString("content");
                String createdBy = result.getString("createdBy");
                String objectId = result.getObjectId();

                ArrayList<String>stepsList = new ArrayList<>();
                JSONArray array = result.getJSONArray("steps");

                if (array != null) {
                    for (int i=0;i<array.length();i++){
                        stepsList.add(array.getString(i));
                    }
                }

                Post post = new Post(objectId,title,content,createdBy,stepsList);
                postsList.add(post);
            }
        } catch (ParseException | JSONException e) {
            e.printStackTrace();
        }

        listView=(ListView)findViewById(R.id.listview);

        if(postsList.isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);

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
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //saving the post that was clicked's ID to be used in the next activity
                objectID = postsList.get(i).getObjectId();

                Intent intent = new Intent(HomePageActivity.this, ReadCommentsActivity.class);
                startActivity(intent);
            }
        });

        userEdit = findViewById(R.id.iv_userEdit);

        userEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, UserEditActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createPost(View view){
        Intent intent = new Intent(HomePageActivity.this,CreatePostActivity.class);
        startActivity(intent);
    }


}