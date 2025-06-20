package com.apisound.model;

import com.apisound.repository.ArtistaRepository;
import jakarta.persistence.*;

@Entity
@Table(name="musicas")
public class Musica {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo")
    private String titulo;

    @ManyToOne
    private Artista artista;

    public Musica() {}

    public Musica(String nomeMusica) {
        this.titulo = nomeMusica;
        this.artista = artista;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Música" + titulo + '\'' +
                ", artista=" + artista.getNome();
    }
}
