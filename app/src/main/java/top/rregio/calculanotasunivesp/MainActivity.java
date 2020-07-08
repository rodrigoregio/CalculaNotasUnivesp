package top.rregio.calculanotasunivesp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void abrePrecisoDe(View view){
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
        Intent sobreIntent = new Intent(this,SobreAppEDesenvolvedor.class);
        startActivity(sobreIntent);
    }
}
