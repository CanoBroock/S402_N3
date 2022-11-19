package cat.itacademy.barcelonactiva.cognoms.nom.s04.t02.n03.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fruita")
public class Fruita {

    @Id
    private int id;
    private String nombre;
    private int quantitatQuilos;
}

