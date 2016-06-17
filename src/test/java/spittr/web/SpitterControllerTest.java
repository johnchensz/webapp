package spittr.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import cn.jcb.dev.spittr.controller.AppWideExceptionHandler;
import cn.jcb.dev.spittr.controller.SpitterController;
import cn.jcb.dev.spittr.data.DuplicateSpitterException;
import cn.jcb.dev.spittr.data.SpitterRepository;
import cn.jcb.dev.spittr.domain.Spitter;

public class SpitterControllerTest {
	
	@Test
	public void testSpitter() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		Spitter spitter = new Spitter(24L, "Jack", "Bauer", "jbauer", "24hours");
		when(mockRepository.findByUsername("jbauer")).thenReturn(spitter);
		
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/jbauer"))
			.andExpect(view().name("profile"))
			.andExpect(model().attributeExists("spitter"))
			.andExpect(model().attribute("spitter", spitter));
	}
	
	@Test
	public void testSpitterNotFound() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		when(mockRepository.findByUsername("johnchen")).thenReturn(null);
		
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(get("/spitter/johnchen"))
			.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testSpitterDuplicate() throws Exception {
		Spitter unsaved = new Spitter("Jack", "Bauer", "johnchen", "24hours" );

		SpitterRepository mockRepository = mock(SpitterRepository.class);
		when(mockRepository.save(unsaved)).thenThrow(DuplicateSpitterException.class);
		
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).setControllerAdvice(new AppWideExceptionHandler()).build();
		mockMvc.perform(post("/spitter/register")
				.param("firstName", unsaved.getFirstName())
				.param("lastName", unsaved.getLastName())
				.param("username", unsaved.getUsername())
				.param("password", unsaved.getPassword())).andExpect(view().name("error/duplicate"));
	}
}
