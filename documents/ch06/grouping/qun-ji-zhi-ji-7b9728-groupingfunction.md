

# Enum GroupingFunction

* BLANK  // 留空
* HEADER // 顯示標題
* SUM
* COUNT
* FIRST // 同一群集中的第一項
* LAST  // 同一群集中的最末項
* MAX_VALUE
* MIN_VALUE

``` java 
tableMetadata.append("年度", new BeanProperty("text1"));
tableMetadata.append("地區", new BeanProperty("text2"));
tableMetadata.append("項目", new BeanProperty("text3")).setGroupFunction(GroupingFunction.COUNT);
tableMetadata.append("值1", new BeanProperty("value1")).setGroupFunction(GroupingFunction.SUM);
tableMetadata.append("值2", new BeanProperty("value2")).setGroupFunction(GroupingFunction.SUM);
```