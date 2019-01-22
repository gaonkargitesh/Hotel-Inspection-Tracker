package com.example.rahul.hit.createcomplaint.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.rahul.hit.R;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AddCreateComplaint extends AppCompatActivity {

    @BindView(R.id.radioButton_AddCreateComplaintPage_LowPriority)
    RadioButton lowRadioButton;

    @BindView(R.id.radioButton_AddCreateComplaintPage_MediumPriority)
    RadioButton mediumRadioButton;

    @BindView(R.id.radioButton_AddCreateComplaintPage_HighPriority)
    RadioButton highRadioButton;

    /*@BindView(R.id.radi)
    RadioGroup radioGroup;
*/
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_create_complaint);

        toolbar=findViewById(R.id.create_complaint_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Create Complaint");

        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setTitle(R.string.add_create_complaint);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick({R.id.radioButton_AddCreateComplaintPage_LowPriority,
            R.id.radioButton_AddCreateComplaintPage_MediumPriority,
            R.id.radioButton_AddCreateComplaintPage_HighPriority})
    public void radioButtonClicked(View view){
        int radioButtonView=view.getId();
        if(radioButtonView==R.id.radioButton_AddCreateComplaintPage_LowPriority){
            Toast.makeText(this,"Low",Toast.LENGTH_SHORT).show();
        }
        if(radioButtonView==R.id.radioButton_AddCreateComplaintPage_MediumPriority){
            Toast.makeText(this,"Medium",Toast.LENGTH_SHORT).show();
        }
        if(radioButtonView==R.id.radioButton_AddCreateComplaintPage_HighPriority){
            Toast.makeText(this,"High",Toast.LENGTH_SHORT).show();
        }
    }

}
