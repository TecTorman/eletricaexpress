package Controler;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import View.inicial;
import View.pedidos;
import connect.conexao;

public class control_pedido {
	public static void insere_pedido(int Cod_Orca,String Status,  String Dt_Atend){
		  conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL = "INSERT INTO cer.pedido VALUES (null,'"+Cod_Orca+"','"+Status+"','"+Dt_Atend+"');";
			   System.out.println(sSQL);
			   boolean res = stmt.execute(sSQL);
			   stmt.close();
			   produtos.fecharBDConn();
			   System.out.println(res);
			   JOptionPane.showMessageDialog(null,"Pedido agendado com sucesso!!!");
		  }catch(Exception e){
			   JOptionPane.showMessageDialog(null,"Os dados não puderam ser inseridos!!!");
		  }
	}
	public static void excluir_pedido(int Cod){
		  conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL = "DELETE FROM pedido WHERE Codigo='"+Cod+"';";
			   System.out.println(sSQL);
			   boolean res = stmt.execute(sSQL);
			   stmt.close();
			   produtos.fecharBDConn();
			   System.out.println(res);
			   //pedidos.carregar_pedidos();
			   //JOptionPane.showMessageDialog(null,"Pedido agendado com sucesso!!!");
			  
		  }catch(Exception e){
			  // JOptionPane.showMessageDialog(null,"Os dados não puderam ser inseridos!!!");
		  }
	}
	public static void atualiza_pedido(int Cod,String Status){
		conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL = "UPDATE cer.pedido Set Status='"+Status+"' WHERE Codigo='"+Cod+"';";
			   System.out.println(sSQL);
			   boolean res = stmt.execute(sSQL);
			   stmt.close();
			   produtos.fecharBDConn();
			   System.out.println(res);
			   JOptionPane.showMessageDialog(null,"O status foi alterado para "+Status+"!!!");
			   pedidos.carregar_pedidos();
			   
		  }catch(Exception e){
			   JOptionPane.showMessageDialog(null,"O status não pode ser alterado!!!");
		  }
		
	}
	
}
