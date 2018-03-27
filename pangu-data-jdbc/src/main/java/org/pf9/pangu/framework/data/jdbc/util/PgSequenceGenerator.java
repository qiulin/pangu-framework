package org.pf9.pangu.framework.data.jdbc.util;

import org.pf9.pangu.framework.data.util.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("pgSequenceGenerator")
public class PgSequenceGenerator implements SequenceGenerator {

    private String sequence;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SequenceGenerator setSequence(String seq) {
        this.sequence = seq;
        return this;
    }

    @Override
    public Long nextValue() {
        List<Map<String, Object>> ls = jdbcTemplate.queryForList("SELECT nextval('" + this.sequence + "')");

        return Long.valueOf(ls.get(0).get("nextval").toString());
    }

    @Deprecated
    public Long nextValue(String seq) {
        this.sequence = seq;
        return this.nextValue();
    }
}
