package com.example.crud_peliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPass extends AppCompatActivity {
    private Button resetBtn;
    private FirebaseAuth mAuth;
    public String  emaill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        mAuth = FirebaseAuth.getInstance();

        resetBtn = findViewById(R.id.btnSentEmail);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentEmail();
            }
        });
    }

    private void sentEmail(){
         emaill = "vasquezanth@gmail.com";
        mAuth.sendPasswordResetEmail(emaill).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
//                loadingBar.dismiss();
                if(task.isSuccessful())
                {
                    Toast.makeText(ResetPass.this,"Correo de recuperacion enviado",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), Login.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(ResetPass.this,"Ooopsss, algo salio mal",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                loadingBar.dismiss();
                Toast.makeText(ResetPass.this,"Error Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
}