package com.example.appcaronamobile.DBMemory;

import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;

import java.util.ArrayList;

public class UsuarioDBMemory implements UsuarioDAO {

    private static ArrayList<Usuario> listaUsuario;
    private static Usuario usuario_logado;
    private static UsuarioDBMemory usuarioDAO;
    private static Long idgerador = new Long(1000);

    private UsuarioDBMemory(){

        listaUsuario = new ArrayList<Usuario>();

        Usuario u1 = new Usuario( new Long(1),"Alessandro", "Souza", "(90) 9 999921232",
                "ale@123", "123", "Discente", "UFC", null);

        u1.addVeiculo( new Veiculo( "Honda", "Moto", "3ER-EWQR", "Lilas", null) );

        Usuario u2 = new Usuario( new Long(2),"Carlos", "Ferreira", "(90) 9 999921232",
                "carlos@123", "123", "Discente", "UFC", null);

        u2.addVeiculo( new Veiculo( "Honda", "Moto", "3ER-EWQR", "Lilas", null) );
        u2.addVeiculo( new Veiculo( "Ferrari", "Carro", "3ER-EWW1", "Lilas", null) );

        Usuario u3 = new Usuario( new Long(3),"Viviane", "Pacheco", "(90) 9 999921232",
                "ale@123", "123", "Docente", "IFCE", null);

        u3.addVeiculo( new Veiculo( "Honda", "Moto", "3ER-EWQR", "Lilas", null) );

        listaUsuario.add(u1);
        listaUsuario.add(u2);
        listaUsuario.add(u3);

        usuario_logado = null;
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
                    usuario_logado = u;
                    return usuario_logado;
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

    @Override
    public Usuario getLogado(){
        return usuario_logado;
    }

}
