package com.bijoydebnath.visitingcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout callALL;
    LinearLayout emailALL;
    LinearLayout mapALL;
    LinearLayout ofcLocLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callALL = findViewById(R.id.callALL);
        emailALL = findViewById(R.id.emailALL);
        mapALL = findViewById(R.id.mapALL);
        ofcLocLL = findViewById(R.id.ofcLocLL);

        callALL.setOnClickListener(view -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + getString(R.string.mobile_number)));
            startActivity(dialIntent);
        });

        mapALL.setOnClickListener(view -> {
            String strUri = "http://maps.google.com/maps?q=loc:23.77069254511841,90.35441757286257" + ("Shekhertec");
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);

        });
        ofcLocLL.setOnClickListener(view -> {
            String strUri = "http://maps.google.com/maps?q=loc:23.7729649,90.3539872" + ("Codeware Ltd.");
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            startActivity(intent);
        });

        emailALL.setOnClickListener(view -> {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));

            intent.putExtra(Intent.EXTRA_EMAIL, getString(R.string.email));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.visiting_card_details));
            intent.putExtra(Intent.EXTRA_TEXT, getCardDetails());
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });


    }

    String getCardDetails() {
        String name = getString(R.string.name);
        String designation = getString(R.string.designation);
        String education = getString(R.string.education);
        String mobile = getString(R.string.mobile_number);
        String address = getString(R.string.address);
        String email = getString(R.string.email);

        return name + "\n" + designation + "\n" + education + "\n" + mobile +"\n" + email + "\n" + address;
    }

    public void shareInfo(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                getCardDetails());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}