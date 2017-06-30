/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.data.source.Const;
import com.iisigroup.ude.report.data.source.Counter;
import com.iisigroup.ude.report.table.NestTableMetadata;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat.AlignH;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.metadata.AbstractTableMetadata;
import com.iisigroup.ude.report.table.metadata.BaseColumnMetadata;
import com.iisigroup.ude.report.table.metadata.ColumnStyle;

/**
 *
 */
public enum SAMPLE_METADATA_NEST {

    BASIC_0 {
        @Override
        public NestTableMetadata get() {
            final NestTableMetadata tableMeta = new NestTableMetadata();
            tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
            tableMeta.append("年度", Const.define(""), 20);
            tableMeta.append("地區", new BeanProperty("text2"), 20);
            tableMeta.append("項目", new BeanProperty("text3"), 20);
            tableMeta.nextRow();
            final BaseColumnMetadata col_V1 = tableMeta.append("值1", new BeanProperty("value1"), 20);
            final BaseColumnMetadata col_V2 = tableMeta.append("值2", new BeanProperty("value2"), 20);
            final BaseColumnMetadata col_V3 = tableMeta.append("值3", new BeanProperty("value3"), 20);
            col_V1.getContentFormat().setAlignH(AlignH.RIGHT);
            col_V2.getContentFormat().setAlignH(AlignH.RIGHT);
            col_V3.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");

            final BaseColumnMetadata col_D = tableMeta.append("資料日期", new BeanProperty("date1"), 20);
            col_D.getContentFormat().setAlignH(AlignH.CENTER);
            return tableMeta;
        }
    },

    ML_6102 {
        @Override
        public NestTableMetadata get() {

            final NestTableMetadata outerTable = new NestTableMetadata();

            // Define Style
            final ColumnStyle styleCenter = outerTable.getStyle("CENTER");
            styleCenter.getHeader().setAlignH(AlignH.CENTER).setAlignV(AlignV.MIDDLE);
            styleCenter.getContent().setAlignH(AlignH.CENTER).setAlignV(AlignV.MIDDLE);

            outerTable.setWidthPercentage(95);
            outerTable.setDefaultColumnWidth(0.3f);
            outerTable.getDefaultFormat().setBorder(Border.N);
            outerTable.getDefaultHeaderFormat().setFontBold(true).setFontSize(14);
            outerTable.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
            outerTable.append("", Const.define("V"));
            // 主要內容
            addAllMainColnum(outerTable.subTable());
            return outerTable;
        }

        @SuppressWarnings("deprecation")
        private void addAllMainColnum(final NestTableMetadata mainTable) {

            // 外框加上粗線
            mainTable.setBorder(Border.A);
            mainTable.setBorderWidth(3f);

            // 內框設為輸出所有框線
            mainTable.getDefaultFormat().setBorder(Border.A);
            mainTable.getDefaultHeaderFormat().setAlignH(AlignH.LEFT);

            mainTable.append("序號", new Counter(1)).setStyle("CENTER");
            mainTable.subTable(sub -> {
                sub.append("姓名", Const.define(""), 4);
                sub.append("出生日期", Const.define(""), 4);
                sub.append("統號", Const.define(""), 4);
                sub.append("連絡電話", Const.define(""), 4);
            });

            final NestTableMetadata table2 = mainTable.subTable();
            table2.append("遷入日期\n時：分：秒", Const.define(""), 4);
            table2.append("遷入別", Const.define(""), 4);
            mainTable.subTable(sub -> {
                sub.append("戶長統號", Const.define(""), 4); //
                sub.append("戶長姓名", Const.define(""), 4);
            });
            table2.append("遷出地", Const.define(""));

            table2.nextRow();
            mainTable.subTable(sub -> {
                sub.append("出生地", Const.define(""), 10); //
                sub.append("學歷", Const.define(""), 10);
            });
            table2.append("遷入地", Const.define(""), 11.5f);
        }
    };

    abstract public AbstractTableMetadata<?> get();
}
