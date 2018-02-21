///*
// * Copyright (c) 2009. 資拓科技. All right reserved.
// */
//package ude.report.sample.rsSample;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.iisigroup.ude.report.BlankTemplateSupplier;
//import com.iisigroup.ude.report.data.source.Const;
//import com.iisigroup.ude.report.itext2.PDFDocument;
//import com.iisigroup.ude.report.itext2.table.transfer.PDFTableTransferUtils;
//import com.iisigroup.ude.report.table.band.NestBlock;
//import com.iisigroup.ude.report.table.band.TableBlock;
//import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
//import com.iisigroup.ude.report.table.format.celltype.VerticalTitle;
//import com.lowagie.text.PageSize;
//
//import ude.report.sample.AbstractSample;
//import ude.report.sample.AbstractSampleReport;
//import ude.report.sample.SampleVO;
//import ude.report.sample.SampleVO_OM;
//
//public class Sample_RS_TableBlockWithDataSource extends AbstractSample {
//
//    final List<SampleVO> reportData = SampleVO_OM.testDataset(101);
//
//    public List<SampleVO> getReportData() {
//        return this.reportData;
//    }
//
//    private BlankTemplateSupplier<AbstractSampleReport> blankTemplateSupplier = () -> new AbstractSampleReport(PageSize.A4) {
//
//        @Override
//        public void generatePDFContent(final PDFDocument pdfDocument) {
//
//            final int size = 9;
//
//            for (int i = 0; i < Sample_RS_TableBlockWithDataSource.this.reportData.size(); i += size) {
//                final List<TableBlock<?, ?>> blocksInPage = new ArrayList<>();
//                final TableBlock<?, ?> headerTable = headerTable();
//                final TableBlock<?, ?> masterHeader = masterHeader();
//                final TableBlock<?, ?> basicTable = basicTable(i,
//                        Math.min(i + size, Sample_RS_TableBlockWithDataSource.this.reportData.size()));
//                final TableBlock<?, ?> masterFooter = masterFooter();
//
//                // PDF
//                pdfDocument.newPage();
//                blocksInPage.add(headerTable);
//                blocksInPage.add(masterHeader);
//                blocksInPage.add(basicTable);
//                blocksInPage.add(masterFooter);
//                for (final TableBlock<?, ?> tableBlock : blocksInPage) {
//                    PDFTableTransferUtils.append(pdfDocument, tableBlock, Sample_RS_TableBlockWithDataSource.this);
//                }
//            }
//            System.out.println(Sample_RS_TableBlockWithDataSource.this.reportData.size());
//        };
//
//        public TableBlock<?, ?> headerTable() {
//            final NestBlock block = NestBlock.noBorder();
//            block.append("戶政所別：", dataSource("reportData[0].text1"));
//            block.nextRow();
//            block.append("調整日期：", Const.define("民國106年09月22日"));
//            return block;
//        }
//
//        public TableBlock<?, ?> masterHeader() {
//
//            final NestBlock block = NestBlock.normalBorder();
//            //         戶　長　統　號*2F4986464 戶　別單獨生活戶
//            //
//            //        (以 打 V 者
//            //        為 準)
//            //        1. (　　) 管區警察
//            block.append("戶　號");
//            block.append(Const.define("F4986464"));
//            block.append("戶　號");
//            block.append(Const.define("F4986464"));
//            block.append("戶　號");
//            block.append(Const.define("F4986464"));
//
//            block.nextRow();
//
//            block.append("受　文　者", 10);
//            block.append(Const.define("F4986464"), 50);
//
//            return block;
//        }
//
//        public TableBlock<?, ?> masterFooter() {
//            final NestBlock block = NestBlock.normalBorder();
//            block.append("承辦人簽章");
//            block.append("");
//            block.append("主　任　簽　章");
//            block.append("");
//            block.append("備　註");
//            return block;
//        }
//
//        public TableBlock<?, ?> basicTable(final int start, final int end) {
//
//            final NestBlock block = NestBlock.normalBorder();
//
//            block.append("0戶內人口戶內人口戶內人口\n1戶內人口戶內人口戶內人口戶內人口", 8).getFormat()//
//                    .setAlignV(AlignV.MIDDLE) //
//                    .setCellType(VerticalTitle.DEFAULT);
//
//            block.subBlock(sub -> {
//                for (int i = start; i < end; i++) {
//                    if (i != start) {
//                        sub.nextRow();
//                    }
//                    sub.append("" + (i + 1), 8);
//                    sub.append(dataSource("reportData[" + i + "].text1"), 10);
//                    sub.append(dataSource("reportData[" + i + "].text2"), 10);
//                    sub.append(dataSource("reportData[" + i + "].text3"), 10);
//                    sub.append(dataSource("reportData[" + i + "].text4"), 20);
//                }
//            });
//            return block;
//        }
//
//    };
//
//    @Test
//    public void test_basicTable() {
//        super.createPDF(this.blankTemplateSupplier.create());
//    }
//
//    @Test
//    public void test_basicTable_showSource() {
//        super.createPDF(this.blankTemplateSupplier.showSource());
//    }
//
//}
