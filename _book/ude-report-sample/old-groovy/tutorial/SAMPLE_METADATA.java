/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import com.iisigroup.ude.report.data.op.NumberOP;
import com.iisigroup.ude.report.data.op.number.ColNumber;
import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.data.source.Const;
import com.iisigroup.ude.report.table.NormalTableMetadata;
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.format.CellFormat.AlignH;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.metadata.AbstractTableMetadata;
import com.iisigroup.ude.report.table.metadata.TreeColumnMetadata;

/**
 *
 */
@SuppressWarnings("unused")
public enum SAMPLE_METADATA {

    BASIC_0 {
        @Override
        public NormalTableMetadata get() {
            final NormalTableMetadata tableMeta = new NormalTableMetadata();
            tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
            final TreeColumnMetadata col_A = tableMeta.append("年度", new BeanProperty("text1"), 20);
            final TreeColumnMetadata col_B = tableMeta.append("地區", new BeanProperty("text2"), 20);
            final TreeColumnMetadata col_C = tableMeta.append("項目", new BeanProperty("text3"), 20);
            final TreeColumnMetadata col_V = tableMeta.append("值");
            final TreeColumnMetadata col_V1 = col_V.append("值1", new BeanProperty("value1"), 20);
            final TreeColumnMetadata col_V2 = col_V.append("值2", new BeanProperty("value2"), 20);
            final TreeColumnMetadata col_V3 = col_V.append("值3", new BeanProperty("value3"), 20);
            col_V1.getContentFormat().setAlignH(AlignH.RIGHT);
            col_V2.getContentFormat().setAlignH(AlignH.RIGHT);
            col_V3.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            final TreeColumnMetadata col_D = tableMeta.append("資料日期", new BeanProperty("date1"), 20);
            col_D.getContentFormat().setAlignH(AlignH.CENTER);
            return tableMeta;
        }
    },
    BASIC_1 {
        @Override
        public TreeTableMetadata get() {
            final TreeTableMetadata tableMeta = new TreeTableMetadata();
            // "測試表格" + name()
            tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
            final TreeColumnMetadata col_A = tableMeta.append("年度", new BeanProperty("text1"), 20);
            final TreeColumnMetadata col_B = tableMeta.append("地區", new BeanProperty("text2"), 20);
            final TreeColumnMetadata col_C = tableMeta.append("項目", new BeanProperty("text3"), 20);
            final TreeColumnMetadata col_V = tableMeta.append("值");
            final TreeColumnMetadata col_V1 = col_V.append("值1", new BeanProperty("value1"), 20);
            final TreeColumnMetadata col_V2 = col_V.append("值2", new BeanProperty("value2"), 20);
            final TreeColumnMetadata col_V3 = col_V.append("值3", new BeanProperty("value3"), 20);
            final TreeColumnMetadata col_V4 = col_V.append("值NULL  #0 4", Const.define(null), 20);
            final TreeColumnMetadata col_V5 = col_V.append("值BLANK #0 4", Const.define(""), 20);
            final TreeColumnMetadata col_V6 = col_V.append("值NULL  ## 5", Const.define(null), 20);
            final TreeColumnMetadata col_V7 = col_V.append("值BLANK ## 5", Const.define(""), 20);
            col_V1.getContentFormat().setAlignH(AlignH.RIGHT);
            col_V2.getContentFormat().setAlignH(AlignH.RIGHT);
            col_V3.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            col_V4.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            col_V5.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            col_V6.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,###");
            col_V7.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,###");
            final TreeColumnMetadata col_D = tableMeta.append("資料日期", new BeanProperty("date1"), 20);
            col_D.getContentFormat().setAlignH(AlignH.CENTER);
            return tableMeta;
        }
    },

    BASIC_1_SIZE0_1ST {
        @Override
        public NormalTableMetadata get() {
            final NormalTableMetadata tableMeta = new NormalTableMetadata();
            // "測試表格" + name()
            tableMeta.getDefaultContentFormat().setAlignV(AlignV.MIDDLE);
            final TreeColumnMetadata col_A = tableMeta.append("年度", new BeanProperty("text1"), 0);
            final TreeColumnMetadata col_B = tableMeta.append("地區", new BeanProperty("text2"), 0);
            final TreeColumnMetadata col_C = tableMeta.append("項目", new BeanProperty("text3"), 40);
            final TreeColumnMetadata col_V = tableMeta.append("值  / (0000) / (.00) / (#,##0)");
            final TreeColumnMetadata col_V1 = col_V.append("值1", new BeanProperty("value1"), 20);
            final TreeColumnMetadata col_V2 = col_V.append("值2", new BeanProperty("value2"), 20);
            final TreeColumnMetadata col_V3 = col_V.append("值3", new BeanProperty("value3"), 20);
            final NumberOP value4 = new ColNumber(col_V1).add(col_V2).add(col_V3);
            final TreeColumnMetadata col_V4 = col_V.prepend("合計值(前)", value4, 20);
            final TreeColumnMetadata col_V5 = col_V.append("合計值(後)", value4, 20);

            col_V1.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("0000");
            col_V2.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0.00");
            col_V3.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            col_V4.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            col_V5.getContentFormat().setAlignH(AlignH.RIGHT).setTextFormat("#,##0");
            final TreeColumnMetadata col_D = tableMeta.append("資料日期", new BeanProperty("date1"), 20);
            col_D.getContentFormat().setAlignH(AlignH.CENTER);
            return tableMeta;
        }
    };

    public abstract AbstractTableMetadata<?> get();
}