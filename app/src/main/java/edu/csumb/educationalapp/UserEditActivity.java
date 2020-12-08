package edu.csumb.educationalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class UserEditActivity extends AppCompatActivity implements UserUpdateDialog.UserUpdateDialogListener {
    private TextView name;
    private TextView emailAddress;
    private Button editUser;
    private ParseUser currentUser;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        name = findViewById(R.id.tvuserName);
        emailAddress = findViewById(R.id.tvemailAddress);
        editUser = findViewById(R.id.btneditUser);
        logout = findViewById(R.id.btnlogout);

        currentUser = ParseUser.getCurrentUser();

        if(currentUser != null){
            name.setText(currentUser.getString("name"));
            emailAddress.setText(currentUser.getEmail());
        }

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public  void openDialog(){
        UserUpdateDialog userUpdateDialog = new UserUpdateDialog();
        userUpdateDialog.show(getSupportFragmentManager(), "user update dialog");
    }

    @Override
    public void Update(String username, String emailaddress) {
        currentUser.put("name", username.trim());
        currentUser.setEmail(emailaddress.trim());
        currentUser.saveInBackground();
        Toast.makeText(UserEditActivity.this, "Successful Updated!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UserEditActivity.this, HomePageActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("Loading...");
        progress.show();
        ParseUser.logOut();
        Intent intent = new Intent(UserEditActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        progress.dismiss();
    }
}