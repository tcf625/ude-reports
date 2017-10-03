/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package ude.report.sample;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.RandomUtils;

import com.iisigroup.ude.util.lang8.date.UdeTimeUtils;

public class SampleVO_OM {

    final static private String[] AREA_NAME = { "臺北市", "新北市", "高雄市" };
    final static private String[] ITEM_NAME = { "第一類", "第二類", "第三類", "第四類" };

    public static SampleVO testData() {
        return testData(99, AREA_NAME[0], ITEM_NAME[1]);
    }

    public static SampleVO testData(final int year) {
        return testData(year, AREA_NAME[0], ITEM_NAME[1]);
    }

    public static List<SampleVO> testDataset() {
        return testDataset(108);
    }

    /** 年度/地區/資料 */
    public static List<SampleVO> testDataset(final int maxYear) {
        final List<SampleVO> values = new ArrayList<>();
        final List<String> tmpList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            tmpList.add("A" + i);
        }

        for (int year = 100; year <= maxYear; year++) {
            for (final String area : AREA_NAME) {
                for (final String item : ITEM_NAME) {
                    final SampleVO vo = testData(year, area, item);
                    vo.setList1(tmpList);
                    values.add(vo);
                }
            }
        }
        return values;
    }

    static AtomicInteger counter = new AtomicInteger(0);

    public static SampleVO testData(final int year, final String area, final String item) {
        final Date now = new Date();
        final SampleVO vo = new SampleVO();
        vo.setText1("" + year + "年");
        vo.setText2(area);
        vo.setText3(item);
        vo.setText4("" + RandomUtils.nextInt(1000, 2000));
        vo.setValue1(counter.incrementAndGet());
        vo.setValue2(RandomUtils.nextInt(0, 200));
        vo.setValue3(RandomUtils.nextInt(0, 10000));
        vo.setDate1(now);
        final MinguoDate minguoDate = MinguoDate.of(year + 11, RandomUtils.nextInt(1, 12), RandomUtils.nextInt(1, 20));
        final LocalDate localDate = LocalDate.from(minguoDate);
        vo.setDate2(UdeTimeUtils.toDate(localDate.atStartOfDay()));
        return vo;
    }

}
