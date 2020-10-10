package com.cutewisecode;


import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * @author Administrator
 */
public class AnimationLoad extends Animation {

    private final float floata;
    private final float floatb;
    private final float floatc;
    private final float floatd;
    private final float floate;
    private final boolean boolf;
    private Camera camerag;

    public AnimationLoad(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, boolean paramBoolean) {
        this.floata = paramFloat1;//0
        this.floatb = paramFloat2;//90.0f
        this.floatc = paramFloat3;//172
        this.floatd = paramFloat4;//172
        this.floate = paramFloat5;//0
        this.boolf = paramBoolean;
    }

    protected void applyTransformation(float paramFloat, Transformation paramTransformation) {
        float f1 = this.floata;//0.0f
        float f2 = f1 + paramFloat * (this.floatb - f1);//90
        float f3 = this.floatc;//172
        float f4 = this.floatd;//172
        Camera localCamera = this.camerag;
        Matrix localMatrix = paramTransformation.getMatrix();
        localCamera.save();//Saves the camera state. Each save should be balanced with a call to restore().
        //if need to accelerate
        if (this.boolf)
            localCamera.translate(0.0F, 0.0F, paramFloat * this.floate);//Applies a translation transform on all three axis
        localCamera.translate(0.0F, 0.0F, this.floate * (1.0F - paramFloat));
        while (true) {
            localCamera.rotateY(f2);//Applies a rotation transform around the Y axis.
            localCamera.getMatrix(localMatrix);//Computes the matrix corresponding to the current transformation and copies it to the supplied matrix object.
            localCamera.restore();//Restores the saved state, if any
//	    localMatrix.postScale(0.5f, 0.5f);// turn to 1/2 of original size
            localMatrix.preTranslate(-f3, -f4);
            localMatrix.postTranslate(f3, f4);
            return;
        }
    }

    /**
     * Initialize this animation with the dimensions of the object being animated
     */
    public void initialize(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.initialize(paramInt1, paramInt2, paramInt3, paramInt4);
        this.camerag = new Camera();
    }


}
