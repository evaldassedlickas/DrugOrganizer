package evased.drugorganizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;


public class IndicatorView extends View {
    public static final int NOTEXECUTED =0;
    public static final int SUCCESS =1;
    public static final int FAILED =2;
    public static final int TRIANGLE =2;
    public static final int ARRAY_SIZE =3;

    private int responseResultCount;

    int state = NOTEXECUTED;

    public IndicatorView (Context context){super(context);}
    public IndicatorView (Context context, AttributeSet attrs) {super(context,attrs);}
    public IndicatorView (Context context, AttributeSet attrs, int defStyleAttr)
    {super(context,attrs, defStyleAttr);}

    public int getState(){
        return state;
    }
    public int getResponseResultCount(){
        return responseResultCount;
    }

    public void setState(int state){
        this.state=state;
    }

    public void setResponseResultCount(int count){
        this.responseResultCount=count;
    }
    Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        //    Paint paint;

        switch(state){

            case SUCCESS:
                paint= new Paint();
                paint.setColor(Color.GREEN);
                paint.setStrokeWidth(20f);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                /*
                drawing three lines to make triangle
                 */
                canvas.drawLine(0, height, width, height, paint);
                canvas.drawLine(width, height, width/2, 0, paint);
                canvas.drawLine(width/2, 0, 0, height, paint);
                break;








            case ARRAY_SIZE:
                paint= new Paint();
                paint.setColor(Color.YELLOW);
                //paint.setStrokeWidth(20f);
                paint.setTextSize(60);
                /*
                drawing response array size as text to indicator
                 */
                canvas.drawText(String.valueOf(responseResultCount) + " in JSON array", 0, height/2, paint);
                break;

            case FAILED:
                paint= new Paint();
                paint.setColor(Color.RED);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0,0,width, height, paint);
                canvas.drawLine(0, height, width, 0, paint);
                break;
            default:
                break;

        }

    }


            /*
            case SUCCESS:
                paint= new Paint();
                paint.setColor(Color.GREEN);
                paint.setStrokeWidth(20f);
                canvas.drawLine(0,0,width/2, height, paint);
                canvas.drawLine(width/2, height, width, height/2, paint);
                break;

             */

}
