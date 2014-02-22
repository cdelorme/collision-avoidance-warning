package com.hackathon.collisionavoidance;


import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class PedWarning extends Activity implements TextToSpeech.OnInitListener {

	private TextToSpeech tts;
	private String toSpeak = "Pedestrian ahead";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Set Default States

		// Prepare Activity UI
        setContentView(new SampleView(this));
        tts = new TextToSpeech(this, this);
       
	}
	
	
    private static class SampleView extends View {
        private AnimateDrawable mDrawable;

        public SampleView(Context context) {
            super(context);
            setFocusable(true);
            setFocusableInTouchMode(true);

            
            Drawable dr = context.getResources().getDrawable(R.drawable.ped_large);
            
            dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());

            Animation an = new TranslateAnimation(500, 0, 150, 150);
            an.setDuration(2000);
            an.setRepeatCount(-1);
            an.initialize(1, 1, 1, 1);

            mDrawable = new AnimateDrawable(dr, an);
            an.startNow();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);
            mDrawable.draw(canvas);
            invalidate();
        }
    }


	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status != TextToSpeech.SUCCESS) {
			Log.d("tts", "TTS engine failed");
		} else {
			Log.d("tts", "PASSED");
			 tts.setLanguage(Locale.US);
			 tts.setPitch((float) 1.5);
			 tts.setSpeechRate((float) .75);
			 tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
		}
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		 if (tts != null) {
			 tts.stop();
	         tts.shutdown();
	        }
	}
}
