package top.rregio.calculanotasunivesp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SobreApp extends AppCompatActivity {
    String linkedIn = "https://www.linkedin.com/in/rodrigo-r%C3%A9gio-de-ara%C3%BAjo-1322b79a/";
    String Facebook = "https://www.facebook.com/rodrigo.r.dearaujo/";
    String gitHub = "https://github.com/rodrigoregio";
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_app);
        //inicio AdMob
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }
    public void abreFace(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(Facebook));
        startActivity(i);
    }
    public void abreGit(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(gitHub));
        startActivity(i);
    }
    public void abreLinkedIn(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(linkedIn));
        startActivity(i);
    }
}
