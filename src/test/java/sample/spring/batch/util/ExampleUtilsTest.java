package sample.spring.batch.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/test-context_util.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleUtilsTest {

    @Autowired
    @Qualifier("appContext")
    ApplicationContext appContext;

    ExampleUtils utils;

    @Before
    public void setUp() throws Exception {
        this.utils = appContext.getAutowireCapableBeanFactory().createBean(ExampleUtils.class);
        utils.setMemberName("Hoge");
    }

    @Test
    public void testGetMemberName() {
        assertThat(utils.getMemberName(), is("Hoge"));
    }

}
