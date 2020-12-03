package edu.csumb.educationalapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapterActivity postAdapter;
    private List<Post> postLists;

    private List<String> followingList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_page, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        postLists = new ArrayList<>();
        postAdapter = new ListAdapterActivity(getContext(), postLists);
        recyclerView.setAdapter(postAdapter);

        readPosts();

        return view;
    }

    private void readPosts() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");

        try {
            List<ParseObject> results = query.find();
            for (ParseObject result : results) {

                String title = result.getString("title");
                String content = result.getString("content");
                String createdBy = result.getString("createdBy");
                String objectId = result.getObjectId();

                Post post = new Post(objectId,title,content,createdBy);
                postLists.add(post);

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        postAdapter.notifyDataSetChanged();

    }


}