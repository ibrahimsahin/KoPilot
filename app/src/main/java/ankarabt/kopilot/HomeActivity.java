package ankarabt.kopilot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by isahin on 9.5.2017.
 */
public class HomeActivity  extends AppCompatActivity {


    ImageView imgLogoview ;
    Button giris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        init();
    }

    void init()
    {

        imgLogoview = (ImageView) findViewById(R.id.ImageViewGirisLogo);
        giris = (Button)findViewById(R.id.button_giris);
        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });
        startAnimationMainPage();
    }

    private void startAnimationMainPage()
    {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.sctrl_anim);
        anim.reset();
        imgLogoview.clearAnimation();
        imgLogoview.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(HomeActivity.this , MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }




}
