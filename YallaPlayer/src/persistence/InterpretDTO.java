package persistence;

public class InterpretDTO {
	private int interpretID;
	private String name;

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

	@Override
	public String toString() {
		return name;
	}
}
