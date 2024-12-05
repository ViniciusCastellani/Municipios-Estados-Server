package br.edu.unifaj.web.Aula6EstadosMunicipiosService.Municipio;

import br.edu.unifaj.web.Aula6EstadosMunicipiosService.Estado.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class MunicipioController {
    @Autowired MunicipioDao dao;

    @PostMapping("api/municipios")
    public List<Municipio> postMunicipios() throws Exception {
        List<Municipio> municipios = new ArrayList<>();
        Municipio m = new Municipio();

        municipios = m.lerArquivoMunicipioCSV();
        return dao.postMunicipios(municipios);
    }

    @GetMapping("api/municipios")
    public List<Municipio> getMunicipios() throws Exception {
        return dao.getMunicipios();
    }
}
