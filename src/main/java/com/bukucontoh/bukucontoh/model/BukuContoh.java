package com.bukucontoh.bukucontoh.model;

import javax.persistence.*;

@Entity
@Table(name="buku")
public class BukuContoh{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="nama")
    private String nama;

    @Column(name="description")
    private String description;

    @Column(name="idrak")
    private String idrak;

    public BukuContoh() {

	}

	public BukuContoh(String nama, String description, String idrak) {
		this.nama = nama;
		this.description = description;
		this.idrak = idrak;
    }
    
    public long getId() {
        return id;
    }
    
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdrak() {
        return idrak;
    }

    public void setIdrak(String idrak) {
        this.idrak = idrak;
    }

    @Override
	public String toString() {
		return "Tutorial [id=" + id + ", nama=" + nama + ", description=" + description + ", idrak=" + idrak + "]";
    }
    
}