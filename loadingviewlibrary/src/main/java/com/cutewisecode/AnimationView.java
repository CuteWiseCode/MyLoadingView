package com.cutewisecode;

import android.com.loadingview.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;


public class AnimationView extends FrameLayout {
    private static String TAG = "AnimationView";
    private float view_width = 0.0F;//with/2
    private float view_height = 0.0F;//height/2
    private float translateZ = 0.0F;
    private int currentIndex = 0;
    private ImageView current_view;
    private ImageView view01;
    private ImageView view02;
    private ImageView view03;
    private ImageView view04;
    private ImageView view05;
    private int DURATION = 400;//duration

    private  int Id_image1;
    private  int Id_image2;
    private  int Id_image3;
    private  int Id_image4;
    private  int Id_image5;

    //struct
    public AnimationView(Context paramContext) {
        super(paramContext);
        Log.i(TAG, "AnimationView");
        inflate(getContext(), R.layout.loading_view, this);//layout_loading_view
        initializeView();
    }

    public AnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "AnimationView");
        // 从描述文件中读出xml中配置的值
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AnimationView);
        int speed = array.getInt(R.styleable.AnimationView_translationspeed, 0);
        Id_image1 = array.getResourceId(R.styleable.AnimationView_image1, 0);
        Id_image2= array.getResourceId(R.styleable.AnimationView_image2, 0);
        Id_image3 = array.getResourceId(R.styleable.AnimationView_image3, 0);
        Id_image4 = array.getResourceId(R.styleable.AnimationView_image4, 0);
        Id_image5 = array.getResourceId(R.styleable.AnimationView_image5, 0);

        if(speed!=0)
            DURATION =speed;
        inflate(getContext(), R.layout.loading_view, this);//layout_loading_view
        initializeView();
    }

    /**
     * @param paramView   specific view
     * @param paramFloat1 0.0f
     * @param paramFloat2 90.0f
     */
    private void tostartAnimation(View paramView, float paramFloat1, float paramFloat2) {

        AnimationLoad animation = new AnimationLoad(paramFloat1, paramFloat2, this.view_width, this.view_height, this.translateZ, true);
        animation.setDuration(this.DURATION);
        animation.setFillAfter(true);//true if the animation should apply its transformation after it ends
        animation.setInterpolator(new AccelerateInterpolator());//Sets the acceleration curve for this animation. Defaults to a linear interpolation.Parameters:i The interpolator which defines the acceleration curve
        animation.setAnimationListener(new animationListener());//the animation listener to be notified
        paramView.startAnimation(animation);
    }

    /**
     * initialize
     */
    private void initializeView() {

        this.view01 = ((ImageView) findViewById(R.id.loading_view_01));
        if(Id_image1!=0)
            view01.setImageResource(Id_image1);
        this.view02 = ((ImageView) findViewById(R.id.loading_view_02));
        if(Id_image2!=0)
            view02.setImageResource(Id_image2);
        this.view03 = ((ImageView) findViewById(R.id.loading_view_03));
        if(Id_image3!=0)
            view03.setImageResource(Id_image3);
        this.view04 = ((ImageView) findViewById(R.id.loading_view_04));
        if(Id_image4!=0)
            view04.setImageResource(Id_image4);
        this.view05 = ((ImageView) findViewById(R.id.loading_view_05));
        if(Id_image5!=0)
            view05.setImageResource(Id_image5);
        this.current_view = this.view01;
        //measure the size of component; UNSPECIFIED = 0 EXACTLY = 1 AT_MOST= 2
        int m = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int n = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        this.view02.measure(m, n);
        this.view_width = (this.view02.getMeasuredWidth() / 2);//get width
        this.view_height = (this.view02.getMeasuredHeight() / 2);//get height

        initView();
    }

    public void initView() {
        tostartAnimation(this.current_view, 0.0F, 90.0F);
    }

    /**
     * animation listener
     *
     * @author Administrator
     */
    private final class animationListener
            implements Animation.AnimationListener {
        private animationListener() {
        }

        public void onAnimationEnd(Animation paramAnimation) {
            //The Runnable that will be executed.
            AnimationView.this.post(new runnable());
        }

        public void onAnimationRepeat(Animation paramAnimation) {
        }

        public void onAnimationStart(Animation paramAnimation) {
        }
    }

    /**
     * runnble class
     *
     * @author Administrator
     */
    private final class runnable
            implements Runnable {
        private runnable() {
        }

        public void run() {
            view01.setVisibility(View.GONE);//1
            view02.setVisibility(View.GONE);//2
            view03.setVisibility(View.GONE);//3
            view04.setVisibility(View.GONE);//4
            view05.setVisibility(View.GONE);//5
            current_view.setVisibility(View.GONE);
            currentIndex++;
            if (currentIndex % 5 == 0)//intd
                setData(view01);//the first one
            while (true) {
                if (1 == currentIndex % 5) {
                    setData(view02);
//	            	continue;
                }
                if (2 == currentIndex % 5) {
                    setData(view03);
//	            	continue;
                }
                if (3 == currentIndex % 5) {
                    setData(view04);
//	            	continue;
                }
                if (4 == currentIndex % 5)
                    setData(view05);
                current_view.setVisibility(View.VISIBLE);
                current_view.requestFocus();
                AnimationLoad animation = new AnimationLoad(-90.0F, 0.0F, view_width, view_height, translateZ, false);
                animation.setDuration(DURATION);
                animation.setFillAfter(true);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.setAnimationListener(new Animation.AnimationListener() {
                    public void onAnimationEnd(Animation paramAnimation) {
                        AnimationView.this.initView();
                    }

                    public void onAnimationRepeat(Animation paramAnimation) {
                    }

                    public void onAnimationStart(Animation paramAnimation) {
                    }
                });

                current_view.startAnimation(animation);
                return;
            }
        }
    }

    public void setData(ImageView target_view) {
        this.current_view = target_view;
//		initView();
    }

}
