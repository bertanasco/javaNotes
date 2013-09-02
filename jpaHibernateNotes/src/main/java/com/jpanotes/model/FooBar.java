package com.jpanotes.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.SecondaryTable;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="foo")
@SecondaryTable(name="bar")
public class FooBar {
	
	@Id
	@GeneratedValue
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
	public String getFooField() {
		return fooField;
	}
	public void setFooField(String fooField) {
		this.fooField = fooField;
	}
	@Column(table="bar")
	public String getBarField() {
		return barField;
	}
	public void setBarField(String barField) {
		this.barField = barField;
	}
	private Long id;
	private String name;
	private String fooField;
	private String barField;
	
}
