package edu.csumb.educationalapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.parse.ParseUser;

public class UserUpdateDialog extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextEmailAddress;
    private UserUpdateDialogListener listener;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        final ParseUser currentUser = ParseUser.getCurrentUser();

        editTextName = view.findViewById(R.id.etuserName);
        editTextEmailAddress = view.findViewById(R.id.etemailAddress);

        if(currentUser != null){
            editTextName.setHint(currentUser.getString("name"));
            editTextEmailAddress.setHint(currentUser.getEmail());
        }

        builder.setView(view)
                .setTitle("Information Update")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String username;
                        String emailAddress;

                        if(TextUtils.isEmpty(editTextName.getText())){
                            username = currentUser.getString("name");
                        }else{
                            username = editTextName.getText().toString();
                        }

                        if(TextUtils.isEmpty(editTextEmailAddress.getText())){
                            emailAddress = currentUser.getEmail();
                        }else{
                            emailAddress = editTextEmailAddress.getText().toString();
                        }
                        listener.Update(username, emailAddress);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (UserUpdateDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
            "must implement UserUpdateDialogListener");
        }
    }

    public interface UserUpdateDialogListener{
        void Update(String username, String emailaddress);
    }
}
