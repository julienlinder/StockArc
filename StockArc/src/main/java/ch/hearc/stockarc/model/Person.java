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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represent a person.
 * 
 * @author Alexandre Bianchi
 */

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
	private Boolean isResponsible = false;

	@OneToMany(mappedBy = "person")
	private Set<Rent> rents;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	/**
	 * Get the id of the person.
	 * 
	 * @return Long The current id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id of the person.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the name of the person-
	 * 
	 * @return String The current name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the person
	 * 
	 * @param name The new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the Sector of the person
	 * 
	 * @return Sector The Sector of the person
	 */
	public Sector getSector() {
		return sector;
	}

	/**
	 * Set the sector of the person
	 * 
	 * @param sector The new sector
	 */
	public void setSector(Sector sector) {
		this.sector = sector;
	}

	/**
	 * Get the status of the person.
	 * 
	 * @return Boolean <code>true</code> if the person is responsible for a sector;
	 *         <code>false</code> otherwise.
	 */
	public Boolean getIsResponsible() {
		return isResponsible;
	}

	/**
	 * Set the status of the rent.
	 * 
	 * @param isResponsible The new status
	 */
	public void setIsResponsible(Boolean isResponsible) {
		this.isResponsible = isResponsible;
	}

	/**
	 * Get all the rents of a person
	 * 
	 * @return Set<Rent> The rents
	 */
	public Set<Rent> getRents() {
		return rents;
	}

}
