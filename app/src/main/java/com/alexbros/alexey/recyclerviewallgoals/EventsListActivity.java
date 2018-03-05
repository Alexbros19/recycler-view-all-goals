package com.alexbros.alexey.recyclerviewallgoals;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class EventsListActivity extends AppCompatActivity {
    private static Bundle bundleRecyclerViewState;

    private RecyclerView recyclerView;
    private ProcessJSON processJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        recyclerView = findViewById(R.id.recycleView);
        Button button = findViewById(R.id.startButton);

        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<ListElement> list = processJSON.getListElements();
                    RecyclerView.Adapter adapter = new RecyclerAdapter(list);
                    recyclerView.setAdapter(adapter);
                }
            });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        bundleRecyclerViewState.putParcelable(Constants.KEY_RECYCLER_STATE, listState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bundleRecyclerViewState != null) {
            Parcelable listState = bundleRecyclerViewState.getParcelable(Constants.KEY_RECYCLER_STATE);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }

        // TODO: start AsyncTask here
        processJSON = new ProcessJSON();
        processJSON.execute(Constants.URL);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter {
        private List<ListElement> listElements;

        private RecyclerAdapter(List<ListElement> elements) {
            listElements = elements;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element_activity_item, parent, false);

            return new ListElementsView(row);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final ListElement listElement = listElements.get(position);

            ((ListElementsView) holder).eventView.setElement(listElement);


            ((ListElementsView) holder).eventView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EventsListActivity.this, ButtonsContextActivity.class);
                    intent.putExtra(Constants.BUTTONS_CONTEXT_ACTIVITY_PARAM, listElement);

                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listElements.size();
        }

        private class ListElementsView extends RecyclerView.ViewHolder {
            EventView eventView;

            private ListElementsView(View view) {
                super(view);

                eventView = (EventView) view;
            }
        }
    }
}
