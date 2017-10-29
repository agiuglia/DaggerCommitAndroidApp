package com.dev.andrewgiugliano.daggercommitwatcher.bo;

/**
 * Object to represent a Commit. Currently, we only hold the Author, Commit Hash and Commit Message
 * info.
 *
 * @author Andrew Giugliano
 * @version 1.0
 */
public class Commit {
    private String author;
    private String commitHash;
    private String commitMessage;

    public Commit(String author, String commitHash, String commitMessage) {
        setAuthor(author);
        setCommitHash(commitHash);
        setCommitMessage(commitMessage);
    }

    /**
     * Getter for author name
     *
     * @return Author name
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Setter for author name
     *
     * @param author Author name
     */
    private void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Getter for commit hash
     *
     * @return Commit hash
     */
    public String getCommitHash() {
        return commitHash;
    }

    /**
     * Setter for commit hash
     *
     * @param commitHash Commit hash
     */
    private void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    /**
     * Getter for commit message
     *
     * @return Commit message
     */
    public String getCommitMessage() {
        return commitMessage;
    }

    /**
     * Setter for commit message
     *
     * @param commitMessage Commit message
     */
    private void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Commit{" +
                "author='" + author + '\'' +
                ", commitHash='" + commitHash + '\'' +
                ", commitMessage='" + commitMessage + '\'' +
                '}';
    }
}
