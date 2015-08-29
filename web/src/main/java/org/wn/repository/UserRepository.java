package org.wn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wn.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> findAll();
	
	public Page<User> findAll(Pageable page);
	
	public User getOne(Long id);
	
	public User findByEmail(String email);
	
	public void delete(Long id);
	
	@SuppressWarnings("unchecked")
	public User save(User user);
	
}
