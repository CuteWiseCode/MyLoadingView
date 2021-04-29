package android.com.librayimporttest;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.cutewisecode.AnimationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout container = findViewById(R.id.rl_container);

        AnimationView animationView = new AnimationView.Builder()
                .addContext(this)
                .addFirstImage(R.mipmap.im_cabbage)
                .addSecondImage(R.mipmap.im_chinesechives)
                .addThirdImage(R.mipmap.im_greenchineseonion)
                .addFourthImage(R.mipmap.im_hyacinthbean)
                .addFithImage(R.mipmap.im_rutabaga)
                .addDuration(400)
                .build();

        container.addView(animationView);
    }
}