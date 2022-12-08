package me.dio.diogamewards.controller.games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.dio.diogamewards.controller.BaseRestController;
import me.dio.diogamewards.domain.model.Game;
import me.dio.diogamewards.service.GameService;

@CrossOrigin
@RestController
public class GameRestController extends BaseRestController {

	// ==== Utilizando @Autowired ====
	@Autowired
	private GameService businessLayer;
	
//  === Utilizando construtor	
//	private final GameService businessLayer;
//
//	public GameRestController(GameService businessLayer) {
//		super();
//		this.businessLayer = businessLayer;
//	}

	@GetMapping("games")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Game>> findAll() {
		List<Game> games = businessLayer.findAll();
		return ResponseEntity.ok(games);
	}
	
	@GetMapping("games/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Game> findById(@PathVariable Long id) {
		Game game = businessLayer.findById(id);
		return ResponseEntity.ok(game);
	}
	
	@PostMapping("games")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Game> insert(@RequestBody Game game) {
		businessLayer.insert(game);
		return ResponseEntity.ok(game);
	}
	
	@PutMapping("games/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Game> update(@PathVariable Long id, @RequestBody Game game) {
		businessLayer.update(id, game);
		return ResponseEntity.ok(game);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Game> delete(@PathVariable Long id) {
		businessLayer.delete(id);
		return ResponseEntity.ok().build();      
	}
	
	
	@PatchMapping("games/{id}/vote")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Game> update(@PathVariable Long id) {
		businessLayer.vote(id);
		return ResponseEntity.ok().build();
	}
}
