package com.test.androidloktra.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.androidloktra.R;
import com.test.androidloktra.model.Commits;

import java.util.ArrayList;

/**
 * Created by pulkit on 11/4/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Commits> commitList;
    public RecyclerAdapter(Context context, ArrayList<Commits> commitList){
        this.context = context;
        this.commitList = commitList;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_view_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        Commits commits = commitList.get(position);
        holder.commiterName.setText(commits.getCommiterName());
        holder.commiterMessage.setText(commits.getCommitMessage());
        holder.commitSha.setText("Commit: " + commits.getCommitSha());

    }

    @Override
    public int getItemCount() {
        return commitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView commiterName;
        public TextView commitSha;
        public TextView commiterMessage;
        public ViewHolder(View itemView) {
            super(itemView);
            commiterName = (TextView) itemView.findViewById(R.id.commiterName);
            commiterMessage = (TextView) itemView.findViewById(R.id.commiterMessage);
            commitSha = (TextView) itemView.findViewById(R.id.commitSha);

        }
    }
}
