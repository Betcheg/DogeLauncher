package com.example.dogelaucher;

import android.R.color;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class Splash extends View {
	
	Context context;
	int LARGEUR_ECRAN = 0;
	int HAUTEUR_ECRAN = 0;
	Bitmap splashArt;
	View game;
	boolean threadLance = false;
	boolean gameCharger = false;
	
	public Splash(Context context, int h, int l)
	{
		super(context);
		this.context = (MainActivity) context;
		LARGEUR_ECRAN = l;
		HAUTEUR_ECRAN = h;
		splashArt = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.splash), HAUTEUR_ECRAN,LARGEUR_ECRAN );
		
	}
	
	protected void onDraw(Canvas canvas)
	{
		
		
		canvas.drawBitmap(splashArt,0,0,null);
		
		
	//	if(!threadLance) lancerThread();
		//if(!gameCharger) preparerView();
		invalidate();

	}
	 /*

	public void lancerThread()
	{
		threadLance = true;
		try {
			Thread.sleep(500);
			((Activity) context).setContentView(game);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} */
	public void preparerView()
	{
		gameCharger = true;
		game = new Game(context, (int) HAUTEUR_ECRAN, (int) LARGEUR_ECRAN);
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, double newHeight, double newWidth) 
	{
		if(newHeight == 0)
		{
			newHeight = newWidth / ((double) bm.getWidth()/bm.getHeight());
		}
		else if(newWidth == 0)
		{
			newWidth = newHeight / ((double) bm.getHeight()/bm.getWidth());
		}
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	    float scaleHeight = ((float) newHeight) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);
	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
}
