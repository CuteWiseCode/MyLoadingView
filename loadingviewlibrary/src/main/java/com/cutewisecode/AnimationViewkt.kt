package com.cutewisecode

import android.com.loadingview.R
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView

/**
 *@author  wisdom
 *@date 2021/3/15
 *@desc
 */
class AnimationViewkt
    : FrameLayout {

    private val TAG = "AnimationView"
    private var view_width = 0.0f //with/2
    private var view_height = 0.0f //height/2
    private val translateZ = 0.0f
    private var currentIndex = 0
    private var current_view: ImageView? = null
    protected var view01: ImageView? = null
    private var view02: ImageView? = null
    private var view03: ImageView? = null
    private var view04: ImageView? = null
    private var view05: ImageView? = null
    private var DURATION = 400 //duration


    private var Id_image1 = 0
    private var Id_image2 = 0
    private var Id_image3 = 0
    private var Id_image4 = 0
    private var Id_image5 = 0



 //struct
     constructor(paramContext: Context) : super(paramContext)  {
        Log.i(TAG, "AnimationView")
        inflate(context, R.layout.loading_view, this) //layout_loading_view
        initializeView()
    }


     constructor(context: Context, attrs: AttributeSet?) : super(context,attrs) {
        Log.i(TAG, "AnimationView")
        // 从描述文件中读出xml中配置的值
        val array = context.obtainStyledAttributes(attrs, R.styleable.AnimationView)
        val speed = array.getInt(R.styleable.AnimationView_translationspeed, 0)
        Id_image1 = array.getResourceId(R.styleable.AnimationView_image1, 0)
        Id_image2 = array.getResourceId(R.styleable.AnimationView_image2, 0)
        Id_image3 = array.getResourceId(R.styleable.AnimationView_image3, 0)
        Id_image4 = array.getResourceId(R.styleable.AnimationView_image4, 0)
        Id_image5 = array.getResourceId(R.styleable.AnimationView_image5, 0)
        if (speed != 0) DURATION = speed
        inflate(getContext(), R.layout.loading_view, this) //layout_loading_view
        initializeView()
    }

    /**
     * @param paramView   specific view
     * @param paramFloat1 0.0f
     * @param paramFloat2 90.0f
     */
    private fun tostartAnimation(paramView: View?, paramFloat1: Float, paramFloat2: Float) {
        val animation = AnimationLoadkt(paramFloat1, paramFloat2, view_width, view_height, translateZ, true)
        animation.duration = DURATION.toLong()
        animation.fillAfter = true //true if the animation should apply its transformation after it ends
        animation.interpolator = AccelerateInterpolator() //Sets the acceleration curve for this animation. Defaults to a linear interpolation.Parameters:i The interpolator which defines the acceleration curve
        animation.setAnimationListener(animationListener()) //the animation listener to be notified
        paramView!!.startAnimation(animation)
    }

    /**
     * initialize
     */
    private fun initializeView() {
        view01 = findViewById<View>(R.id.loading_view_01) as ImageView
        if (Id_image1 != 0) view01!!.setImageResource(Id_image1)
        view02 = findViewById<View>(R.id.loading_view_02) as ImageView
        if (Id_image2 != 0) view02!!.setImageResource(Id_image2)
        view03 = findViewById<View>(R.id.loading_view_03) as ImageView
        if (Id_image3 != 0) view03!!.setImageResource(Id_image3)
        view04 = findViewById<View>(R.id.loading_view_04) as ImageView
        if (Id_image4 != 0) view04!!.setImageResource(Id_image4)
        view05 = findViewById<View>(R.id.loading_view_05) as ImageView
        if (Id_image5 != 0) view05!!.setImageResource(Id_image5)
        current_view = view01
        //measure the size of component; UNSPECIFIED = 0 EXACTLY = 1 AT_MOST= 2
        val m = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val n = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        view02!!.measure(m, n)
        view_width = (view02!!.measuredWidth / 2).toFloat() //get width
        view_height = (view02!!.measuredHeight / 2).toFloat() //get height
        initView()
    }

    fun initView() {
        tostartAnimation(current_view, 0.0f, 90.0f)
    }

    /**
     * animation listener
     *
     * @author Administrator
     */
    private inner class animationListener  : AnimationListener {
        override fun onAnimationEnd(paramAnimation: Animation) {
            //The Runnable that will be executed.
           post(runnable())
        }

        override fun onAnimationRepeat(paramAnimation: Animation) {}
        override fun onAnimationStart(paramAnimation: Animation) {}
    }

    /**
     * runnble class
     *
     * @author Administrator
     */
    private inner class runnable() : Runnable {
        override fun run() {
            view01?.setVisibility(GONE) //1
            view02?.setVisibility(GONE) //2
            view03?.setVisibility(GONE) //3
            view04?.setVisibility(GONE) //4
            view05?.setVisibility(GONE) //5
            current_view?.setVisibility(GONE)
            currentIndex++
            if (currentIndex % 5 == 0) //intd
                setData(view01) //the first one
            while (true) {
                if (1 == currentIndex % 5) {
                    setData(view02)
                    //	            	continue;
                }
                if (2 == currentIndex % 5) {
                    setData(view03)
                    //	            	continue;
                }
                if (3 == currentIndex % 5) {
                    setData(view04)
                    //	            	continue;
                }
                if (4 == currentIndex % 5) setData(view05)
                current_view?.setVisibility(VISIBLE)
                current_view?.requestFocus()
                val animation = AnimationLoad(-90.0f, 0.0f, view_width, view_height, translateZ, false)
                animation.duration = DURATION.toLong()
                animation.fillAfter = true
                animation.interpolator = DecelerateInterpolator()
                animation.setAnimationListener(object : AnimationListener {
                    override fun onAnimationEnd(paramAnimation: Animation) {
                       initView()
                    }

                    override fun onAnimationRepeat(paramAnimation: Animation) {}
                    override fun onAnimationStart(paramAnimation: Animation) {}
                })
                current_view?.startAnimation(animation)
                return
            }
        }
    }

    fun setData(target_view: ImageView?) {
        current_view = target_view
//		initView();
    }
}