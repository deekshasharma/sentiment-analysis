package com.sentiments.twitter;

import java.util.List;

public interface DataSource {

    public List<String> getTweets(String keyword);
}
