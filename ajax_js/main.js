
function busca_cep(){
    var api = "https://viacep.com.br/ws/";

    var cep = document.getElementById('cep').value;
    document.getElementById("div-erro").style.display = "none";
    document.getElementById("div-resp").style.display = "none";

    if( cep.length != 8){
        document.getElementById('resp-erro').innerHTML = "Digite o cep para buscar os dados!";
        document.getElementById("div-erro").style.display = "block";
    }

    // alert(cep);

    var ajax = new XMLHttpRequest;

    ajax.onloadend = function(){

        var json = ajax.responseText;
        json = JSON.parse(json);

        if( json.erro === true ){
            document.getElementById('resp-erro').innerHTML = "O cep passado está invalido!";
            document.getElementById("div-erro").style.display = "block";
            document.getElementById('cep').value = "";
            return;
        }

        // console.log(json);
        
        var resposta = "O cep possui os seguintes dados... <br><b>Rua:</b> "+ json.logradouro +"<br><b>Bairro:</b> " + json.bairro;
        resposta += "<br><b>Cidade:</b> " + json.localidade + "<br><b>Estado:</b> " + json.uf + "<br><b>IBGE:</b> " + json.ibge;

        document.getElementById('resposta').innerHTML = resposta;
        document.getElementById("div-resp").style.display = "block";
    }

    var url = api + cep + '/json';
    // console.log(url);
    
    ajax.open('GET', url );
    ajax.send(null);
}