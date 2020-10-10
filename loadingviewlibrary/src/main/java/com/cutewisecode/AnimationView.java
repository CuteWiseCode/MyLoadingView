package com.cutewisecode;

import android.com.loadingview.R;
import android.content.Context;
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
    private float floata = 0.0F;//with/2
    private float floatb = 0.0F;//height/2
    private float floatc = 0.0F;
    private int intd = 0;
    private View viewe;
    private View viewf;
    private View viewg;
    private View viewh;
    private View viewi;
    private View viewj;
    private int intk = 400;//duration

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
        inflate(getContext(), R.layout.loading_view, this);//layout_loading_view
        initializeView();
    }

    /**
     * @param paramView   specific view
     * @param paramFloat1 0.0f
     * @param paramFloat2 90.0f
     */
    private void tostartAnimation(View paramView, float paramFloat1, float paramFloat2) {

        AnimationLoad animation = new AnimationLoad(paramFloat1, paramFloat2, this.floata, this.floatb, this.floatc, true);
        animation.setDuration(this.intk);
        animation.setFillAfter(true);//true if the animation should apply its transformation after it ends
        animation.setInterpolator(new AccelerateInterpolator());//Sets the acceleration curve for this animation. Defaults to a linear interpolation.Parameters:i The interpolator which defines the acceleration curve
        animation.setAnimationListener(new animationListener());//the animation listener to be notified
        paramView.startAnimation(animation);
    }

    /**
     * initialize
     */
    private void initializeView() {

        this.viewf = ((ImageView) findViewById(R.id.loading_view_01));
        this.viewg = ((ImageView) findViewById(R.id.loading_view_02));
        this.viewh = ((ImageView) findViewById(R.id.loading_view_03));
        this.viewi = ((ImageView) findViewById(R.id.loading_view_04));
        this.viewj = ((ImageView) findViewById(R.id.loading_view_05));
        this.viewe = this.viewf;
        //measure the size of component; UNSPECIFIED = 0 EXACTLY = 1 AT_MOST= 2
        int m = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int n = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        this.viewf.measure(m, n);
        this.floata = (this.viewf.getMeasuredWidth() / 2);//get width
        this.floatb = (this.viewf.getMeasuredHeight() / 2);//get height
//	    g.a("mCenterX=" + this.floata + "   mCenterY=" + this.floatb);//output
//	    Log.i(TAG, "initializeView"+floata+":"+floatb);
        initView();
    }

    public void initView() {
        tostartAnimation(this.viewe, 0.0F, 90.0F);
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
            viewf.setVisibility(View.GONE);//1
            viewg.setVisibility(View.GONE);//2
            viewh.setVisibility(View.GONE);//3
            viewi.setVisibility(View.GONE);//4
            viewj.setVisibility(View.GONE);//5
            viewe.setVisibility(View.GONE);
            intd++;
            if (intd % 5 == 0)//intd
                setData(viewf);//the first one
            while (true) {
                if (1 == intd % 5) {
                    setData(viewg);
//	            	continue;
                }
                if (2 == intd % 5) {
                    setData(viewh);
//	            	continue;
                }
                if (3 == intd % 5) {
                    setData(viewi);
//	            	continue;
                }
                if (4 == intd % 5)
                    setData(viewj);
                viewe.setVisibility(View.VISIBLE);
                viewe.requestFocus();
                AnimationLoad animation = new AnimationLoad(-90.0F, 0.0F, floata, floatb, floatc, false);
                animation.setDuration(intk);
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

                viewe.startAnimation(animation);
                return;
            }
        }
    }

    public void setData(View viewf2) {
        this.viewe = viewf2;
//		initView();
    }

}
