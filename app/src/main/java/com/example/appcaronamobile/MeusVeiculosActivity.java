package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.Util.Codes.RequestCodes;
import com.example.appcaronamobile.Util.Codes.ResultCodes;
import com.example.appcaronamobile.Util.CustomAdapters.VeiculosAdapter;

public class MeusVeiculosActivity extends AppCompatActivity {
    private ListView listView;
    private Usuario usuario;
    private VeiculosAdapter veiculosAdapter;
    private int selecionado = -1;

    UsuarioDAO usuarioDAO;

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
                listView.setSelector(android.R.color.holo_orange_light);
            }
        });

        usuarioDAO = UsuarioDBMemory.getInstance();
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
        intent.putExtra("modelo", "");
        intent.putExtra("cor", "");
        intent.putExtra("placa", "");
        intent.putExtra("code", String.valueOf(RequestCodes.ADD_VEICULO));
        startActivityForResult(intent, RequestCodes.ADD_VEICULO);
    }

    public void onClickButtonEditar(View view) {
        if(selecionado == -1) {
            Toast.makeText(this, "Selecione um veículo", Toast.LENGTH_SHORT).show();
        } else {
            Veiculo veiculo = usuario.getVeiculoAt(selecionado);
            Intent intent = new Intent(this, CadastroVeiculoActivity.class);
            intent.putExtra("modelo", veiculo.getModelo());
            intent.putExtra("cor", veiculo.getCor());
            intent.putExtra("placa", veiculo.getPlaca());
            intent.putExtra("senha", usuario.getSenha());
            intent.putExtra("imagem", veiculo.getImagem());
            intent.putExtra("code", String.valueOf(RequestCodes.EDITAR_VEICULOS));
            startActivityForResult(intent, RequestCodes.EDITAR_VEICULOS);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.putExtra("usuario", usuario);
            setResult(ResultCodes.MEUS_VEICULOS, intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCodes.ADD_VEICULO) {
            if(resultCode == ResultCodes.ADD_VEICULOS) {
                Veiculo veiculo = (Veiculo) data.getSerializableExtra("veiculo");
                usuario.addVeiculo(veiculo);
                veiculosAdapter.notifyDataSetChanged();
                usuario = usuarioDAO.editUsuario(usuario);
            }
            if(resultCode == ResultCodes.CANCELAR) {
                Toast.makeText(this, "Operação cancelada", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == RequestCodes.EDITAR_VEICULOS) {
            if(resultCode == ResultCodes.EDITAR_VEICULO) {
                Veiculo veiculo = (Veiculo) data.getSerializableExtra("veiculo");
                usuario.overrideVeiculo(selecionado, veiculo);
                veiculosAdapter.notifyDataSetChanged();
                usuario = usuarioDAO.editUsuario(usuario);
                selecionado = -1;
            }
            if(resultCode == ResultCodes.CANCELAR) {
                Toast.makeText(this, "Operação cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
