package com.dev.andrewgiugliano.daggercommitwatcher.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.andrewgiugliano.daggercommitwatcher.R;
import com.dev.andrewgiugliano.daggercommitwatcher.bo.Commit;
import com.dev.andrewgiugliano.daggercommitwatcher.view.CommitItemViewHolder;

import java.util.List;

/**
 * Adapter used to display the list of Commits.
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class CommitListAdapter extends RecyclerView.Adapter<CommitItemViewHolder> {

    private List<Commit> commitList;

    /**
     * Default constructor to initialize list of Commits
     * @param commitList List of Commits
     */
    public CommitListAdapter(List<Commit> commitList) {
        this.commitList = commitList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommitItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.commit_list_item, parent, false);

        return new CommitItemViewHolder(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(CommitItemViewHolder holder, int position) {
        holder.bindDataToView(commitList.get(position));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return commitList.size();
    }
}
