package com.geekerstar.starrocks.config.mybatis_plus;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * @author geekerstar
 * @date 2022/2/17 15:26
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? "耗时 " + elapsed + " ms | SQL 语句：" + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : "";
    }
}
