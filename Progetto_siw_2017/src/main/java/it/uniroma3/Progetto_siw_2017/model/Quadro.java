package it.uniroma3.Progetto_siw_2017.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Quadro {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String titolo;

	@NotNull
	private Integer anno;

	private String tecnica;

	private Float lunghezza;

	private Float larghezza;

	@NotNull
	@ManyToOne
	private Autore autore;


	protected Quadro() {}

	public Quadro(String titolo, Integer anno, String tecnica, Float lunghezza, Float larghezza, Autore autore) {
		this.titolo=titolo;
		this.anno=anno;
		this.tecnica=tecnica;
		this.lunghezza=lunghezza;
		this.larghezza=larghezza;
		this.autore=autore;
	}

	//public long getId() {
	//return id;
	//}

	//public void setId(long id) {
	//this.id = id;
	//}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public String getTecnica() {
		return tecnica;
	}

	public void setTecnica(String tecnica) {
		this.tecnica = tecnica;
	}

	public Float getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(Float lunghezza) {
		this.lunghezza = lunghezza;
	}

	public Float getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(Float larghezza) {
		this.larghezza = larghezza;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
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
		Quadro other = (Quadro) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quadro [titolo=" + titolo + ", anno=" + anno + ", tecnica=" + tecnica + ", lunghezza=" + lunghezza
				+ ", larghezza=" + larghezza + "]";
	}

}
