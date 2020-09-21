package top.rregio.calculanotasunivesp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder().build();
        /*mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId("ca-app-pub-7864032411926869/8055853379");*/
        mAdView.loadAd(adRequest);
    }
    @Override
    protected void onDestroy(){
        if(mAdView != null){
            mAdView.destroy();
        }
        super.onDestroy();
    }
    public void abrePrecisoDe(View view){
        ReviewManager manager= ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                ReviewInfo reviewInfo = task.getResult();
            }
        });
        Intent calcularNota = new Intent(this, PrecisoDeActivity.class);
        startActivity(calcularNota);
    }
    public void abreComparacaoGabarito(View view){
        Intent comparaGabarito = new Intent(this, CompararGabarito.class);
        startActivity(comparaGabarito);
    }
    public void abreArquivosApp(View view){
        Intent appFiles = new Intent(this,FilesActivity.class);
        startActivity(appFiles);
    }
    public void abreSobre(View view){
        Intent sobreIntent = new Intent(this, SobreApp.class);
        startActivity(sobreIntent);
    }
}
