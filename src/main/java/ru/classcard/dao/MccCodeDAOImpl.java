package ru.classcard.dao;

import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.MccCode;

import java.util.Collection;
import java.util.List;

import static org.hibernate.criterion.Restrictions.in;

public class MccCodeDAOImpl extends AbstractEntityDAOImpl implements MccCodeDAO {

    @Override
    @Transactional
    public List<MccCode> getListByCodes(Collection<String> codes) {
        return getSession().createCriteria(MccCode.class).add(in("code", codes)).list();
    }
}
