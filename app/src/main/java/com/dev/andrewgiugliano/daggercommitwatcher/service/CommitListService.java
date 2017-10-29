package com.dev.andrewgiugliano.daggercommitwatcher.service;

import android.util.Pair;

import com.dev.andrewgiugliano.daggercommitwatcher.bo.Commit;
import com.dev.andrewgiugliano.daggercommitwatcher.dao.NetworkDAO;
import com.dev.andrewgiugliano.daggercommitwatcher.exception.DaoException;
import com.dev.andrewgiugliano.daggercommitwatcher.exception.ServiceException;
import com.dev.andrewgiugliano.daggercommitwatcher.exception.UtilException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Service Object that will retrieve a list of commits given the URL to a GitHub repository and branch
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class CommitListService {

    private static final String BRANCH_PARAM_KEY = "sha";

    /**
     * Method to retrieve a list of commits given the URL to a GitHub repository and branch, and return
     * as a list of Commits
     *
     * @param repoUrlStr Path to GitHub repository
     * @param branch Branch name to retrieve commits from
     * @return List of Commits
     * @throws ServiceException If there is an issue retrieving the Commits
     */
    public static List<Commit> getCommitList(String repoUrlStr, String branch) throws ServiceException {
        NetworkDAO networkDAO = new NetworkDAO();

        // Build the URL object given the path and branch name
        List<Pair<String, String>> paramList = new ArrayList<>();
        Pair<String, String> branchParam = new Pair<>(BRANCH_PARAM_KEY, branch);
        paramList.add(branchParam);
        URL mURL;
        try {
            mURL = com.dev.andrewgiugliano.daggercommitwatcher.util.URLUtil.buildUrl(repoUrlStr, paramList);
        } catch (UtilException e) {
            throw new ServiceException(e);
        }

        // Retrieve and parse the JSON response
        JsonArray jsonArray;
        try {
            jsonArray = networkDAO.executeGetRequest(mURL).getAsJsonArray();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        List<Commit> list = new ArrayList<>();
        for(JsonElement entryElement : jsonArray) {
            JsonObject entry = entryElement.getAsJsonObject();
            String sha = entry.get("sha").getAsString();

            JsonObject commit = entry.get("commit").getAsJsonObject();
            String commitMessage = commit.get("message").getAsString();

            JsonObject author = commit.get("author").getAsJsonObject();
            String authorName = author.get("name").getAsString();

            list.add(new Commit(authorName, sha, commitMessage));
        }

        return list;
    }
}
