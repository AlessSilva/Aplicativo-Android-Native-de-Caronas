package com.example.appcaronamobile.Dao;

import com.example.appcaronamobile.Model.Usuario;

import java.util.ArrayList;

public interface UsuarioDAO {

    public void addUsuario( Usuario usuario);

    public void editUsuario( Usuario usuario );

    public void deleteUsuario( Long usuarioId );

    public Usuario getUsuario( Long usuarioId );

    public ArrayList<Usuario> getListaUsuario();

    public Usuario login( String login, String senha );
}
