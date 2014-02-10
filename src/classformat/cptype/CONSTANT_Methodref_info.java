package classformat.cptype;

public class CONSTANT_Methodref_info extends ConstantPoolType {

	private int classrefIndex;
	private int nameTypeIndex;
	
	public CONSTANT_Methodref_info(int classrefIndex, int nameTypeIndex) {
		super();
		this.classrefIndex = classrefIndex;
		this.nameTypeIndex = nameTypeIndex;
	}
	public int getClassrefIndex() {
		return classrefIndex;
	}
	public void setClassrefIndex(int classrefIndex) {
		this.classrefIndex = classrefIndex;
	}
	public int getNameTypeIndex() {
		return nameTypeIndex;
	}
	public void setNameTypeIndex(int nameTypeIndex) {
		this.nameTypeIndex = nameTypeIndex;
	}
	
}
