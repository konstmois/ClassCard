package ru.classcard.services.studentclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.classcard.dao.CardDAO;
import ru.classcard.dao.StudentClassDAO;
import ru.classcard.dao.UserDAO;
import ru.classcard.model.StudentClassContainer;
import ru.classcard.model.User;

import static org.apache.myfaces.shade.commons.codec.digest.DigestUtils.shaHex;

public class StudentClassService {

    @Autowired private CardDAO cardDAO;

    @Autowired private UserDAO userDao;

    @Autowired private StudentClassDAO classDao;

    @Transactional
    public void save(StudentClassContainer entities) {
        cardDAO.save(entities.getCard());
        classDao.save(entities.getStudentClass());

        saveUser(entities.getClassManager(),  entities.getPrevClassManagerPass());
        saveUser(entities.getClassMember(), entities.getPrevClassMemberPass());
    }

    private void saveUser(User user, String prevPass) {
        if (shouldSetPrevPass(user)) {
            user.setPassword(prevPass);
        } else if (shouldUpdatePass(user, prevPass)) {
            String hash = shaHex(user.getPassword());
            user.setPassword(hash);
        }
        userDao.save(user);
    }

    private boolean shouldSetPrevPass(User user) {
        return user.getPassword() == null || user.getPassword().equals("");
    }

    private boolean shouldUpdatePass(User user, String prevPass) {
        return !user.getPassword().equals(prevPass);
    }
}
