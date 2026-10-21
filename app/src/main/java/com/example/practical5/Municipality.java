package com.example.practical5;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        String input = getIntent().getStringExtra("municipality_name");
        String name = new TextProcessor().processMunicipality(input);

        if (name == null) {
            Toast.makeText(this, "Invalid municipality", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView nameView = findViewById(R.id.municipalityName);
        ImageView imageView = findViewById(R.id.municipalityImage);
        TextView infoView = findViewById(R.id.municipalityInfo);
        Button visitButton = findViewById(R.id.visitSiteButton);

        int index;
        int image;
        int color;

        switch (name) {
            case "Beijing":
                index = 0;
                image = R.drawable.beijing;
                color = R.color.municipality_beijing;
                break;

            case "Shanghai":
                index = 1;
                image = R.drawable.shanghai;
                color = R.color.municipality_shanghai;
                break;

            case "Chongqing":
                index = 2;
                image = R.drawable.chongqing;
                color = R.color.municipality_chongqing;
                break;

            default:
                index = 3;
                image = R.drawable.tianjin;
                color = R.color.municipality_tianjin;
                break;
        }

        String[] histories = getResources()
                .getStringArray(R.array.municipalities_array);

        int themeColor = ContextCompat.getColor(this, color);
        int textColor = ContextCompat.getColor(this,
                name.equals("Chongqing")
                        ? R.color.color_text_dark
                        : R.color.color_text_light);

        nameView.setText(name);
        nameView.setBackgroundColor(themeColor);
        nameView.setTextColor(textColor);

        imageView.setImageResource(image);
        imageView.setContentDescription(name);

        infoView.setText(histories[index]);
        infoView.setTextColor(themeColor);

        visitButton.setBackgroundTintList(
                ColorStateList.valueOf(themeColor));
        visitButton.setTextColor(textColor);

        visitButton.setOnClickListener(v -> {
            String url;

            switch (name) {
                case "Beijing":
                    url = "https://english.beijing.gov.cn/";
                    break;
                case "Shanghai":
                    url = "https://english.shanghai.gov.cn/";
                    break;
                case "Tianjin":
                    url = "https://en.tj.gov.cn/";
                    break;
                case "Chongqing":
                    url = "https://english.cq.gov.cn/";
                    break;
                default:
                    return;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No browser available",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
