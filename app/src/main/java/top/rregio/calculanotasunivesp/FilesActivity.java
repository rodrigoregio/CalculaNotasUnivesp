package top.rregio.calculanotasunivesp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FilesActivity extends AppCompatActivity {
    private Spinner fileList;
    private TextView fileConteudo;
    private Button btnCarregarFile;
    private Button btnDel;
    private ArrayList<String> Arquivos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        fileList = (Spinner) findViewById(R.id.fileList);

        fileConteudo = findViewById(R.id.fileContent);
        btnDel=findViewById(R.id.delBtn);
        Listar();
        //dispFiles.setText(Arquivos.toString());
    }
    public void mensagem(String texto){
        Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();
    }
    private String obterDiretorio(){
        File dir = getFilesDir();
        return dir.toString();
    }
    public void clickCarregar(View view){
        final String lstrNomeArquivo;
        final File arquivo;
        String arquivoLinha;
        try{
            lstrNomeArquivo=fileList.getSelectedItem().toString();
            fileConteudo.setText("");
            arquivo=new File(obterDiretorio(),lstrNomeArquivo);
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            while((arquivoLinha = br.readLine()) != null){
                if(!fileConteudo.getText().toString().equals("")){
                    fileConteudo.append("\n");
                }
                fileConteudo.append(arquivoLinha);
            }
            mensagem("Arquivo carregado com sucesso!");
            btnDel.setEnabled(true);
            View.OnClickListener clickOuvinte = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arquivo.delete();
                    mensagem("Arquivo apagado com sucesso!");
                    fileConteudo.setText("");
                    limpaLista();
                    Listar();
                }
            };
            btnDel.setOnClickListener(clickOuvinte);
        } catch (FileNotFoundException e) {
            mensagem(e.toString());
        } catch (IOException e) {
            mensagem(e.toString());
        }

    }
    public void limpaLista(){
        Arquivos.clear();
    }
    public void Listar(){
        File diretorio = new File(obterDiretorio());
        File[] arquivos = diretorio.listFiles();
        if(arquivos != null){
            int tamanho = arquivos.length;
            for(int i=0;i<tamanho;i++){
                File f = arquivos[i];
                if(f.isFile()){
                    Arquivos.add(f.getName());
                }
            }
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,Arquivos);
            fileList.setAdapter(arrayAdapter);
        }
    }
}