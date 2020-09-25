package elevatorTest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import elevatorTest.API.ActionAPI;
import elevatorTest.API.OnCallsAPI;
import elevatorTest.API.StartAPI;
import elevatorTest.Data.Call;
import elevatorTest.Data.Command;
import elevatorTest.Data.Elevator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
	private static APICall apiCall = new APICall(); // start, onCall, action
	private static Gson gson = new Gson();

	public static void main(String[] args) {
		System.out.println("------------Start------------");
		problem_1(0);
	}

	public static void problem_1(Integer num) {
		StartAPI startAPI = gson.fromJson(apiCall.start(num.toString(), "test", "0", "1"), StartAPI.class);
		System.out.println(startAPI.toString());

		int check = 0;
		while (true) {

			check++;
			if(check == 10) break; // 테스트용

			OnCallsAPI onCallsAPI = gson.fromJson(apiCall.onCall(startAPI.getToken()), OnCallsAPI.class);
			System.out.println("-------TimeStamp : " + onCallsAPI.getTimestamp() + " -------");
			if(onCallsAPI.is_end()) break;
			System.out.println(onCallsAPI.getElevators());
			System.out.println("Call 수 : " + onCallsAPI.getCalls().size()); // 현재 Call 수 출력
			System.out.println("끝? : " + onCallsAPI.is_end());
			System.out.println("인원 : " + onCallsAPI.getElevators().get(0).getPassengers().size());

			if(onCallsAPI.getCalls().size() != 0) {
				for (Call call: onCallsAPI.getCalls()) {
					System.out.print(call.toString() + " ");
				}
				System.out.println();
			}

			ArrayList<Elevator> elevators = onCallsAPI.getElevators(); // 엘리베이터 상태
			ArrayList<Call> calls = onCallsAPI.getCalls(); // 넣어주면 삭제해주자.
			JsonArray commandArray = new JsonArray();

			for (Elevator elevator : elevators) {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("elevator_id", elevator.getId());
				jsonObject.addProperty("command", "STOP");

				if(onCallsAPI.getTimestamp() == 1) jsonObject.addProperty("command", "STOP");
				if(onCallsAPI.getTimestamp() == 2) jsonObject.addProperty("command", "OPEN");
				if(onCallsAPI.getTimestamp() == 3) {
					jsonObject.addProperty("command", "ENTER");
					JsonArray callList = new JsonArray();
					callList.add(onCallsAPI.getCalls().get(0).getId());
					callList.add(onCallsAPI.getCalls().get(1).getId());
					callList.add(onCallsAPI.getCalls().get(2).getId());
					callList.add(onCallsAPI.getCalls().get(3).getId());
					callList.add(onCallsAPI.getCalls().get(4).getId());
					callList.add(onCallsAPI.getCalls().get(5).getId());


					jsonObject.add("call_ids", callList);
				}
				if(onCallsAPI.getTimestamp() == 4) jsonObject.addProperty("command", "CLOSE");
				if(onCallsAPI.getTimestamp() == 5) jsonObject.addProperty("command", "UP");
				if(onCallsAPI.getTimestamp() == 6) jsonObject.addProperty("command", "STOP");
				if(onCallsAPI.getTimestamp() == 7) jsonObject.addProperty("command", "OPEN");
				if(onCallsAPI.getTimestamp() == 8) {
					jsonObject.addProperty("command", "EXIT");
					JsonArray callList = new JsonArray();
					callList.add(elevator.getPassengers().get(0).getId());
					callList.add(elevator.getPassengers().get(1).getId());
					callList.add(elevator.getPassengers().get(2).getId());
					callList.add(elevator.getPassengers().get(3).getId());
					callList.add(elevator.getPassengers().get(4).getId());
					callList.add(elevator.getPassengers().get(5).getId());

					jsonObject.add("call_ids", callList);
				}
				if(onCallsAPI.getTimestamp() == 9) jsonObject.addProperty("command", "CLOSE");
				if(onCallsAPI.getTimestamp() == 10) jsonObject.addProperty("command", "STOP");
				if(onCallsAPI.getTimestamp() == 11) jsonObject.addProperty("command", "STOP");
				if(onCallsAPI.getTimestamp() == 12) jsonObject.addProperty("command", "STOP");



//				switch (elevator.getStatus()) {
//					case "STOPPED" :
//
//
//						for (Call call: calls) {
//							if(call.getStart() == elevator.getFloor()) {
//
//							}
//						}
//						break;
//
//					case "UPWARD" :
//
//						break;
//
//					case "DOWNWARD" :
//
//						break;
//
//					case "OPENNED" :
//
//						break;
//				}

				commandArray.add(jsonObject);
			}

			JsonObject commandObject = new JsonObject();
			commandObject.add("commands", commandArray);
			ActionAPI actionAPI = gson.fromJson(apiCall.action(startAPI.getToken(), commandObject.toString()), ActionAPI.class);
			System.out.println(actionAPI.toString());
			System.out.println();
		}

		System.out.println("------END-----");
	}
}
