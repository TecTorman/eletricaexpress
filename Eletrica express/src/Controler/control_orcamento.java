package Controler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Model.*;
import connect.conexao;
public class control_orcamento {
	public static void InsereOrcamento(int Carrinho,String Status, String Tipo, String Nm_Cliente, String DT_orca,Double VL_Obra,Double VL_Prod,Double VL_Total){
		  conexao produtos = new conexao();
		  String retorno = "erro";
		  try {
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL = "INSERT INTO cer.orcamento VALUES (null,'"+Carrinho+"','"+Status+"','"+Tipo+"','"+Nm_Cliente+"','"+DT_orca+"','"+VL_Obra+"','"+VL_Prod+"','"+VL_Total+"');";
			   System.out.println(sSQL);
			   boolean res = stmt.execute(sSQL);
			   stmt.close();
			   produtos.fecharBDConn();
			   System.out.println(res);
			   JOptionPane.showMessageDialog(null,"Orçamento registrado com sucesso!!!");
		  }catch(Exception e){
			   JOptionPane.showMessageDialog(null,"Orçamento não pude ser registrado, verifique os campos e tente gravar novamente!!!");
		  }
		 }
	public static void UpdateOrcamento(int codigoCar, String Status, String Tipo, String Cliente, String Orca, Double Obra,Double Produto,Double Total,int codigo){
		   conexao produtos = new conexao();
		   try { 
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL="UPDATE cer.orcamento SET Codigo_carrinho ='"+codigoCar+"',Status='"+Status+"',Tipo='"+Tipo+"',Nm_cliente='"+Cliente+"',Dt_Orc='"+Orca+"',Vl_Obra='"+Obra+"',Vl_Prod='"+Produto+"',Vl_Total='"+Total+"' WHERE Codigo='"+codigo+"';";
			   System.out.println(sSQL);
			   boolean res = stmt.execute(sSQL);
			   stmt.close();
			   produtos.fecharBDConn();
			   System.out.println(res);
			   if(res!=false)JOptionPane.showMessageDialog(null,"Dados do orçamento atualizado com sucesso!");
		  }catch(Exception e){
			   JOptionPane.showMessageDialog(null,"Os dados não puderam ser atualizados, verifique os campos e tente gravar novamente!"); 
		  }
		 }
	public static void Orcamento_Pedido(int codigo){
		   String Status="Agendado";
		   conexao produtos = new conexao();
		   try { 
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL="UPDATE cer.orcamento SET Status='"+Status+"' WHERE Codigo='"+codigo+"';";
			   System.out.println(sSQL);
			   boolean res = stmt.execute(sSQL);
			   stmt.close();
			   produtos.fecharBDConn();
			   System.out.println(res);
			   if(res!=false)JOptionPane.showMessageDialog(null,"Dados atualizados com sucesso!");
		  }catch(Exception e){
			   JOptionPane.showMessageDialog(null,"Os dados não puderam ser atualizados!"); 
		  }
		 }
	public static void InsereCarrinho(int codigo,String modelo,String categoria,String qtd,String unitario,String maodeobra,String frete,String total){
		conexao produtos=new conexao();
		try { 
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL="INSERT INTO cer.item_orcamento VALUES ('"+codigo+"','"+modelo+"','"+categoria+"','"+qtd+"','"+unitario+"','"+maodeobra+"','"+frete+"','"+total+"');";
			   System.out.println(sSQL);
			   boolean res = stmt.execute(sSQL);
			   stmt.close();
			   produtos.fecharBDConn();
			   System.out.println(res);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Os dados não puderam ser atualizados!!!"); 
		}
			   
	}
	public static void LimpaCarrinho(int codigo){
		conexao produtos=new conexao();
		try { 
			   Connection ExConn = (Connection) produtos.abrirBDConn();
			   Statement stmt = (Statement) ExConn.createStatement();
			   String sSQL="DELETE FROM cer.item_orcamento WHERE Codigo='"+codigo+"';";
			   ResultSet rs = stmt.executeQuery(sSQL);
			   System.out.println(rs);		   
		}catch(Exception e){
			
		}

	}
}
