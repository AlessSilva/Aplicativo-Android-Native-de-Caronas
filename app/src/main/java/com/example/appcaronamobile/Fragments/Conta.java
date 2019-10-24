package com.example.appcaronamobile.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Util.Masks.MaskEditUtil;

public class Conta extends Fragment {
    Usuario usuario = null;
    private String senhaPedida = "";
    private EditText input;

    private EditText nomeE;
    private EditText sobrenomeE;
    private EditText telefoneE;
    private EditText emailE;
    private EditText senhaE;
    private EditText senharepetidaE;

    private String inst;
    private String sit;

    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;
    private String senharepetida;

    private RadioGroup rginst;
    private RadioGroup rgsit;

    private RadioButton instB;
    private RadioButton sitB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conta, container, false);

        Bundle arguments = getArguments();

        usuario = (Usuario) arguments.getSerializable("usuario");

        nomeE = view.findViewById(R.id.editTextPrimeiroNomeConta);
        sobrenomeE = view.findViewById(R.id.editTextSobrenomeConta);
        telefoneE = view.findViewById(R.id.editTextTelefoneConta);

        telefoneE.addTextChangedListener(MaskEditUtil.mask(telefoneE, MaskEditUtil.FORMAT_PHONE));

        emailE = view.findViewById(R.id.editTextEmailConta);
        senhaE = view.findViewById(R.id.editTextSenhaConta);
        senharepetidaE = view.findViewById(R.id.editTextSenhaRepetidaConta);

        rginst = view.findViewById(R.id.radioGroupInstituicoes);
        rgsit = view.findViewById(R.id.radioGroupSituacao);

        if(usuario.getInstituicao().equals("UFC")) {
            instB = view.findViewById(R.id.radioButtonUFC);
        } else {
            instB = view.findViewById(R.id.radioButtonIFCE);
        }

        if(usuario.getSituacao().equals("Docente")) {
            sitB = view.findViewById(R.id.radioButtonDocente);
        } else if(usuario.getSituacao().equals("Discente")) {
            sitB = view.findViewById(R.id.radioButtonDiscente);
        } else {
            sitB = view.findViewById(R.id.radioButtonOutro);
        }

        nomeE.setText(usuario.getPrimeiroNome());
        sobrenomeE.setText(usuario.getSobrenome());
        telefoneE.setText(usuario.getTelefone());
        emailE.setText(usuario.getEmail());

        instB.setChecked(true);
        sitB.setChecked(true);

        Button finalizar = view.findViewById(R.id.buttonSalvarAlteracoesConta);

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inst = ((RadioButton) getView().findViewById(rginst.getCheckedRadioButtonId())).getText().toString();
                sit = ((RadioButton) getView().findViewById(rgsit.getCheckedRadioButtonId())).getText().toString();

                nome = nomeE.getText().toString();
                sobrenome = sobrenomeE.getText().toString();
                telefone = telefoneE.getText().toString();
                email = emailE.getText().toString();
                senha = senhaE.getText().toString();
                senharepetida = senharepetidaE.getText().toString();

                if(nome.equals("")) {
                    nome = usuario.getPrimeiroNome();
                }
                if(sobrenome.equals("")) {
                    sobrenome = usuario.getSobrenome();
                }
                if(telefone.equals("")) {
                    telefone = usuario.getTelefone();
                }
                if(email.equals("")) {
                    email = usuario.getEmail();
                }

                if(!senha.equals(senharepetida)) {
                    Toast.makeText(view.getContext(), "Senhas estão diferentes!", Toast.LENGTH_LONG).show();
                } else {
                    if(senha.equals("")) {
                        senha = usuario.getSenha();
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Digite sua antiga senha");
                    input = new EditText(getContext());
                    input.setTransformationMethod(new PasswordTransformationMethod());
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            senhaPedida = input.getText().toString();

                            if(senhaPedida.equals(usuario.getSenha())) {
                                usuario.setPrimeiroNome(nome);
                                usuario.setSobrenome(sobrenome);
                                usuario.setEmail(email);
                                usuario.setInstituicao(inst);
                                usuario.setSenha(senha);
                                usuario.setSituacao(sit);
                                usuario.setTelefone(telefone);

                                Toast.makeText(getContext(), "Alterações Salvas", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Senha incorreta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }
        });

        return view;
    }
}
