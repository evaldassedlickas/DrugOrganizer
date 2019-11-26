package evased.drugorganizer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.os.CountDownTimer;
import java.util.ArrayList;
import java.util.List;

public class LogOnWindow extends AppCompatActivity {

    private EditText emailInput, passwordInput;
    private Button myButton;
    private Button registerButton;
    private TextView myTextField;
    private Context context = this;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private Button openRequestActivityBtn;

    private IndicatingView indicator;
    private int counter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivitydesign);

        mAuth = FirebaseAuth.getInstance();
        initializeUI();

        myButton.setOnClickListener(logInClick);
        registerButton.setOnClickListener(registerClick);

        openRequestActivityBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SendRequestActivity.class);
                context.startActivity(intent);
            }
        });





    }

    public void logOn(boolean b){
        Intent intent = new Intent(context, MainWindow.class);
        intent.putExtra("flag",b );
        context.startActivity(intent);
    }

    View.OnClickListener logInClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            loginUserAccount();

        }
    };

    View.OnClickListener registerClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            register();
        }
    };


    //@Override
   // public void onStart() {
       // super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

       // FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    //}

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            myButton.setVisibility(View.GONE);
        } else {

            myButton.setVisibility(View.VISIBLE);

        }
    }

    private void initializeUI() {
        progressBar = findViewById(R.id.progressBar);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        myButton = findViewById(R.id.logInButton);
        registerButton = findViewById(R.id.registerButton);
        openRequestActivityBtn = findViewById(R.id.openRequestActivityBtn);
        myTextField = findViewById(R.id.countdowtextview);



        indicator = (IndicatingView)findViewById(R.id.barView);
        List<Rect> rects = new ArrayList<>();
        rects.add(new Rect(0, 0, 200, 50));
        rects.add(new Rect(200, 0, 400, 50));
        rects.add(new Rect(400, 0, 600, 50));
        rects.add(new Rect(600, 0, 800, 50));
        rects.add(new Rect(800, 0, 1000, 50));

        indicator.drawBar(rects, Color.BLUE);


    }


    private void loginUserAccount() {



        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(25);
        progressBar.setProgress(50);
        progressBar.setProgress(75);
        String email, password;
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LogOnWindow.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //progressBar.setSecondaryProgressTintList(ColorStateList.valueOf(Color.BLUE));
                            progressBar.setProgress(100);
                            Intent intent = new Intent(context, MainWindow.class);
                            context.startActivity(intent);
                            Toast.makeText(LogOnWindow.this, "Login Succesfull", Toast.LENGTH_LONG).show();
                        } else {
                            //progressBar.setVisibility(View.GONE);
                            //FQprogressBar.setProgress(0);
                            Toast.makeText(LogOnWindow.this, "Login failed", Toast.LENGTH_LONG).show();

                        }

                    }
                });
    }




    private void register() {
        progressBar.setVisibility(View.VISIBLE);

        String email, password;
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(context, MainWindow.class);
                            context.startActivity(intent);
                            Toast.makeText(LogOnWindow.this, "Registration completed", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LogOnWindow.this, "Registration failed", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
