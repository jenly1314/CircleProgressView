package com.king.view.circleprogressview;

import java.lang.String;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;


/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class CircleProgressView extends View {


    /**
     * 画笔
     */
    private Paint mPaint;

    /**
     * 文本画笔
     */
    private TextPaint mTextPaint;

    /**
     * 笔画描边的宽度
     */
    private float mStrokeWidth;

    /**
     * 开始角度(默认从12点钟方向开始)
     */
    private int mStartAngle = 270;
    /**
     * 扫描角度(一个圆)
     */
    private int mSweepAngle = 360;

    /**
     * 圆心坐标x
     */
    private float mCircleCenterX;
    /**
     * 圆心坐标y
     */
    private float mCircleCenterY;

    /**
     * 圆正常颜色
     */
    private int mNormalColor = 0xFFC8C8C8;
    /**
     * 进度颜色
     */
    private int mProgressColor = 0xFF4FEAAC;

    /**
     * 是否使用着色器
     */
    private boolean isShader = true;

    /**
     * 着色器
     */
    private Shader mShader;

    /**
     * 着色器颜色
     */
    private int[] mShaderColors = new int[]{0xFF4FEAAC,0xFFA8DD51,0xFFE8D30F,0xFFA8DD51,0xFF4FEAAC};

    /**
     * 半径
     */
    private float mRadius;

    /**
     * 内圆与外圆的间距
     */
    private float mCirclePadding;

    /**
     * 刻度间隔的角度大小
     */
    private int mTickSplitAngle = 5;

    /**
     * 刻度的角度大小
     */
    private int mBlockAngle = 1;

    /**
     * 总刻度数
     */
    private int mTotalTickCount;

    /**
     * 最大进度
     */
    private int mMax = 100;

    /**
     * 当前进度
     */
    private int mProgress = 0;

    /**
     * 动画持续的时间
     */
    private int mDuration = 500;

    /**
     * 标签内容
     */
    private String mLabelText;

    /**
     * 字体大小
     */
    private float mLabelTextSize;

    /**
     * 字体颜色
     */
    private int mLabelTextColor = 0xFF333333;

    /**
     * 标签内容距圆形内间距
     */
    private float mLabelPaddingLeft;
    private float mLabelPaddingTop;
    private float mLabelPaddingRight;
    private float mLabelPaddingBottom;

    /**
     * 进度百分比
     */
    private int mProgressPercent;

    /**
     * 是否显示标签文字
     */
    private boolean isShowLabel = true;
    /**
     * 是否默认显示百分比为标签文字
     */
    private boolean isShowPercentText = true;
    /**
     * 是否显示外边框刻度
     */
    private boolean isShowTick = true;

    /**
     * 是否旋转
     */
    private boolean isTurn = false;

    /**
     * 是否是圆形线冒（圆角弧度）
     */
    private boolean isCapRound = true;

    private boolean isMeasureCircle = false;


    private OnChangeListener mOnChangeListener;


    public CircleProgressView(Context context) {
        this(context,null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    /**
     * 初始化
     * @param context
     * @param attrs
     */
    private void init(Context context,AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CircleProgressView);

        DisplayMetrics displayMetrics = getDisplayMetrics();
        mStrokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,12,displayMetrics);

        mLabelTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,30,displayMetrics);

        mCirclePadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,displayMetrics);

        int size = a.getIndexCount();
        for(int i = 0;i < size;i++){
            int attr = a.getIndex(i);
            if (attr == R.styleable.CircleProgressView_cpvStrokeWidth) {
                mStrokeWidth = a.getDimension(attr,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,12,displayMetrics));
            }else if(attr == R.styleable.CircleProgressView_cpvNormalColor){
                mNormalColor = a.getColor(attr,0xFFC8C8C8);
            }else if(attr == R.styleable.CircleProgressView_cpvProgressColor){
                mProgressColor = a.getColor(attr,0xFF4FEAAC);
                isShader = false;
            }else if(attr == R.styleable.CircleProgressView_cpvStartAngle){
                mStartAngle = a.getInt(attr,270);
            }else if(attr == R.styleable.CircleProgressView_cpvSweepAngle){
                mSweepAngle = a.getInt(attr,360);
            }else if(attr == R.styleable.CircleProgressView_cpvMax){
                mMax = a.getInt(attr,100);
            }else if(attr == R.styleable.CircleProgressView_cpvProgress){
                mProgress = a.getInt(attr,0);
            }else if(attr == R.styleable.CircleProgressView_cpvDuration){
                mDuration = a.getInt(attr,500);
            }else if(attr == R.styleable.CircleProgressView_cpvLabelText){
                mLabelText = a.getString(attr);
            }else if(attr == R.styleable.CircleProgressView_cpvLabelTextSize){
                mLabelTextSize = a.getDimension(attr,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,30,displayMetrics));
            }else if(attr == R.styleable.CircleProgressView_cpvLabelTextColor){
                mLabelTextColor = a.getColor(attr,0xFF333333);
            }else if(attr == R.styleable.CircleProgressView_cpvShowLabel) {
                isShowLabel = a.getBoolean(attr, isShowLabel);
            }else if(attr == R.styleable.CircleProgressView_cpvShowTick){
                isShowTick = a.getBoolean(attr,isShowTick);
            }else if(attr == R.styleable.CircleProgressView_cpvCirclePadding){
                mCirclePadding = a.getDimension(attr,TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,10,displayMetrics));
            }else if(attr == R.styleable.CircleProgressView_cpvTickSplitAngle){
                mTickSplitAngle = a.getInt(attr,mTickSplitAngle);
            }else if(attr == R.styleable.CircleProgressView_cpvBlockAngle){
                mBlockAngle = a.getInt(attr,mBlockAngle);
            }else if(attr == R.styleable.CircleProgressView_cpvTurn){
                isTurn = a.getBoolean(attr,isTurn);
            }else if(attr == R.styleable.CircleProgressView_cpvCapRound){
                isCapRound = a.getBoolean(attr,isCapRound);
            }else if(attr == R.styleable.CircleProgressView_cpvLabelPaddingLeft){
                mLabelPaddingLeft = a.getDimension(attr,0);
            }else if(attr == R.styleable.CircleProgressView_cpvLabelPaddingTop){
                mLabelPaddingTop = a.getDimension(attr,0);
            }else if(attr == R.styleable.CircleProgressView_cpvLabelPaddingRight){
                mLabelPaddingRight = a.getDimension(attr,0);
            }else if(attr == R.styleable.CircleProgressView_cpvLabelPaddingBottom){
                mLabelPaddingBottom = a.getDimension(attr,0);
            }
        }

        isShowPercentText = TextUtils.isEmpty(mLabelText);

        a.recycle();
        mProgressPercent = (int)(mProgress * 100.0f / mMax);
        mPaint = new Paint();
        mTextPaint = new TextPaint();

        mTotalTickCount = (int)(1.0f * mSweepAngle / (mTickSplitAngle + mBlockAngle));

    }


    private DisplayMetrics getDisplayMetrics(){
        return getResources().getDisplayMetrics();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int defaultValue = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,200,getDisplayMetrics());

        int width = measureHandler(widthMeasureSpec,defaultValue);
        int height = measureHandler(heightMeasureSpec,defaultValue);

        //圆心坐标
        mCircleCenterX = (width + getPaddingLeft() - getPaddingRight())/ 2.0f;
        mCircleCenterY = (height + getPaddingTop() - getPaddingBottom())/ 2.0f;
        //计算间距
        int padding = Math.max(getPaddingLeft() + getPaddingRight(),getPaddingTop() + getPaddingBottom());
        //半径=视图宽度-横向或纵向内间距值 - 画笔宽度
        mRadius = (width - padding - mStrokeWidth) / 2.0f - mCirclePadding ;

        //默认着色器
        mShader = new SweepGradient(mCircleCenterX,mCircleCenterX,mShaderColors,null);
        isMeasureCircle = true;

        setMeasuredDimension(width,height);


    }

    /**
     * 测量
     * @param measureSpec
     * @param defaultSize
     * @return
     */
    private int measureHandler(int measureSpec,int defaultSize){

        int result = defaultSize;
        int measureMode = MeasureSpec.getMode(measureSpec);
        int measureSize = MeasureSpec.getSize(measureSpec);
        if(measureMode == MeasureSpec.EXACTLY){
            result = measureSize;
        }else if(measureMode == MeasureSpec.AT_MOST){
            result = Math.min(defaultSize,measureSize);
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas);
        drawText(canvas);
    }


    /**
     * 绘制弧形(默认为一个圆)
     * @param canvas
     */
    private void drawArc(Canvas canvas){
        mPaint.reset();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeWidth(mStrokeWidth);

        if(isShowTick){//是否显示外边框刻度
            float tickDiameter = mRadius * 2;
            float tickStartX = mCircleCenterX - mRadius;
            float tickStartY = mCircleCenterY - mRadius;
            RectF rectF = new RectF(tickStartX,tickStartY,tickStartX + tickDiameter,tickStartY + tickDiameter);

            final int currentBlockIndex = (int)(mProgressPercent / 100f * mTotalTickCount);
            if(isTurn){
                for (int i = 0; i < mTotalTickCount; i++) {
                    //底层未选中刻度
                    mPaint.setShader(null);
                    mPaint.setColor(mNormalColor);
                    //绘制外边框刻度
                    canvas.drawArc(rectF, i * (mBlockAngle + mTickSplitAngle) + mStartAngle, mBlockAngle, false, mPaint);
                }

                for (int i = currentBlockIndex; i < currentBlockIndex + currentBlockIndex; i++) {
                    //已选中的刻度
                    if (isShader && mShader != null) {
                        mPaint.setShader(mShader);
                    } else {
                        mPaint.setColor(mProgressColor);
                    }
                    //绘制外边框刻度
                    canvas.drawArc(rectF, i * (mBlockAngle + mTickSplitAngle) + mStartAngle, mBlockAngle, false, mPaint);
                }
            }else{
                for (int i = 0; i < mTotalTickCount; i++) {
                    if (i < currentBlockIndex) {
                        //已选中的刻度
                        if (isShader && mShader != null) {
                            mPaint.setShader(mShader);
                        } else {
                            mPaint.setColor(mProgressColor);
                        }
                        //绘制外边框刻度
                        canvas.drawArc(rectF, i * (mBlockAngle + mTickSplitAngle) + mStartAngle, mBlockAngle, false, mPaint);
                    } else if(mNormalColor != 0){
                        //未选中的刻度
                        mPaint.setShader(null);
                        mPaint.setColor(mNormalColor);
                        //绘制外边框刻度
                        canvas.drawArc(rectF, i * (mBlockAngle + mTickSplitAngle) + mStartAngle, mBlockAngle, false, mPaint);
                    }

                }
            }

        }

        mPaint.setShader(null);
        if(isCapRound){
            mPaint.setStrokeCap(Paint.Cap.ROUND);
        }

        //进度圆半径
        float circleRadius = isShowTick ? mRadius - mCirclePadding - mStrokeWidth : mRadius;
        float diameter = circleRadius * 2;
        float startX = mCircleCenterX - circleRadius;
        float startY = mCircleCenterY - circleRadius;
        RectF rectF1 = new RectF(startX,startY,startX + diameter,startY + diameter);

        if(mNormalColor != 0){
            mPaint.setColor(mNormalColor);
            //绘制底层弧形
            canvas.drawArc(rectF1,mStartAngle,mSweepAngle,false,mPaint);
        }

        //着色器不为空则设置着色器，反之用纯色
        if(isShader && mShader!=null){
            mPaint.setShader(mShader);
        }else{
            mPaint.setColor(mProgressColor);
        }

        if(isTurn){
            //绘制当前进度弧形
            canvas.drawArc(rectF1,mStartAngle + mSweepAngle * getRatio(),mSweepAngle * getRatio(),false,mPaint);
        }else{
            //绘制当前进度弧形
            canvas.drawArc(rectF1,mStartAngle,mSweepAngle * getRatio(),false,mPaint);
        }

    }

    /**
     * 绘制中间的文本
     * @param canvas
     */
    private void drawText(Canvas canvas){
        if(!isShowLabel){
            return;
        }
        mTextPaint.reset();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextSize(mLabelTextSize);
        mTextPaint.setColor(mLabelTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        // 计算文字高度 
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        // 计算文字baseline 
        float textBaseX = getWidth() / 2 + mLabelPaddingLeft - mLabelPaddingRight;
        float textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom + mLabelPaddingTop - mLabelPaddingBottom;
        if(isShowPercentText){//是否显示百分比
            canvas.drawText(mProgressPercent + "%",textBaseX,textBaseY,mTextPaint);
        }else if(!TextUtils.isEmpty(mLabelText)){//显示自定义文本
            canvas.drawText(mLabelText,textBaseX,textBaseY,mTextPaint);
        }

    }

    /**
     * 显示进度动画效果（根据当前已有进度开始）
     * @param progress
     */
    public void showAppendAnimation(int progress){
        showAnimation(mProgress,progress,mDuration);
    }

    /**
     * 显示进度动画效果
     * @param progress
     */
    public void showAnimation(int progress){
        showAnimation(progress,mDuration);
    }

    /**
     * 显示进度动画效果
     * @param progress
     * @param duration 动画时长
     */
    public void showAnimation(int progress,int duration){
        showAnimation(0,progress,duration);
    }

    /**
     * 显示进度动画效果，从from到to变化
     * @param from
     * @param to
     * @param duration 动画时长
     */
    public void showAnimation(int from,int to,int duration){
        showAnimation(from,to,duration,null);
    }

    /**
     * 显示进度动画效果，从from到to变化
     * @param from
     * @param to
     * @param duration 动画时长
     * @param listener
     */
    public void showAnimation(int from, int to, int duration, Animator.AnimatorListener listener){
        this.mDuration = duration;
        this.mProgress = from;
        ValueAnimator valueAnimator = ValueAnimator.ofInt(from,to);
        valueAnimator.setDuration(duration);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setProgress((int)animation.getAnimatedValue());
            }
        });

        if(listener!=null){
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.addListener(listener);
        }

        valueAnimator.start();
    }

    /**
     * 进度比例
     * @return
     */
    private float getRatio(){
        return mProgress * 1.0f /mMax;
    }

    /**
     * 设置最大进度
     * @param max
     */
    public void setMax(int max){
        this.mMax = max;
        invalidate();
    }

    /**
     * 设置当前进度
     * @param progress
     */
    public void setProgress(int progress){
        this.mProgress = progress;
        mProgressPercent = (int)(mProgress * 100.0f / mMax);
        invalidate();

        if(mOnChangeListener!=null){
            mOnChangeListener.onProgressChanged(mProgress,mMax);
        }
    }

    /**
     * 设置正常颜色
     * @param color
     */
    public void setNormalColor(int color){
        this.mNormalColor = color;
        invalidate();
    }


    /**
     * 设置着色器
     * @param shader
     */
    public void setShader(Shader shader){
        isShader = true;
        this.mShader = shader;
        invalidate();
    }

    /**
     * 设置进度颜色（通过着色器实现渐变色）
     * @param colors
     */
    public void setProgressColor(int... colors){
        if(isMeasureCircle){
            Shader shader = new SweepGradient(mCircleCenterX,mCircleCenterX,colors,null);
            setShader(shader);
        }else{
            mShaderColors = colors;
            isShader = true;
        }

    }

    /**
     * 设置进度颜色（纯色）
     * @param color
     */
    public void setProgressColor(int color){
        isShader = false;
        this.mProgressColor = color;
        invalidate();
    }

    /**
     * 设置进度颜色
     * @param resId
     */
    public void setProgressColorResource(int resId){
        int color = getResources().getColor(resId);
        setProgressColor(color);
    }

    /**
     * 设置是否显示外环刻度
     * @param isShowTick
     */
    public void setShowTick(boolean isShowTick){
        this.isShowTick = isShowTick;
        invalidate();
    }

    /**
     * 设置是否旋转
     * @param isTurn
     */
    public void setTurn(boolean isTurn){
        this.isTurn = isTurn;
        invalidate();
    }

    /**
     * 是否是圆形线冒（圆角弧度）
     * @param capRound
     */
    public void setCapRound(boolean capRound) {
        isCapRound = capRound;
        invalidate();
    }

    public int getStartAngle() {
        return mStartAngle;
    }

    public int getSweepAngle() {
        return mSweepAngle;
    }

    public float getCircleCenterX() {
        return mCircleCenterX;
    }

    public float getCircleCenterY() {
        return mCircleCenterY;
    }

    public float getRadius() {
        return mRadius;
    }

    public int getMax() {
        return mMax;
    }

    public int getProgress(){
        return mProgress;
    }

    public String getLabelText() {
        return mLabelText;
    }

    public void setLabelPaddingLeft(float labelPaddingLeft) {
        this.mLabelPaddingLeft = labelPaddingLeft;
        invalidate();
    }

    public void setLabelPaddingTop(float labelPaddingTop) {
        this.mLabelPaddingTop = labelPaddingTop;
        invalidate();
    }

    public void setLabelPaddingRight(float labelPaddingRight) {
        this.mLabelPaddingRight = labelPaddingRight;
        invalidate();
    }

    public void setLabelPaddingBottom(float labelPaddingBottom) {
        this.mLabelPaddingBottom = labelPaddingBottom;
        invalidate();
    }

    /**
     * 设置标签文本
     * @param labelText
     */
    public void setLabelText(String labelText) {
        this.mLabelText = labelText;
        this.isShowPercentText = TextUtils.isEmpty(labelText);
        invalidate();
    }

    /**
     * 进度百分比
     * @return
     */
    public int getProgressPercent() {
         return mProgressPercent;
    }

    /**
     * 如果自定义设置过{@link #setLabelText(String)} 或通过xml设置过{@code app:labelText}则
     * 返回{@link #mLabelText}，反之默认返回百分比{@link #mProgressPercent}
     * @return
     */
    public String getText(){
        if(isShowPercentText){
            return mProgressPercent + "%";
        }

        return mLabelText;
    }

    public int getLabelTextColor() {
        return mLabelTextColor;
    }

    public void setLabelTextColor(int color) {
        this.mLabelTextColor = color;
        invalidate();
    }

    public void setLabelTextColorResource(int resId){
        int color = getResources().getColor(resId);
        setLabelTextColor(color);
    }

    public void setLabelTextSize(float textSize){
        setLabelTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
    }

    public void setLabelTextSize(int  unit,float textSize){
        float size = TypedValue.applyDimension(unit,textSize,getDisplayMetrics());
        if(mLabelTextSize!= size){
            this.mLabelTextSize = size;
            invalidate();
        }

    }

    /**
     * 设置进度改变监听
     * @param onChangeListener
     */
    public void setOnChangeListener(OnChangeListener onChangeListener){
        this.mOnChangeListener = onChangeListener;
    }

    public interface OnChangeListener{
        void onProgressChanged(float progress,float max);
    }
}
