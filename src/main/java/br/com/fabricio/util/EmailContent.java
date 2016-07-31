package br.com.fabricio.util;

import java.text.SimpleDateFormat;
import java.util.List;

public final class EmailContent {

    private EmailContent() {
        throw new AssertionError();
    }

    public static String criticality(String user){

        StringBuilder text = new StringBuilder("<font face='Arial' size='2'>");
        text.append("<p>Prezados(as) Senhores(as)</p>");

        if (user == "0") {

            text.append("<p>Em conversa com os responsáveis das operações da equipe, hoje devemos ficar em alerta com movimentações e carregamentos a noite, com as operações descritas abaixo:</p>");

        
        } else {
            text.append("<p>Em conversa com os responsáveis das operações da equipe, hoje não existem movimentações ou carregamentos em alerta.</p>");
        }

        text.append("<p>Atenciosamente,</p>");
        text.append("<p>"+user+"</p>");

        text.append("</font>");

        return text.toString();
    }
  
    public static String incidentReport(String user) {

        StringBuilder text = new StringBuilder("<font face='Arial' size='2'>");
        text.append("<p>Prezados(as) Senhores(as)</p>");

        text.append("<p>Segue anexo relatório com todos os incidentes detectados com suas respectivas ações.<p>");

        text.append("<p>Atenciosamente,</p>");
        text.append("<p>"+user+"</p>");

        text.append("</font>");

        return text.toString();
    }
}
