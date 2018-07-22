package com.self.quiz.components;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.widget.TextView;

import com.self.quiz.R;

/**
 * Author   :  Tomcat
 * Date     :  2018/7/19
 * CopyRight:  JinkeGroup
 */

@SuppressLint("AppCompatCustomView")
public class CountDownView extends TextView {
    private static final int TIME_MILLIS = 10000;
    private static final int SECOND_MILLIS = 1000;

    private int outLineColor = Color.BLACK;
    private int outLineWidth = 3;
    private ColorStateList colorStateList = ColorStateList.valueOf(Color.TRANSPARENT);
    private int inCircleColor = Color.GREEN;
    private int progressLineColor = Color.GREEN;
    private int progressLineWidth = 18;
    private Paint mPaint = new Paint();
    private RectF arcRect = new RectF();
    private int progress = TIME_MILLIS;
    private final Rect bounds = new Rect();

    private OnCountDownProgressChangedListener listener;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize(context, attrs);
    }

    private void initialize(Context context,AttributeSet attributeSet){
        mPaint.setAntiAlias(true);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.count_down_view);
        if (typedArray.hasValue(R.styleable.count_down_view_color)){
            colorStateList = typedArray.getColorStateList(R.styleable.count_down_view_color);
        }
        inCircleColor = colorStateList.getColorForState(getDrawableState(),Color.TRANSPARENT);
        typedArray.recycle();
    }

    public void setOutLineColor(@ColorInt int color){
        this.outLineColor = color;
        invalidate();
    }

    public void setOutLineWidth(int width){
        this.outLineWidth = width;
        invalidate();
    }

    public void setInCircleColor(@ColorInt int color){
        this.colorStateList = ColorStateList.valueOf(color);
        invalidate();
    }

    private void updateCircleColor(){
        int temp = colorStateList.getColorForState(getDrawableState(),Color.TRANSPARENT);
        if(inCircleColor!=temp){
            inCircleColor = temp;
            invalidate();
        }
    }

    public void setProgressLineColor(@ColorInt int color){
        this.progressLineColor = color;
        invalidate();
    }

    public void setProgressLineWidth(int width){
        this.progressLineWidth = width;
        invalidate();
    }

    public void setProgress(int p){
        this.progress = p;
        invalidate();
    }

    private int validateProgress(int p){
        if (progress>TIME_MILLIS)
            progress = TIME_MILLIS;
        else if (progress<0)
            progress = 0;
        return progress;
    }

    public int getProgress(){
        return progress;
    }


    public void start(){
        removeCallbacks(progressChangeTask);
        reset();
        post(progressChangeTask);
        if (listener!=null)
            listener.beforeProgress();
    }

    public void stop(){
        removeCallbacks(progressChangeTask);
    //    reset();
        if (listener!=null)
            listener.afterProgress();
    }

    @Override
    protected void onDraw(Canvas canvas){
        getDrawingRect(bounds);

        int size = bounds.height()>bounds.width() ? bounds.width() :bounds.height();
        float outterRadius = (size-10)/2;

        drawOutterCircle(canvas,outterRadius);

        Paint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        float y = bounds.centerY()-(paint.descent()+paint.ascent())/2;
        canvas.drawText(String.valueOf(10-((TIME_MILLIS-progress)/SECOND_MILLIS)),bounds.centerX(),y,paint);

        mPaint.setColor(progressLineColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressLineWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        int width = progressLineWidth;
        arcRect.set(bounds.left+width/2,bounds.top+width/2,bounds.right-width/2,bounds.bottom-width/2);
        canvas.drawArc(arcRect,-90,-360*progress/TIME_MILLIS,false,mPaint);
    }

    private void drawOutterCircle(Canvas canvas,float outterRadius){
     //   mPaint.setShadowLayer(10,2,2,Color.BLUE);
     //   setLayerType(LAYER_TYPE_SOFTWARE,null);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(outLineWidth);
        mPaint.setColor(outLineColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        canvas.drawCircle(bounds.centerX(),bounds.centerY(),outterRadius-10,mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int lineWidth = 4*(outLineWidth+progressLineWidth);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int size = (width>height? width:height)+lineWidth;
        setMeasuredDimension(size,size);
    }

    @Override
    protected void drawableStateChanged(){
        super.drawableStateChanged();
        updateCircleColor();
    }

    public void setProgressChangeListener(OnCountDownProgressChangedListener listener){
        this.listener = listener;
    }

    private Runnable progressChangeTask = new Runnable() {
        @Override
        public void run() {
            removeCallbacks(this);
            progress-=100;
            if (progress>=0 && progress<=TIME_MILLIS){
                if (listener!=null){
                    listener.inPgregress(progress/SECOND_MILLIS);
                }
                invalidate();
                postDelayed(progressChangeTask,100);
            }else if (progress<0){
                if (listener!=null){
                    listener.afterProgress();
                    removeCallbacks(this);
                }
            }
        }
    };

    private void reset(){
        progress = TIME_MILLIS;
        invalidate();
    }


    public interface OnCountDownProgressChangedListener{
        void beforeProgress();
        void inPgregress(int p);
        void afterProgress();
    }



}
