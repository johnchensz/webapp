package cn.jcb.dev.spittr.data;

import cn.jcb.dev.spittr.domain.Spitter;

public interface SpitterRepository {

	Spitter save(Spitter unsaved) ;
	
	Spitter findByUsername(String username);

}
