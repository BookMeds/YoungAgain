package com.bookmeds.youngagain;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    private ImageView medImage;
    private TextView medName;
    private TextView medDetail;
    private TextView medPrice;
    private boolean restriction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        medImage = (ImageView) findViewById(R.id.med_icon);
        medName = (TextView) findViewById(R.id.med_name);
        medDetail = (TextView) findViewById(R.id.med_details);
        medPrice = (TextView) findViewById(R.id.med_price);

        Bundle extras = getIntent().getExtras();
        medImage.setImageResource(extras.getInt(getString(R.string.med_icon)));
        medName.setText(extras.getString(getString(R.string.med_name)));
        medDetail.setText(extras.getString(getString(R.string.med_desc)));
        medPrice.setText(extras.getString(getString(R.string.med_price)));
        restriction = extras.getBoolean(getString(R.string.med_restrict));
    }

    public void buy(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Successfull")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OrderActivity.this.finish();
                    }
                });
        if (!restriction) {
            builder.setMessage("Your Medicine order has been placed");
        } else {
            builder.setMessage("Your Medicine order has been placed. Your medicines will be given to you after you provide the prescription.");
        }
        builder.create().show();
    }
}
