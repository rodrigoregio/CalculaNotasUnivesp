package top.rregio.calculanotasunivesp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import top.rregio.CalculaNotasUnivesp.NotaDeProva;
import top.rregio.CalculaNotasUnivesp.ValidaCampos;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrecisoDeActivity extends AppCompatActivity{
    private AdView mAdView;
    private InterstitialAd myInter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precisode);
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
        myInter=new InterstitialAd(this);
        myInter.setAdUnitId("ca-app-pub-7864032411926869/1490445022");
        myInter.loadAd(new AdRequest.Builder().build());

        myInter.setAdListener(new AdListener(){
            @Override
            public void onAdClosed(){
                myInter.loadAd(new AdRequest.Builder().build());
            }
        });
    }

    public void compartilharDados(View view){
        final TextView nfT = findViewById(R.id.txtNotaFinal);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, nfT.getText().toString()+"\n\n#RREGIO_DEV");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent,null);
        startActivity(shareIntent);
    }

    public void mensagem(String texto){
        Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();
    }
    public void salvaDados(View view){
        final TextView nfT2 = findViewById(R.id.txtNotaFinal);
        final TextView matT = findViewById(R.id.txtMat);
        String fileName = matT.getText().toString();
        String fileType=".txt";
        String putOnFile = nfT2.getText().toString();

        FileOutputStream fos;
        try{
            fos = openFileOutput(fileName+fileType,Context.MODE_PRIVATE);
            fos.write(putOnFile.getBytes());
            fos.flush();
            fos.close();
            mensagem("Salvo com sucesso!");
        } catch (FileNotFoundException e) {
            mensagem(e.toString());
        } catch (IOException e) {
            mensagem(e.toString());
        }finally {
            mensagem("Terminei de salvar!");
        }
    }

    public void calcularNota(View view){
        final TextView nS3 = findViewById(R.id.txtS3);
        final TextView nS4 = findViewById(R.id.txtS4);
        final TextView nS5 = findViewById(R.id.txtS5);
        final TextView nS6 = findViewById(R.id.txtS6);
        final TextView txtNF = findViewById(R.id.txtNotaFinal);
        final TextView txtMat = findViewById(R.id.txtMat);

        final Button btnSMat = findViewById(R.id.btnSaveMat);
        final Button shareBtn = findViewById(R.id.shareBtn);

        double notas[] = new double[4];
        notas[0] = ValidaCampos.transformaTexto(nS3.getText().toString());
        notas[1] = ValidaCampos.transformaTexto(nS4.getText().toString());
        notas[2] = ValidaCampos.transformaTexto(nS5.getText().toString());
        notas[3] = ValidaCampos.transformaTexto(nS6.getText().toString());
        double media = NotaDeProva.calculaMediaAtividades(notas);
        double notaFinal = NotaDeProva.mediaFinal(media);
        StringBuilder sb = new StringBuilder();
        sb.append("Nota Semana 3: ").append(ValidaCampos.soDuasCasas(notas[0])).append("\n")
                .append("Nota Semana 4: ").append(ValidaCampos.soDuasCasas(notas[1])).append("\n")
                .append("Nota Semana 5: ").append(ValidaCampos.soDuasCasas(notas[2])).append("\n")
                .append("Nota Semana 6: ").append(ValidaCampos.soDuasCasas(notas[3])).append("\n")
                .append("\n")
                .append("Media das atividades: ").append(ValidaCampos.soDuasCasas(media)).append("\n")
                .append("Na prova você precisa de: ").append(ValidaCampos.soDuasCasas(notaFinal));
        txtNF.setText(sb.toString());

        txtMat.setEnabled(true);
        btnSMat.setEnabled(true);
        if(myInter.isLoaded()){
            myInter.show();
        }else{
            Log.d("TAG", "O instersticial ainda não carregou!");
        }
    }

}
//EAAKr2HFNb8gBAJq71yJaLOmgn2baZCXpL8QGjZCX7tX6BfwrJiazHFVbGfnhpX3mNzBizQS5OZBbrnPJZAZAlZBD90qSxwuaza2ZCEN4z19oJmwgR2fkKJkWba24p4bvRQzVUE287xrnKZBvAhbih2XqENnlJ1MpZAfcy1UAAjNprprR3HX0v1ZCVk