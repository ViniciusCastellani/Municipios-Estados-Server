package br.edu.unifaj.web.Aula6EstadosMunicipiosService.Estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class EstadoController {
    @Autowired EstadoDao dao;

    @PostMapping("api/estados")
    public List<Estado> postEstados() throws Exception {
        List<Estado> estados = new ArrayList<>();
        Estado e = new Estado();

        estados = e.lerArquivoEstadoCSV();
        return dao.postEstados(estados);
    }

    @GetMapping("api/estados")
    public List<Estado> getEstados() throws Exception {
        return dao.getEstados();
    }

}
