package fr.formation.webflix.services;

import fr.formation.webflix.entities.UserEntity;
import fr.formation.webflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}

	public Iterable<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public Optional<UserEntity> findById(Long id) {
		return userRepository.findById(id);
	}

	public void deleteAll() {
		userRepository.deleteAll();
	}
}
