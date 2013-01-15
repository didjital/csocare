package com.didjital.csocare.manual;

import com.didjital.csocare.R;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
 
public class PartsAct extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parts_act);
    }
        @SuppressWarnings("unused")
		private void animate() {
    		ImageView imgView = (ImageView) findViewById(R.id.animImg);
    		// imgView.setVisibility(ImageView.VISIBLE);
    		imgView.setBackgroundResource(R.drawable.anim);

    		AnimationDrawable frameAnimation = (AnimationDrawable) imgView
    				.getBackground();
    	
    }
}
