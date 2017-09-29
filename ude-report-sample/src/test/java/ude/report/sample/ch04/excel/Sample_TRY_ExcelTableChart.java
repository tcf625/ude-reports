/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.excel;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Range;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCatAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTValAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.STAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STBarDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.STTickLblPos;

import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.excel.ExcelPoint;
import com.iisigroup.ude.report.excel.ExcelRange;
import com.iisigroup.ude.report.excel.ExcelSheet;
import com.iisigroup.ude.report.excel.chart.data.XSSFBarChart;
import com.iisigroup.ude.report.excel.chart.data.XSSFPieChart;
import com.iisigroup.ude.report.excel.table.transfer.ExcelTableTransfer;
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.chart.ChartBand;
import com.iisigroup.ude.report.table.chart.PieChartBand;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO_OM;

public class Sample_TRY_ExcelTableChart extends AbstractSample {

    /***
     * https://stackoverflow.com/questions/38913412/create-bar-chart-in-excel-with-apache-poi
     */
    public void sampleChart(final Sheet sheet) {

        Row row;
        Cell cell;

        row = sheet.createRow(0);
        row.createCell(0);
        row.createCell(1).setCellValue("HEADER 1");
        row.createCell(2).setCellValue("HEADER 2");
        row.createCell(3).setCellValue("HEADER 3");

        for (int r = 1; r < 5; r++) {
            row = sheet.createRow(r);
            cell = row.createCell(0);
            cell.setCellValue("Serie " + r);
            cell = row.createCell(1);
            cell.setCellValue(new java.util.Random().nextDouble());
            cell = row.createCell(2);
            cell.setCellValue(new java.util.Random().nextDouble());
            cell = row.createCell(3);
            cell.setCellValue(new java.util.Random().nextDouble());
        }

        final Drawing<?> drawing = sheet.createDrawingPatriarch();
        final ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 8, 20);
        final Chart chart = drawing.createChart(anchor);
        final CTChart ctChart = ((XSSFChart) chart).getCTChart();
        final CTPlotArea ctPlotArea = ctChart.getPlotArea();
        final CTBarChart ctBarChart = ctPlotArea.addNewBarChart();
        final CTBoolean ctBoolean = ctBarChart.addNewVaryColors();
        ctBoolean.setVal(true);
        ctBarChart.addNewBarDir().setVal(STBarDir.COL);

        for (int r = 2; r < 6; r++) {
            final CTBarSer ctBarSer = ctBarChart.addNewSer();
            final CTSerTx ctSerTx = ctBarSer.addNewTx();
            CTStrRef ctStrRef = ctSerTx.addNewStrRef();
            ctStrRef.setF("Sheet1!$A$" + r);
            ctBarSer.addNewIdx().setVal(r - 2);
            final CTAxDataSource cttAxDataSource = ctBarSer.addNewCat();
            ctStrRef = cttAxDataSource.addNewStrRef();
            ctStrRef.setF("Sheet1!$B$1:$D$1");
            final CTNumDataSource ctNumDataSource = ctBarSer.addNewVal();
            final CTNumRef ctNumRef = ctNumDataSource.addNewNumRef();
            ctNumRef.setF("Sheet1!$B$" + r + ":$D$" + r);

            //at least the border lines in Libreoffice Calc ;-)
            ctBarSer.addNewSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { 0, 0, 0 });

        }

        //telling the BarChart that it has axes and giving them Ids
        ctBarChart.addNewAxId().setVal(123456);
        ctBarChart.addNewAxId().setVal(123457);

        //cat axis
        final CTCatAx ctCatAx = ctPlotArea.addNewCatAx();
        ctCatAx.addNewAxId().setVal(123456); //id of the cat axis
        CTScaling ctScaling = ctCatAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctCatAx.addNewDelete().setVal(false);
        ctCatAx.addNewAxPos().setVal(STAxPos.B);
        ctCatAx.addNewCrossAx().setVal(123457); //id of the val axis
        ctCatAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //val axis
        final CTValAx ctValAx = ctPlotArea.addNewValAx();
        ctValAx.addNewAxId().setVal(123457); //id of the val axis
        ctScaling = ctValAx.addNewScaling();
        ctScaling.addNewOrientation().setVal(STOrientation.MIN_MAX);
        ctValAx.addNewDelete().setVal(false);
        ctValAx.addNewAxPos().setVal(STAxPos.L);
        ctValAx.addNewCrossAx().setVal(123456); //id of the cat axis
        ctValAx.addNewTickLblPos().setVal(STTickLblPos.NEXT_TO);

        //legend
        final CTLegend ctLegend = ctChart.addNewLegend();
        ctLegend.addNewLegendPos().setVal(STLegendPos.B);
        ctLegend.addNewOverlay().setVal(false);

        System.out.println(ctChart);
    }

    @Test
    public void test_basic() {
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheetB = excelDocument.createSheet("Sheet1");
            this.sampleChart(sheetB.getRealSheet());
        });
    }

    @Test
    public void test_pieChart() {
        final TreeTableMetadata metadata = new TreeTableMetadata();
        metadata.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
        final TreeColumnMetadata append1 = metadata.append("項目", new BeanProperty("value2"));
        final TreeColumnMetadata append2 = metadata.append("值2", new BeanProperty("value3"));
        final ChartBand p = new PieChartBand(append1, append2);
        metadata.addChart(p);
        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            final ExcelTableTransfer transfer = new ExcelTableTransfer(metadata, sheet);
            transfer.transTable(SampleVO_OM.testDataset(100));
        });
    }

    private static class Mpvo {
        int s1 = RandomUtils.nextInt(0, 10);
        int s2 = RandomUtils.nextInt(0, 20);
        int s3 = RandomUtils.nextInt(0, 30);
        int s4 = RandomUtils.nextInt(0, 80);
        int s5 = RandomUtils.nextInt(0, 40);
    }

    @Test
    public void test_MP_FORM() {

        super.createExcel(excelDocument -> {
            final ExcelSheet<?> sheet = excelDocument.createSheet("A");
            sheet.appendCell(new ExcelPoint(0, 0), "Score");
            sheet.appendCell(new ExcelPoint(1, 0), "非常不滿意");
            sheet.appendCell(new ExcelPoint(2, 0), "不滿意");
            sheet.appendCell(new ExcelPoint(3, 0), "尚可");
            sheet.appendCell(new ExcelPoint(4, 0), "滿意");
            sheet.appendCell(new ExcelPoint(5, 0), "非常滿意");
            sheet.appendCell(new ExcelPoint(6, 0), "Total");
            sheet.appendCell(new ExcelPoint(7, 0), "Avg");

            for (int i = 1; i <= 6; i++) {
                final Mpvo vo = new Mpvo();
                sheet.appendCell(new ExcelPoint(0, i), "Q" + i);
                sheet.appendCell(new ExcelPoint(1, i), vo.s1);
                sheet.appendCell(new ExcelPoint(2, i), vo.s2);
                sheet.appendCell(new ExcelPoint(3, i), vo.s3);
                sheet.appendCell(new ExcelPoint(4, i), vo.s4);
                sheet.appendCell(new ExcelPoint(5, i), vo.s5);
                sheet.appendFormulaCell(new ExcelPoint(6, i), "SUM(" + ExcelPoint.excelRange(1, i, 5, i) + ")");
                sheet.appendFormulaCell(new ExcelPoint(7, i),
                        "SUM(" //
                                + " 1*" + ExcelPoint.excelForamt(1, i) //
                                + "+2*" + ExcelPoint.excelForamt(2, i) //
                                + "+3*" + ExcelPoint.excelForamt(3, i) //
                                + "+4*" + ExcelPoint.excelForamt(4, i) //
                                + "+5*" + ExcelPoint.excelForamt(5, i) //
                                + ")/" + ExcelPoint.excelForamt(6, i));

            }

            sheet.appendCell(new ExcelPoint(0, 7), "TOTAL");
            sheet.appendFormulaCell(new ExcelPoint(1, 7), "SUM(B2:G2)");
            sheet.appendFormulaCell(new ExcelPoint(2, 7), "SUM(B3:G3)");
            sheet.appendFormulaCell(new ExcelPoint(3, 7), "SUM(B4:G4)");
            sheet.appendFormulaCell(new ExcelPoint(4, 7), "SUM(B5:G5)");
            sheet.appendFormulaCell(new ExcelPoint(5, 7), "SUM(B6:G6)");
            sheet.appendFormulaCell(new ExcelPoint(6, 7), "SUM(B7:G7)");
            sheet.appendFormulaCell(new ExcelPoint(7, 7),
                    "SUM(" //
                            + " 1*" + ExcelPoint.excelForamt(1, 7) //
                            + "+2*" + ExcelPoint.excelForamt(2, 7) //
                            + "+3*" + ExcelPoint.excelForamt(3, 7) //
                            + "+4*" + ExcelPoint.excelForamt(4, 7) //
                            + "+5*" + ExcelPoint.excelForamt(5, 7) //
                            + ")/" + ExcelPoint.excelForamt(6, 7));

            final CellFormat cf = new CellFormat().setTextFormat("0.00%");
            sheet.appendCell(new ExcelPoint(0, 8), "%");
            sheet.appendFormulaCell(new ExcelPoint(1, 8), "H2/$H$7", cf);
            sheet.appendFormulaCell(new ExcelPoint(2, 8), "H3/$H$7", cf);
            sheet.appendFormulaCell(new ExcelPoint(3, 8), "H4/$H$7", cf);
            sheet.appendFormulaCell(new ExcelPoint(4, 8), "H5/$H$7", cf);
            sheet.appendFormulaCell(new ExcelPoint(5, 8), "H6/$H$7", cf);
            sheet.appendCell(new ExcelPoint(6, 8), "");
            sheet.appendCell(new ExcelPoint(7, 8), "");

            final XSSFPieChart pieChart = new XSSFPieChart(sheet);
            pieChart.addColumnSeries("", Range.between(1, 5), 0, 8);
            pieChart.appendMe(new ExcelRange(9, 0, 9, 7), 200);

            final XSSFBarChart barChart = new XSSFBarChart(sheet); // , "滿意度"
            barChart.addRowSeries("非常不滿意", 0, 1, Range.between(1, 6));
            barChart.addRowSeries("不滿意", 0, 2, Range.between(1, 6));
            barChart.addRowSeries("尚可", 0, 3, Range.between(1, 6));
            barChart.addRowSeries("滿意", 0, 4, Range.between(1, 6));
            barChart.addRowSeries("非常滿意", 0, 5, Range.between(1, 6));
            // barChart.appendMe(new ExcelRange(0, 8, 7, 12));
            barChart.appendMe(new ExcelRange(11, 0, 11, 7), 200);

        });
    }

}
