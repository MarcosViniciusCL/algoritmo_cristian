/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.model;

import java.net.InetAddress;

/**
 *
 * @author aluno
 */
public class Mensagem {
    private InetAddress endereco;
    private String mensagem;
    private int protocolo;

    public Mensagem(InetAddress endereco, String mensagem, int protocolo) {
        this.endereco = endereco;
        this.mensagem = mensagem;
        this.protocolo = protocolo;
    }

    public void adicionarMensagem(String part){
        this.mensagem += ","+part;
    }
    public InetAddress getEndereco() {
        return endereco;
    }

    public void setEndereco(InetAddress endereco) {
        this.endereco = endereco;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }
    
    
}
