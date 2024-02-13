package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


public class Main extends UserDaoJDBCImpl {
    public static void main(String[] args) {
        UserDaoJDBCImpl user = new UserDaoJDBCImpl();
        // user.createUsersTable();
  /*      user.saveUser("NameOne", "LastNameOne", (byte) 11);
        user.saveUser("NameTwo", "LastNameTwo", (byte) 22);
        user.saveUser("NameThree", "LastNameThree", (byte) 33);
        user.saveUser("NameFour", "LastNameFour", (byte) 44);*/

        // user.getAllUsers();
        //  user.cleanUsersTable();
        // user.dropUsersTable();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("name", "last", (byte) 11);
        userDaoHibernate.saveUser("name2", "last2", (byte) 22);
        userDaoHibernate.saveUser("name3", "last3", (byte) 33);
        userDaoHibernate.saveUser("name4", "last4", (byte) 44);
        userDaoHibernate.getAllUsers();
      //  userDaoHibernate.cleanUsersTable();
       // userDaoHibernate.dropUsersTable();

    }
}

