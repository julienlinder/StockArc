package ch.hearc.stockarc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

/**
 * Represent a tool.
 * 
 * @author Alexandre Bianchi
 */

@Entity
@Component
@Table(name = "tool")

public class Tool {

	public enum Type {
		UNIQUE, DISPOSABLE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@Column
	private String name;

	@Column
	private Integer quantity;

	@Enumerated
	private Type type;

	@OneToMany(mappedBy = "tool")
	private Set<Rent> rents;

	/**
	 * Get the id of the tool.
	 * 
	 * @return Long The current id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id of the tool.
	 * 
	 * @param id The new id
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Get the tool's name.
	 * 
	 * @return String The current tool's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the tool's name.
	 * 
	 * @param name The new tool's name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get the tool's max quantity.
	 * 
	 * @return Integer The max quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Set the tool's max quantity.
	 * 
	 * @param quantity The new max quantity
	 */
	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Get the tool's type.
	 * 
	 * @return Type The type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Set the tool's type
	 * 
	 * @param type The new type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Get the tool's available quantity.
	 * 
	 * @return Integer The available quantity.
	 */
	@Transient
	public Integer availableQuantity() {

		Integer totalOpenedLocation = 0;

		if (this.type == Type.UNIQUE) {
			// Get the total of opened rents for this tool
			final Set<Rent> rentSet = this.rents;
			rentSet.removeIf(Rent::getIsOver);
			rentSet.removeIf(r -> r.getTool().getType() == Type.DISPOSABLE);

			// Calculate the number of lended tool
			for (Rent rent : rentSet) {
				totalOpenedLocation += rent.getQuantity();
			}
		}

		// Return the max quantity - the total of lended tool
		return this.quantity - totalOpenedLocation;
	}

	/**
	 * Get all the rents of a tool
	 * 
	 * @return Set<Rent> The rents
	 */
	public Set<Rent> getRents() {
		return rents;
	}

}
