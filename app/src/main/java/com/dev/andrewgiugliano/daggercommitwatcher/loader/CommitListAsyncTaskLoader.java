package com.dev.andrewgiugliano.daggercommitwatcher.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import com.dev.andrewgiugliano.daggercommitwatcher.bo.Commit;
import com.dev.andrewgiugliano.daggercommitwatcher.exception.ServiceException;
import com.dev.andrewgiugliano.daggercommitwatcher.service.CommitListService;

import java.util.List;

/**
 * Loader to retrieve the commit list from GitHub.
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class CommitListAsyncTaskLoader extends AsyncTaskLoader<List<Commit>> {
    private static final int NUM_PARAMS = 2;
    private static final String BUNDLE_PATH_KEY = "PATH";
    private static final String BUNDLE_BRANCH_KEY = "BRANCH";

    private Bundle params;

    /**
     * Constructor to get context and the param bundle, where the bundle has an entry for the path and branch
     *
     * @param context Context
     * @param params Parameters for the path and bundle
     */
    public CommitListAsyncTaskLoader(Context context, Bundle params) {
        super(context);

        this.params = params;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * Action run in background that calls our Service object that retrieves the commits from
     * GitHub.
     *
     * @return List of Commits, null if error occurred
     */
    @Override
    public List<Commit> loadInBackground() {
        if (params.keySet().size() < NUM_PARAMS) {
            return null;
        }

        String path = params.getString(BUNDLE_PATH_KEY);
        String branch = params.getString(BUNDLE_BRANCH_KEY);

        List<Commit> commitList = null;

        try {
            commitList = CommitListService.getCommitList(path, branch);
        } catch (ServiceException e) {
            // Leave commitList as null. Exception already logged at lower class.
        }

        return commitList;
    }
}
