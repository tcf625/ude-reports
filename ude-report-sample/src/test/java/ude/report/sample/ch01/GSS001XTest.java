package ude.report.sample.ch01;

import org.junit.Test;

public class GSS001XTest extends BaseSampleTest {

    public GSS001XTest() {
        super(GSS0011.class);
    }

    @Test
    public void testGSS0010() {
        super.doDocumentAllForamt(new GSS0010());
    }

    @Test
    public void testGSS0011() {
        super.doDocumentAllForamt(new GSS0011());
    }

}
