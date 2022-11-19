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
public class FruitaController {

    @Autowired
    private FruitaRepository fruitaRepository;

    @PostMapping("/add")
    public String saveBook(@RequestBody Fruita fruita) {
        fruitaRepository.save(fruita);
        return "Added Successfully";
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
    public String deleteFruita(@PathVariable int id) {
        fruitaRepository.deleteById(id);

        return "Deleted Successfully";
    }

    @GetMapping("/getOne/{id}")
    public Optional<Fruita> getFruita(@PathVariable int id) {
        return fruitaRepository.findById(id);
    }

    @GetMapping("/getAll")
    public List<Fruita> getFruitas() {
        return fruitaRepository.findAll();
    }
}

