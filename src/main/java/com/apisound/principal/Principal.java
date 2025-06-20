package com.apisound.principal;

import com.apisound.model.Artista;
import com.apisound.model.Musica;
import com.apisound.model.TipoArtista;
import com.apisound.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    Scanner sc = new Scanner(System.in);

    private final ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void exibeMenu() {

        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    ______________________________________
                    
                    *** Screen Sound Músicas ***
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                                    
                    9 - Sair
                    ______________________________________
                    """;

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void pesquisarDadosDoArtista() {
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Buscar músicas de que artista? ");
        var nome = sc.nextLine();
        List<Musica> musicasPorArtista = artistaRepository.buscaMusicaPorArtista(nome);
        musicasPorArtista.forEach(System.out::println);
    }

    private void listarMusicas() {
        List<Artista> artistaList = artistaRepository.findAll();
        artistaList.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void cadastrarMusicas() {

        System.out.println("Informe o artista da música que deseja cadastrar: ");
        var nomeArtista = sc.nextLine();
        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if (artista.isPresent()){
            System.out.println("Informe o nome da música: ");
            var nomeMusica = sc.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void cadastrarArtistas() {
        var novoArtista = "S";

        while (novoArtista.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome deste artista: ");
            var nome = sc.nextLine();
            System.out.println("Informe o tipo deste artista: (solo, dupla ou banda) ");
            var tipo = sc.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            artistaRepository.save(artista);
            System.out.println("Deseja cadastrar novo artista? (S/N) ");
            novoArtista = sc.nextLine();
        }
    }

}
