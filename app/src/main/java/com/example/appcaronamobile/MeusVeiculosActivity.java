package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.Util.CustomAdapters.VeiculosAdapter;

public class MeusVeiculosActivity extends AppCompatActivity {
    private ListView listView;
    private Usuario usuario;
    private VeiculosAdapter veiculosAdapter;
    private int selecionado = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_veiculos);

        listView = (ListView) findViewById(R.id.listMeusVeiculos);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        veiculosAdapter = new VeiculosAdapter(this, usuario.getVeiculos());
        listView.setAdapter(veiculosAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selecionado = position;
                listView.setSelection(selecionado);
                listView.setSelector(android.R.color.holo_blue_light);
            }
        });
    }

    public void onClickButtonRemover(View view) {
        if(selecionado == -1) {
            Toast.makeText(this, "Selecione um veículo", Toast.LENGTH_SHORT).show();
        } else {
            veiculosAdapter.remove(selecionado);
            veiculosAdapter.notifyDataSetChanged();
            selecionado = -1;
            Toast.makeText(this, "Veículo deletado da sua lista!", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickButtonAdicionar(View view) {
        Intent intent = new Intent(this, CadastroVeiculoActivity.class);
        intent.putExtra("senha", usuario.getSenha());
        startActivityForResult(intent, 111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111) {
            if(resultCode == 222) {
                Veiculo veiculo = (Veiculo) data.getSerializableExtra("veiculo");
                usuario.addVeiculo(veiculo);
                veiculosAdapter.notifyDataSetChanged();
            }
            if(resultCode == 333) {
                Toast.makeText(this, "Operação cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
