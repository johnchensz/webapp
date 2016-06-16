package cn.jcb.dev.spittr.data;

import org.springframework.stereotype.Component;

import cn.jcb.dev.spittr.domain.Spitter;

@Component
public class SimpleSpitterRepository implements SpitterRepository {

	private static long id = 1;
	private static synchronized long idGenerator(){
		id++;
		return id;
	}
	
	@Override
	public Spitter save(Spitter unsaved) {
		if ("johnchen".equalsIgnoreCase(unsaved.getUsername()) )
			throw new DuplicateSpitterException();
		return new Spitter(idGenerator(), unsaved.getFirstName(), unsaved.getLastName(), unsaved.getUsername(), unsaved.getPassword());
	}

	@Override
	public Spitter findByUsername(String username) {
		if ("jbauer".equals(username))
			return new Spitter(24L, "Jack", "Bauer", "jbauer", "24hours");
		return null;
	}

}
