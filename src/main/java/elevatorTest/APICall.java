package elevatorTest;

import com.google.gson.JsonObject;
import elevatorTest.Connection.Connection;

public class APICall {
	private static String basicURL = "http://localhost:8000";
	private static Connection connection = new Connection();

	public static String start(String number, String userKey, String problemId, String numOfElevators) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("user_key", userKey);
		jsonObject.addProperty("problem_id", problemId);
		jsonObject.addProperty("number_of_elevators", numOfElevators);
		return connection.post(basicURL + "/start/" + userKey + "/" + problemId + "/" + numOfElevators, jsonObject.toString());
	}

	public static String onCall(String token) {
		return connection.get(basicURL + "/oncalls", token);
	}

	public static String action(String token, String commands) {
		return connection.post(basicURL + "/action", token, commands);
	}
}
