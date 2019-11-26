package evased.drugorganizer;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import java.sql.Time;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class IndicatingView extends View {
    public static final int NOTEXECUTED =0;
    public static final int SUCCESS =1;
    public static final int FAILED =2;
    public static final int TRIANGLE =2;
    private ArrayDeque<Rect> rects = new ArrayDeque<>();
    int state = NOTEXECUTED;

    public IndicatingView (Context context){super(context);}
    public IndicatingView (Context context, AttributeSet attrs) {super(context,attrs);}
    public IndicatingView (Context context, AttributeSet attrs, int defStyleAttr)
    {super(context,attrs, defStyleAttr);}

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state=state;
    }
    Paint paint = new Paint();
    Paint paint2 = new Paint();
    private Paint mPaint = new Paint();
    private Rect rectangle;
    private int counter;
    private ArrayList<Paint> paints = new ArrayList<>();

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        Rect nextRectangle = rects.pollFirst();

        for(int i = 0; i < 5; i++){
            if (nextRectangle != null){
                Paint p = new Paint();
                p.setColor(Color.GREEN);
                paints.add(p);
                canvas.drawRect(nextRectangle, p);
            }
            invalidate();
            //nextRectangle = rects.pollFirst();
            //postInvalidateDelayed(TimeUnit.SECONDS.toMillis(1));


        }

/*
        if (nextRectangle != null){
            Paint p = new Paint();
            p.setColor(Color.GREEN);
            paints.add(p);
          canvas.drawRect(nextRectangle, p);
            if (rects.size() > 0) {
                //postInvalidateDelayed(TimeUnit.SECONDS.toMillis(2));

                //postInvalidateDelayed(TimeUnit.SECONDS.toMillis(1));
               // mPaint = new Paint();
                /// invalidate();
            }
        }
*/


        //    Paint paint;

        /* case FAILED:
                paint= new Paint();
                paint.setColor(Color.RED);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0,0,width, height, paint);
                canvas.drawLine(0, height, width, 0, paint);
                break;*/

        /*
        switch(state){
            case SUCCESS:
                paint= new Paint();
                paint.setColor(Color.GREEN);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0,0,width/2, height, paint);
                canvas.drawLine(width/2, height, width, height/2, paint);
                break;

            case TRIANGLE:
                Rect r = new Rect(0, 0, 600, 300);

                paint.setColor(Color.YELLOW);
                canvas.drawRect(r, paint);
                paint= new Paint();
                paint.setColor(Color.YELLOW);
                paint.setStrokeWidth(20f);
                paint.setStyle(Paint.Style.FILL);

                canvas.drawLine(height,width/2, height,0, paint);
                canvas.drawLine(0,0, 0,height/2, paint);
                canvas.drawLine(0, height/2, height, width/2, paint);
                canvas.drawLine(0, 0, height, 0, paint);
                break;
            default:
                break;

        }
        */
        int sideLength = 50;
// create a rectangle that we'll draw later
        /*

        rectangle = new Rect(0, 0, 200, sideLength);


        // create the Paint and set its color


        paint= new Paint();
        paint.setColor(Color.GRAY);
        canvas.drawRect(rectangle, paint);
        */



/*
        rectangle = new Rect(200, 0, 400, sideLength);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        canvas.drawRect(rectangle, paint2);

        */

        //paint.setStrokeWidth(20f);
       // canvas.drawLine(0,0,width/2, height, paint);
        //canvas.drawLine(width/2, height, width, height/2, paint);

    }

    public void drawBar(List<Rect> rcts, int color){
        rects.addAll(rcts);
        mPaint = new Paint();
        mPaint.setColor(color);
        /*

        new CountDownTimer(4000, 1000){
            public void onTick(long millisUntilFinished){
                canvas.drawRect(rects.get(counter), mPaint);
                counter++;
            }
            public  void onFinish(){
                //
            }
        }.start();
        */


        //invalidate();
       postInvalidateDelayed(TimeUnit.SECONDS.toMillis(1));

    }
}
