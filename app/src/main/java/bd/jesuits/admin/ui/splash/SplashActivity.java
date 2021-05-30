package bd.jesuits.admin.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bd.jesuits.admin.R;
import bd.jesuits.admin.databinding.ActivitySplashBinding;
import bd.jesuits.admin.ui.auth.SignInActivity;
import bd.jesuits.admin.ui.main.MainActivity;


public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    private Animation fadeOut;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this,R.layout.activity_splash );



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Window window = getWindow();
        window.setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );

        fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        binding.splashLogo.setAnimation( fadeOut );
        binding.splashTitle.setAnimation( fadeOut );
        binding.splashSlogan.setAnimation( fadeOut );

        Handler handler= new Handler();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {

                if(firebaseUser!=null){
                    startActivity( new Intent( SplashActivity.this, MainActivity.class ));
                    SplashActivity.this.finish();
                }else {
                    startActivity( new Intent( SplashActivity.this, SignInActivity.class ));
                    SplashActivity.this.finish();
                }

            }
        },2000 );
    }
}