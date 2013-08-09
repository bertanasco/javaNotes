package com.jpanotes.model;

import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
@Table(name="blobs", schema="javanotes")
public class Blobber {
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Lob
	public String getStringLob() {
		return stringLob;
	}
	public void setStringLob(String stringLob) {
		this.stringLob = stringLob;
	}
	@Lob
	public char[] getCharArrayLob() {
		return charArrayLob;
	}
	public void setCharArrayLob(char[] charArrayLob) {
		this.charArrayLob = charArrayLob;
	}
	@Lob
	public Character[] getCharWrapLob() {
		return charWrapLob;
	}
	public void setCharWrapLob(Character[] charWrapLob) {
		this.charWrapLob = charWrapLob;
	}
	@Lob
	public java.sql.Clob getSqlClob() {
		return sqlClob;
	}
	public void setSqlClob(java.sql.Clob sqlClob) {
		this.sqlClob = sqlClob;
	}
	@Lob
	public java.sql.Blob getSqlBlob() {
		return sqlBlob;
	}
	public void setSqlBlob(java.sql.Blob sqlBlob) {
		this.sqlBlob = sqlBlob;
	}

	private Long id;
	String stringLob;
	char[] charArrayLob;
	Character[] charWrapLob;
	java.sql.Clob sqlClob;
	java.sql.Blob sqlBlob;

}
