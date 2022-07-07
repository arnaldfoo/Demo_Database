package sg.edu.rp.c346.id20039583.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnGetTasks;
    TextView tvData;
    ListView lv;
    ArrayAdapter<Task> aa;
    ArrayList<Task> alTask;
    EditText etTask, etDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tvData);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTask);
        lv = findViewById(R.id.lv);
        etTask = findViewById(R.id.etTask);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);
                etTask.getText().toString();
                db.insertTask("Push projects to GitHub", "7 July 2022");

            }
        });



        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                //populate TextView
                ArrayList<String> al = db.getTaskContent();
                String data = "";
                for (int i = 0; i < al.size(); i++){
                    data += al.get(i) + "\n";
                }
                tvData.setText(data);

                //populate ListView
                alTask = db.getTasks();
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alTask);
                lv.setAdapter(aa);
            }
        });

    }
}
