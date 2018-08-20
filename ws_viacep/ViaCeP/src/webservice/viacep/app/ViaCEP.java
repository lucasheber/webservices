package webservice.viacep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class ViaCEP {
    
    // Define a url de conexao com WebServices.
    // Note: A string já possui o parametro %s embutido. Basta usar 
    // o String.format( WS_URL, seuCep ) para obter a URL completa.

    private final String WS_URL = "https://viacep.com.br/ws/%s/json";
    private final String VIA_CEP = "Via CEP";

    public static void main(String[] args) {
        new ViaCEP().iniciar();
    }

    public void iniciar() {
    	String cep = JOptionPane.showInputDialog(null, "Digite o CEP:", VIA_CEP, JOptionPane.QUESTION_MESSAGE);
    	getCEP( cep.replace("-", "") );
    }
    
    /**
     * Realiza a consulta do cep passado.
     */
    public void getCEP( String cep ){
        String ws = String.format( WS_URL, cep );

        try {

            URL url = new URL( ws );
            URLConnection connection = url.openConnection();

            BufferedReader input = new BufferedReader( new InputStreamReader(connection.getInputStream(), "utf-8") );
            
            String line;

            StringBuilder source = new StringBuilder();

            while( (line = input.readLine()) != null )
                source.append(line);

            input.close();

            JSONObject jsonObject = new JSONObject(source.toString());
            
            if( jsonObject.has("error") ) {
            	messageError("Não foi possivel consultar o CEP", VIA_CEP);
            	return;
            }
            
            String dados = "";
	            dados += "CEP: " + jsonObject.getString("cep");
	            dados += "\nLogradouro: " + jsonObject.getString("logradouro");
	            dados += "\nComplemento: " + jsonObject.getString("complemento");
	            dados += "\nBairro: " + jsonObject.getString("bairro");
	            dados += "\nLocalidade: " + jsonObject.getString("localidade");
	            dados += "\nUF: " + jsonObject.getString("uf");
	            dados += "\nUnidade: " + jsonObject.getString("unidade");
	            dados += "\nIBGE: " + jsonObject.getString("ibge");
	            dados += "\nGIA: " + jsonObject.getString("gia");
            
            messageInfo(dados, VIA_CEP);
            
        } catch ( MalformedURLException e ) {
            messageError( e.getMessage(), VIA_CEP);

        } catch ( IOException e ){
            messageError( e.getMessage(), VIA_CEP);
        } catch ( Exception e ){
        	messageError( e.getMessage(), VIA_CEP);
        } 
    }

    
    /* *********************************************************** */
    /* ****************** PRIVATED METHODS *********************** */

    private void messageError( String message, String title ){
    	JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE, null);
    }
    
    private void messageInfo( String message, String title ) {
    	JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE, null);
    }
    
}