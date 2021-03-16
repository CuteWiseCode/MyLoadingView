package com.cutewisecode

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 *@author  wisdom
 *@date 2021/3/11
 *@desc
 */
class AnimationLoadkt:Animation{

    private var floata = 0f
    private var floatb = 0f
    private var half_view_width = 0f
    private var half_view_height = 0f
    private var translateZ = 0f
    private var ifneedToAccelerate = false
    private var camerag: Camera? = null

     constructor(paramFloat1: Float, paramFloat2: Float, view_width: Float, view_height: Float, translateZ: Float, ifneedToAccelerate: Boolean) {
        floata = paramFloat1 //0
        floatb = paramFloat2 //90.0f
        half_view_width = view_width //172
        half_view_height = view_height //172
        this.translateZ = translateZ //0
        this.ifneedToAccelerate = ifneedToAccelerate
    }

    /**
     *
     * @param interpolatedTime The value of the normalized time (0.0 to 1.0)
     * after it has been run through the interpolation function.
     * @param paramTransformation The Transformation object to fill in with the current
     * transforms.
     */
    override fun applyTransformation(interpolatedTime: Float, paramTransformation: Transformation) {
        val f1 = floata //0.0f
        val rotateY = f1 + interpolatedTime * (floatb - f1) //90
        val half_view_width = half_view_width //172
        val half_view_height = half_view_height //172
        val localCamera = camerag
        val localMatrix = paramTransformation.matrix
        localCamera!!.save() //Saves the camera state. Each save should be balanced with a call to restore().
        //if need to accelerate
        if (ifneedToAccelerate) localCamera.translate(0.0f, 0.0f, interpolatedTime * translateZ) //Applies a translation transform on all three axis
        localCamera.translate(0.0f, 0.0f, translateZ * (1.0f - interpolatedTime))
        while (true) {
            localCamera.rotateY(rotateY) //Applies a rotation transform around the Y axis.
            localCamera.getMatrix(localMatrix) //Computes the matrix corresponding to the current transformation and copies it to the supplied matrix object.
            localCamera.restore() //Restores the saved state, if any
            localMatrix.postScale(0.5f, 0.5f) // turn to 1/2 of original size
            localMatrix.preTranslate(-half_view_width, -half_view_height)
            localMatrix.postTranslate(half_view_width, half_view_height)
            return
        }
    }

    /**
     * Initialize this animation with the dimensions of the object being animated
     */
    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        camerag = Camera()
    }

}