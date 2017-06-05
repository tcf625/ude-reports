package ude.report.sample.ch01;

import com.iisigroup.ude.report.csv.CSVGenerator;
import com.iisigroup.ude.report.excel.ExcelGenerator;
import com.iisigroup.ude.report.itext2.AbstractPDFGenerator;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.layout.ItemPosition;
import com.iisigroup.ude.report.utils.LengthUnit;
import com.lowagie.text.Rectangle;

abstract class AbstractReport extends AbstractPDFGenerator implements ExcelGenerator, CSVGenerator {

    protected final ReportDefinition reportDefinition;

    protected AbstractReport(final ReportDefinition reportDefinition, final Rectangle pageSize) {
        this.reportDefinition = reportDefinition;
        super.setPageSize(pageSize);
    }

    public ReportDefinition getReportDefinition() {
        return this.reportDefinition;
    }

    //####################################################################
    //## [Method] sub-block : 預設 PDF layout
    //####################################################################

    @Override
    public final LayoutInfo prepareLayoutInfo() {
        final LayoutInfo layout = this.createLayoutInfo();
        this.defaultHeader(layout);
        return layout;
    }

    protected LayoutInfo createLayoutInfo() {
        return this.createLayoutInfo(0, 0, 0, 0);
    }

    protected final LayoutInfo createLayoutInfo(final double deltaLeft, final double deltaRight, final double deltaTop,
            final double deltaBottom) {
        final LayoutInfo layoutInfo = new LayoutInfo(//
                LengthUnit.CM.trans(1.5f + (float) deltaLeft)     // 左
                , LengthUnit.CM.trans(1.5f + (float) deltaRight)  // 右
                , LengthUnit.CM.trans(2.0f + (float) deltaTop)    // 上
                , LengthUnit.CM.trans(1.8f + (float) deltaBottom) // 下
        );
        return layoutInfo;
    }

    protected void defaultHeader(final LayoutInfo layoutInfo) {
        layoutInfo.setHeader(ItemPosition.LeftHeader, this.reportDefinition.getReportCode(), 12);
        layoutInfo.setHeader(ItemPosition.CenterHeader, this.reportDefinition.getReportName(), 12);
    }

}