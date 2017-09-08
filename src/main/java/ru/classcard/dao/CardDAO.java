package ru.classcard.dao;

import ru.classcard.model.Card;
import ru.classcard.model.User;

public interface CardDAO extends AbstractEntityDAO {

    Card getCardBy(User user);

}
