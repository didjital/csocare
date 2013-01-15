package com.didjital.csocare;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class ChatActivity extends Activity {
	Button chatroom, chatprivate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat_pilihan);
		chatroom = (Button) findViewById(R.id.chatroom);
		chatprivate = (Button) findViewById(R.id.chatprivate);
		
		chatroom.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ChatRoom.class);
                startActivity(i);
            }
        });
		
		chatprivate.setOnClickListener(new View.OnClickListener() {
			 
            @Override
            public void onClick(View view) {
                // Launching News Feed Screen
                Intent i = new Intent(getApplicationContext(), ChatBuddy.class);
                startActivity(i);
            }
        });
		
	}

}
