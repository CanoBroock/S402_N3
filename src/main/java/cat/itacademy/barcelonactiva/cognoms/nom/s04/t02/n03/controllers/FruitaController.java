package cat.itacademy.barcelonactiva.cognoms.nom.s04.t02.n03.controllers;

import cat.itacademy.barcelonactiva.cognoms.nom.s04.t02.n03.model.Fruita;
import cat.itacademy.barcelonactiva.cognoms.nom.s04.t02.n03.model.repository.FruitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fruita")
public class FruitaController {

    @Autowired
    private FruitaRepository fruitaRepository;

    @PostMapping("/add")
    public ResponseEntity<Fruita> anadirFruita(@RequestBody Fruita fruita) {
        try {
            Fruita _fruita = fruitaRepository
                    .save(new Fruita(fruita.getNombre(), fruita.getQuantitatQuilos()));
            return new ResponseEntity<>(_fruita, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fruita> updateTutorial(@PathVariable("id") long id, @RequestBody Fruita fruita) {
        Optional<Fruita> fruitaData = fruitaRepository.findById((int) id);

        if (fruitaData.isPresent()) {
            Fruita _fruita = fruitaData.get();
            _fruita.setNombre(fruita.getNombre());
            _fruita.setQuantitatQuilos(fruita.getQuantitatQuilos());
            return new ResponseEntity<>(fruitaRepository.save(_fruita), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteFruita(@PathVariable("id") int id) {
        try {
            fruitaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Fruita> getFruitaById(@PathVariable("id") int id) {
        Optional<Fruita> fruitaData = fruitaRepository.findById(id);

        if (fruitaData.isPresent()) {
            return new ResponseEntity<>(fruitaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Fruita>> getAllFruita() {
        try {
            List<Fruita> fruitas = fruitaRepository.findAll();
            if (fruitas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(fruitas, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

