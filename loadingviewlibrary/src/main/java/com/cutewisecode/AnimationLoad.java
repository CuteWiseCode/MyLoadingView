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
    private final float half_view_width;
    private final float half_view_height;
    private final float translateZ;
    private final boolean ifneedToAccelerate;
    private Camera camerag;

    public AnimationLoad(float paramFloat1, float paramFloat2, float view_width, float view_height, float translateZ, boolean ifneedToAccelerate) {
        this.floata = paramFloat1;//0
        this.floatb = paramFloat2;//90.0f
        this.half_view_width = view_width;//172
        this.half_view_height = view_height;//172
        this.translateZ = translateZ;//0
        this.ifneedToAccelerate = ifneedToAccelerate;
    }

    /**
     *
     * @param interpolatedTime The value of the normalized time (0.0 to 1.0)
     *        after it has been run through the interpolation function.
     * @param paramTransformation The Transformation object to fill in with the current
     *        transforms.
     */
    protected void applyTransformation(float interpolatedTime, Transformation paramTransformation) {
        float f1 = this.floata;//0.0f
        float rotateY = f1 + interpolatedTime * (this.floatb - f1);//90
        float half_view_width = this.half_view_width;//172
        float half_view_height = this.half_view_height;//172
        Camera localCamera = this.camerag;
        Matrix localMatrix = paramTransformation.getMatrix();
        localCamera.save();//Saves the camera state. Each save should be balanced with a call to restore().
        //if need to accelerate
        if (this.ifneedToAccelerate)
            localCamera.translate(0.0F, 0.0F, interpolatedTime * this.translateZ);//Applies a translation transform on all three axis
        localCamera.translate(0.0F, 0.0F, this.translateZ * (1.0F - interpolatedTime));
        while (true) {
            localCamera.rotateY(rotateY);//Applies a rotation transform around the Y axis.
            localCamera.getMatrix(localMatrix);//Computes the matrix corresponding to the current transformation and copies it to the supplied matrix object.
            localCamera.restore();//Restores the saved state, if any
//            localMatrix.postScale(0.5f, 0.5f);// turn to 1/2 of original size
            localMatrix.preTranslate(-half_view_width, -half_view_height);
            localMatrix.postTranslate(half_view_width, half_view_height);
            return;
        }
    }

    /**
     * Initialize this animation with the dimensions of the object being animated
     */
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.camerag = new Camera();
    }


}
