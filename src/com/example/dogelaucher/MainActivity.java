package com.example.dogelaucher;


import java.io.IOException;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity 
{
 View Splash;
 View Game;
 
 @Override
 protected void onCreate(Bundle savedInstanceState) 
 {
  super.onCreate(savedInstanceState);
  requestWindowFeature(Window.FEATURE_NO_TITLE);
  Splash = new Splash(this, getWindowManager().getDefaultDisplay().getHeight(), getWindowManager().getDefaultDisplay().getWidth());
  setContentView(Splash);
  
  new Handler().postDelayed(new Runnable(){
      @Override
      public void run() {
          /* Create an Intent that will start the Menu-Activity. */
          setContentView(Game);
          
      }
  }, 5000);
  
  Game = new Game(this, getWindowManager().getDefaultDisplay().getHeight(), getWindowManager().getDefaultDisplay().getWidth());

  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
  
  this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
  

  
	
 }
}