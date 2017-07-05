package Model;

public class produto {
	public String nome;
	public String descricao;
	public String url;
	public Double valor_unit;
	public Double valor_obra;
	public String categoria;
	public Double frete;
	//construtor
	public produto(){
		this.nome= nome;
		this.descricao= descricao;
		this.url= url;
		this.valor_unit=valor_unit;
		this.valor_obra=valor_obra;
		this.categoria=categoria;
		this.frete=frete;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Double getValor_unit() {
		return valor_unit;
	}
	public void setValor_unit(Double valor_unit) {
		this.valor_unit = valor_unit;
	}
	public Double getValor_obra() {
		return valor_obra;
	}
	public void setValor_obra(Double valor_obra) {
		this.valor_obra = valor_obra;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Double getFrete() {
		return frete;
	}
	public void setFrete(Double frete) {
		this.frete = frete;
	}
	

}
