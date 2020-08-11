package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Roller roller = new Roller();

    Button roll_button;
    TextView roll_data;
    EditText num_rolls;
    EditText dc_check;
    EditText hit_bonus;
    EditText die_type;
    EditText dmg_bonus;
    RadioGroup roll_type;
    RadioButton default_roll;
    RadioButton advantage;
    RadioButton disadvantage;
    Switch roll_damage;

    String roll_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll_button = (Button)findViewById(R.id.roll_button);
        roll_button.setOnClickListener(rbListener);

        roll_data = (TextView)findViewById(R.id.roll_data);
        num_rolls = (EditText)findViewById(R.id.num_rolls);
        dc_check = (EditText)findViewById(R.id.check_dc);
        hit_bonus = (EditText)findViewById(R.id.bonus_to_hit);
        die_type = (EditText)findViewById(R.id.die_type);
        dmg_bonus = (EditText)findViewById(R.id.dmg_bonus);
        roll_type = (RadioGroup)findViewById(R.id.roll_type);
        default_roll = (RadioButton)findViewById(R.id.default_roll);
        advantage = (RadioButton)findViewById(R.id.roll_advantage);
        disadvantage = (RadioButton)findViewById(R.id.roll_disadvantage);
        roll_damage = (Switch)findViewById(R.id.roll_damage);
    }

    private View.OnClickListener rbListener = new View.OnClickListener() {
        public void onClick(View v) {
            roll_output = "";

            int n_rolls = Integer.parseInt(num_rolls.getText().toString());
            int dc = Integer.parseInt(dc_check.getText().toString());
            int h_bonus = Integer.parseInt(hit_bonus.getText().toString());
            int d_type = Integer.parseInt(die_type.getText().toString());
            int d_bonus = Integer.parseInt(dmg_bonus.getText().toString());

            if (roll_damage.isChecked()) {
                if(advantage.isChecked()) {
                    roll_output = roller.run(n_rolls, dc, h_bonus, d_type, d_bonus,true, false);
                }

                if(disadvantage.isChecked()) {
                    roll_output = roller.run(n_rolls, dc, h_bonus, d_type, d_bonus, false, true);
                }

                if(default_roll.isChecked()) {
                    roll_output = roller.run(n_rolls, dc, h_bonus, d_type, d_bonus,false, false);
                }
            } else {
                if(advantage.isChecked()) {
                    roll_output = roller.run(n_rolls, dc, h_bonus,true, false);
                }

                if(disadvantage.isChecked()) {
                    roll_output = roller.run(n_rolls, dc, h_bonus, false, true);
                }

                if(default_roll.isChecked()) {
                    roll_output = roller.run(n_rolls, dc, h_bonus, false, false);
                }
            }

            roll_data.setText(roll_output);
        }
    };
}