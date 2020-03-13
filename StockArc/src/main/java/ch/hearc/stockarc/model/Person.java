package ch.hearc.stockarc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@Column
	private String name;

	@ManyToOne
	@JoinColumn(name = "sector_id", nullable = false)
	private Sector sector;

	@Column
	private Boolean is_responsible;
	
	@OneToMany(mappedBy="person")
    private Set<Rent> rents;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public Boolean getIs_responsible() {
		return is_responsible;
	}

	public void setIs_responsible(Boolean is_responsible) {
		this.is_responsible = is_responsible;
	}

}
