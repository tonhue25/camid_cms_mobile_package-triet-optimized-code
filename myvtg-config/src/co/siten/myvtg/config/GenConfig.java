package co.siten.myvtg.config;

public class GenConfig {

	public static void main(String[] args) throws Exception {
		try {
			// FTP gen config

			String output = "";
			String[] plainText = { "10.120.44.215", "SDD_TEST", "SDD_TEST2@18#" };

			output = AES.encrypt("vin123"); // encrypt
			System.out.println("ftp.host= " + output);

			output = AES.encrypt("sch"); // encrypt
			System.out.println("ftp.username= " +output);

			output = AES.encrypt("jdbc:oracle:thin:@//45.117.83.197:1521/pdborcl"); // encrypt
			
//			spring.datasource.url= jdbc:oracle:thin:@//45.117.83.197:1521/pdborcl
//					spring.datasource.username=sch
//					spring.datasource.password=vin123
//					
			System.out.println("ftp.password= " + output);
			
			System.out.println("==============================================================");

			// Db gen config
                        //10.120.8.195/testlab
			String ipSale = "(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.34)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.39)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = sales) ) )";
			String ipPrecus = "(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.54)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.46)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = precus) ) )";
			String ipPoscus = "(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.54)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.46)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = poscus) ) ) ";
			String ipPayment = "(DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.34)(PORT = 1521)) (ADDRESS = (PROTOCOL = TCP)(HOST = 10.78.3.39)(PORT = 1521)) (LOAD_BALANCE = yes) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = payment) ) )";

			String ip = "10.79.121.2";
			String service = ":1521/test";

			String[][] schemaList = { { "myvtg", "mymetfone", "Metfone@12345b", "jdbc:oracle:thin:@" + ip + service, "MYMETFONE" },
					{ "cmpos", "CM_POS2", "Up$Jzv7bs3kb", "jdbc:oracle:thin:@" + ip + service, "cm_pos2" },
					{ "cmpre", "CM_PRE2", "Up$Qb4kjaa2x", "jdbc:oracle:thin:@" + ip + service, "cm_pre2" },
					{ "data", "PROMOTION_OWNER", "Up$Mjhbsa0li23", "jdbc:oracle:thin:@" + ip + service, "promotion_owner" },
					{ "precall", "CM_PRE2", "Up$Qb4kjaa2x", "jdbc:oracle:thin:@" + ip + service, "cm_pre2" },
					{ "payment", "PAYMENT", "Up$Mn7Dvcxl5", "jdbc:oracle:thin:@" + ip + service, "payment" },
					{ "billing", "BILLING", "Up$Zcbqja9H5s", "jdbc:oracle:thin:@" + ip + service, "billing" },
					{ "sm", "BCCS_IM", "Up$Qh20k1kiisx", "jdbc:oracle:thin:@" + ip + service, "bccs_im" },
                                        { "loyalty", "privilege_owner", "Up$Qh20k1kiisx", "jdbc:oracle:thin:@" + ip + service, "privilege_owner" },
                                        { "mkishare", "MKISHARE", "Up$Kajb21jlb4", "jdbc:oracle:thin:@" + ip + service, "MKISHARE" },
                                        { "apigw", "apigw", "apigw", "jdbc:oracle:thin:@" + ip + service, "apigw" },
                                        { "bankplus", "BANKPLUS", "Up$DbiaOQlk1", "jdbc:oracle:thin:@" + ip + service, "BANKPLUS" },
                                        { "sms", "apigw", "apigw", "jdbc:oracle:thin:@" + ip + service, "SMS_APP" },
                                        { "im", "BCCS_IM", "Up$Qh20k1kiisx", "jdbc:oracle:thin:@" + ip + service, "BCCS_IM" },
					{ "product", "PRODUCT", "Up$Kilp0Qjbi", "jdbc:oracle:thin:@" + ip + service, "product" } };

			// //16 bytes = 128 bit

			// myvtg.jdbc.url =
			// 813821FE1280B47E9C14B5C6429406B25160CF9CD0877339944763FB0F980A6E3BC5C5AC89C35AEDB614DF80BBCCB45D
			// myvtg.jdbc.username = F75892A9DAD5840B8C2EDB52F199EB1B
			// myvtg.jdbc.password = 54DE4A3FF036F7EF0D41A1505255A73E
			for (String[] schema : schemaList) {
				String schemaName = schema[0];
				String user = AES.encrypt(schema[1]);
				String password = AES.encrypt(schema[2]);
				String url = AES.encrypt(schema[3]);
				String sname = schema[4];
				System.out.println("#============" + schemaName + "=============");
				System.out.println(schemaName + ".jdbc.url =" + url);
				System.out.println(schemaName + ".jdbc.username =" + user);
				System.out.println(schemaName + ".jdbc.password =" + password);
				if (!"".equalsIgnoreCase(sname))
					System.out.println(schemaName + ".default_schema =" + sname);
				System.out.println("#==============================");
			}

		} catch (Exception e) {
			
		}

	}
}