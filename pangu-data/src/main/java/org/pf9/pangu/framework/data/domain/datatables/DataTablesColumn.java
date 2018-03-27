package org.pf9.pangu.framework.data.domain.datatables;


public class DataTablesColumn {

    /**
     * DataTablesColumn's data source
     *
     * @see http://datatables.net/reference/option/columns.data
     */
    private String data;

    /**
     * DataTablesColumn's name
     *
     * @see http://datatables.net/reference/option/columns.name
     */
    private String name;

    /**
     * Flag to indicate if this column is searchable (true) or not (false).
     *
     * @see http://datatables.net/reference/option/columns.searchable
     */
    private Boolean searchable;

    /**
     * Flag to indicate if this column is orderable (true) or not (false).
     *
     * @see http://datatables.net/reference/option/columns.orderable
     */
    private Boolean orderable;

    /**
     * DataTablesSearch value to apply to this specific column.
     */
    private DataTablesSearch search;

    /**
     * Set the search value to apply to this column
     *
     * @param searchValue if any, the search value to apply
     */
    public void setSearchValue(String searchValue) {
        this.search.setValue(searchValue);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public Boolean getOrderable() {
        return orderable;
    }

    public void setOrderable(Boolean orderable) {
        this.orderable = orderable;
    }

    public DataTablesSearch getSearch() {
        return search;
    }

    public void setSearch(DataTablesSearch search) {
        this.search = search;
    }


    public DataTablesColumn(String data, String name, Boolean searchable, Boolean orderable, DataTablesSearch search) {
        this.data = data;
        this.name = name;
        this.searchable = searchable;
        this.orderable = orderable;
        this.search = search;
    }

    public DataTablesColumn() {

    }
}
