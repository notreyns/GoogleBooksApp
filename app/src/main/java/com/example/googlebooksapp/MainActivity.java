package com.example.googlebooksapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();

    private BookAdapter adapter;

    private static final String GOOGLEBOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=kill&filter=ebooks&orderBy=relevance&maxResults=3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView bookListView = (ListView) findViewById(R.id.list_item);

        adapter = new BookAdapter(this, new ArrayList<Book>());


        bookListView.setAdapter(adapter);
        BookAsyncTask task = new BookAsyncTask();
        task.execute(GOOGLEBOOK_REQUEST_URL);
    }
    private class BookAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Book> result = QueryUtils.fetchBookData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Book> data) {
            adapter.clear();
            if (data != null && !data.isEmpty()) {
                adapter.addAll(data);
            }
        }
    }


        //bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           // @Override
            //public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
               // Book currentBook = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                //Uri bookUri = Uri.parse(currentBook.getUrl());

                // Create a new intent to view the earthquake URI
                //Intent websiteIntent = new Intent(Intent.ACTION_VIEW,currentUri);

                // Send the intent to launch a new activity
                //startActivity(websiteIntent);
          //  }
       // });



}