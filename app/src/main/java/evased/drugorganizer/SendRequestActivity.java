package evased.drugorganizer;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class SendRequestActivity extends AppCompatActivity implements RequestOperatorArray.RequestOperatorListener {

    private Button sendRequestButton;
    TextView title, bodyText;

    private ModelPost publication;
    private Context context = this;
    private IndicatorView indicator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_request_design);

        sendRequestButton = (Button)findViewById(R.id.sendRequestBtn);
        sendRequestButton.setOnClickListener(requestButtonClick);

        title = (TextView) findViewById(R.id.titleTextView);
        bodyText = (TextView) findViewById(R.id.body_text);
        indicator = (IndicatorView) findViewById(R.id.indicator);
    }


    View.OnClickListener requestButtonClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            sendRequest();
        }
    };

    private void sendRequest(){
        RequestOperatorArray ro = new RequestOperatorArray();
        ro.setListener(this);
        ro.start();

    }
    public void setIndicatorStatus(final int status, final int result){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indicator.setState(status);
                indicator.invalidate();
                indicator.setResponseResultCount(result);
            }
        });
    }
    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (publication != null){
                    title.setText(publication.getTitle());
                    bodyText.setText(publication.getBodyText());
                }
                else{
                    title.setText("Indicator shows how many items JSON array has (size of)");

                }
            }
        });
    }

    @Override
    public void success (int count){
        this.publication = publication;
        updatePublication();
        setIndicatorStatus(IndicatorView.ARRAY_SIZE, count);
    }

    public void failed(int responseCode){
        this.publication = null;
        updatePublication();
        setIndicatorStatus(IndicatingView.FAILED, -1);
    }


}

