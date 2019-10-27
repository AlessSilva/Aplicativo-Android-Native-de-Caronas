package com.example.appcaronamobile.DBMemory;

import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;

import java.util.ArrayList;

public class CaronaDBMemory implements CaronaDAO {

    private static ArrayList<Carona> listaCarona;
    private static CaronaDBMemory caronaDAO;
    private static Long idgerador = new Long(5000);

    private CaronaDBMemory(){

        listaCarona = new ArrayList<Carona>();

        listaCarona.add( new Carona(new Long(1), new Long(3) , 3 ,
                new Veiculo(  "Cacimbinha" , "CARRO", "J3SUS", "Pink" ),
                "15:35", "Praça do Chalé", false,
                -4.970172002073431, -39.0174075588584, new ArrayList<Usuario>()) );

        listaCarona.add( new Carona(new Long(2), new Long(3) , 2 ,
                new Veiculo( "Cacimbinha" , "CARRO", "J3SUS", "Pink" ),
                "9:30", "UFC", false,
                -4.9698172002073431, -39.0146215588584, new ArrayList<Usuario>()) );

        listaCarona.add( new Carona(new Long(3), new Long(1) , 1 ,
                new Veiculo( "HONDA", "MOTO", "4L355", "AZUL DA COR DO MAR" ),
                "13:00", "Rodoviária", false,
                -4.930172002073431, -39.0174075588584, new ArrayList<Usuario>()) );

        listaCarona.add( new Carona(new Long(4), new Long(2) , 1 ,
                new Veiculo( "COROLA", "CARRO", "JOJO666", "PRETO" ),
                "21:30", "Quixeramobim", true,
                -4.910172002073431, -39.0274075588584, new ArrayList<Usuario>()) );

    }

    @Override
    public void addCarona(Carona carona) {

    }

    @Override
    public void editCarona(Carona carona) {

    }

    @Override
    public void deleteCarona(Long caronaId) {

    }

    @Override
    public Carona getCarona(Long caronaId) {
        return null;
    }

    @Override
    public ArrayList<Carona> getListaCarona() {
        return listaCarona;
    }

    public static CaronaDAO getInstance(){

        if( caronaDAO == null ){
            caronaDAO = new CaronaDBMemory();
        }

        return caronaDAO;
    }

}
