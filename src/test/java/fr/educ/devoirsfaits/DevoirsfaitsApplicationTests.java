package fr.educ.devoirsfaits;

import fr.educ.devoirsfaits.controller.EleveController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(EleveController.class)
//@AutoConfigureRestDocs
public class DevoirsfaitsApplicationTests {

	@Test
	public void contextLoads() {
	}


/*
    @Autowired
    private MockMvc mvc;

    @Test
    public void listUsers() throws Exception {
        this.mvc.perform(get("/eleve").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andDo(document("list-users"));
    }
    private MockRestServiceServer server;



    @Autowired
    private EleveRepository eleveRepository;

    @Test
	public void getEleveDetail() throws Exception {
        this.server.expect(requestTo("/eleve/1"))
                .andRespond(withSuccess("testnom", MediaType.TEXT_PLAIN));
        String greeting = this.eleveRepository.toString();
        assertThat(greeting).isEqualTo("testnom");
	}*/

}
