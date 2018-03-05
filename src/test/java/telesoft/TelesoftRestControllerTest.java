package telesoft;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import lt.telesoft.Application;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TelesoftRestControllerTest
{
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception
	{
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void generate_item_data_not_found_method() throws Exception
	{
		mockMvc.perform(post("/api/generateTesting").contentType(contentType)).andExpect(status().isNotFound());
	}

	@Test
	public void found_all_data() throws Exception
	{
		mockMvc.perform(get("/api/find/all")).andExpect(status().isOk());
	}

	@Test
	public void found_one_item() throws Exception
	{
		mockMvc.perform(get("/api/find/one").param("id", "1")).andExpect(status().isOk());
	}

	@Test
	public void create_item_data() throws Exception
	{
		mockMvc.perform(
				post("/api/createGildedRoseItem").param("name", "Test").param("sellIn", "1").param("quality", "6")
						.contentType(contentType)).andExpect(status().isOk());
	}

}
