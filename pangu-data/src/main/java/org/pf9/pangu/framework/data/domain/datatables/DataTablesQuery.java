package org.pf9.pangu.framework.data.domain.datatables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTablesQuery {

    /**
     * Draw counter. This is used by DataTables to ensure that the Ajax returns from server-side
     * processing requests are drawn in sequence by DataTables (Ajax requests are asynchronous and
     * thus can return out of sequence). This is used as part of the draw return parameter (see
     * below).
     */
    private Integer draw = 1;

    /**
     * Paging first record indicator. This is the start point in the current data set (0 index based -
     * i.e. 0 is the first record).
     */
    private Integer start = 0;

    /**
     * Number of records that the table can display in the current draw. It is expected that the
     * number of records returned will be equal to this number, unless the server has fewer records to
     * return. Note that this can be -1 to indicate that all records should be returned (although that
     * negates any benefits of server-side processing!)
     */
    private Integer length = 10;

    /**
     * Global search parameter.
     */
    private DataTablesSearch search = new DataTablesSearch();

    /**
     * DataTablesOrder parameter
     */
    private List<DataTablesOrder> order = new ArrayList<DataTablesOrder>();

    /**
     * Per-column search parameter
     */
    private List<DataTablesColumn> columns = new ArrayList<DataTablesColumn>();

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public DataTablesSearch getSearch() {
        return search;
    }

    public void setSearch(DataTablesSearch search) {
        this.search = search;
    }

    public List<DataTablesOrder> getOrder() {
        return order;
    }

    public void setOrder(List<DataTablesOrder> order) {
        this.order = order;
    }

    public List<DataTablesColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<DataTablesColumn> columns) {
        this.columns = columns;
    }

    /**
     * @return a {@link Map} of {@link DataTablesColumn} indexed by name
     */
    public Map<String, DataTablesColumn> getColumnsAsMap() {
        Map<String, DataTablesColumn> map = new HashMap<String, DataTablesColumn>();
        for (DataTablesColumn column : columns) {
            map.put(column.getData(), column);
        }
        return map;
    }

    /**
     * Find a column by its name
     *
     * @param columnName the name of the column
     * @return the given DataTablesColumn, or <code>null</code> if not found
     */
    public DataTablesColumn getColumn(String columnName) {
        if (columnName == null) {
            return null;
        }
        for (DataTablesColumn column : columns) {
            if (columnName.equals(column.getData())) {
                return column;
            }
        }
        return null;
    }

    /**
     * Add a new column
     *
     * @param columnName  the name of the column
     * @param searchable  whether the column is searchable or not
     * @param orderable   whether the column is orderable or not
     * @param searchValue if any, the search value to apply
     */
    public void addColumn(String columnName, boolean searchable, boolean orderable,
                          String searchValue) {
        this.columns.add(new DataTablesColumn(columnName, "", searchable, orderable,
                new DataTablesSearch(searchValue, false)));
    }

    /**
     * Add an order on the given column
     *
     * @param columnName the name of the column
     * @param ascending  whether the sorting is ascending or descending
     */
    public void addOrder(String columnName, boolean ascending) {
        if (columnName == null) {
            return;
        }
        for (int i = 0; i < columns.size(); i++) {
            if (!columnName.equals(columns.get(i).getData())) {
                continue;
            }
            order.add(new DataTablesOrder(i, ascending ? "asc" : "desc"));
        }
    }


    public DataTablesQuery(Integer draw, Integer start, Integer length, DataTablesSearch search, List<DataTablesOrder> order, List<DataTablesColumn> columns) {
        this.draw = draw;
        this.start = start;
        this.length = length;
        this.search = search;
        this.order = order;
        this.columns = columns;
    }

    public DataTablesQuery() {

    }
}
