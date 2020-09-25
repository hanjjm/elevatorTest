package elevatorTest.API;

import elevatorTest.Data.Elevator;
import lombok.Data;

import java.util.ArrayList;

@Data
public class StartAPI {
	private String token;
	private Integer timestamp;
	private ArrayList<Elevator> elevators;
	private boolean is_end;
}
