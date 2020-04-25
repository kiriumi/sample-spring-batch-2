package sample.spring.batch.example;

import junit.framework.TestCase;

public class ExampleItemReaderTests extends TestCase {

    private final ExampleItemReader reader = new ExampleItemReader();

    public void testReadOnce() throws Exception {
        assertEquals("Hello world!", reader.read());
    }

    public void testReadTwice() throws Exception {
        reader.read();
        assertEquals(null, reader.read());
    }

}
