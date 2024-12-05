function buscarEstados() {
    fetch("http://localhost:8081/api/estados", {
       method: "GET",
       headers: {
           'Accept': 'application/json',
           'Content-Type': 'application/json'
       }
    })
    .then(res => res.json())
    .then(res => atualizarTabelaEstados(res))
    .catch(err => alert(err.message));
}


function buscarMunicipios() {
    fetch("http://localhost:8081/api/municipios", {
       method: "GET",
       headers: {
           'Accept': 'application/json',
           'Content-Type': 'application/json'
       }
    })
    .then(res => res.json())
    .then(res => atualizarTabelaMunicipios(res))
    .catch(err => alert(err.message));
}


function atualizarTabelaMunicipios(listaMunicipios){
    var tabela = "<table class='w3-table-all w3-striped w3-bordered w3-centered w3-black' style='width:100%;'>";

    tabela += "<thead>";
    tabela += "<tr>";
    tabela += "<th class='w3-text-black'>COD</th>";
    tabela += "<th class='w3-text-black'>SIGLA_UF</th>";
    tabela += "<th class='w3-text-black'>NOME_UF</th>";
    tabela += "<th class='w3-text-black'>NOME</th>";
    tabela += "</tr>";
    tabela += "</thead>";

    listaMunicipios.forEach(municipio => {
        var municipioData = JSON.stringify(municipio);

        tabela += "<tr>";
        tabela += `<td class='w3-text-black'>${municipio.cod}</td>`;
        tabela += `<td class='w3-text-black'>${municipio.sigla_uf}</td>`;
        tabela += `<td class='w3-text-black'>${municipio.nome_uf}</td>`;
        tabela += `<td class='w3-text-black'>${municipio.nome}</td>`;
        tabela += "</tr>";
    });

    tabela += "</table>";

    document.getElementById("divPrincipal").innerHTML = tabela;

}


function atualizarTabelaEstados(listaEstados){
    var tabela = "<table class='w3-table-all w3-striped w3-bordered w3-centered w3-black' style='width:100%;'>";

        tabela += "<thead>";
        tabela += "<tr>";
        tabela += "<th class='w3-text-black'>COD</th>";
        tabela += "<th class='w3-text-black'>SIGLA</th>";
        tabela += "<th class='w3-text-black'>NOME</th>";
        tabela += "</tr>";
        tabela += "</thead>";

        listaEstados.forEach(estado => {
            var estadoData = JSON.stringify(estado);

            tabela += "<tr>";
            tabela += `<td class='w3-text-black'>${estado.cod}</td>`;
            tabela += `<td class='w3-text-black'>${estado.sigla}</td>`;
            tabela += `<th class='w3-text-black'>${estado.nome}</td>`;
            tabela += "</tr>";
        });

        tabela += "</table>";

        document.getElementById("divPrincipal").innerHTML = tabela;
}


function enviarMunicipios(municipios){

   let headers = {
           'Accept': 'application/json',
           'Content-Type': 'application/json'
       };
       fetch("http://localhost:8081/api/municipios", {
           headers: headers,
           method: "POST",
           body: null
       })
       .then(res => res.json())
       .then(res => alert("Municipios inseridos com sucesso"))
       .catch(err => alert("Erro ao inserir no servidor" + err.message));
}


function enviarEstados(estados){

   let headers = {
           'Accept': 'application/json',
           'Content-Type': 'application/json'
       };
       fetch("http://localhost:8081/api/estados", {
           headers: headers,
           method: "POST",
           body: null
       })
       .then(res => res.json())
       .then(res => alert("Estados inseridos com sucesso"))
       .catch(err => alert("Erro ao inserir no servidor" + err.message));
}


function irTelaEstados(){
    window.location.href = "estados.html";
}


function irTelaMunicipios(){
    window.location.href = "municipios.html";
}


function irTelaPrincipal(){
    window.location.href = "index.html";
}


    // Used to toggle the menu on small screens when clicking on the menu button
    function myFunction() {
       var x = document.getElementById("navDemo");
        if (x.className.indexOf("w3-show") == -1) {
          x.className += " w3-show";
        } else {
          x.className = x.className.replace(" w3-show", "");
        }
    }

    // When the user clicks anywhere outside of the modal, close it
    var modal = document.getElementById('ticketModal');
    window.onclick = function(event) {
      if (event.target == modal) {
        modal.style.display = "none";
      }
    }