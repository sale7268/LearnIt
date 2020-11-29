package edu.csumb.educationalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    TextView tvName, tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Once user is logged in options are displayed along with username and email

        ParseUser currentUser = ParseUser.getCurrentUser();

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        if(currentUser != null){
            tvName.setText(currentUser.getString("name"));
            tvEmail.setText(currentUser.getEmail());
        }
    }

    public void createPost(View view){
        Intent intent = new Intent(HomeActivity.this,CreatePostActivity.class);
        startActivity(intent);
    }

    public void readPosts(View view){
        Intent intent = new Intent(HomeActivity.this,ReadPostsActivity.class);
        startActivity(intent);
    }
    //function to logout
    public void logout(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.show();
        ParseUser.logOut();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        progress.dismiss();
    }
}