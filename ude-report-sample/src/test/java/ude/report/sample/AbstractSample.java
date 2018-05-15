package ude.report.sample;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.excel.ExcelGenerator;
import com.iisigroup.ude.report.itext2.PDFGenerator;
import com.iisigroup.ude.report.itext2.testkit.AbstractITextTestkit;
import com.iisigroup.ude.report.itext2.testkit.ITextTestConfig;
import com.iisigroup.ude.system.UdeSystemPropertyItem;
import com.iisigroup.ude.util.internal.UdeRuntimeUtils;

public abstract class AbstractSample extends AbstractITextTestkit {
    //================================================
    //== [Enumeration types] Block Start
    //== [Enumeration types] Block End
    //================================================
    //== [static variables] Block Start

    private static final ITextTestConfig CONFIG = new ITextTestConfig();

    protected static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AbstractSample.class);

    //== [static variables] Block Stop
    //================================================
    //== [instance variables] Block Start
    //== [instance variables] Block Stop
    //================================================
    //== [static Constructor] Block Start

    static {
        CONFIG.setFixedBaseDir(".");
        CONFIG.iTextConfigPath = "classpath:itext-config-test.properties";
        CONFIG.setPath(UdeSystemPropertyItem.GLOBAL_RESOURCE_PATH, "${BASEDIR}");
    }

    //== [static Constructor] Block Stop
    //================================================
    //== [Constructors] Block Start (含init method)

    public AbstractSample() {
        super(CONFIG);
    }

    //== [Constructors] Block Stop
    //================================================
    //== [Static Method] Block Start
    //== [Static Method] Block Stop
    //================================================
    //== [Accessor] Block Start
    //== [Accessor] Block Stop
    //================================================
    //== [Overrided JDK Method] Block Start (Ex. toString / equals+hashCode)
    //== [Overrided JDK Method] Block Stop
    //================================================
    //== [Method] Block Start

    @Override
    protected File createFileByTestName(final String suffix, final DocumentFormat format) {
        final String methodName = this.testName.getMethodName();
        final String method_suffix = methodName.replaceAll("(?i)^test_?", "");
        final String packageName = this.getClass().getPackage().getName();
        final String subFolder = StringUtils.substringAfterLast(packageName, ".");

        final String testClassName = this.getClass().getSimpleName().replaceAll("^Sample_", "");
        final String fileName = "" //
                + "[" + testClassName + "]" //
                + StringUtils.capitalize(method_suffix) //
                + suffix;

        final File file = new File(this.config.outputRoot + "/" + subFolder + "/" + testClassName,
                fileName + "." + format.getExtFileName());
        UdeRuntimeUtils.quietRun(() -> FileUtils.forceMkdirParent(file));
        if (!this.config.keepOutputFile) {
            file.deleteOnExit();
        }
        return file;
    }

    //####################################################################
    //## [Method] sub-block :
    //####################################################################

    //####################################################################
    //## [Method] sub-block :
    //####################################################################

    public final File createPDF(final PDFSampleContent sampleContent, final PDFSampleContent content) {
        final File doDocument = super.doDocument(sampleContent.andThen(content), DocumentFormat.PDF, "");
        return doDocument;
    }

    public final File createPDF(final PDFGenerator content) {
        final File doDocument = super.doDocument(content, DocumentFormat.PDF, "");
        return doDocument;
    }

    public final File createExcel(final ExcelGenerator content) {
        final File doDocument = super.doDocument(content, DocumentFormat.EXCEL, "");
        return doDocument;
    }

    //== [Method] Block Stop
    //================================================
    //== [Inner Class] Block Start
    //== [Inner Class] Block Stop
    //================================================

}
