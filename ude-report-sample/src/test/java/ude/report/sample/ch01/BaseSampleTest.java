package ude.report.sample.ch01;

import java.io.File;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.itext2.testkit.AbstractITextTest;
import com.iisigroup.ude.report.itext2.testkit.ITextTestConfig;
import com.iisigroup.ude.system.UdeSystemPropertyItem;

public abstract class BaseSampleTest extends AbstractITextTest<AbstractReport> {

    private static final ITextTestConfig CONFIG = new ITextTestConfig();

    static {
        CONFIG.setFixedBaseDir(".");
        CONFIG.iTextConfigPath = "classpath:itext-config-test.properties";
        CONFIG.setPath(UdeSystemPropertyItem.GLOBAL_RESOURCE_PATH, "${BASEDIR}");
    }

    public BaseSampleTest() {
        super(CONFIG);
    }

    public BaseSampleTest(final Class<? extends AbstractReport> generatorClass) {
        super(CONFIG, generatorClass);
    }

    @Override
    public Set<DocumentFormat> getSuppertedFormats(final AbstractReport generator) {

        return generator.getReportDefinition().getSuppertedFormats();
    }

    @Override
    public File createFile(final AbstractReport reportGenerator, final String suffix, final DocumentFormat format) {
        final ReportDefinition definition = reportGenerator.getReportDefinition();
        if (StringUtils.isNotBlank(suffix)) {
            return super.createFileByTestName(definition.getReportCode() + "_" + suffix, format);
        } else {
            return super.createFileByTestName(definition.getReportCode(), format);
        }
    }

}
