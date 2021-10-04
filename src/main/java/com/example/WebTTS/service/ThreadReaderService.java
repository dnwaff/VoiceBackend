package com.example.WebTTS.service;
import com.example.WebTTS.model.RedditThread;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;


import java.net.URL;

@Service
public class ThreadReaderService {
    public String RssFeed(){
        URL feedSource = null;
        SyndFeed feed = null;
        RedditThread thread = new RedditThread();
        try {
            SyndFeedInput input = new SyndFeedInput();
            feedSource = new URL("https://www.reddit.com/r/tifu/comments/py3g0k/tifu_by_not_closely_inspecting_the_contents_of_my/.rss");
            feed = input.build(new XmlReader(feedSource));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String title = feed.getTitle();
        thread.setTitle(title);
        for (SyndEntry entry: feed.getEntries()){
            String user = entry.getAuthor();
            String comment = entry.getContents().get(0).getValue();
            thread.addComment(user,comment);
        }
        return thread.toString();
    }
}
