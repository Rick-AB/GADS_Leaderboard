package com.ricknotes.gadsleaderboard.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ricknotes.gadsleaderboard.Network.ApiInterface;
import com.ricknotes.gadsleaderboard.Network.RetrofitBuilder;
import com.ricknotes.gadsleaderboard.R;
import com.ricknotes.gadsleaderboard.Utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Submit extends AppCompatActivity implements View.OnClickListener{
    
    private EditText mFirstName, mLastName, mEmail, mProjectLink;
    private ApiInterface mApi;
    private AlertDialog mDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        
        mApi = RetrofitBuilder.buildApi(ApiInterface.class);
        
        initViews();
    }
    
    private void initViews() {
        mFirstName = findViewById(R.id.submit_first_name);
        mLastName = findViewById(R.id.submit_last_name);
        mEmail = findViewById(R.id.submit_email);
        mProjectLink = findViewById(R.id.submit_project_link);
        Button submit = findViewById(R.id.submit_btn);
        ImageButton backButton = findViewById(R.id.submit_back_arrow);
        
        submit.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }
    
    
    private boolean checkValidInfo() {
        boolean valid = true;
        
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String email = mEmail.getText().toString();
        String projectLink = mProjectLink.getText().toString();
        
        if (firstName.isEmpty()){
            Toast.makeText(this, "First name required!", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (lastName.isEmpty()){
            Toast.makeText(this, "Last name required!", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (email.isEmpty()){
            Toast.makeText(this, "Email required!", Toast.LENGTH_SHORT).show();
            valid = false;
        }else if (projectLink.isEmpty()){
            Toast.makeText(this, "Project link required!", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        
        return valid;
    }
    
    
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_back_arrow){
            finish();
        }else if (v.getId() == R.id.submit_btn){
            boolean validInfo = checkValidInfo();
            
            if (validInfo){
                showCustomDialog();
            }
        }
    }
    
    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.confirm_submission_layout,
                viewGroup, false);
    
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        mDialog = builder.create();
        mDialog.show();
        
        Button yesBtn = dialogView.findViewById(R.id.confirm_submission_btn);
        ImageButton cancel = dialogView.findViewById(R.id.confirm_submission_cancel);
        
        yesBtn.setOnClickListener(v -> submit());

        cancel.setOnClickListener(v -> mDialog.dismiss());
    }
    
    private void submit() {
        mDialog.dismiss();
        
        String email = mEmail.getText().toString();
        String firstName = mFirstName.getText().toString();
        String lastName = mLastName.getText().toString();
        String projectLink = mProjectLink.getText().toString();
    
        Call<Void> call = mApi.submit(Constants.SUBMIT_URL, email, firstName, lastName,
                projectLink);
        
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                showSuccessDialog();
            }
    
            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                showFailureDialog();
            }
        });
    }
    
    private void showFailureDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View failedDialogView = LayoutInflater.from(this).inflate(R.layout.submission_failed_dialog,
                viewGroup, false);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(failedDialogView);
        final AlertDialog failedDialog = builder.create();
        failedDialog.show();
    
        ConstraintLayout layout = failedDialogView.findViewById(R.id.submission_failed_layout);
        layout.setOnClickListener(v -> failedDialog.dismiss());
    }
    
    private void showSuccessDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View successDialogView = LayoutInflater.from(this).inflate(R.layout.submission_successful_dialog,
                viewGroup, false);
    
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(successDialogView);
        final AlertDialog successDialog = builder.create();
        successDialog.show();
    
        ConstraintLayout layout = successDialogView.findViewById(R.id.submission_successful_layout);
        layout.setOnClickListener(v -> successDialog.dismiss());
    }
}