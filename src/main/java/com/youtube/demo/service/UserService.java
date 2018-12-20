package com.youtube.demo.service;

import java.util.List;
import java.util.Optional;

import com.youtube.demo.model.User;

public interface UserService {

	/**
	 * Guarda un usuario
	 * 
	 * @param user
	 * @return el usuario guardado 
	 */
	User save(User user);

	/**
	 * Recupera la lista de usuarios
	 * @return lista de usuarios
	 */
	List<User> findAll();

	void deleteUser(Long id);

	Optional<User> findByUserId(Long userId);

}
