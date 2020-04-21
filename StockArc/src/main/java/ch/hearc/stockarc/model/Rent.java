package ch.hearc.stockarc.model;

import java.io.Serializable;
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

@Entity
@Table(name = "rent")

public class Rent implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id", referencedColumnName = "id")
	private Person person;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tool_id", referencedColumnName = "id")
	private Tool tool;

	@Column
	private Boolean isOver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public Boolean getIs_over() {
		return isOver;
	}

	public void setIs_over(Boolean isOver) {
		this.isOver = isOver;
	}

}
