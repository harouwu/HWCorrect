package com.example.android.navigationdrawerexample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.example.android.navigationdrawerexample.DisplayHomeworksActivity.PlaceholderFragment;

import android.app.Activity;  
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;  
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;  
import android.graphics.Color;  
import android.graphics.Matrix;
import android.graphics.Paint;  
import android.graphics.Path;  
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;  
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;  
import android.view.SubMenu;
import android.view.SurfaceHolder;  
import android.view.SurfaceView;  
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;  
import android.view.WindowManager;  
import android.view.SurfaceHolder.Callback;  
import android.widget.EditText;
import android.widget.Toast;
 
 
public class DrawActivity extends Activity {  
 
    static MyView mAnimView = null;  
 
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    // ȫ����ʾ����  
	    //requestWindowFeature(Window.FEATURE_NO_TITLE);  
	    //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
	        //WindowManager.LayoutParams.FLAG_FULLSCREEN);  
	    // ��ʾ�Զ������ϷView  
	    //fuck u
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    mAnimView = new MyView(this);  
	    setContentView(mAnimView);
    }
    /**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_draw,
					container, false);
			return rootView;
		}
	}

    @Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_MENU)  
        {             
            super.openOptionsMenu();  
              
            return true;  
        }  
        else  
        {   
            return super.onKeyDown(keyCode, event);  
        }         
    } 
    
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
     // TODO Auto-generated method stub
    	menu.add(0, 0, 0, "����");
    	menu.add(0, 2, 2, "�ָ�");
        menu.add(0, 4, 4, "���");  
        menu.add(0, 6, 6, "����");
        SubMenu graphcolor = menu.addSubMenu(0, 8, 8, "����ͼ����ɫ..");
        SubMenu bgcolor = menu.addSubMenu(0, 9, 9, "���ı�����ɫ..");
        menu.add(0, 10, 10, "ѡ�񱳾�ͼƬ..");
        menu.add(0, 12, 12, "����ȡ����..");
        ///////////////////////////////
        menu.add(0, 44, 44, "��һҳ");
        menu.add(0, 46, 46, "��һҳ");
        ////////////////////////////////
        graphcolor.add(1, 15, 15, "��ɫ");
        graphcolor.add(1, 16, 16, "��ɫ");
        graphcolor.add(1, 17, 17, "��ɫ");
        graphcolor.add(1, 18, 18, "��ɫ");
        graphcolor.add(1, 19, 19, "��ɫ");
        graphcolor.add(1, 20, 20, "��ɫ");
        graphcolor.add(1, 21, 21, "��ɫ");
        graphcolor.add(1, 22, 22, "��ɫ");
        bgcolor.add(2, 34, 34, "��ɫ");
        bgcolor.add(2, 35, 35, "��ɫ");
        bgcolor.add(2, 36, 36, "��ɫ");
        bgcolor.add(2, 37, 37, "��ɫ");
        bgcolor.add(2, 38, 38, "��ɫ");
        bgcolor.add(2, 39, 39, "��ɫ");
        bgcolor.add(2, 40, 40, "��ɫ");
        bgcolor.add(2, 41, 41, "��ɫ");
        return super.onCreateOptionsMenu(menu);   
    }
    
    @Override  
    public boolean onMenuItemSelected(int featureId, MenuItem item) {  
        // TODO Auto-generated method stub
    	if(item.getItemId()==0)
    	{
    		mAnimView.undo();
    	}
    	if(item.getItemId()==2)
    	{
    		mAnimView.redo();
    	}
        if(item.getItemId()==4)  
        {  
        	mAnimView.clear();
        }            
        if(item.getItemId()==6)  
        {  
        	mAnimView.save();
        }
        if(item.getItemId()==10)
        {
        	getbgpic();
        }
        if(item.getItemId()==12)
        {
        	camera();
        }
        if(item.getItemId()>=15 && item.getItemId()<=22)  
        {  
        	mAnimView.changecolor(item.getItemId()-15);
        }
        if(item.getItemId()>=34 && item.getItemId()<=41)  
        {  
        	mAnimView.changebg(item.getItemId()-34);
        }
        ////////////////////
        if (item.getItemId()==44) {
			//nextpage();
		}
        if (item.getItemId()==46) {
			//prevpage();
		}
        ////////////////
        /*if(item.getItemId()==30)
        {
        	final EditText inputServer = new EditText(this);
            inputServer.setFocusable(true);
            inputServer.setText(mAnimView.comment);
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setTitle("��������").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
        		@Override
        		public void onClick(DialogInterface dialog, int which) {
        			mAnimView.comment = inputServer.getText().toString();
        		}
        	}).setNegativeButton("ȡ��", null).show();       	
        }*/
        return super.onMenuItemSelected(featureId, item);  
    }  
 
    public void getbgpic() {
    	Intent intent=new Intent();
        //�ƶ����ݵ�����Ϊͼ��
        intent.setType("image/*");
        //�ƶ�����ϵͳ���ݵ�action
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //��ʾϵͳ���
        startActivityForResult(intent, 1);        
    }
    
    public void camera() {
    	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
        startActivityForResult(intent, 2);
    }
    
    @Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (resultCode == RESULT_OK && requestCode == 1) {
        	mAnimView.background = null;
        	Bundle extras = data.getExtras();
        	Bitmap bitmap = null;
        	if (null != extras){
        		bitmap = (Bitmap)extras.get("data");
        		if (bitmap.getWidth() > bitmap.getHeight()) {
        			Matrix m = new Matrix();
        			m.setRotate(90, (float)bitmap.getWidth()/2, (float)bitmap.getHeight()/2);
        			m.postScale((float)getWindowManager().getDefaultDisplay().getWidth()/bitmap.getHeight(), (float)getWindowManager().getDefaultDisplay().getHeight()/bitmap.getWidth());
        			mAnimView.background = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
        		}
        		else {
        			Matrix m = new Matrix();
           			m.postScale((float)getWindowManager().getDefaultDisplay().getWidth()/bitmap.getWidth(), (float)getWindowManager().getDefaultDisplay().getHeight()/bitmap.getHeight());
        			mAnimView.background = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
        		}
        	} else {
            	Uri uri = data.getData();
            	if (uri != null) {
            		bitmap = BitmapFactory.decodeFile(uri.getPath());
            		if (bitmap.getWidth() > bitmap.getHeight()) {
            			Matrix m = new Matrix();
            			m.setRotate(90, (float)bitmap.getWidth()/2, (float)bitmap.getHeight()/2);
               			m.postScale((float)getWindowManager().getDefaultDisplay().getWidth()/bitmap.getHeight(), (float)getWindowManager().getDefaultDisplay().getHeight()/bitmap.getWidth());
            			mAnimView.background = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            		}
            		else {
            			Matrix m = new Matrix();
               			m.postScale((float)getWindowManager().getDefaultDisplay().getWidth()/bitmap.getWidth(), (float)getWindowManager().getDefaultDisplay().getHeight()/bitmap.getHeight());
            			mAnimView.background = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            		}
            	}
        	}
        }
        else if (resultCode == RESULT_OK && requestCode == 2) {
        	Bundle bundle = data.getExtras();  
            Bitmap bitmap = (Bitmap) bundle.get("data");
            if (bitmap.getWidth() > bitmap.getHeight()) {
    			Matrix m = new Matrix();
    			m.setRotate(90, (float)bitmap.getWidth()/2, (float)bitmap.getHeight()/2);
    			m.postScale((float)getWindowManager().getDefaultDisplay().getWidth()/bitmap.getHeight(), (float)getWindowManager().getDefaultDisplay().getHeight()/bitmap.getWidth());
    			mAnimView.background = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    		}
    		else {
    			Matrix m = new Matrix();
       			m.postScale((float)getWindowManager().getDefaultDisplay().getWidth()/bitmap.getWidth(), (float)getWindowManager().getDefaultDisplay().getHeight()/bitmap.getHeight());
    			mAnimView.background = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    		}
        }
        super.onActivityResult(requestCode, resultCode, data);  
    }   
    
    
    
    public class MyView extends SurfaceView implements Callback,Runnable {  
 
     /**ÿ50֡ˢ��һ����Ļ**/    
    public static final int TIME_IN_FRAME = 5;   
    
    /** ��Ϸ���� **/  
    Paint mPaint[] = new Paint[8];
    Paint mTextPaint = new Paint();
    String comment = "";
    
    SurfaceHolder mSurfaceHolder = null;
    
    private ArrayList<Path> PathList = new ArrayList();
    private ArrayList<Paint> PaintList = new ArrayList();
    
    private ArrayList<Path> Pathundo = new ArrayList();
    private ArrayList<Paint> Paintundo = new ArrayList();
    
    int color = 2;
    int bgcolor = Color.WHITE;
 
    /** ������Ϸ����ѭ�� **/  
    boolean mRunning = false;  
    
    /** ��Ϸ���� **/  
    Canvas mCanvas = null;
    
    Bitmap background = null;

    /**������Ϸѭ��**/  
    boolean mIsRunning = false;  
      
    /**���߷���**/  
    private Path mPath;  
      
    private float mposX, mposY;  
      
    public MyView(Context context) {  
        super(context);  
        /** ���õ�ǰViewӵ�п��ƽ��� **/  
        this.setFocusable(true);  
        /** ���õ�ǰViewӵ�д����¼� **/  
        this.setFocusableInTouchMode(true);  
        /** �õ�SurfaceHolder���� **/  
        mSurfaceHolder = this.getHolder();  
        /** ��mSurfaceHolder��ӵ�Callback�ص������� **/  
        mSurfaceHolder.addCallback(this);  
        /** �������� **/
        mCanvas = new Canvas();
        /** �������߻��� **/
        for (int i = 0; i < mPaint.length; i++) {
            mPaint[i] = new Paint();    
            /**���û��ʿ����**/  
            mPaint[i].setAntiAlias(true);  
            /**���ʵ�����**/  
            mPaint[i].setStyle(Paint.Style.STROKE);  
            /**���û��ʱ�ΪԲ��״**/  
            mPaint[i].setStrokeCap(Paint.Cap.ROUND);  
            /**�����ߵĿ��**/  
            mPaint[i].setStrokeWidth(3);  
            /**����·������**/  
        }
        mPaint[0].setColor(Color.BLACK);
        mPaint[1].setColor(Color.WHITE);
        mPaint[2].setColor(Color.RED);
        mPaint[3].setColor(Color.BLUE);
        mPaint[4].setColor(Color.GREEN);
        mPaint[5].setColor(Color.argb(255, 255, 0, 255));
        mPaint[6].setColor(Color.argb(255, 255, 255, 0));
        mPaint[7].setColor(Color.GRAY);
        
        /** �������ֻ��� **/
        //mTextPaint.setTextSize(60);
        //mTextPaint.setColor(Color.RED);
    } 
 
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
        /** �õ�������״̬ **/  
        int action = event.getAction();  
        float x = event.getX();  
        float y = event.getY();  
        switch (action) {  
        // �������µ��¼�  
        case MotionEvent.ACTION_DOWN:  
        /**�������߹켣��� X Y����**/  
        	PathList.add(new Path());
        	PaintList.add(mPaint[color]);
        	mPath = PathList.get(PathList.size()-1);
        	mPath.moveTo(x, y);  
        	break;  
        // �����ƶ����¼�  
        case MotionEvent.ACTION_MOVE:  
        /**�������߹켣**/  
        //����1 ��ʼ��X����  
        //����2 ��ʼ��Y����  
        //����3 ������X����  
        //����4 ������Y����  
        	mPath.quadTo(mposX, mposY, x, y);  
        	break;  
        // ����̧����¼�  
        case MotionEvent.ACTION_UP:  
        /**����̧������·���켣**/ 
        //mPath.reset();
        	Pathundo.clear();
        	Paintundo.clear();
        break;  
        }  
       //��¼��ǰ����X Y����  
        mposX = x;  
        mposY = y;  
        return true;  
    }  
          
    private void Draw(Canvas canvas) {  
        /**��ջ���**/  
    	canvas.drawColor(bgcolor);
    	if (background != null) canvas.drawBitmap(background, 0, 0, null);
        /**��������**/  
        int i;
        for (i = 0; i < PathList.size(); i++) {          
        	canvas.drawPath(PathList.get(i), PaintList.get(i));
        }
        
        //canvas.drawText(comment, 0, getWindowManager().getDefaultDisplay().getHeight(), mTextPaint);
        /**��¼��ǰ����λ��**/  
        //mCanvas.drawText("��ǰ���� X��" + mposX, 0, 20,mTextPaint);  
        //mCanvas.drawText("��ǰ���� Y��" + mposY, 0, 40,mTextPaint);  
    }  
      
    @Override  
    public void surfaceChanged(SurfaceHolder holder, int format, int width,  
        int height) {  
 
    }  
 
    @Override  
    public void surfaceCreated(SurfaceHolder holder) {  
        /**��ʼ��Ϸ��ѭ���߳�**/  
        mIsRunning = true;  
        new Thread(this).start();  
    }  
 
    @Override  
    public void surfaceDestroyed(SurfaceHolder holder) {  
        mIsRunning = false;  
    }
    
    public void undo() {
    	if (!PathList.isEmpty()) {
    		Path tmppath = PathList.remove(PathList.size()-1);
    		Pathundo.add(tmppath);
    		Paint tmppaint = PaintList.remove(PaintList.size()-1);
    		Paintundo.add(tmppaint);
    	}
    }
    
    public void redo() {
    	if (!Pathundo.isEmpty()) {
    		Path tmppath = Pathundo.remove(Pathundo.size()-1);
    		PathList.add(tmppath);
    		Paint tmppaint = Paintundo.remove(Paintundo.size()-1);
    		PaintList.add(tmppaint);    		
    	}
    }
    
    public void clear() {
    	for (Path p:PathList) {
    		p.reset();
    	}
    	PathList.clear();
    	PaintList.clear();
    	Pathundo.clear();
    	Paintundo.clear();
    	background = null;
    }
    
    public void changecolor(int newcolor) {
    	color = newcolor;
    	//PathList.add(new Path());
    	//PaintList.add(mPaint[color]);
    	//mPath = PathList.get(PathList.size()-1);
    	//mTextPaint.setColor(mPaint[color].getColor());
    }
    
    public void changebg(int newcolor) {
    	if(newcolor == 0)bgcolor = Color.BLACK;
    	if(newcolor == 1)bgcolor = Color.WHITE;
    	if(newcolor == 2)bgcolor = Color.RED;
    	if(newcolor == 3)bgcolor = Color.BLUE;
    	if(newcolor == 4)bgcolor = Color.GREEN;
    	if(newcolor == 5)bgcolor = Color.argb(255, 255, 0, 255);
    	if(newcolor == 6)bgcolor = Color.argb(255, 255, 255, 0);
    	if(newcolor == 7)bgcolor = Color.GRAY;
    	background = null;
    }
    
 
    @Override  
    public void run() {  
        while (mIsRunning) {  
 
        /** ȡ�ø�����Ϸ֮ǰ��ʱ�� **/  
        long startTime = System.currentTimeMillis();  
 
        /** ����������̰߳�ȫ�� **/  
        synchronized (mSurfaceHolder) {  
            /** �õ���ǰ���� Ȼ������ **/  
            mCanvas = mSurfaceHolder.lockCanvas();  
            Draw(mCanvas);
            /** ���ƽ����������ʾ����Ļ�� **/  
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);  
        }  
 
        /** ȡ�ø�����Ϸ������ʱ�� **/  
        long endTime = System.currentTimeMillis();  
 
        /** �������Ϸһ�θ��µĺ����� **/  
        int diffTime = (int) (endTime - startTime);  
 
        /** ȷ��ÿ�θ���ʱ��Ϊ50֡ **/  
        while (diffTime <= TIME_IN_FRAME) {  
            diffTime = (int) (System.currentTimeMillis() - startTime);  
            /** �̵߳ȴ� **/  
            Thread.yield();  
        }  
 
        }  
 
    }
    
    public void save() {           
		Bitmap mbitmap = Bitmap.createBitmap(getWindowManager().getDefaultDisplay().getWidth(), getWindowManager().getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
		Canvas savecv = new Canvas(mbitmap);
		Draw(savecv);
		
        File dir = new File("/mnt/sdcard/Drawer");
        if (!dir.exists()) {
        	dir.mkdir();
        }           
        String picname = ((Long)System.currentTimeMillis()).toString()+".png";
        try {
			FileOutputStream fos = new FileOutputStream(new File(dir.getPath() + "/" + picname));
			mbitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
			Toast toast=Toast.makeText(getApplicationContext(), "����Ϊ"+picname, Toast.LENGTH_SHORT);
			toast.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    }
    
    }  
} 