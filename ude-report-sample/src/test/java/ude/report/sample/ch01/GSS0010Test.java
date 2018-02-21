package ude.report.sample.ch01;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.itext2.testkit.AbstractITextTestkit;
import com.iisigroup.ude.report.itext2.testkit.ITextTestConfig;
import com.iisigroup.ude.system.UdeSystemPropertyItem;

public class GSS0010Test extends AbstractITextTestkit {

    final GSS0010 gss0010 = new GSS0010();

    public GSS0010Test() {
        super(prepareConfig());
    }

    private static ITextTestConfig prepareConfig() {
        final ITextTestConfig config = new ITextTestConfig();
        config.setFixedBaseDir(".");
        config.setPath(UdeSystemPropertyItem.GLOBAL_RESOURCE_PATH, "${BASEDIR}");
        config.iTextConfigPath = "classpath:itext-config-test.properties";
        config.outputRoot = new File(FileUtils.getTempDirectory(), "__Report_Sample");
        config.keepOutputFile = true;
        config.showMarginBorder = true;
        return config;
    }

    @Test
    public void testPDF() {
        final String fileName = AllReports.GSS0010.toFileName(DocumentFormat.PDF);
        final String suffix = "_" + FilenameUtils.getBaseName(fileName);
        final File createFileByTestName = createFileByTestName(suffix, DocumentFormat.PDF);
        this.gss0010.generatePDF(getDocumentManager(), createFileByTestName);
    }

    @Test
    public void testExcel() {
        this.gss0010.generateExcel(createFileByTestName(DocumentFormat.EXCEL));
    }

}
