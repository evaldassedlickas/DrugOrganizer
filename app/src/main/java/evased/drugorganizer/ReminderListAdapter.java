package evased.drugorganizer;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReminderListAdapter extends ArrayAdapter<ReminderListItem> {
    public ReminderListAdapter(Context context, List<ReminderListItem> objects){
        super(context, R.layout.list_reminder_design, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_reminder_design, null);

        }
        TextView title =  (TextView) v.findViewById(R.id.reminderTitle);
        TextView description = (TextView) v.findViewById(R.id.reminderDescription);
        ImageView image = (ImageView) v.findViewById(R.id.reminderImage);
        TextView date = (TextView) v.findViewById(R.id.reminderDate);


        ReminderListItem item = getItem(position);
        title.setText(item.GetTitle());
        description.setText(item.GetDescription());
        image.setImageResource(item.GetImageId());
        date.setText(item.GetDate().toString());
        return v;
    }
}
