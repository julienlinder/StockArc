package ch.hearc.stockarc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hearc.stockarc.repository.ToolRepository;

@Entity
@Component
@Table(name = "tool")

public class Tool {

	enum Type {
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

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

	@Transient
	public Integer availableQuantity() {

		Integer totalOpenedLocation = 0;

		if (this.type == Type.UNIQUE) {
			final Set<Rent> rentSet = this.rents;
			rentSet.removeIf(Rent::getIsOver);

			for (Rent rent : rentSet) {
				totalOpenedLocation += rent.getQuantity();
			}
		}

		return this.quantity - totalOpenedLocation;
	}

}
