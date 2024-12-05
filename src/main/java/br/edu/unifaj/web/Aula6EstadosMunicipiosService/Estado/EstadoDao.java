package br.edu.unifaj.web.Aula6EstadosMunicipiosService.Estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EstadoDao {
    private @Autowired JdbcTemplate jdbcTemplate;

    public List<Estado> getEstados() throws Exception {
        String sqlQuery = "SELECT cod, nome, sigla FROM ESTADOS";

        try(Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQuery);){

            List<Estado> listaEstados = new ArrayList<>();

            try(ResultSet rs = ps.executeQuery()){

                while(rs.next()){
                    Estado e = new Estado();
                    e.setCod(rs.getInt("cod"));
                    e.setNome(rs.getString("nome"));
                    e.setSigla(rs.getString("sigla"));
                    listaEstados.add(e);
                }
            }
            return listaEstados;
        }
    }

    public List<Estado> postEstados(List<Estado> estados) throws Exception{
        String sqlQuery = "INSERT INTO ESTADOS (cod, nome, sigla) VALUES (?, ?, ?)";

        try(Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sqlQuery)){

            con.setAutoCommit(false);

            List<Estado> listaEstados = new ArrayList<>();

            for(Estado e : estados){
                ps.setInt(1, e.getCod());
                ps.setString(2, e.getNome());
                ps.setString(3, e.getSigla());
                ps.addBatch();
                listaEstados.add(e);
            }

            ps.executeBatch();
            con.commit();

            return listaEstados;
        }
    }
}
