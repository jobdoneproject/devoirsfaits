package fr.educ.devoirsfaits;

import fr.educ.devoirsfaits.controller.EleveController;
import fr.educ.devoirsfaits.repository.EleveRepository;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EleveController.class)
//@AutoConfigureRestDocs
public class DevoirsfaitsApplicationTests {

	// @Test
	// public void contextLoads() {
	// }

        //@Autowired
        //Mock Server Side
        private MockMvc mvc;

        // @Test
        // public void listUsers() throws Exception {
        //         this.mvc.perform(get("/eleve").accept(MediaType.TEXT_PLAIN))
        //                 .andExpect(status().isOk())
        //                 .andDo(document("list-users"));
        // }

        //Mock Client Side
        private MockRestServiceServer server;


        @Autowired
        private EleveRepository eleveRepository;

        // @Test
        // public void getEleveDetail() throws Exception {
        //         this.server.expect(requestTo("/eleve/1"))
        //                 .andRespond(withSuccess("testnom", MediaType.TEXT_PLAIN));
        //         String greeting = this.eleveRepository.toString();
        //         assertThat(greeting).isEqualTo("testnom");
        // }

}
