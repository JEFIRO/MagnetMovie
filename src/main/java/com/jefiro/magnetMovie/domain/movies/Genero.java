package com.jefiro.magnetMovie.domain.movies;

public enum Genero {
    Ação("28"),
    Aventura("12"),
    Animação("16"),
    Comédia("35"),
    Crime("80"),
    Documentário("99"),
    Drama("18"),
    Família("10751"),
    Fantasia("14"),
    História("36"),
    Terror("27"),
    Música("10402"),
    Mistério("9648"),
    Romance("10749"),
    Ficção_científica("878"),
    Cinema_TV("10770"),
    Thriller("53"),
    Guerra("10752"),
    Faroeste("37");

    private String id;

    Genero(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // Método para obter o enum a partir do nome do gênero
    public static String fromString(String text) {
        for (Genero genero : Genero.values()) {
            if (genero.name().equalsIgnoreCase(text.replace(" ", "_"))) {
                return genero.id;
            }
        }
        throw new IllegalArgumentException("Nenhum gênero encontrado para a string fornecida: " + text);
    }
}
