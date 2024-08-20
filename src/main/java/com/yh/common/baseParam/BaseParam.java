//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yh.common.baseParam;

import java.io.Serializable;
import java.util.regex.Pattern;

public class BaseParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Pattern PATTERN_SORT = Pattern.compile("^(?<field>[a-zA-Z0-9]+)(?<order>asc|desc)$", 2);
    private String sortField;
    private String sortOrder;
    private String sortString;
    public int pageSize = 20;
    public int pageNo = 1;

    public void setNoPaging() {
        this.pageSize = 9999;
        this.pageNo = 1;
    }

    public BaseParam() {
    }

    public String getSortField() {
        return this.sortField;
    }

    public String getSortOrder() {
        return this.sortOrder;
    }

    public String getSortString() {
        return this.sortString;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseParam)) {
            return false;
        } else {
            BaseParam other = (BaseParam)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getPageSize() != other.getPageSize()) {
                return false;
            } else if (this.getPageNo() != other.getPageNo()) {
                return false;
            } else {
                label52: {
                    Object this$sortField = this.getSortField();
                    Object other$sortField = other.getSortField();
                    if (this$sortField == null) {
                        if (other$sortField == null) {
                            break label52;
                        }
                    } else if (this$sortField.equals(other$sortField)) {
                        break label52;
                    }

                    return false;
                }

                Object this$sortOrder = this.getSortOrder();
                Object other$sortOrder = other.getSortOrder();
                if (this$sortOrder == null) {
                    if (other$sortOrder != null) {
                        return false;
                    }
                } else if (!this$sortOrder.equals(other$sortOrder)) {
                    return false;
                }

                Object this$sortString = this.getSortString();
                Object other$sortString = other.getSortString();
                if (this$sortString == null) {
                    if (other$sortString != null) {
                        return false;
                    }
                } else if (!this$sortString.equals(other$sortString)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof BaseParam;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + this.getPageSize();
        result = result * 59 + this.getPageNo();
        Object $sortField = this.getSortField();
        result = result * 59 + ($sortField == null ? 43 : $sortField.hashCode());
        Object $sortOrder = this.getSortOrder();
        result = result * 59 + ($sortOrder == null ? 43 : $sortOrder.hashCode());
        Object $sortString = this.getSortString();
        result = result * 59 + ($sortString == null ? 43 : $sortString.hashCode());
        return result;
    }

    public String toString() {
        return "BaseParam(sortField=" + this.getSortField() + ", sortOrder=" + this.getSortOrder() + ", sortString=" + this.getSortString() + ", pageSize=" + this.getPageSize() + ", pageNo=" + this.getPageNo() + ")";
    }

    public @interface detail {
    }

    public @interface delete {
    }

    public @interface edit {
    }

    public @interface add {
    }

    public @interface page {
    }

    public @interface list {
    }
}
