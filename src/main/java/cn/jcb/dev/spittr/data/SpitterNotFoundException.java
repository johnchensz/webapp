package cn.jcb.dev.spittr.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="spitter not found")
public class SpitterNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -622826341268106916L;

}
