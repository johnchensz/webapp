package spittr.web;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import org.junit.Test;

import cn.jcb.dev.spittr.controller.HomeController;
import cn.jcb.dev.spittr.domain.ProfilePicture;
import cn.jcb.dev.spittr.domain.Spitter;
import cn.jcb.dev.spittr.controller.SpitterController;
import cn.jcb.dev.spittr.data.SpitterRepository;
import cn.jcb.dev.spittr.controller.SpittleController;
import cn.jcb.dev.spittr.data.SpittleRepository;
import cn.jcb.dev.spittr.domain.Spittle;

public class ControllerTest {

	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();
		// assertEquals("home", controller.home());

		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}

	//@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles")).andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle((long) i, "Spittle " + i, new Date()));
		}
		return spittles;
	}

	@Test
	public void shouldShowPagedSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(50);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(238900, 50)).thenReturn(expectedSpittles);
		
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles?max=238900&count=50")).andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}
	
	@Test
	public void testSpittle() throws Exception {
		Spittle expectedSpittle = new Spittle((long)12345, "Hello", new Date());
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findOne(12345)).thenReturn(expectedSpittle);
		
		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spittles/12345")).andExpect(view().name("spittle"))
				.andExpect(model().attributeExists("spittle"))
				.andExpect(model().attribute("spittle", expectedSpittle));
	}
	
	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/register")).andExpect(view().name("registerForm"));
	}
	
	@Test
	public void shouldProcessRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		Spitter unsaved = new Spitter("Jack", "Bauer", "jbauer", "24hours" );
		Spitter saved = new Spitter(24L, "Jack", "Bauer", "jbauer", "24hours");
		when(mockRepository.save(unsaved)).thenReturn(saved);
		
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/spitter/register")
				.param("firstName", "Jack")
				.param("lastName", "Bauer")
				.param("username", "jbauer")
				.param("password", "24hours")).andExpect(redirectedUrl("/spitter/jbauer"));
		verify(mockRepository, atLeastOnce()).save(unsaved);
	}
	
	@Test
	public void shouldProcessFileupload() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		
		MockMultipartFile uploadfile = new MockMultipartFile("profilePicture", "abc.txt", "text/plain", "some xml".getBytes());
		Spitter unsaved = new Spitter("Jack", "Bauer", "jbauer", "24hours" );
		unsaved.setProfilePicture(uploadfile);
		Spitter saved = new Spitter(24L, "Jack", "Bauer", "jbauer", "24hours");
		saved.setProfilePicture(uploadfile);
		when(mockRepository.save(unsaved)).thenReturn(saved);	
		
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(fileUpload("/spitter/register")
				.file(uploadfile)
				.param("firstName", "Jack")
				.param("lastName", "Bauer")
				.param("username", "jbauer")
				.param("password", "24hours")).andExpect(redirectedUrl("/spitter/jbauer"));
		verify(mockRepository, atLeastOnce()).save(unsaved);
	}
	
	@Test
	public void validateRegistration() throws Exception {
		SpitterRepository mockRepository = mock(SpitterRepository.class);
		SpitterController controller = new SpitterController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(post("/spitter/register")
				.param("username", "notnull")
				.param("password", "notnull"))
				.andExpect(view().name("registerForm"));

	}
	
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
	
}
