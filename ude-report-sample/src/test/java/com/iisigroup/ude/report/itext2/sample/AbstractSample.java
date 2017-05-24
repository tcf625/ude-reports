package com.iisigroup.ude.report.itext2.sample;

import java.io.File;
import java.util.EnumSet;
import java.util.Set;

import com.iisigroup.ude.report.AbstractDocumentGenerator;
import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.report.itext2.PDFGenerator;
import com.iisigroup.ude.report.itext2.sample.content.PDFContent;
import com.iisigroup.ude.report.itext2.testkit.AbstractITextTest;
import com.iisigroup.ude.report.itext2.testkit.ITextTestConfig;
import com.iisigroup.ude.system.UdeSystemPropertyItem;

public abstract class AbstractSample extends AbstractITextTest<AbstractDocumentGenerator> {
    //================================================
    //== [Enumeration types] Block Start
    //== [Enumeration types] Block End
    //================================================
    //== [static variables] Block Start

    private static final ITextTestConfig CONFIG = new ITextTestConfig();

    //== [static variables] Block Stop
    //================================================
    //== [instance variables] Block Start

    private Set<DocumentFormat> suppertedFormats = EnumSet.allOf(DocumentFormat.class);

    //== [instance variables] Block Stop
    //================================================
    //== [static Constructor] Block Start

    static {
        final String BASEDIR = System.getProperty("BASEDIR", ".");
        CONFIG.setFixedBaseDir(BASEDIR);
        CONFIG.iTextConfigPath = "classpath:itext-config.properties";
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
    public File createFile(final AbstractDocumentGenerator reportGenerator, final String suffix, final DocumentFormat foramt) {
        return super.createFileByTestName(suffix, foramt);
    }

    @Override
    public Set<DocumentFormat> getSuppertedFormats(final AbstractDocumentGenerator generator) {
        return this.suppertedFormats;
    }

    //####################################################################
    //## [Method] sub-block :
    //####################################################################

    public final void createPDF(final PDFContent content) {
        final File file = super.createFileByTestName("", DocumentFormat.PDF);
        this.doDocument(file, new SamplePDFGenerator(content), this::outputPDF);
    }

    //== [Method] Block Stop
    //================================================
    //== [Inner Class] Block Start
    //== [Inner Class] Block Stop
    //================================================

}
