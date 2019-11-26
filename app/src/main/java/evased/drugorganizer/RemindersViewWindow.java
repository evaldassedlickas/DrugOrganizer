package evased.drugorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemindersViewWindow extends AppCompatActivity {
    private ListView remindersList;
    private ReminderListAdapter remindersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminders_view_design);
        remindersList = (ListView) findViewById(R.id.listView);

        List<ReminderListItem> items = new ArrayList<ReminderListItem>();

        Intent intent = getIntent();

        if (intent.getBooleanExtra("showPending", true)){
            items.add(new ReminderListItem("Reminder1", R.drawable.ic_launcher_background, "TestDescription", new Date(2019, 10, 01, 15, 10)));
            items.add(new ReminderListItem("Reminder2", R.drawable.ic_launcher_background, "TestDescription", new Date(2019, 10, 01, 15, 10)));
            items.add(new ReminderListItem("Reminder3", R.drawable.ic_launcher_background, "TestDescription", new Date(2019, 10, 01, 15, 10)));
            items.add(new ReminderListItem("Reminder4", R.drawable.ic_launcher_background, "TestDescription", new Date(2019, 10, 01, 15, 10)));
            items.add(new ReminderListItem("Reminder5", R.drawable.ic_launcher_background, "TestDescription", new Date(2019, 10, 01, 15, 10)));
            items.add(new ReminderListItem("Reminder6", R.drawable.ic_launcher_background, "TestDescription", new Date(2019, 10, 01, 15, 10)));
            items.add(new ReminderListItem("Reminder7", R.drawable.ic_launcher_background, "TestDescription", new Date(2019, 10, 01, 15, 10)));

        }
        else {

        }

        remindersAdapter = new ReminderListAdapter(this, items);
        remindersList.setAdapter(remindersAdapter);
    }
}
