package br.com.fabricio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable{
   
	private static final long serialVersionUID = -2502507343244301250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@Column(name = "name", nullable = false, length = 50)
    private String name;

	@Column(name = "email", nullable = false, length = 100)
    private String email;

	@Column(name = "composite_key", nullable = false, length = 50)
    private String compositeKey;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompositeKey() {
		return compositeKey;
	}

	public void setCompositeKey(String compositeKey) {
		this.compositeKey = compositeKey;
	}
}

