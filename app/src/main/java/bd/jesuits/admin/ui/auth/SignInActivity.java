package bd.jesuits.admin.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bd.jesuits.admin.R;
import bd.jesuits.admin.databinding.ActivitySigninBinding;
import bd.jesuits.admin.ui.main.MainActivity;


public class SignInActivity extends AppCompatActivity {
    private ActivitySigninBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this,R.layout.activity_signin );

        progressDialog = new ProgressDialog( this );
        //firebase
        firebaseAuth = FirebaseAuth.getInstance();


        binding.btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Login();
            }
        } );

    }
    private void Login() {
        progressDialog.show();
        progressDialog.setMessage( "Checking Logging Credential...Please Wait..." );
        final String email = binding.etEmail.getText().toString().trim();
        final String password = binding.etPassword.getText().toString().trim();

     /*   if (TextUtils.isEmpty( email )){
            edtEmail.setError( "Email is Required" );
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher( email ).matches()){
            edtEmail.setError( "Please enter a valid email" );
            edtEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty( password )){
            edtPassword.setError( "Password is Required" );
            return;
        }
        if (password.length()<6){
            edtPassword.setError( "Min password length is 6 characters!" );
            edtPassword.requestFocus();
            return;
        }*/

        firebaseAuth.signInWithEmailAndPassword(email,password ).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() ){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){

                        Toast.makeText( SignInActivity.this, "Logged in Successful", Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent( getApplicationContext(), MainActivity.class );
                        startActivity( intent );
                        finish();
                        progressDialog.dismiss();

                    }else {
                        user.sendEmailVerification();
                        Toast.makeText( SignInActivity.this, "Check your email to verify your account!", Toast.LENGTH_SHORT ).show();
                    }
                }else {
                    Toast.makeText( SignInActivity.this, "Connection Error!", Toast.LENGTH_SHORT ).show();

                }

            }
        } );

    }
}