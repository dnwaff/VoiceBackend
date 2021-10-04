package com.example.WebTTS.model;

import java.util.ArrayList;
import java.util.List;

public class RedditThread {
    private String url;
    private String subreddit;
    private String title;
    private List<RedditComment> comments;

    private class RedditComment {
       private String user;
       private String comment;

        public RedditComment(String user, String comment){
            this.user = user;
            this.comment = comment;
        }

        public String getUser() {
            return user;
        }

        public String getComment() {
            return comment;
        }

        @Override
        public String toString() {
            return "RedditComment{" +
                    "user='" + user + '\'' +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }

    public RedditThread(){
        comments = new ArrayList<RedditComment>();
    }

    public List<RedditComment> getComments() {
        return comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addComment(String user, String comment){
        this.comments.add(new RedditComment(user,comment));
    }

    @Override
    public String toString() {
        return "RedditThread{" +
                "title='" + title + '\'' +
                ", comments=" + comments +
                '}';
    }
}
