package com.dev.andrewgiugliano.daggercommitwatcher.activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dev.andrewgiugliano.daggercommitwatcher.R;
import com.dev.andrewgiugliano.daggercommitwatcher.adapter.CommitListAdapter;
import com.dev.andrewgiugliano.daggercommitwatcher.bo.Commit;
import com.dev.andrewgiugliano.daggercommitwatcher.loader.CommitListAsyncTaskLoader;

import java.util.List;

/**
 * Main Activity used in the application. Purpose is to display the latest list of commits in the
 * Dagger repository (currently configured to pull from Master branch).
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class CommitWatchListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Commit>> {

    private static final int RETRIEVE_COMMIT_LOADER_ID = 1;
    private static final String BUNDLE_PATH_KEY = "PATH";
    private static final String BUNDLE_BRANCH_KEY = "BRANCH";

    private RecyclerView mCommitListRecyclerView;
    private TextView mErrorTextView;

    /**
     * Called when Activity is being created. Here we will set the view layout, initialize the
     * RecyclerView and start the Loader to retrieve the list of commits.
     *
     * @param savedInstanceState Saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_watch_list);

        mErrorTextView = (TextView) findViewById(R.id.error_occurred);

        // Set up our Recycler view, then later attach the adapter once we retrieve our data
        mCommitListRecyclerView = (RecyclerView) findViewById(R.id.commit_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCommitListRecyclerView.setLayoutManager(linearLayoutManager);

        /*
         * Get the path and branch parameters to execute search upon, then execute the background
         * task to retrieve the data.
         *
         * NOTE: Currently configured to look in Master branch
         */
        String path = getString(R.string.dagger_repository_path);
        String branch = getString(R.string.master_branch);

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_PATH_KEY, path);
        bundle.putString(BUNDLE_BRANCH_KEY, branch);

        LoaderManager loaderManager = getLoaderManager();
        Loader<List<Commit>> commitLoader = loaderManager.getLoader(RETRIEVE_COMMIT_LOADER_ID);

        if( commitLoader != null ) {
            loaderManager.restartLoader(RETRIEVE_COMMIT_LOADER_ID, bundle, this);
        } else {
            loaderManager.initLoader(RETRIEVE_COMMIT_LOADER_ID, bundle, this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Loader<List<Commit>> onCreateLoader(int id, Bundle args) {
        return new CommitListAsyncTaskLoader(this, args);
    }

    /**
     * Action run after background task is finished. Here we will initialize our
     * CommitListAdapter with the list of commits, and attach it to the RecyclerView. If an
     * error occurs in the system, then show the error message.
     *
     * @param loader Loader object
     * @param data List of Commits
     */
    @Override
    public void onLoadFinished(Loader<List<Commit>> loader, List<Commit> data) {
        if (data != null) {
            CommitListAdapter adapter = new CommitListAdapter(data);
            mCommitListRecyclerView.setAdapter(adapter);
        } else {
            mCommitListRecyclerView.setVisibility(View.INVISIBLE);
            mErrorTextView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLoaderReset(Loader<List<Commit>> loader) {}
}
