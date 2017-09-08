package ru.classcard.dao;

import ru.classcard.model.MccCode;

import java.util.Collection;
import java.util.List;

public interface MccCodeDAO extends AbstractEntityDAO {

    List<MccCode> getListByCodes(Collection<String> mccCodesFrom);
}
