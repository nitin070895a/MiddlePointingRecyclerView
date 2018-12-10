package com.example.hog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitin Khurana on 10/12/18.
 *
 * A {@link RecyclerView.Adapter} that loads items containing a single {@link TextView}
 * in the {@link RecyclerView}, such that whenever the user scrolls the {@link RecyclerView}
 * this adapter will highlight the middle item that's on the screen
 *
 */
public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyHolder> {

//    private final String TAG = getClass().getSimpleName();

    /**
     * Will hold the context of the {@link android.app.Activity} in which this adapter will be set to a {@link RecyclerView}
     */
    private Context context;

    /**
     * The number of items to be inflated in the {@link RecyclerView}
     */
    private int itemCount;

    /**
     * Will contain all the physical items in the recycler view
     */
    private List<MyHolder> holders = new ArrayList<>();

    /**
     * Initialize the {@link RecyclerView} with the number of items passed in the constructor
     * @param context the context of the activity
     * @param count the number of items that the {@link RecyclerView} will contain
     */
    NumbersAdapter(Context context, int count){

        this.context = context;
        this.itemCount = count;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        // Highlight Logic Begins here

        final int itemHeight = context.getResources().getDimensionPixelOffset(R.dimen.item_height);     // height of each item

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int top = recyclerView.computeVerticalScrollOffset() / itemHeight;                      // get the current top item index
                int rv_center = recyclerView.getHeight() / 2;                                           // center position of the recycler view in pixels
                int itemsInTopHalf = rv_center / itemHeight;                                            // the number of items in the top half of the recycler view
                int midPos = top + itemsInTopHalf;                                                      // middle item = top item + number of items in top half

                MyHolder holder = (MyHolder) recyclerView.findViewHolderForAdapterPosition(midPos);     // get the view holder at middle position

                if (holder != null){

                    for (MyHolder holder1 : holders){
                        holder1.highLight(false);                                                       // unhighlight all the items
                    }

                    holder.highLight(true);                                                             // highlight the middle item
                }
            }
        });

        // Highlight Logic Ends here

    }

    @NonNull
    @Override
    public NumbersAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.item_numbers, viewGroup, false), context);

        holders.add(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersAdapter.MyHolder viewHolder , int i) {

        // set item number as text to the item's TextView
        viewHolder.setText(Integer.toString(i));

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Class that contains all the views inside an item in the {@link RecyclerView}
     */
    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        /**
         * The color to be highlighted with
         */
        int highlightColor;

        MyHolder(@NonNull View itemView, Context context) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            highlightColor = context.getResources().getColor(R.color.colorAccent);
        }

        /**
         * Set the text of the {@link TextView} in the view holder
         * @param text the text to be set to the {@code textView}
         */
        public void setText(String text){

            if (text != null) {
                this.textView.setText(text);
            }
        }

        /**
         * Highlight the current item
         * @param highLight true to highlight, false to unhighlight
         */
        void highLight(boolean highLight){
            itemView.setBackgroundColor(highLight ? highlightColor : Color.TRANSPARENT);
        }
    }
}
