/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author tsaicf
 */
public class SAMPLE_VO {

    final static private String[] AREA_NAME = { "臺北市", "新北市", "高雄市" };
    final static private String[] ITEM_NAME = { "第一類", "第二類", "第三類", "第四類" };

    static List<SAMPLE_VO> mockDataset() {
        return mockDataset(108);
    }

    /** 年度/地區/資料 */
    @SuppressWarnings("deprecation")
    static List<SAMPLE_VO> mockDataset(int maxYear) {
        final List<SAMPLE_VO> values = new ArrayList<SAMPLE_VO>();
        final List<String> tmpList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            tmpList.add("A" + i);
        }

        final Date now = new Date();
        for (int y = 100; y <= maxYear; y++) {
            for (String area : AREA_NAME) {
                for (String item : ITEM_NAME) {
                    SAMPLE_VO vo = new SAMPLE_VO();
                    vo.setText1("" + y + "年");
                    vo.setText2(area);
                    vo.setText3(item);
                    vo.setText4("" + RandomUtils.nextInt(1000, 2000));
                    vo.setValue1(y);
                    vo.setValue2(RandomUtils.nextInt(0, 200));
                    vo.setValue3(RandomUtils.nextInt(0, 10000));
                    vo.setDate1(now);
                    vo.setDate2(new java.sql.Date(y + 11, RandomUtils.nextInt(0, 11), RandomUtils.nextInt(1, 20)));
                    vo.setList1(tmpList);
                    values.add(vo);
                }
            }
        }
        return values;
    }

    private String text1;

    private String text2;

    private String text3;

    private String text4;

    private Integer value1;

    private Integer value2;

    private Integer value3;

    private Date date1;

    private Date date2;

    private Date date3;

    private Object obj1;

    private Object obj2;

    private Object obj3;

    private List<? extends Object> list1;

    private List<List<Object>> list2;

    public String getText1() {
        return this.text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return this.text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return this.text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return this.text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public Integer getValue1() {
        return this.value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    public Integer getValue2() {
        return this.value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    public Integer getValue3() {
        return this.value3;
    }

    public void setValue3(Integer value3) {
        this.value3 = value3;
    }

    public Date getDate1() {
        return this.date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return this.date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Date getDate3() {
        return this.date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public Object getObj1() {
        return this.obj1;
    }

    public void setObj1(Object obj1) {
        this.obj1 = obj1;
    }

    public Object getObj2() {
        return this.obj2;
    }

    public void setObj2(Object obj2) {
        this.obj2 = obj2;
    }

    public Object getObj3() {
        return this.obj3;
    }

    public void setObj3(Object obj3) {
        this.obj3 = obj3;
    }

    public List<? extends Object> getList1() {
        return this.list1;
    }

    public void setList1(List<? extends Object> list1) {
        this.list1 = list1;
    }

    public List<List<Object>> getList2() {
        return this.list2;
    }

    public void setList2(List<List<Object>> list2) {
        this.list2 = list2;
    }

}
