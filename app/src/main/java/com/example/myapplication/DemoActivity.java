package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class DemoActivity extends AppCompatActivity {

    private EditText diseaseEditText;
    private Button searchButton;
    private ListView videoListView;

    private HashMap<String, String[]> diseaseVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        diseaseEditText = findViewById(R.id.diseaseEditText);
        searchButton = findViewById(R.id.searchButton);
        videoListView = findViewById(R.id.videoListView);

        // Demo disease videos
        diseaseVideos = new HashMap<>();
        diseaseVideos.put("migraine", new String[]{
                "Video 1 |https://youtu.be/pCANnjHlRw0?si=1eg3bESG_hFdWIrl",
                "Video 2|https://youtube.com/shorts/N8w0QqoPZpM?si=ZDxaZcdsaAcIGR5c"
        });
        diseaseVideos.put("jointpain", new String[]{
                "Video 1|https://youtube.com/shorts/L1XQoXxMzFA?si=aE7gDmo5qvVqxN7l",
                "Video 2|https://youtube.com/shorts/irXbC7B5xeg?si=QeiH9u602miTL3qn"
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String disease = diseaseEditText.getText().toString().trim().toLowerCase();

                if(disease.isEmpty()){
                    Toast.makeText(DemoActivity.this, "Please enter a disease", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(diseaseVideos.containsKey(disease)){
                    String[] videos = diseaseVideos.get(disease);

                    // Extract titles
                    String[] titles = new String[videos.length];
                    for(int i=0; i<videos.length; i++){
                        titles[i] = videos[i].split("\\|")[0];
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            DemoActivity.this,
                            android.R.layout.simple_list_item_1,
                            titles
                    );
                    videoListView.setAdapter(adapter);

                    videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String url = videos[position].split("\\|")[1];
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        }
                    });

                } else {
                    Toast.makeText(DemoActivity.this, "No videos found for this disease", Toast.LENGTH_SHORT).show();
                    videoListView.setAdapter(null);
                }
            }
        });
    }
}
