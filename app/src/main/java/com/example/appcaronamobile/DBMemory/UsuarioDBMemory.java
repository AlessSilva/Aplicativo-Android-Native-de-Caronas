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

        Usuario u1 = new Usuario(new Long(1),"Alessandro", "Souza","(87) 9 893443882",
                "ale@123", "123", "Discente", "UFC");

        u1.addVeiculo( new Veiculo("Toyota","Carro","MM5-DD12","Branco") );
        u1.addVeiculo(new Veiculo("Corola","Carro","NN3-J3SU","Branco"));

        listaUsuario.add( u1 );

        Usuario u2 = new Usuario(new Long(2),"Carlos Eduardo", "Ferreira","(94) 848454399",
                "cadu@123", "nhem", "Discente", "UFC");

        u2.addVeiculo( new Veiculo("Honda","Moto","F23-DD12","Azul") );
        u2.addVeiculo(new Veiculo("Honda","Moto","LOP-LOCO","Preto"));

        listaUsuario.add( u2 );

        Usuario u3 = new Usuario(new Long(3),"Victoria", "Pacheco","(24) 898989898",
                "vivi@123", "vivi", "Docente", "IFCE");

        u3.addVeiculo( new Veiculo("Fusca","Carro","666-D3AB","Vermelho") );

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
