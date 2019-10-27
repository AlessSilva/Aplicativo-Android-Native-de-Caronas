package com.example.appcaronamobile.DBMemory;

import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Usuario;

import java.util.ArrayList;

public class UsuarioDBMemory implements UsuarioDAO {

    private static ArrayList<Usuario> listaUsuario;
    private static UsuarioDBMemory usuarioDAO;
    private static Long idgerador = new Long(1000);

    private UsuarioDBMemory(){

        listaUsuario = new ArrayList<Usuario>();
        listaUsuario.add( new Usuario(new Long(1),"Alessandro", "Souza","(66) 666666666",
                "ale@alu.ufc.com.br", "mirio", "Discente", "UFC") );

        listaUsuario.add( new Usuario(new Long(2),"Carlos Edu", "Ferreira","(44) 444444444",
                "cadu@alu.ufc.com.br", "nhem", "Discente", "UFC") );

        listaUsuario.add( new Usuario(new Long(3),"Victoria", "Pacheco","(24) 898989898",
                "viviPacheco@ifce.com.br", "vivi", "Docente", "IFCE") );

    }

    @Override
    public Usuario addUsuario(Usuario usuario) {

        idgerador = new Long( idgerador.intValue()+1 );
        usuario.setId( idgerador );
        listaUsuario.add(usuario);

        return usuario;
    }

    @Override
    public Usuario editUsuario(Usuario usuario) {

        int i = 0;
        for ( Usuario u : listaUsuario ){

            if ( u.getId().equals( usuario.getId() ) ){

                listaUsuario.set(i,usuario);
                return usuario;
            }
            i++;
        }

        return usuario;

    }

    @Override
    public void deleteUsuario(Long usuarioId) {

    }

    @Override
    public Usuario getUsuario(Long usuarioId) {

        for( Usuario u : listaUsuario ){

            if ( u.getId().equals(usuarioId) ){
                return u;
            }

        }
        return null;
    }

    public Usuario login( String login, String senha ){

        for ( Usuario u : listaUsuario ){

            if ( u.getEmail().equals(login) || u.getTelefone().equals(login) ){
                if ( u.getSenha().equals(senha) ){
                    return u;
                }
            }

        }

        return null;
    }

    @Override
    public ArrayList<Usuario> getListaUsuario() {
        return null;
    }

    public static UsuarioDAO getInstance(){

        if( usuarioDAO == null ){
            usuarioDAO = new UsuarioDBMemory();
        }

        return usuarioDAO;
    }

}
