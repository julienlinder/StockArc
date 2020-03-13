package ch.hearc.stockarc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sector")

public class Sector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@Column
	private String name;

	@OneToMany(mappedBy = "sector", cascade = CascadeType.ALL)
	private Set<Person> persons;

	public Sector() {

	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Person> getPersons() {
		return persons;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}
}
