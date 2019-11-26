package evased.drugorganizer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.net.URL;

public class MainWindow extends AppCompatActivity {
    private Button pendingRemindersBtn;
    private Button createNewReminderBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference refference;
    private FirebaseDatabase database;
    private TextView username;
    private ImageView userPhoto;
    //private Toolbar toolbar;

    private Context context = this;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.main_page_design);
        Intent intent = getIntent();
        if (intent.getBooleanExtra("flag", true)){
            //
        }
        else
        {
            //
        }




        pendingRemindersBtn = (Button) findViewById(R.id.pendingRemindersButton);
        pendingRemindersBtn.setOnClickListener(showRemindersClick);

        createNewReminderBtn = (Button) findViewById(R.id.createNewReminderButton);
        createNewReminderBtn.setOnClickListener(createNewReminder);


        FirebaseUser currentUser = mAuth.getCurrentUser();
        username = (TextView)findViewById(R.id.userName);
        userPhoto = (ImageView) findViewById(R.id.userPhoto);
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        String userEmail = "";
        String photoUrl = "";

        if(currentUser.getEmail() != null) {
            userEmail = currentUser.getEmail();
        }

        if(currentUser.getPhotoUrl() != null) {
            photoUrl = currentUser.getPhotoUrl().toString();
        }
        else
        {
            photoUrl = "https://cdn2.iconfinder.com/data/icons/mobile-apps-10/512/13_contact_profile-13-512.png";
        }

        if (userEmail != null) {
            username.setText(userEmail);
        }

        if (photoUrl != null) {
            new DownLoadImageTask(userPhoto).execute(photoUrl);
        }

        //refference = database.getReference().child(("Reminder"));
    }


    /*
    @Override
     public void onStart() {
    super.onStart();
    // Check if user is signed in (non-null) and update UI accordingly.

    FirebaseUser currentUser = mAuth.getCurrentUser();
    username = (TextView)findViewById(R.id.userName);
    String userEmail = "";

    if(currentUser.getEmail() != null) {
        userEmail = currentUser.getEmail();
    }
    if (userEmail != null) {
        username.setText(userEmail);
    }

    }
*/

    public void showPendingReminders(boolean b){
        Intent intent = new Intent(context, RemindersViewWindow.class);
        intent.putExtra("showPending",b );
        context.startActivity(intent);
    }

    public void createNewReminder(boolean b){
        Intent intent = new Intent(context, CreateReminderWindow.class);
        intent.putExtra("createNewReminder",b );
        context.startActivity(intent);
    }

    View.OnClickListener showRemindersClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            showPendingReminders(true);
        }
    };

    View.OnClickListener createNewReminder = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            createNewReminder(true);
        }
    };



    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
