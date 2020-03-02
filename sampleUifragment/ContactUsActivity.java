package com.scc.loomi.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.scc.loomi.R;
import com.scc.loomi.databinding.ActivityContactUsBinding;
import com.scc.loomi.utils.AppUtils;
import com.scc.loomi.utils.Constants;

public class ContactUsActivity extends AppCompatActivity {
    private final AppCompatActivity activity = this;
    private ActivityContactUsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_contact_us);
        initToolbar();
        initListener();
    }

    private void initListener() {
        binding.tvCall.setOnClickListener(v ->
                new AlertDialog.Builder(activity)
                        .setMessage(Constants.MO_NO)
                        .setPositiveButton(getString(R.string.call), (dialog, which) -> {
                            Intent callIntent = new Intent(Intent.ACTION_VIEW);
                            callIntent.setData(Uri.parse("tel:" + Constants.MO_NO));
                            startActivity(callIntent);
                        })
                        .setCancelable(true)
                        .setNegativeButton(getString(android.R.string.cancel), null)
                        .show());
        binding.tvEmail.setOnClickListener(v -> AppUtils.sendEmail(activity));

    }


    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
