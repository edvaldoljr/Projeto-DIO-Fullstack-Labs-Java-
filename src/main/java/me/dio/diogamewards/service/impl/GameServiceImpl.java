package me.dio.diogamewards.service.impl;

import java.util.Objects;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.diogamewards.domain.model.Game;
import me.dio.diogamewards.domain.model.GameRepository;
import me.dio.diogamewards.service.GameService;
import me.dio.diogamewards.service.exception.BusinessException;
import me.dio.diogamewards.service.exception.NoContentException;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	private GameRepository repository;
	
	@Override
	public List<Game> findAll() {
		List<Game> games = repository.findAll();
		return games;
	}

	@Override
	public Game findById(Long id) {
		Optional<Game> game =  repository.findById(id);
		return game.orElseThrow(() -> new NoContentException());
	}

	@Override
	public void insert(Game game) {
		if (Objects.nonNull(game.getId())) {
			throw new BusinessException("Os ID é diferente de NULL na inclusão");
		}
		repository.save(game);
	}

	@Override
	public void update(Long id, Game game) {
		Game gameDb = findById(id);
		if (gameDb.getId().equals(game.getId())) {
			repository.save(game);
		} else {
			throw new BusinessException("Os IDs para alteração são divergentes.");
		}
	}

	@Override
	public void delete(Long id) {
		Game gameDb = findById(id);
		repository.delete(gameDb);
	}

}
