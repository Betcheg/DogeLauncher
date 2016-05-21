package com.example.dogelaucher;


import android.R.color;
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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class Game extends LinearLayout 
{


	protected Bitmap _fond;
	protected Bitmap _doge;
	protected Bitmap _catapulte;
	protected Bitmap _power0;
	protected Bitmap _power1;
	protected Bitmap _power2;
	protected Bitmap _power3;
	protected Bitmap _power4;
	protected Bitmap _power5;
	protected Bitmap _power6;
	protected Bitmap _power7;
	protected Bitmap _power8;
	protected Bitmap _power9;
	protected Bitmap _power10;
	protected Bitmap _power11;
	protected Bitmap _power12;
	protected Bitmap _power13;
	protected Bitmap _power14;
	protected Bitmap _power15;
	protected boolean monterCompteur = true;
	
	
	protected Doge doge = new Doge();
	protected Catapulte catapulte = new Catapulte();
	
	protected Paint paint;
	protected MainActivity context;

	protected double LARGEUR_ECRAN = 0;
	protected double HAUTEUR_ECRAN = 0;
	protected boolean ecranAppuie = false;
	protected int posAppuie;
	protected int posAppuieY;
	protected boolean maintenue = false;
	protected boolean gameover = false;
	protected boolean credit = false;
	protected boolean lance = false;
	protected boolean powerChoisit = false;
	protected boolean angleChoisit = false;
	protected int compteur = 0;
	
	protected SeekBar seekbar;
	protected boolean skAffiche = false;
	protected int nombreObstacle = 1;
	protected int i =0;
	
	protected GameLance GameLance;

	
	
	
	public Game(Context context, int h, int l)
	{
		super(context);
		
		this.context = (MainActivity) context;
		seekbar = new SeekBar(context);
		LARGEUR_ECRAN = l;
		HAUTEUR_ECRAN = h;
		setWillNotDraw(false); 
		initialiserVariable();
		
	}
	
	public void initialiserVariable()
	{
		
		_fond = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fond1double), HAUTEUR_ECRAN,LARGEUR_ECRAN*2 );
		_catapulte = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.canon), HAUTEUR_ECRAN*0.3625,LARGEUR_ECRAN*0.2625 );
		_doge = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead), HAUTEUR_ECRAN*0.134375,LARGEUR_ECRAN*0.083 );
		
		_power0 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power0), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power1 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power1), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power2= getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power2), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power3= getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power3), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power4= getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power4), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power5 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power5), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power6 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power6), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power7 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power7), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power8 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power8), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power9 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power9), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power10 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power10), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power11 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power11), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power12 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power12), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power13 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power13), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power14 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power14), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
		_power15 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.power15), HAUTEUR_ECRAN*0.046875,LARGEUR_ECRAN*0.15625 );
			
		catapulte.angle -=3;
		_catapulte = rotateImage(_catapulte, (float) catapulte.angle);
		
		/*
		 * doge.posX = (float) (LARGEUR_ECRAN * 0.05);
		doge.posY = (float) (HAUTEUR_ECRAN * 0.01);
		catapulte.posX = (float) (LARGEUR_ECRAN * -0.05);
		catapulte.posY = (float) (HAUTEUR_ECRAN * 0.65); */
		GameLance = new GameLance(context, (int) HAUTEUR_ECRAN, (int) LARGEUR_ECRAN, doge, catapulte);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN)
		{
				ecranAppuie = true;
				this.posAppuie = (int) event.getX();
				this.posAppuieY = (int) event.getY();
		}
		
		else if(event.getAction() == MotionEvent.ACTION_UP)
		{
			this.ecranAppuie = false;
			maintenue = false;
		}
		
		return true;
	}
	
	protected void onDraw(Canvas canvas)
	{
		
		if (lance == false) 
		{
			afficherDebut(canvas);	
		}
		
		else 
		{
			GameLance.setDoge(doge);
			GameLance.setCatapulte(catapulte);
			context.setContentView(GameLance);
		}
		
		invalidate();

	}
	
	public void afficherDebut(Canvas canvas)
	{
		// =========== ECRITURE DU TEXTE =========
		Paint paint = new Paint();
		canvas.drawPaint(paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize((float) (HAUTEUR_ECRAN*0.055));
		paint.setFakeBoldText(true);
		// =======================================
		
		
		canvas.drawBitmap(_fond, 0,0, null);
		canvas.drawBitmap(_catapulte, 0, (float) (HAUTEUR_ECRAN*0.64), null);
		canvas.drawBitmap(_doge, (float) (LARGEUR_ECRAN*0.2375),(float) (HAUTEUR_ECRAN*0.59375), null);
		
		if(!powerChoisit)
		{
			choisirPower(canvas);
		}
		else
		{
			afficherPower(canvas);
		}
		
		if(powerChoisit && !angleChoisit)
		{
			choisirAngle(canvas);
			canvas.drawText("Angle",(float) LARGEUR_ECRAN*(float)0.25625,(float) HAUTEUR_ECRAN  * (float)0.20,paint);
		}
		
		if(powerChoisit && angleChoisit)
		{
			afficherAngle(canvas);
			canvas.drawText("Angle",(float) LARGEUR_ECRAN*(float)0.25625,(float) HAUTEUR_ECRAN  * (float)0.20,paint);
			lance = true;
		}
		
		if(ecranAppuie && !maintenue)
		{
			maintenue = true;
		}
		
		canvas.drawText("Power",(float) LARGEUR_ECRAN*(float)0.25625,(float) HAUTEUR_ECRAN  * (float)0.10,paint);
		
	
	}
	
	public void afficherJeu(Canvas canvas)
	
	{
		

		
		
	}
	
	public void choisirPower(Canvas canvas)
	{
		
		
		if(compteur == 0)
		{
			canvas.drawBitmap(_power0, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
			monterCompteur = true;
		}
		if(compteur == 1) canvas.drawBitmap(_power1, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 2) canvas.drawBitmap(_power2, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 3) canvas.drawBitmap(_power3, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 4) canvas.drawBitmap(_power4, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 5) canvas.drawBitmap(_power5, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 6) canvas.drawBitmap(_power6, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 7) canvas.drawBitmap(_power7, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 8) canvas.drawBitmap(_power8, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 9) canvas.drawBitmap(_power9, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 10) canvas.drawBitmap(_power10, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 11) canvas.drawBitmap(_power11, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 12) canvas.drawBitmap(_power12, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 13) canvas.drawBitmap(_power13, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 14) canvas.drawBitmap(_power14, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(compteur == 15) 
		{
			canvas.drawBitmap(_power15, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
			monterCompteur = false;
		}
		if(monterCompteur) compteur++;
		else compteur--;
		
		if(ecranAppuie) 
		{
			powerChoisit = true;
			catapulte.puissance = compteur+1;
			compteur = 0;
			monterCompteur=true;
			ecranAppuie = false;
		}
	}
	
	public void afficherPower(Canvas canvas)
	{
		if(catapulte.puissance == 1) canvas.drawBitmap(_power0, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 2) canvas.drawBitmap(_power1, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 3) canvas.drawBitmap(_power2, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 4) canvas.drawBitmap(_power3, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 5) canvas.drawBitmap(_power4, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 6) canvas.drawBitmap(_power5, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 7) canvas.drawBitmap(_power6, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 8) canvas.drawBitmap(_power7, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 9) canvas.drawBitmap(_power8, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 10) canvas.drawBitmap(_power9, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 11) canvas.drawBitmap(_power10, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 12) canvas.drawBitmap(_power11, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 13) canvas.drawBitmap(_power12, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 14) canvas.drawBitmap(_power13, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 15) canvas.drawBitmap(_power14, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
		if(catapulte.puissance == 16) canvas.drawBitmap(_power15, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.05), null);
	}


	public void choisirAngle(Canvas canvas)
	{
		
		
		if(compteur == 0)
		{
			canvas.drawBitmap(_power0, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
			monterCompteur = true;
		}
		if(compteur == 1) canvas.drawBitmap(_power1, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 2) canvas.drawBitmap(_power2, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 3) canvas.drawBitmap(_power3, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 4) canvas.drawBitmap(_power4, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 5) canvas.drawBitmap(_power5, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 6) canvas.drawBitmap(_power6, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 7) canvas.drawBitmap(_power7, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 8) canvas.drawBitmap(_power8, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 9) canvas.drawBitmap(_power9, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 10) canvas.drawBitmap(_power10, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 11) canvas.drawBitmap(_power11, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 12) canvas.drawBitmap(_power12, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 13) canvas.drawBitmap(_power13, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 14) canvas.drawBitmap(_power14, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(compteur == 15) 
		{
			canvas.drawBitmap(_power15, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
			monterCompteur = false;
		}
		if(monterCompteur) compteur++;
		else compteur--;
		if(ecranAppuie) 
		{
			angleChoisit = true;
			catapulte.angle = compteur+3;
			compteur = 0;
			ecranAppuie = false;
		}
	}
	
	public void afficherAngle(Canvas canvas)
	{
		if(catapulte.angle == 0) canvas.drawBitmap(_power0, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 1) canvas.drawBitmap(_power1, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 2) canvas.drawBitmap(_power2, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 3) canvas.drawBitmap(_power3, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 4) canvas.drawBitmap(_power4, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 5) canvas.drawBitmap(_power5, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 6) canvas.drawBitmap(_power6, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 7) canvas.drawBitmap(_power7, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 8) canvas.drawBitmap(_power8, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 9) canvas.drawBitmap(_power9, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 10) canvas.drawBitmap(_power10, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 11) canvas.drawBitmap(_power11, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 12) canvas.drawBitmap(_power12, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 13) canvas.drawBitmap(_power13, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 14) canvas.drawBitmap(_power14, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 15) canvas.drawBitmap(_power15, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
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

	public static Bitmap rotateImage(Bitmap src, float degree) 
	{
	        // create new matrix
	        Matrix matrix = new Matrix();
	        // setup rotation degree
	        matrix.postRotate(degree);
	        Bitmap bmp = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
	        return bmp;
	}
	
}
