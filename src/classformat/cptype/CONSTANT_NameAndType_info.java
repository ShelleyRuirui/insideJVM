package classformat.cptype;

public class CONSTANT_NameAndType_info extends ConstantPoolType {

	private int nameIndex;
	private int descIndex;
	public CONSTANT_NameAndType_info(int nameIndex, int descIndex) {
		super();
		this.nameIndex = nameIndex;
		this.descIndex = descIndex;
	}
	public int getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	public int getDescIndex() {
		return descIndex;
	}
	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}
	
}
