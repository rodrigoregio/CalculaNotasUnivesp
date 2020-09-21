package top.rregio.calculanotasunivesp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import top.rregio.CalculaNotasUnivesp.NotaDeProva;
import top.rregio.CalculaNotasUnivesp.ValidaCampos;

public class CompararGabarito extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparar_gabarito);
        runBanner();
        runAdInterstitial();
    }
    public void runBanner(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adViewBanner);
        /*mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId("ca-app-pub-3396081615784704/6115380505");*/
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    public void runAdInterstitial(){
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId("ca-app-pub-3396081615784704/7109199298");
        /*
         * mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");   //teste
         * mInterstitialAd.setAdUnitId("ca-app-pub-3396081615784704/7109199298");   //meu
         */
        mInterstitial.loadAd(new AdRequest.Builder().build());
    }
    public void mosstraInterstitialBanner(){
        if(mInterstitial.isLoaded()){
            mInterstitial.show();
        }else{
            Log.d("TAG","O interstitial não carregou ainda.");
        }
    }
    public void realizarComparacao(View view){
        TextView txtMedia = findViewById(R.id.edtMedia);//media atividades semanais
        TextView txtAlt = findViewById(R.id.edtAlt);//acerto de questões na prova
        TextView txtRes = findViewById(R.id.edtRes);//resultado a ser exibido...

        String textoAlt = txtAlt.getText().toString();
        if(textoAlt.equals(""))
            textoAlt = "0";

        if(textoAlt.startsWith("."))
            textoAlt = "0" + textoAlt;
        if(textoAlt.contains("."))
            textoAlt = textoAlt.charAt(0) + "";

        int acertoAlternativa = Integer.parseInt(textoAlt);

        if (acertoAlternativa > 4)
            acertoAlternativa = 4;

        String textoMedia = txtMedia.getText().toString();
        if(textoMedia.equals(""))
            textoMedia = "0";
        double media = Double.parseDouble(textoMedia);

        if(media > 4)
            media = 4;
        else if(media < 0)
            media = 0;
        double mediaFinal = NotaDeProva.mediaFinal(media);
        System.out.println("media: "+media+"Nota final: "+NotaDeProva.mediaFinal(media));
        double notaProvaObtida = 1.5*acertoAlternativa;

        double nf = (media*0.4)+(notaProvaObtida*0.6);
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Acertos alternativas: ").append(acertoAlternativa)
                .append("\nMedia das semanas: ").append(media)
                .append("\n\n")
                .append("Na prova você precisa de: ").append(ValidaCampos.soDuasCasas(mediaFinal)).append(" pontos.")
                .append("\nMedia final somente com alternativas: ").append(notaProvaObtida).append("\n").append(NotaDeProva.notaFinal(media,nf));

        txtRes.setText(stringBuilder.toString());
        mosstraInterstitialBanner();
    }
}
