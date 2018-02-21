package ude.report.sample;
//package ude.report.sample;
//
//import java.io.IOException;
//
//import org.apache.commons.csv.CSVPrinter;
//
//import com.iisigroup.ude.report.csv.CSVGenerator;
//import com.iisigroup.ude.report.excel.ExcelDocument;
//import com.iisigroup.ude.report.excel.ExcelGenerator;
//import com.iisigroup.ude.report.itext2.AbstractPDFGenerator;
//import com.iisigroup.ude.report.itext2.PDFDocument;
//import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
//import com.iisigroup.ude.report.utils.LengthUnit;
//import com.iisigroup.ude.util.internal.UdeRuntimeUtils;
//import com.lowagie.text.Rectangle;
//
//public abstract class AbstractSampleReport extends AbstractPDFGenerator implements ExcelGenerator, CSVGenerator {
//
//    protected AbstractSampleReport(final Rectangle pageSize) {
//        super.setPageSize(pageSize);
//    }
//
//    //####################################################################
//    //## [Method] sub-block : 預設 PDF layout
//    //####################################################################
//
//    @Override
//    public final LayoutInfo prepareLayoutInfo() {
//        return this.createLayoutInfo();
//    }
//
//    protected LayoutInfo createLayoutInfo() {
//        return this.createLayoutInfo(0, 0, 0, 0);
//    }
//
//    protected final LayoutInfo createLayoutInfo(final double deltaLeft, final double deltaRight, final double deltaTop,
//            final double deltaBottom) {
//        final LayoutInfo layoutInfo = new LayoutInfo(//
//                LengthUnit.CM.trans(1.5f + (float) deltaLeft)     // 左
//                , LengthUnit.CM.trans(1.5f + (float) deltaRight)  // 右
//                , LengthUnit.CM.trans(2.0f + (float) deltaTop)    // 上
//                , LengthUnit.CM.trans(1.8f + (float) deltaBottom) // 下
//        );
//        return layoutInfo;
//    }
//
//    @Override
//    public void generateCSVContent(final CSVPrinter csvPrinter) throws IOException {
//        UdeRuntimeUtils.doNothing();
//
//    }
//
//    @Override
//    public void generateExcelContent(final ExcelDocument<?, ?> document) {
//        UdeRuntimeUtils.doNothing();
//
//    }
//
//    @Override
//    public void generatePDFContent(final PDFDocument pdfDocument) {
//        UdeRuntimeUtils.doNothing();
//
//    }
//
//}
