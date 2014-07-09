package com.welaika.menadi.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.builder.Builders;
import com.squareup.picasso.Picasso;
import com.welaika.menadi.R;


import java.util.List;

import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;


public class TwitterFragment extends Fragment {

    private static final String LOG_TAG = "TwitterFragment:";
    private View view;

    // Tweet and User are the classes that Gson will deserialize the JSON into
    static class Tweet {
        @SerializedName("retweeted_status")
        Tweet retweetedStatus;
        User user;
        String text;
        @SerializedName("id_str")
        String id;
    }

    static class User {
        @SerializedName("screen_name")
        String screenName;
        @SerializedName("profile_image_url")
        String imageUrl;
    }

    // adapter that holds tweets, obviously :)
    ArrayAdapter<Tweet> tweetAdapter;

    SwipeRefreshLayout listViewLayout;

    // This "Future" tracks loading operations.
    // A Future is an object that manages the state of an operation
    // in progress that will have a "Future" result.
    // You can attach callbacks (setCallback) for when the result is ready,
    // or cancel() it if you no longer need the result.
    //Future<List<Tweet>> loading;
    Future<JsonObject> loading;

    // Lets grab the authentication
    String accessToken;

    public TwitterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        getActivity().getActionBar().setDisplayShowTitleEnabled(true);
        getActivity().getActionBar().setTitle((getResources().getStringArray(R.array.menu_array))[5]);

        view = inflater.inflate(R.layout.twitter_fragment, container, false);
        setUI();

        return view;
    }

    public void setUI(){

        // Set a listener to be invoked when the list should be refreshed.
        listViewLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout_container);
        listViewLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do work to refresh the list here.
                load();

            }
        });
        listViewLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        ListView listview = (ListView) view.findViewById(R.id.list_tweets);


        //mEmptyLayout = new EmptyLayout(getActivity(), listView.getRefreshableView());
        //mEmptyLayout.setLoadingMessage(getActivity().getResources().getString(R.string.pd_title));

        //mEmptyLayout.showLoading();
        //initSocialAuthAdapter();
        // create a tweet adapter for our list view
        tweetAdapter = new ArrayAdapter<Tweet>(getActivity(), 0) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(
                            R.layout.tweet_row, null);
                }

                // we're near the end of the list adapter, so load more items
                if (position >= getCount() - 3) {
                    load();
                }

                // grab the tweet (or retweet)
                Tweet tweet = getItem(position);
                Tweet retweet = tweet.retweetedStatus;
                if (retweet != null)
                    tweet = retweet;

                ImageView imageView = (ImageView) convertView
                        .findViewById(R.id.image);

                // Use Ion's builder set the google_image on an ImageView from a
                // URL

                // start with the ImageView
                Picasso.with(getActivity()).load(tweet.user.imageUrl).into(imageView);


                // and finally, set the name and text
                TextView handle = (TextView) convertView
                        .findViewById(R.id.handle);
                handle.setText(tweet.user.screenName);

                TextView text = (TextView) convertView.findViewById(R.id.tweet);
                text.setText(tweet.text);
                Linkify.addLinks(text, Linkify.ALL);
                return convertView;
            }
        };

        listview.setAdapter(tweetAdapter);
        // authenticate and do the first load
        getCredentials();
    }

    private void getCredentials() {
        Log.i("Twitter", "getCredentials");
        Ion.with(getActivity(), "https://api.twitter.com/oauth2/token")

                .basicAuthentication("wy1dfZOF6Rfw4mKDxcS6QCynl",
                        "GRnmHbbGPINKTvem1MCcJ1XBlMJFuGNjN9xp3Vh1yHYcSZNKMM")
                .setBodyParameter("grant_type", "client_credentials")
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                if (e != null) {
                    Toast.makeText(getActivity(),
                            "Error loading tweets", Toast.LENGTH_LONG)
                            .show();
                    e.printStackTrace();
                    return;
                }
                accessToken = result.get("access_token").getAsString();
                load();
            }
        });
    }

    private void load() {
        // don't attempt to load more if a load is already in progress
        if (loading != null && !loading.isDone() && !loading.isCancelled()) {
            Log.i("Twitter", "loading not nullo");
            return;
        }

        Log.i("Twitter", "loading!!");

        // load the tweets
        //String url = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=iSaloniofficial&count=20&include_rts=true";
        String url = "https://api.twitter.com/1.1/search/tweets.json?count=20&q=iSaloni";
        if (tweetAdapter.getCount() > 0) {
            // load from the "last" id
            Tweet last = tweetAdapter.getItem(tweetAdapter.getCount() - 1);
            url += "&max_id=" + last.id;
        }

        // Request tweets from Twitter using Ion.
        // This is done using Ion's Fluent/Builder API.
        // This API lets you chain calls together to build
        // complex requests.

        // This request loads a URL as JsonArray and invokes
        // a callback on completion.

        loading = Ion.with(getActivity(), url)
                .setLogging("MyLogs", Log.DEBUG)
                .setHeader("Authorization", "Bearer " + accessToken)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // this is called back onto the ui thread, no
                        // Activity.runOnUiThread or Handler.post necessary.
                        if (e != null) {
                            Toast.makeText(getActivity(),
                                    "Error loading tweets", Toast.LENGTH_LONG)
                                    .show();
                            return;
                        }
                        // add the tweets

                        Gson gson = new Gson();
                        String result_tweets_raw = result.get("statuses").toString();
                        List<Tweet> tweets = gson.fromJson(result_tweets_raw, new TypeToken<List<Tweet>>(){}.getType());

                        for(Tweet tweet: tweets){
                            tweetAdapter.add(tweet);
                        }
                        tweetAdapter.notifyDataSetChanged();
                        // adapter.authorize(getActivity(), Provider.TWITTER);
                        listViewLayout.setRefreshing(false);
                    }

                });
    }
}
