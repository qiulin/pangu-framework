package org.pf9.pangu.framework.data.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.pf9.pangu.framework.data.domain.datatables.DataTablesQuery;
import org.pf9.pangu.framework.data.domain.datatables.DataTablesResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataTablesTemplate {

    @Autowired
    private SqlSession sqlSession;

    public <T> DataTablesResult<T> selectDataTable(String statement, DataTablesQuery query) {

        int pageIndex = query.getStart() / query.getLength() + 1;
        int pageSize = query.getLength();

//        List<?> rows = sqlSession.selectList(statement, query);
        Page<T> paged = PageHelper.startPage(pageIndex, pageSize)
                .doSelectPage(() -> sqlSession.selectList(statement, query));

        DataTablesResult<T> result = new DataTablesResult<>();
        result.setData(paged.getResult());
        result.setDraw(query.getDraw());
        result.setRecordsFiltered(paged.getTotal());
        result.setRecordsTotal(paged.getTotal());
        return result;
    }
}
