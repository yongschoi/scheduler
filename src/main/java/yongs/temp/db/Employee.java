package yongs.temp.db;

public class Employee {
	private int seq;
	private String name;
	private String sex;
	private String departmentCode;
	private String joblevelCode;
	private String skillCode;
	private String telephone;
	private String birthdate;
	private String postal;
	private String address;
 
	public Employee(int seq, String name, String sex, String departmentCode, String joblevelCode, String skillCode,
			String telephone, String birthdate, String postal, String address) {
		this.seq = seq;
		this.name = name;
		this.sex = sex;
		this.departmentCode = departmentCode;
		this.joblevelCode = joblevelCode;
		this.skillCode = skillCode;
		this.telephone = telephone;
		this.birthdate = birthdate;
		this.postal = postal;
		this.address = address;
	}

	public Employee(String name, String sex, String departmentCode, String joblevelCode, String skillCode,
			String telephone, String birthdate, String postal, String address) {
		this.name = name;
		this.sex = sex;
		this.departmentCode = departmentCode;
		this.joblevelCode = joblevelCode;
		this.skillCode = skillCode;
		this.telephone = telephone;
		this.birthdate = birthdate;
		this.postal = postal;
		this.address = address;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getJoblevelCode() {
		return joblevelCode;
	}

	public void setJoblevelCode(String joblevelCode) {
		this.joblevelCode = joblevelCode;
	}

	public String getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
