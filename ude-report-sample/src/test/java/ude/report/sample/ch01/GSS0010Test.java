package ude.report.sample.ch01;

import java.io.File;

import org.junit.Test;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.itext2.testkit.AbstractITextTestkit;
import com.iisigroup.ude.report.itext2.testkit.ITextTestConfig;

public class GSS0010Test extends AbstractITextTestkit {

    public GSS0010Test() {
        super(new ITextTestConfig());
    }

    @Test
    public void testPDF() {
        final GSS0010 gss0010 = new GSS0010();
        final File file = createFileByTestName("", DocumentFormat.PDF);
        gss0010.generatePDF(getPdfManager(), file);
    }

    @Test
    public void testExcel() {
        final GSS0010 gss0010 = new GSS0010();
        final File file = createFileByTestName("", DocumentFormat.EXCEL);
        gss0010.generateExcel(file);
    }

}
