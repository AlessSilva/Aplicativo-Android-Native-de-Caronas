package com.example.appcaronamobile.Firebase;

import androidx.annotation.NonNull;

import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

//TODO: Tudo
public class UsuarioFirebase implements UsuarioDAO {

    ConexaoDB conexaoDB = MyConexaoDB.getInstance();

    private static Usuario usuario_logado;

    private static UsuarioFirebase usuarioDAO;

    private static ArrayList<Usuario> usuarios;

    private UsuarioFirebase(){
        usuarios = new ArrayList<>();

        Query query = conexaoDB.getReference().child("Usuario").orderByChild("primeiroNome");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usuarios.clear();
                for( DataSnapshot obj : dataSnapshot.getChildren() ){
                    usuarios.add( obj.getValue(Usuario.class) );
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static UsuarioDAO getInstance(){

        if( usuarioDAO == null ){
            usuarioDAO = new UsuarioFirebase();
        }

        return usuarioDAO;
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        usuario.setId(UUID.randomUUID().toString());
        //idgerador++;
        conexaoDB.getReference().child("Usuario").child(usuario.getId()).setValue(usuario);
        return usuario;
    }

    @Override
    public Usuario editUsuario(Usuario usuario) {
        conexaoDB.getReference().child("Usuario").child(usuario.getId()).setValue(usuario);
        return usuario;
    }

    @Override
    public void deleteUsuario(String usuarioId) {

    }

    @Override
    public Usuario getUsuario(String usuarioId) {

        for( Usuario u : usuarios ){

            if( u.getId().equals(usuarioId) ){
                return u;
            }

        }

        return null;
    }

    @Override
    public ArrayList<Usuario> getListaUsuario() {
        return usuarios;
    }

    @Override
    public Usuario login(String login, String senha) {

        for ( Usuario u : usuarios ){

            if ( u.getEmail().equals(login) || u.getTelefone().equals(login) ){
                if ( u.getSenha().equals(senha) ){
                    usuario_logado = u;
                    return usuario_logado;
                }
            }

        }

        return null;

    }

    @Override
    public Usuario getLogado() {
        return usuario_logado;
    }

}
