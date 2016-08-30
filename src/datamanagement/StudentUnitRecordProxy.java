package datamanagement;
public class StudentUnitRecordProxy implements IStudentUnitRecord {// Inherit IStudentUnitRecord
private Integer studentID;
private String unitCode;
private StudentUnitRecordManager mngr;// creating object of other class
		public StudentUnitRecordProxy( Integer id, String code ) 
		{
			this.studentID = id;this.unitCode = code;
			this.mngr = StudentUnitRecordManager.instance();// initiating a new instance 
		}
		public Integer getStudentID() //function return integer value
		{
			return studentID; //getting student ID
		}
		public String getUnitCode()// function return string value
		{ 
			return unitCode;  // return unit code
		}
		public void setAsg1(float mark)
		{
			mngr.getStudentUnitRecord( studentID, unitCode).setAsg1(mark);
		}
		public float getAsg1() 
		{
			return mngr.getStudentUnitRecord( studentID, unitCode ).getAsg1();// getting assignment 1 from other class
		}
		public void setAsg2(float mark) 
		{ 
			mngr.getStudentUnitRecord( studentID, unitCode ).setAsg2(mark);//set value to asg2 which is in getStudentUnitRecord
		}
		public float getAsg2() 
		{
			return mngr.getStudentUnitRecord( studentID, unitCode ).getAsg2();
		}
		public void setExam(float mark) 
		{
			mngr.getStudentUnitRecord( studentID, unitCode ).setExam(mark);
		}
		public float getExam() 
		{
			return mngr.getStudentUnitRecord( studentID, unitCode ).getExam();
		}
		public float getTotal() 
		{
			return mngr.getStudentUnitRecord( studentID, unitCode ).getTotal();//getting total marks based on studentID
		}
		
}
