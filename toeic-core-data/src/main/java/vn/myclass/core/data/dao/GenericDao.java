package vn.myclass.core.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable,T> {
    List<T> findAll();
}
