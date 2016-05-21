package com.example.dogelaucher;


import java.text.DecimalFormat;

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

public class GameLance extends View {



	protected Bitmap _fond;
	protected Bitmap _dogeCourant;
	protected Bitmap _doge;
	protected Bitmap _doge2;
	protected Bitmap _doge3;
	protected Bitmap _doge4;
	protected Bitmap _doge5;
	protected Bitmap _doge6;
	protected Bitmap _doge7;
	protected Bitmap _doge8;
	protected Bitmap _doge9;
	
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
	
	
	protected Doge doge;
	protected Catapulte catapulte;
	
	protected Paint paint;
	protected MainActivity context;

	protected double LARGEUR_ECRAN = 0;
	protected double HAUTEUR_ECRAN = 0;
	protected boolean ecranAppuie = false;
	protected int posAppuie;
	protected int posAppuieY;
	protected int fondBougeGauche = 0;
	protected int fondBougeHaut= 0;
	
	protected boolean solTouche = true;
	protected double puissanceInitiale = 0;
	protected double angleInitial = 0;
	protected boolean nePlusMonter = false;
	protected boolean nePlusDescendre = false;
	protected DecimalFormat df = new DecimalFormat("########.0"); 
	protected View GameLance;
	protected boolean arret=false;
	protected int tourne =0;
	
	public GameLance(Context context, int h, int l,Doge d,Catapulte c)
	{
		super(context);
		
		this.context = (MainActivity) context;
		LARGEUR_ECRAN = l;
		HAUTEUR_ECRAN = h;
		fondBougeHaut = -3*h;
		this.catapulte = c;
		this.doge = d;
		initialiserVariable();
		
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
		}
		
		return true;
	}
	
	protected void onDraw(Canvas canvas)
	{
		
		afficherJeu(canvas);
		invalidate();

	}
	
	public void afficherJeu(Canvas canvas)
	{
		// =========== ECRITURE DU TEXTE =========
		Paint paint = new Paint();
		canvas.drawPaint(paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize((float) (HAUTEUR_ECRAN*0.055));
		paint.setFakeBoldText(true);
		// =======================================
		
	
		bougerFond(canvas);
		
		dessinerDoge(canvas);
		//canvas.drawBitmap(_catapulte, catapulte.posX, catapulte.posY, null);
		
		

		afficherPower(canvas);
		afficherAngle(canvas);
		
		canvas.drawText("Angle",(float) LARGEUR_ECRAN*(float)0.25625,(float) HAUTEUR_ECRAN  * (float)0.20,paint);
		canvas.drawText("Power",(float) LARGEUR_ECRAN*(float)0.25625,(float) HAUTEUR_ECRAN  * (float)0.10,paint);
		canvas.drawText(Integer.toString((int) doge.longueur)+"m",(float) LARGEUR_ECRAN*(float)0.80,(float) HAUTEUR_ECRAN  * (float)0.10,paint);
		
		if(catapulte.angle - Math.sqrt(Math.pow(angleInitial,2)) <=0)
		{
			canvas.drawText("0m",(float) LARGEUR_ECRAN*(float)0.80,(float) HAUTEUR_ECRAN  * (float)0.20,paint);
		}
		else canvas.drawText(Integer.toString((int) doge.hauteur+2)+"m",(float) LARGEUR_ECRAN*(float)0.80,(float) HAUTEUR_ECRAN  * (float)0.20,paint);
		
		canvas.drawText(Integer.toString((int) catapulte.angle),(float) LARGEUR_ECRAN*(float)0.60,(float) HAUTEUR_ECRAN  * (float)0.20,paint);
		canvas.drawText(Integer.toString((int) catapulte.puissance),(float) LARGEUR_ECRAN*(float)0.60,(float) HAUTEUR_ECRAN  * (float)0.10,paint);
		if(arret) canvas.drawText("BRAVO",(float) LARGEUR_ECRAN*(float)0.50,(float) HAUTEUR_ECRAN  * (float)0.50,paint);
		if(arret && ecranAppuie)
		{
			View game = new Game(context, (int) HAUTEUR_ECRAN, (int) LARGEUR_ECRAN);
			context.setContentView(game);
		}
	}
	
	public void dessinerDoge(Canvas canvas)
	{


		if(tourne/2 == 0) _dogeCourant = _doge;
		else if(tourne/2 == 1)_dogeCourant = _doge;
		else if(tourne/2 == 2)_dogeCourant = _doge2;
		else if(tourne/2 == 3)_dogeCourant = _doge3;
		else if(tourne/2 == 4)_dogeCourant = _doge4;
		else if(tourne/2 == 5)_dogeCourant = _doge5;
		else if(tourne/2 == 6)_dogeCourant = _doge6;
		else if(tourne/2 == 7)_dogeCourant = _doge7;
		else if(tourne/2 == 8)_dogeCourant = _doge8;
		else if(tourne/2 == 9)
			{
			_dogeCourant = _doge9;
			tourne=0;
			}
		
		
		canvas.drawBitmap(_dogeCourant, doge.posX,doge.posY, null); 
		if(!arret)
		{
			tourne++;
		}
	}
	public void bougerFond(Canvas canvas)
	{
		if(solTouche) 
		{
			puissanceInitiale = catapulte.puissance;
			angleInitial = catapulte.angle;
			solTouche = false;
		}
		
		
		fondBougeGauche -= puissanceInitiale;
		fondBougeHaut += (angleInitial/3);
		
		if(catapulte.angle - (int) Math.sqrt(Math.pow(angleInitial,2)) <0)
		{
			angleInitial = - catapulte.angle;
		}
		else
		{
			angleInitial = angleInitial - 0.05;
		}
		
		doge.longueur += (puissanceInitiale/50);
		doge.hauteur = (catapulte.angle - Math.sqrt(Math.pow(angleInitial,2)))*4;
		
		if(nePlusDescendre)
		{
			if(puissanceInitiale > 0)
			{
				puissanceInitiale -= 0.05;
			}
			else
			{
				puissanceInitiale=0;
				arret = true;
			}
		}
		
		if(fondBougeGauche < - 2*LARGEUR_ECRAN)
		{
			fondBougeGauche = 0;
		}
		
		if (fondBougeHaut > 0)
		{
			nePlusMonter = true;
		}
		else nePlusMonter = false;
		
		if(fondBougeHaut <= -3*HAUTEUR_ECRAN)
		{
			nePlusDescendre = true;
		}
		else nePlusDescendre = false;
		
		if(nePlusMonter)	
		{
			canvas.drawBitmap(_fond, fondBougeGauche,0, null);
		}
		else if(nePlusDescendre){
			
			
			canvas.drawBitmap(_fond, fondBougeGauche,(float) (-3*HAUTEUR_ECRAN), null);
			
		}
			
		else
		{
			canvas.drawBitmap(_fond, fondBougeGauche,fondBougeHaut, null);
		}
		//canvas.drawBitmap(_fond, fondBougeGauche,(float) -HAUTEUR_ECRAN, null);
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

	public void afficherAngle(Canvas canvas)
	{
		if(catapulte.angle == 3) canvas.drawBitmap(_power0, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 4) canvas.drawBitmap(_power1, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 5) canvas.drawBitmap(_power2, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 6) canvas.drawBitmap(_power3, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 7) canvas.drawBitmap(_power4, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 8) canvas.drawBitmap(_power5, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 9) canvas.drawBitmap(_power6, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 10) canvas.drawBitmap(_power7, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 11) canvas.drawBitmap(_power8, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 12) canvas.drawBitmap(_power9, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 13) canvas.drawBitmap(_power10, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 14) canvas.drawBitmap(_power11, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 15) canvas.drawBitmap(_power12, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 16) canvas.drawBitmap(_power13, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 17) canvas.drawBitmap(_power14, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		if(catapulte.angle == 18) canvas.drawBitmap(_power15, (float)(LARGEUR_ECRAN * 0.05),(float)(HAUTEUR_ECRAN * 0.15), null);
		
	}
	
	public void setDoge(Doge d)
	{
		this.doge = d;
	}
	
	public void setCatapulte(Catapulte c)
	{
		this.catapulte = c;
	}
	
	public void initialiserVariable()
	{
		
		_fond = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fondmega), HAUTEUR_ECRAN*4,LARGEUR_ECRAN*4 );
		_catapulte = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.canon), HAUTEUR_ECRAN*0.33,LARGEUR_ECRAN*0.236 );
		_doge = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
			
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
			
	
		_doge2 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead2), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		_doge3 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead3), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		_doge4 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead4), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		_doge5 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead5), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		_doge6 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead6), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		_doge7 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead7), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		_doge8 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead8), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		_doge9 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.dogehead9), HAUTEUR_ECRAN*0.20,LARGEUR_ECRAN*0.15 );
		
	//	catapulte.angle -=3;
		// _catapulte = rotateImage(_catapulte, (float) catapulte.angle);
		
		doge.posX = (float) (LARGEUR_ECRAN * 0.21);
		doge.posY = (float) (HAUTEUR_ECRAN * 0.60);
		catapulte.posX = (float) (LARGEUR_ECRAN * 0.01);
		catapulte.posY = (float) (HAUTEUR_ECRAN * 0.65);
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
