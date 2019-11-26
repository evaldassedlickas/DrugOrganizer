package evased.drugorganizer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class CreateReminderWindow extends AppCompatActivity {

    private Button createReminderButton;
    private Context context = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_create_design);
        Intent intent = getIntent();
        if (intent.getBooleanExtra("createNewReminder", true)){
            //
        }
        else
        {
            //
        }

        createReminderButton = (Button) findViewById(R.id.createReminderConfirm);
        createReminderButton.setOnClickListener(onCreateClick);
    }

    public void returnMainAfterCreated(boolean b){
        Intent intent = new Intent(context, MainWindow.class);
        intent.putExtra("afterNewCreated",b );
        context.startActivity(intent);
    }

    View.OnClickListener onCreateClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            returnMainAfterCreated(true);
        }
    };
}
