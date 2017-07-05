package Model;

public class clientes {
	public String nome;
	public String telefone;
	public int celular;
	public String endereço;
	public String cidade;
	public String estado;
	public String bairro;
	public String cpf;
	public String cep;
	public String rg;
	public String email;
	public String anotacoes;
	public clientes(){
		nome=this.nome;
		telefone=this.telefone;
		celular=this.celular;
		endereço=this.endereço;
		cidade=this.cidade;
		estado=this.estado;
		bairro=this.bairro;
		cpf=this.cpf;
		cep=this.cep;
		rg=this.rg;
		email=this.email;
		anotacoes=this.anotacoes;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone2) {
		this.telefone = telefone2;
	}
	public int getCelular() {
		return celular;
	}
	public void setCelular(int celular) {
		this.celular = celular;
	}
	public String getEndereço() {
		return endereço;
	}
	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpfGet) {
		this.cpf = cpfGet;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep2) {
		this.cep = cep2;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getanotacoes() {
		return anotacoes;
	}
	public void setanotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}
	
	

}
