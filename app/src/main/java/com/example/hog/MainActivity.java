package com.example.hog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Test Activity that contains a {@link RecyclerView} which loads
 * {@link MainActivity#RECYCLER_VIEW_ITEM_COUNT} number of items
 *
 * Created by Nitin Khurana on 10/12/18.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Number of items that should be loaded in the {@link RecyclerView}
     */
    private static final int RECYCLER_VIEW_ITEM_COUNT = 10000;

    /**
     * The list that will contains {@link MainActivity#RECYCLER_VIEW_ITEM_COUNT} items
     */
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get recycler view from the layout file
        recyclerView = findViewById(R.id.recyclerView);

        // set adapter to the {@code recyclerView}
        loadRecyclerView();
    }

    /**
     * Sets the adapter to the {@code recyclerView}
     */
    private void loadRecyclerView(){

        if(recyclerView == null)
            return;

        // set adapter
        recyclerView.setAdapter(new NumbersAdapter(this, RECYCLER_VIEW_ITEM_COUNT));
    }
}
