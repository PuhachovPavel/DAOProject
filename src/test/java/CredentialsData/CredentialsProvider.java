package CredentialsData;

import org.testng.annotations.DataProvider;

public class CredentialsProvider {

    @DataProvider
    public static Object[][] credentialsProvider() {
        Object[][] data = new Object[5][1];
        data[0][0] = new CredentialsRequestDataObject("mail1@mail.com", "pass1");
        data[1][0] = new CredentialsRequestDataObject("mail2@mail.com", "pass2");
        data[2][0] = new CredentialsRequestDataObject("mail3@mail.com", "pass3");
        data[3][0] = new CredentialsRequestDataObject("mail4@mail.com", "pass4");
        data[4][0] = new CredentialsRequestDataObject("mail5@mail.com", "pass5");
        return data;
    }

}
