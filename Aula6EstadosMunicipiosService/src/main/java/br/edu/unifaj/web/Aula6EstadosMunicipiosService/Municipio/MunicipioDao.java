package br.edu.unifaj.web.Aula6EstadosMunicipiosService.Municipio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipioDao {
    private @Autowired JdbcTemplate jdbcTemplate;

    public List<Municipio> getMunicipios() throws Exception {
        String sqlQuery = "SELECT M.cod AS COD_MUNICIPIO, E.sigla as SIGLA_ESTADO, E.nome AS NOME_ESTADO, " +
                                 "M.nome AS NOME_MUNICIPIO " +
                          "FROM ESTADOS E, MUNICIPIOS M " +
                          "WHERE M.cod_uf = E.cod ";

        try(Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQuery);){

            List<Municipio> listaMunicipios = new ArrayList<>();

            try(ResultSet rs = ps.executeQuery()){

                while(rs.next()){
                    Municipio m = new Municipio();
                    m.setCod(rs.getInt("COD_MUNICIPIO"));
                    m.setSigla_uf(rs.getString("SIGLA_ESTADO"));
                    m.setNome_uf(rs.getString("NOME_ESTADO"));
                    m.setNome(rs.getString("NOME_MUNICIPIO"));
                    listaMunicipios.add(m);
                }
            }
            return listaMunicipios;
        }
    }


    public List<Municipio> postMunicipios(List<Municipio> municipios) throws Exception{
        String sqlQuery = "INSERT INTO MUNICIPIOS (cod_uf, cod, nome) VALUES (?, ?, ?)";

        try(Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQuery)){

            con.setAutoCommit(false);

            List<Municipio> listaMunicipios = new ArrayList<>();

            for(Municipio m : municipios){
                ps.setInt(1, m.getCod_uf());
                ps.setInt(2, m.getCod());
                ps.setString(3, m.getNome());
                ps.addBatch();
                listaMunicipios.add(m);
            }

            ps.executeBatch();
            con.commit();

            return listaMunicipios;
        }
    }
}
