package br.edu.unifaj.web.Aula6EstadosMunicipiosService.Estado;

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
public class Estado {
    private int cod;
    private String nome;
    private String sigla;


    public List<Estado> lerArquivoEstadoCSV() throws Exception{
        List<Estado> estados = new ArrayList<>();

        try (FileReader fr = new FileReader("estados.csv");
             BufferedReader br = new BufferedReader(fr)) {

            String linha = null;
            boolean isFirstLine = true;

            while ((linha = br.readLine()) != null) {
                if (isFirstLine) { // Ignora o cabeçalho
                    isFirstLine = false;
                    continue;
                }
                String[] data = linha.split(",");

                int cod  = Integer.parseInt(data[0]);
                String nome = data[1].trim();
                String sigla = data[2].trim();

                Estado e = new Estado(cod, nome, sigla);
                estados.add(e);
            }
        }
        return estados;
    }
}
