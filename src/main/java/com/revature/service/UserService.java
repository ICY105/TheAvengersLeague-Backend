package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return this.userDao.findAll();
	}
	
	@Transactional(readOnly=true)
	public User findById(final int id) {
		final Optional<User> user = this.userDao.findById(id);
		return user.isPresent() ? user.get() : null;
	}
	
	@Transactional(readOnly=true)
	public User findByUsername(final String username) {
		final Optional<User> user = this.userDao.findByUsername(username);
		return user.isPresent() ? user.get() : null;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User insert(final User user) {
		return this.userDao.save(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void remove(final User user) {
		this.userDao.delete(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void remove(final int id) {
		this.userDao.deleteById(id);
	}
	
}
