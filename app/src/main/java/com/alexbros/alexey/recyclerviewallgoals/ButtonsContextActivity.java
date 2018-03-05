package com.alexbros.alexey.recyclerviewallgoals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

public class ButtonsContextActivity extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_number);

        TextView buttonNumber = findViewById(R.id.elementNumberInfo);
        ListElement listElement = (ListElement) getIntent().getSerializableExtra(Constants.BUTTONS_CONTEXT_ACTIVITY_PARAM);
        buttonNumber.setText(listElement.getFirstTeamName() + " " + listElement.getSecondTeamName());
    }

    public void OnBackToList(View view){

        Intent intent = new Intent(ButtonsContextActivity.this, EventsListActivity.class);
        startActivity(intent);
    }
}
