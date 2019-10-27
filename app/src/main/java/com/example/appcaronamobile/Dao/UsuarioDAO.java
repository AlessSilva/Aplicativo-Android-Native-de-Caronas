package com.example.appcaronamobile.Dao;

import com.example.appcaronamobile.Model.Usuario;

import java.util.ArrayList;

public interface UsuarioDAO {

    public Usuario addUsuario( Usuario usuario);

    public Usuario editUsuario( Usuario usuario );

    public void deleteUsuario( Long usuarioId );

    public Usuario getUsuario( Long usuarioId );

    public ArrayList<Usuario> getListaUsuario();

    public Usuario login( String login, String senha );
}
