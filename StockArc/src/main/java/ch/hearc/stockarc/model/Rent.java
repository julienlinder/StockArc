package ch.hearc.stockarc.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

/**
 * Represent a rent.
 * 
 * @author Alexandre Bianchi
 */

@Entity
@DynamicUpdate
@Table(name = "rent")
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt = Calendar.getInstance().getTime();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
	private Person person;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tool_id", referencedColumnName = "id", nullable = false)
	private Tool tool;

	@Column(nullable = false)
	private Integer quantity = 0;

	@Column(nullable = false)
	private Boolean isOver = false;

	/**
	 * Get the id of the rent.
	 * 
	 * @return Long The current id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the id of the rent.
	 * 
	 * @param id The new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the creation date of the rent.
	 * 
	 * @return Date The creation date
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Set the creation date of the rent.
	 * 
	 * @param createdAt The new creation date
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Get the person owning the rent.
	 * 
	 * @return Person The person owning the rent.
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Set the person owning the rent.
	 * 
	 * @param person The new person owning the rent.
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Get the lended tool.
	 * 
	 * @return Tool The lended tool.
	 */
	public Tool getTool() {
		return tool;
	}

	/**
	 * Set the lended tool.
	 * 
	 * @param tool The new lended tool.
	 */
	public void setTool(Tool tool) {
		this.tool = tool;
	}

	/**
	 * Get the number of tool lended in this rent.
	 * 
	 * @return Integer The quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Set the number of tool lended in this rent.
	 * 
	 * @param quantity The new quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * Get the status of the rent.
	 * 
	 * @return Boolean <code>true</code> if the rent is over; <code>false</code>
	 *         otherwise.
	 */
	public Boolean getIsOver() {
		return isOver;
	}

	/**
	 * Set the status of the rent.
	 * 
	 * @param isOver The new status
	 */
	public void setIsOver(Boolean isOver) {
		this.isOver = isOver;
	}

}
