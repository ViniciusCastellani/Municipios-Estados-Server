package br.edu.unifaj.web.Aula6EstadosMunicipiosService.Municipio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Municipio {
    private int cod_uf;
    private String nome_uf;
    private String sigla_uf;
    private int cod;
    private String nome;

    public Municipio(Integer cod_uf, Integer cod, String nome) {
        this.cod_uf = cod_uf;
        this.cod = cod;
        this.nome = nome;
    }

    public List<Municipio> lerArquivoMunicipioCSV() throws Exception{
        List<Municipio> municipios = new ArrayList<>();

        try (FileReader fr = new FileReader("municipios.csv");
             BufferedReader br = new BufferedReader(fr)) {

            String linha = null;

            boolean isFirstLine = true;

            while ((linha = br.readLine()) != null) {
                if (isFirstLine) { // Ignora o cabeçalho
                    isFirstLine = false;
                    continue;
                }

                String[] data = linha.split(",");

                int cod_uf  = Integer.parseInt(data[0]);
                int cod  = Integer.parseInt(data[1]);
                String nome = data[2].trim();

                Municipio m = new Municipio(cod_uf, cod, nome);
                municipios.add(m);
            }
        }
        return municipios;
    }
}