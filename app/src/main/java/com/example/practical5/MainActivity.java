package com.example.practical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.editMunicipality);
        TextProcessor processor = new TextProcessor();

        input.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO ||
                    (event != null &&
                            event.getKeyCode() == KeyEvent.KEYCODE_ENTER &&
                            event.getAction() == KeyEvent.ACTION_DOWN)) {

                String name = processor.processMunicipality(
                        input.getText().toString());

                if (name == null) {
                    input.setError("Please enter a valid municipality");
                } else {
                    Intent intent = new Intent(
                            MainActivity.this, Municipality.class);
                    intent.putExtra("municipality_name", name);
                    startActivity(intent);
                }

                return true;
            }
            return false;
        });
    }
}
