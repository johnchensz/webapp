package cn.jcb.dev.spittr.data;

import org.springframework.stereotype.Component;

import cn.jcb.dev.spittr.domain.Spitter;

@Component
public class SimpleSpitterRepository implements SpitterRepository {

	private static long id = 1;
	
	@Override
	public Spitter save(Spitter unsaved) {
		//unsaved.setId(21L);
		return new Spitter(id++, unsaved.getFirstName(), unsaved.getLastName(), unsaved.getUsername(), unsaved.getPassword());
	}

	@Override
	public Spitter findByUsername(String username) {
		// TODO Auto-generated method stub
		return new Spitter(24L, "Jack", "Bauer", "jbauer", "24hours");
	}

}
