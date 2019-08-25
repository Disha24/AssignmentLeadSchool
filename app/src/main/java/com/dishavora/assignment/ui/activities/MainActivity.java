package com.dishavora.assignment.ui.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dishavora.assignment.model.ChatMessage;
import com.dishavora.assignment.R;
import com.dishavora.assignment.ui.adapter.MessageAdapter;
import com.dishavora.assignment.util.Utility;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView btnSend;
    private EditText editText;
    boolean isMine = true;
    private List<ChatMessage> chatMessages;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.toolbar_bg));

        setStatusBarGradiant(this);


        chatMessages = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_msg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnSend = (ImageView) findViewById(R.id.img_chat_send);
        editText = (EditText) findViewById(R.id.msg_type);

        //set ListView adapter first
        adapter = new MessageAdapter(this, chatMessages);
        recyclerView.setAdapter(adapter);

        //event for button SEND
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add message to list
                    ChatMessage chatMessage = new ChatMessage(editText.getText().toString(), isMine);
                    chatMessages.add(chatMessage);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    if (isMine) {
                        isMine = false;
                    } else {
                        isMine = true;
                    }
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarGradiant(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.toolbar_bg);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_video:
                Utility.showLongToast(this, getString(R.string.msg_coming_soon));
                break;
            default:
                Utility.showLongToast(this, getString(R.string.msg_coming_soon));
                break;
        }
        return true;
    }

}

