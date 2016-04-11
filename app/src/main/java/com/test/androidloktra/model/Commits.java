package com.test.androidloktra.model;

/**
 * Created by pulkit on 11/4/16.
 */
public class Commits {

    private String commiterName;
    private String commitSha;
    private String commitMessage;


    public Commits(String commiterName, String commitSha, String commitMessage) {
        this.commiterName = commiterName;
        this.commitSha = commitSha;
        this.commitMessage = commitMessage;
    }

    public String getCommiterName() {
        return commiterName;
    }

    public void setCommiterName(String commiterName) {
        this.commiterName = commiterName;
    }

    public String getCommitSha() {
        return commitSha;
    }

    public void setCommitSha(String commitSha) {
        this.commitSha = commitSha;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage;
    }
}
