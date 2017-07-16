package xyz.stepsecret.dialogmedicinelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xyz.stepsecret.searchselectbsd.dialog.SearchSelectBSD;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private TextView tv_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_view = (TextView) findViewById(R.id.tv_view);
        Button btn_open = (Button) findViewById(R.id.btn_open);

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchSelectBSD searchSelectBSD = new SearchSelectBSD();
                searchSelectBSD.setClientCallback(new SearchSelectBSD.SearchCallback() {
                    @Override
                    public void onSelect(String message) {
                        Log.e(TAG, "message : "+message);
                        tv_view.setText(message);
                    }
                    @Override
                    public void onCreateComplete(SearchSelectBSD searchSelectBSD) {
                        Log.e(TAG, "onCreateComplete : ");
                        searchSelectBSD.SetItem(getResources().getStringArray(R.array.medicine_name));
                        searchSelectBSD.SetHint("Search...");
                    }

                });
                searchSelectBSD.show(getSupportFragmentManager(), "Dialog");

            }
        });
    }
}
