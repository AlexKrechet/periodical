//package ua.andrii.project_19.dao.impl;
//
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import ua.andrii.project_19.dao.UserDao;
//import ua.andrii.project_19.entity.Admin;
//import ua.andrii.project_19.entity.User;
//
//import javax.naming.NamingException;
//
//import static org.junit.Assert.*;
//
//public class UserDaoImplTest {
//
//    private TestJdbcDaoFactory daoFactory;
//
//    private UserDao userDao;
//    private final String login = "testTest";
//    private final String password = "11";
//    private final String name = "Test";
//    private final String surname = "Unit";
//
//
//    @Before
//    public void init() throws NamingException {
//        daoFactory = TestUtil.getDaoFactory();
//        userDao = daoFactory.getUserDao();
//    }
//
//    @After
//    public void destroy() {
//        daoFactory.closeDataSource();
//    }
//
//    @Test
//    public void crudTestOK() {
//        // create
//        User user = new Admin(email, password, name, familyName);
//        Long id = userDao.create(user);
//        Assert.assertTrue(id != null);
//
//        // read
//        user = userDao.read(id);
//        Assert.assertTrue(user.getEmail().equals(email));
//        Assert.assertTrue(user.getPassword().equals(password));
//        Assert.assertTrue(user.getName().equals(name));
//        Assert.assertTrue(user.getFamilyName().equals(familyName));
//
//        // update
//        String newName = "New";
//        user.setName(newName);
//        userDao.update(user);
//        user = userDao.read(id);
//        Assert.assertTrue(user.getName().equals(newName));
//
//        // delete
//        Assert.assertTrue(userDao.delete(user));
//    }
//
//    @Test
//     public void getUserTestOK() {
//        User user = new Admin(email, password, name, familyName);
//        userDao.create(user);
//
//        user = userDao.getUser(email, password);
//        Assert.assertTrue(user.getEmail().equals(email));
//        Assert.assertTrue(user.getPassword().equals(password));
//        Assert.assertTrue(user.getName().equals(name));
//        Assert.assertTrue(user.getFamilyName().equals(familyName));
//
//        userDao.delete(user);
//    }
//
//    @Test
//    public void getUserTestNull() {
//        User user = userDao.getUser("", "");
//        Assert.assertTrue(user == null);
//    }
//
//    @Test
//    public void hasUserTestTrue() {
//        User user = new Admin(email, password, name, familyName);
//        userDao.create(user);
//        Assert.assertTrue(userDao.hasUser(email));
//        userDao.delete(user);
//    }
//
//    @Test
//    public void hasUserTestFalse() {
//        Assert.assertFalse(userDao.hasUser(""));
//    }
//
//}