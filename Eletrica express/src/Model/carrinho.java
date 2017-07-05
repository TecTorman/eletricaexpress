package Model;

public class carrinho {
	static String modelo, categoria,qtd,unitario,maodeobra,frete,total;
	
	public static void carrinho(){
		modelo=carrinho.modelo;
		categoria=carrinho.categoria;
		qtd=carrinho.qtd;
		unitario=carrinho.unitario;
		maodeobra=carrinho.maodeobra;
		frete=carrinho.frete;
		total=carrinho.total;
		
	}
	public static String getModelo() {
		return modelo;
	}

	public static void setModelo(String modelo) {
		carrinho.modelo = modelo;
	}

	public static String getCategoria() {
		return categoria;
	}

	public static void setCategoria(String categoria) {
		carrinho.categoria = categoria;
	}

	public static String getQtd() {
		return qtd;
	}

	public static void setQtd(String qtd) {
		carrinho.qtd = qtd;
	}

	public static String getUnitario() {
		return unitario;
	}

	public static void setUnitario(String unitario) {
		carrinho.unitario = unitario;
	}

	public static String getMaodeobra() {
		return maodeobra;
	}

	public static void setMaodeobra(String maodeobra) {
		carrinho.maodeobra = maodeobra;
	}

	public static String getFrete() {
		return frete;
	}

	public static void setFrete(String frete) {
		carrinho.frete = frete;
	}

	public static String getTotal() {
		return total;
	}

	public static void setTotal(String total) {
		carrinho.total = total;
	}
	
}
