import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.*;

import org.junit.Test;

import play.mvc.Content;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
//        Content html = views.html.index.render(
//        	"何か書いて。",
//        	new play.data.Form(
//        		controllers.Application.SampleForm.class));
//        assertThat(contentType(html)).isEqualTo("text/html");
//        assertThat(contentAsString(html)).contains("何か書いて。");
    }


}
