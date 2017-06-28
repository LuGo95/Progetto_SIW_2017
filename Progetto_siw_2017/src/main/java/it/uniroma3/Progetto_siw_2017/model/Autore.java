package it.uniroma3.Progetto_siw_2017.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
//@NamedQueries(value = { @NamedQuery(name="findAll", query="SELECT a FROM Autore a") })
public class Autore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String nome;

	@NotNull
	private String cognome;

	@NotNull
	private String nazionalita;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date data_nascita;

	@Temporal(TemporalType.DATE)
	private Date data_morte;

	@OneToMany (mappedBy="autore", fetch = FetchType.EAGER)
	@Cascade({CascadeType.DELETE})   // elimina tutto in cascata, se elimino l' autore elimino anche tutti i suoi quadri
	private List<Quadro> quadri;


	protected Autore() {
		this.quadri=new ArrayList<>();
	}

	public Autore(String nome, String cognome, String nazionalita) {
		this.nome=nome;
		this.cognome=cognome;
		this.nazionalita=nazionalita;
		this.quadri=new ArrayList<>();
	}

	//public long getId() {
	//return id;
	//}

	//public void setId(long id) {
	//this.id = id;
	//}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public Date getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(Date data_nascita) {
		this.data_nascita = data_nascita;
	}

	public Date getData_morte() {
		return data_morte;
	}

	public void setData_morte(Date data_morte) {
		this.data_morte = data_morte;
	}

	public List<Quadro> getQuadri() {
		return quadri;
	}

	public void setQuadri(List<Quadro> quadri) {
		this.quadri = quadri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autore other = (Autore) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autore [nome=" + nome + ", cognome=" + cognome + ", nazionalita=" + nazionalita + ", data_nascita="
				+ data_nascita + ", data_morte=" + data_morte + ", quadri=" + quadri + "]";
	}

}
