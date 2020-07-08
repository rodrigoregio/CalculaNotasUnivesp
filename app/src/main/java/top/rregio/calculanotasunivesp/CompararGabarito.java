package top.rregio.calculanotasunivesp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import top.rregio.CalculaNotasUnivesp.NotaDeProva;
import top.rregio.CalculaNotasUnivesp.ValidaCampos;

public class CompararGabarito extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparar_gabarito);
    }
    public void realizarComparacao(View view){
        TextView txtMedia = findViewById(R.id.edtMedia);
        TextView txtAlt = findViewById(R.id.edtAlt);
        TextView txtRes = findViewById(R.id.edtRes);

        String textoAlt = txtAlt.getText().toString();
        int acertoAlternativa;
        if(textoAlt.startsWith(".")){
            textoAlt = "0"+textoAlt;
        }
        if(textoAlt.contains(".")){
            textoAlt = textoAlt.charAt(0)+"";
        }
        acertoAlternativa = Integer.parseInt(textoAlt);
        if(acertoAlternativa > 4){
            acertoAlternativa = 4;
        }
        double media = Double.parseDouble(txtMedia.getText().toString());
        if(media > 4){
            media = 4;
        }else if(media < 0){
            media = 0;
        }
        double mediaFinal = NotaDeProva.mediaFinal(media);
        System.out.println("media: "+media+"Nota final: "+NotaDeProva.mediaFinal(media));
        double notaProvaObtida = 1.5*acertoAlternativa;

        double nf = (media*0.4)+(notaProvaObtida*0.6);
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("Acertos alternativas: ").append(acertoAlternativa)
                .append("\nMedia das semanas: ").append(media)
                .append("\n\n")
                .append("Media Final esperada: ").append(ValidaCampos.soDuasCasas(mediaFinal))
                .append("\nMedia final com alternativas: ").append(notaProvaObtida).append("\n").append(NotaDeProva.notaFinal(media,nf));

        txtRes.setText(stringBuilder.toString());
    }
}
