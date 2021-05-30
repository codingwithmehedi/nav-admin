package bd.jesuits.admin.firebase;

import android.content.Context;
import android.content.Intent;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

import bd.jesuits.admin.ui.main.MainActivity;

public class FirebaseSource {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    Context context;

    public  void Login(String email,String password){
       if (email.length()>5&&password.length()>5){
           Toast.makeText( context,"Login Success",Toast.LENGTH_LONG ).show();
       }
    }
    public void firebaseLogin(String email, String password) {


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful() ){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){

                        Toast.makeText(context , "Logged in Successful", Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent( context, MainActivity.class );
                        context.startActivity( intent );

                    }else {
                        user.sendEmailVerification();
                        Toast.makeText( context, "Check your email to verify your account!", Toast.LENGTH_SHORT ).show();
                    }
                }else {
                    // Toast.makeText( LoginActivity.this, "Connection Error!", Toast.LENGTH_SHORT ).show();

                }

            }
        } );

    }

    public void logout(){
        firebaseAuth.signOut();
    }

    public void currentUser(){
        firebaseAuth.getCurrentUser();
    }
}
