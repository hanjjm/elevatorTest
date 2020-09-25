package elevatorTest.API;

import elevatorTest.Data.Call;
import elevatorTest.Data.Elevator;
import lombok.Data;

import java.util.ArrayList;

@Data
public class OnCallsAPI {
	private String token;
	private Integer timestamp;
	private ArrayList<Elevator> elevators;
	private ArrayList<Call> calls;
	private boolean is_end;
}
