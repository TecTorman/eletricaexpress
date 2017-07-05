package Controler;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connect.conexao;

public class control_produto {
	public static void InsereProdutos(String nome, Double uni, Double custo, String url, String descricao, Double frete, String categoria){
		conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
		    
		   Connection ExConn = (Connection) produtos.abrirBDConn();
		   Statement stmt = (Statement) ExConn.createStatement();
		   String sSQL = "INSERT INTO cer.produtos VALUES (null,'"+nome+"','"+uni+"','"+custo+"','"+descricao+"','"+url+"','"+frete+"','"+categoria+"');";
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
	public static void UpdateProdutos(int codigo,String nome, String descricaoU, String categoria, Double uniU, Double custoU, Double frete, String link){
		conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
		    
		   Connection ExConn = (Connection) produtos.abrirBDConn();
		   Statement stmt = (Statement) ExConn.createStatement();
		   String sSQL="UPDATE cer.produtos SET Nome ='"+nome+"',Unitario='"+uniU+"',M_obra='"+custoU+"',Descr='"+descricaoU+"',Link='"+link+"',Envio='"+frete+"',Categoria='"+categoria+"'"
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
