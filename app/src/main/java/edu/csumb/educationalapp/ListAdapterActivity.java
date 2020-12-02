package edu.csumb.educationalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

import java.util.List;

class ListAdapterActivity extends RecyclerView.Adapter<ListAdapterActivity.ViewHolder> {

    public Context mContext;
    public List<Post> mPost; //a list that uses the post class

    ParseUser currentUser = ParseUser.getCurrentUser();

    public ListAdapterActivity(Context context, List<Post> post) {
        mContext = context;
        mPost = post;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_list_adapter,viewGroup,false);
        return new ListAdapterActivity.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = mPost.get(i);
        if(post.getContent().equals("")){
            viewHolder.userPost.setVisibility(View.GONE);
        }else{
            viewHolder.userPost.setVisibility(View.VISIBLE);
            viewHolder.userPost.setText(post.getContent());
        }

        publisherInfo(viewHolder.image_profile,viewHolder.usernamePost);
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image_profile,comment;
        public TextView usernamePost,titlePost,userPost;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            image_profile = itemView.findViewById(R.id.userProfilePicture);
            comment = itemView.findViewById(R.id.commentBtn);
            usernamePost = itemView.findViewById(R.id.usernamePost);
            titlePost = itemView.findViewById(R.id.tvTitlePost);
            userPost = itemView.findViewById(R.id.userPost);
        }

    }

    private void publisherInfo(ImageView image_profile, TextView usernamePost){
        //can't set image profile yet need to do
        usernamePost.setText(currentUser.getUsername());
    }

}