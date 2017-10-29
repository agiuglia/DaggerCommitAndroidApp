package com.dev.andrewgiugliano.daggercommitwatcher.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dev.andrewgiugliano.daggercommitwatcher.R;
import com.dev.andrewgiugliano.daggercommitwatcher.bo.Commit;

/**
 * View Holder to display a Commit object within a Recycler View.
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class CommitItemViewHolder extends RecyclerView.ViewHolder {
    private View view;

    /**
     * Constructor for the View Holder. Requires a reference to the View which will be populated.
     * @param view View that will be populated with a commit
     */
    public CommitItemViewHolder(View view) {
        super(view);

        this.view = view;
    }

    /**
     * Method to bind the data in commit to the View we have a reference to.
     *
     * @param commit Commit object used to populate the View we have a reference to
     */
    public void bindDataToView(Commit commit){
        TextView authorName = view.findViewById(R.id.commit_author);
        authorName.setText(commit.getAuthor());

        TextView commitHash = view.findViewById(R.id.commit_hash);
        commitHash.setText(commit.getCommitHash());

        TextView commitMessage = view.findViewById(R.id.commit_message);
        commitMessage.setText(commit.getCommitMessage());
    }
}
