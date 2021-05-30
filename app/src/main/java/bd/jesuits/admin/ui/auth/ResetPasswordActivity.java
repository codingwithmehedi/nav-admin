package bd.jesuits.admin.ui.auth;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import java.util.Objects;

import bd.jesuits.admin.R;
import bd.jesuits.admin.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password );

        Toolbar toolbar = findViewById( R.id.reset_toolbar );
        setSupportActionBar(toolbar);

        Objects.requireNonNull( getSupportActionBar() ).setDisplayShowHomeEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Reset Password");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        actionBar.setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}