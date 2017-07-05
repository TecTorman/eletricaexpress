package object;

import java.sql.Date;

public class orcamento {
	public int Codigo;
	public int CodigoCar;
	public int getCodigoCar() {
		return CodigoCar;
	}
	public void setCodigoCar(int codigoCar) {
		CodigoCar = codigoCar;
	}
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getCliente() {
		return Cliente;
	}
	public void setCliente(String cliente) {
		Cliente = cliente;
	}
	public String getDt_Orc() {
		return Dt_Orc;
	}
	public void setDt_Orc(String dt_Orc) {
		dt_Orc.replaceAll("//","\\");
		Dt_Orc = dt_Orc;
	}
	public String getDt_Atend() {
		return Dt_Atend;
	}
	public void setDt_Atend(String dt_Atend) {
		dt_Atend.replaceAll("//","\\");
		Dt_Atend = dt_Atend;
	}
	public Double getVL_produtos() {
		return VL_produtos;
	}
	public void setVL_produtos(Double vL_produtos) {
		VL_produtos = vL_produtos;
	}
	public Double getVL_Obra() {
		return VL_Obra;
	}
	public void setVL_Obra(Double vL_Obra) {
		VL_Obra = vL_Obra;
	}
	public Double getVL_Total() {
		return VL_Total;
	}
	public void setVL_Total(Double vL_Total) {
		VL_Total = vL_Total;
	}
	public String Status,Tipo,Cliente;
	String Dt_Orc,Dt_Atend;
	public Double VL_produtos,VL_Obra, VL_Total;
	

}
