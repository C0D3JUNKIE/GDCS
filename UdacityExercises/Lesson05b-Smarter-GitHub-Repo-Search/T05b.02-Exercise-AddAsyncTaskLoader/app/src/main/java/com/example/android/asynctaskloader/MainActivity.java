/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.asynctaskloader;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.asynctaskloader.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

// COMPLETE (1) implement LoaderManager.LoaderCallbacks<String> on MainActivity
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    /* A constant to save and restore the URL that is being displayed */
    private static final String SEARCH_QUERY_URL_EXTRA = "query";
    // COMPLETE (28) Remove the key for storing the search results JSON
    // COMPLETE (2) Create a constant int to uniquely identify your loader. Call it GITHUB_SEARCH_LOADER
    private static final int GITHUB_SEARCH_LOADER = 11;

    private EditText mSearchBoxEditText;
    private TextView mUrlDisplayTextView;
    private TextView mSearchResultsTextView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);
        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_github_search_results_json);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        if (savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString(SEARCH_QUERY_URL_EXTRA);
            // COMPLETE (25) Remove the code that displays the JSON
            // COMPLETE (26) Remove the code that retrieves the JSON
        }
        // COMPLETE (24) Initialize the loader with GITHUB_SEARCH_LOADER as the ID, null for the bundle, and this for the context
        getSupportLoaderManager().initLoader(GITHUB_SEARCH_LOADER, null, this);


    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally request that an AsyncTaskLoader performs the GET request.
     */
    private void makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        // COMPLETE (17) If no search was entered, indicate that there isn't anything to search for and return
        if(TextUtils.isEmpty(githubQuery)){
            mUrlDisplayTextView.setText("Nothing entered, nothing searched!");
            return;
        }

        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        mUrlDisplayTextView.setText(githubSearchUrl.toString());
        // COMPLETE (18) Remove the call to execute the AsyncTask
        // COMPLETE (19) Create a bundle called queryBundle
        // COMPLETE (20) Use putString with SEARCH_QUERY_URL_EXTRA as the key and the String value of the URL as the value
        // COMPLETE (21) Call getSupportLoaderManager and store it in a LoaderManager variable
        // COMPLETE (22) Get our Loader by calling getLoader and passing the ID we specified
        Bundle queryBundle = new Bundle();
        queryBundle.putString(SEARCH_QUERY_URL_EXTRA, githubSearchUrl.toString());
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String>  githubSearchLoader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);
        // COMPLETE (23) If the Loader was null, initialize it. Else, restart it.
        if(githubSearchLoader == null){
            loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        }
    }

    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showJsonDataView() {
        /* First, make sure the error is invisible */
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        /* Then, make sure the JSON data is visible */
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     * <p>
     * Since it is okay to redundantly set the visibility of a View, we don't
     * need to check whether each view is currently visible or invisible.
     */
    private void showErrorMessage() {
        /* First, hide the currently visible data */
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        /* Then, show the error */
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    // COMPLETE (3) Override onCreateLoader
    // Within onCreateLoader
    // COMPLETE (4) Return a new AsyncTaskLoader<String> as an anonymous inner class with this as the constructor's parameter
    // COMPLETE (5) Override onStartLoading
    // Within onStartLoading
    // COMPLETE (6) If args is null, return.
    // COMPLETE (7) Show the loading indicator
    // COMPLETE (8) Force a load
    // END - onStartLoading
    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                if(args == null){
                    return;
                }
                mLoadingIndicator.setVisibility(View.VISIBLE);
                forceLoad();
            }

            // COMPLETE (9) Override loadInBackground
            // Within loadInBackground
            // COMPLETE (10) Get the String for our URL from the bundle passed to onCreateLoader
            // COMPLETE (11) If the URL is null or empty, return null
            // COMPLETE (12) Copy the try / catch block from the AsyncTask's doInBackground method
            // END - loadInBackground
            @Override
            public String loadInBackground() {
                String searchQueryUrlString = args.getString(SEARCH_QUERY_URL_EXTRA);
                if(searchQueryUrlString == null || TextUtils.isEmpty(searchQueryUrlString)){
                    try {
                        URL githubUrl = new URL(searchQueryUrlString);
                        return NetworkUtils.getResponseFromHttpUrl(githubUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
        };
    }

    // COMPLETE (13) Override onLoadFinished
    // Within onLoadFinished
    // COMPLETE (14) Hide the loading indicator
    // COMPLETE (15) Use the same logic used in onPostExecute to show the data or the error message
    // END - onLoadFinished
    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (data != null && !data.equals("")) {
            showJsonDataView();
            mSearchResultsTextView.setText(data);
        } else {
            showErrorMessage();
        }
    }

    // COMPLETE (16) Override onLoaderReset as it is part of the interface we implement, but don't do anything in this method
    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    // COMPLETE (29) Delete the AsyncTask class

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String queryUrl = mUrlDisplayTextView.getText().toString();
        outState.putString(SEARCH_QUERY_URL_EXTRA, queryUrl);
        // COMPLETE (27) Remove the code that persists the JSON
    }
}