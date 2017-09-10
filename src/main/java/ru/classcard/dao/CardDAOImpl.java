package ru.classcard.dao;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import ru.classcard.model.Card;
import ru.classcard.model.User;

public class CardDAOImpl extends AbstractEntityDAOImpl implements CardDAO {

    @Override
    @Transactional
    public Card getCardBy(User user) {
        return (Card) getSession().createCriteria(Card.class)
                .add(Restrictions.eq("owner", user))
                .uniqueResult();
    }

}
