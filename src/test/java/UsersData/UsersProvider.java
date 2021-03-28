package UsersData;

import UsersData.UserRequestDataObject;
import org.testng.annotations.DataProvider;

public class UsersProvider {

    @DataProvider
    public static Object[][] usersProvider () {
        Object[][] data = new Object[5][1];
        data [0][0] = new UserRequestDataObject("name1", "worker1");
        data [1][0] = new UserRequestDataObject("name2", "worker2");
        data [2][0] = new UserRequestDataObject("name3", "worker3");
        data [3][0] = new UserRequestDataObject("name4", "worker4");
        data [4][0] = new UserRequestDataObject("name5", "worker5");
        return data;
    }
}
