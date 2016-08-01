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

    private Integer id;

    private String name;

    private String email;
    
    private String compositeKey;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", nullable = false, length = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "COMPOSITEKEY", nullable = false, length = 50)
	public String getCompositeKey() {
		return compositeKey;
	}

	public void setCompositeKey(String compositeKey) {
		this.compositeKey = compositeKey;
	}

}

