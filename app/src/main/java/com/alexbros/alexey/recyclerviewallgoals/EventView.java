package com.alexbros.alexey.recyclerviewallgoals;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventView extends LinearLayout {
    private TextView timeText;
    private TextView firstTeamNameText;
    private TextView secondTeamNameText;

    public EventView(Context context) {
        super(context);
        init(context);
    }

    public EventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EventView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.list_element_activity, this);

        timeText = findViewById(R.id.timeText);
        firstTeamNameText = findViewById(R.id.firstTeamName);
        secondTeamNameText = findViewById(R.id.secondTeamName);
    }

    public void setElement(ListElement listElement) {
        timeText.setText(Constants.TIME_VALUE);
        firstTeamNameText.setText(listElement.getFirstTeamName());
        secondTeamNameText.setText(listElement.getSecondTeamName());
    }
}
