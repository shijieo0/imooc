package org.imooc.dao;

import java.util.List;

import org.imooc.bean.Dic;

public interface DicDao {
    List<Dic> select(Dic dic);
}