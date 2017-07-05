package Controler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import View.cliente_controle;
import connect.conexao;

public class control_cliente {
	public static void InsereClientes(String nome,String telefone, String endereco,String cidade, String estado,String cep, String bairro, String cpfGet, String rg,String email, String anotacoes){
		  conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
		    
		   Connection ExConn = (Connection) produtos.abrirBDConn();
		   Statement stmt = (Statement) ExConn.createStatement();
		   String sSQL = "INSERT INTO cer.clientes VALUES (null,'"+nome+"','"+telefone+"','"+endereco+"','"+cidade+"','"+estado+"','"+cep+"','"+bairro+"','"+cpfGet+"','"+rg+"','"+email+"','"+anotacoes+"');";
		   System.out.println(sSQL);
		   boolean res = stmt.execute(sSQL);
		   stmt.close();
		   produtos.fecharBDConn();
		   System.out.println(res);
		   JOptionPane.showMessageDialog(null,"Dados inseridos com sucesso!!!");
		  }catch(Exception e){
		    JOptionPane.showMessageDialog(null,"Os dados não puderam ser inseridos!!!");
		  }
	}
	public static void UpdateClientes(int codigo,String nome,String telefone, String endereco,String cidade, String estado,String cep, String bairro, String cpfGet, String rg,String email, String anotacoes){
		conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
		    
		   Connection ExConn = (Connection) produtos.abrirBDConn();
		   Statement stmt = (Statement) ExConn.createStatement();
		   String sSQL="UPDATE cer.clientes SET Nome ='"+nome+"',Telefone='"+telefone+"',Endereco='"+endereco+"',Cidade='"+cidade+"',Estado='"+estado+"',Cep='"+cep+"',Bairro='"+bairro+"',Cpf='"+cpfGet+"',Rg='"+rg+"',Email='"+email+"',Anotacoes='"+anotacoes+"'"
		   + " WHERE Codigo='"+codigo+"';";
		   System.out.println(sSQL);
		   boolean res = stmt.execute(sSQL);
		   stmt.close();
		   produtos.fecharBDConn();
		   System.out.println(res);
		   if(res!=false)JOptionPane.showMessageDialog(null,"Dados atualizados com sucesso!!!");
		  }catch(Exception e){
		    JOptionPane.showMessageDialog(null,"Os dados não puderam ser atualizados!!!");
		    
		  }
		 }
}
