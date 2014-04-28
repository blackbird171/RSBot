package scripts.crisisplanker.data;

public enum Log {
	NONE("", -1, -1, -1, -1), 
	NOMRAL("Normal", 1511, 960, 100, 93), 
	OAK("Oak", 1521, 8778, 250, 94), 
	TEAK("Teak", 6333, 8780, 500, 95), 
	MAHOGANY("Mahogany", 6332, 8782, 1500, 96);
	
	private String name;
	private int logId;
	private int plankId;
	private int fee;
	private int child;
	
	Log(final String name, final int logId, final int plankId, final int fee, final int child) {
		this.name = name;
		this.logId = logId;
		this.plankId = plankId;
		this.fee = fee;
		this.child = child;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public int getLogId() {
		return logId;
	}
	
	public int getPlankId() {
		return plankId;
	}
	
	public int getFee() {
		return fee;
	}
	
	public int getChild() {
		return child;
	}
}
