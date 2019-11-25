package com.example.appcaronamobile.Firebase;

import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Usuario;

import java.util.ArrayList;

public class UsuarioFirebase implements UsuarioDAO {

    private static ArrayList<Usuario> listaUsuario;

    private static Usuario usuario_logado;

    private static UsuarioFirebase usuarioDAO;

    private static Long idgerador = new Long(1000);

    private UsuarioFirebase(){}

    public static UsuarioDAO getInstance(){

        if( usuarioDAO == null ){
            usuarioDAO = new UsuarioFirebase();
        }

        return usuarioDAO;
    }

    @Override
    public Usuario addUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario editUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void deleteUsuario(Long usuarioId) {

    }

    @Override
    public Usuario getUsuario(Long usuarioId) {
        return null;
    }

    @Override
    public ArrayList<Usuario> getListaUsuario() {
        return null;
    }

    @Override
    public Usuario login(String login, String senha) {
        return null;
    }

    @Override
    public Usuario getLogado() {
        return null;
    }
}
