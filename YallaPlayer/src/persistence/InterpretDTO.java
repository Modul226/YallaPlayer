package persistence;

public class InterpretDTO {
	public int interpretID;
	public String name;

	public InterpretDTO(int interpretID, String name) {
		this.interpretID = interpretID;
		this.name = name;
	}

	public int getInterpretID() {
		return interpretID;
	}

	public String getName() {
		return name;
	}
}
