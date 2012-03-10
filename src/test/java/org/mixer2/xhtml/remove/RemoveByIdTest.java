package org.mixer2.xhtml.remove;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Button;
import org.mixer2.jaxb.xhtml.Div;
import org.mixer2.jaxb.xhtml.Dt;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.jaxb.xhtml.Li;
import org.mixer2.jaxb.xhtml.Menu;
import org.mixer2.jaxb.xhtml.Small;
import org.mixer2.jaxb.xhtml.Span;
import org.mixer2.jaxb.xhtml.Strong;
import org.mixer2.jaxb.xhtml.Ul;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;

/**
 *
 * @author watanabe
 *
 */
public class RemoveByIdTest {

    private String templateFileName = "RemoveByIdTest.html";
    private String templateFilePath;
    private static Mixer2Engine m2e = new Mixer2Engine();
    private Html html;

    @AfterClass
    public static void afterClass() {
        m2e = null;
    }

    @Before
    public void init() throws IOException {
        templateFilePath = getClass().getResource(templateFileName).toString();
        String osname = System.getProperty("os.name");
        if(osname.indexOf("Windows")>=0){
            templateFilePath = templateFilePath.replaceFirst("file:/", "");
        } else {
            templateFilePath = templateFilePath.replaceFirst("file:", "");
        }
    }

    @Test
    public void testRemoveById() throws IOException, TagTypeUnmatchException {
        html = m2e.loadHtmlTemplate(new File(templateFilePath));

        assertTrue(html.getById("span2", Span.class) != null);
        html.removeById("span2");
        assertTrue(html.getById("span2", Span.class) == null);

        assertTrue(html.getById("hellomsg", Div.class) != null);
        html.removeById("hellomsg");
        assertTrue(html.getById("hellomsg", Div.class) == null);

        assertTrue(html.getById("dt01", Dt.class) != null);
        html.removeById("dt01");
        assertTrue(html.getById("dt01", Dt.class) == null);

        assertTrue(html.getById("strong_a", Strong.class) != null);
        html.removeById("strong_a");
        assertTrue(html.getById("strong_a", Strong.class) == null);

        assertTrue(html.getById("small_a", Small.class) != null);
        html.removeById("small_a");
        assertTrue(html.getById("small_a", Small.class) == null);

        assertTrue(html.getById("li_a", Li.class) != null);
        html.removeById("li_a");
        assertTrue(html.getById("li_a", Li.class) == null);

        assertTrue(html.getById("ul_a", Ul.class) != null);
        html.removeById("ul_a");
        assertTrue(html.getById("ul_a", Ul.class) == null);

        assertTrue(html.getById("lastfoo", Div.class) != null);
        html.removeById("lastfoo");
        assertTrue(html.getById("lastfoo", Div.class) == null);

    }

    @Test
    public void removeMenuAndLi() throws IOException, TagTypeUnmatchException {
        html = m2e.loadHtmlTemplate(new File(templateFilePath));
        assertNotNull(html.getById("menu01", Menu.class));
        assertNotNull(html.getById("menu01_li", Li.class));
        assertNotNull(html.getById("menu02", Menu.class));
        assertNotNull(html.getById("menu02_li", Li.class));
        html.removeById("menu01");
        assertNull(html.getById("menu01", Menu.class));
        assertNull(html.getById("menu01_li", Li.class));
        assertNotNull(html.getById("menu02", Menu.class));
        assertNotNull(html.getById("menu02_li", Li.class));
        html.removeById("menu02_li");
        assertNull(html.getById("menu02_li", Li.class));
        assertNotNull(html.getById("menu02_button", Button.class));

    }
}
