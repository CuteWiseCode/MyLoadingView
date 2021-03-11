package com.cutewisecode

import android.graphics.Camera
import android.view.animation.Animation
import android.view.animation.Transformation

/**
 *@author  wisdom
 *@date 2021/3/11
 *@desc
 */
class AnimationLoadkt:Animation {

    private var floata = 0f
    private var floatb = 0f
    private var floatc = 0f
    private var floatd = 0f
    private var floate = 0f
    private var boolf = false
    private var camerag: Camera? = null

    constructor(paramFloat1: Float, paramFloat2: Float, paramFloat3: Float, paramFloat4: Float, paramFloat5: Float, paramBoolean: Boolean) {
        floata = paramFloat1 //0
        floatb = paramFloat2 //90.0f
        floatc = paramFloat3 //172
        floatd = paramFloat4 //172
        floate = paramFloat5 //0
        boolf = paramBoolean
    }


    protected override fun applyTransformation(paramFloat: Float, paramTransformation: Transformation) {
        val f1 = floata //0.0f
        val f2 = f1 + paramFloat * (floatb - f1) //90
        val f3 = floatc //172
        val f4 = floatd //172
        val localCamera = camerag
        val localMatrix = paramTransformation.matrix
        localCamera!!.save() //Saves the camera state. Each save should be balanced with a call to restore().
        //if need to accelerate
        if (boolf) localCamera.translate(0.0f, 0.0f, paramFloat * floate) //Applies a translation transform on all three axis
        localCamera.translate(0.0f, 0.0f, floate * (1.0f - paramFloat))
        while (true) {
            localCamera.rotateY(f2) //Applies a rotation transform around the Y axis.
            localCamera.getMatrix(localMatrix) //Computes the matrix corresponding to the current transformation and copies it to the supplied matrix object.
            localCamera.restore() //Restores the saved state, if any
            //	    localMatrix.postScale(0.5f, 0.5f);// turn to 1/2 of original size
            localMatrix.preTranslate(-f3, -f4)
            localMatrix.postTranslate(f3, f4)
            return
        }
    }

    /**
     * Initialize this animation with the dimensions of the object being animated
     */
    override fun initialize(paramInt1: Int, paramInt2: Int, paramInt3: Int, paramInt4: Int) {
        super.initialize(paramInt1, paramInt2, paramInt3, paramInt4)
        camerag = Camera()
    }

}