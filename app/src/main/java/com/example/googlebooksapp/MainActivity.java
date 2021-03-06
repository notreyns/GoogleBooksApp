package com.example.googlebooksapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getName();

    private BookAdapter adapter;
    Button searchBtn= findViewById(R.id.search_button);
    EditText searchElem;

    private String GOOGLEBOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchElem= findViewById(R.id.input_view);

        ListView bookListView = (ListView) findViewById(R.id.list_item);

        adapter = new BookAdapter(this, new ArrayList<Book>());


        bookListView.setAdapter(adapter);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUrl(searchElem.getText().toString());
                BookAsyncTask task = new BookAsyncTask();
                task.execute(GOOGLEBOOK_REQUEST_URL);
            }
        });
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
         Book currentBook = adapter.getItem(position);
        Uri bookUri = Uri.parse(currentBook.getUrl());
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW,bookUri);
        startActivity(websiteIntent);
         }
     });
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

    private String updateUrl(String searchElem){
        if (searchElem.contains(" ")){
            searchElem = searchElem.replace(" ","+");
        }
        StringBuilder str = new StringBuilder();
        str.append(GOOGLEBOOK_REQUEST_URL).append(searchElem).append("&filter=ebooks&orderBy=relevance&maxResults=20");
        GOOGLEBOOK_REQUEST_URL = str.toString();
        return GOOGLEBOOK_REQUEST_URL;
    }






}