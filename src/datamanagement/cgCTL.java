package datamanagement;

		public class cgCTL 
		{

			cgUI CGUI;//graphical user interface class to show objects on screen or window
			String cuc = null;
			Integer currentStudentID = null;
			boolean changed = false;

		public cgCTL() 
		{
		}

		public void execute() 
		{
			//setting states and visibility to different CGUI's
			CGUI = new cgUI(this);
			CGUI.setState1(false);

			CGUI.setState2(false);
			CGUI.setState3(false);
			CGUI.setState4(false);
			CGUI.setState5(false);
			CGUI.setState6(false);
			CGUI.Refresh3();
			ListUnitsCTL luCTL = new ListUnitsCTL();
			luCTL.listUnits(CGUI);
			CGUI.setVisible(true);
			CGUI.setState1(true);
		}

		public void unitSelected(String code) 
		{

			if (code.equals("NONE"))CGUI.setState2(false);
			else 
			{
				ListStudentsCTL lsCTL = new ListStudentsCTL();
				lsCTL.listStudents(CGUI, code);
				cuc = code;
				CGUI.setState2(true);
			}
			CGUI.setState3(false);
		}

		public void studentSelected(Integer id) 
		{
			currentStudentID = id;
			if (currentStudentID.intValue() == 0) 
			{
				CGUI.Refresh3();
				CGUI.setState3(false);
				CGUI.setState4(false);
				CGUI.setState5(false);
				CGUI.setState6(false);
			}

			else 
			{
				IStudent s = StudentManager.get().getStudent(id);
				IStudentUnitRecord r = s.getUnitRecord(cuc);
				CGUI.setRecord(r);
				CGUI.setState3(true);
				CGUI.setState4(true);
				CGUI.setState5(false);
				CGUI.setState6(false);
				changed = false;

			}
		}

		public String checkGrade(float f, float g, float h) //Purpose of this functioon if to check grade of specific student based on its ID
		{
			IUnit u = UnitManager.UM().getUnit(cuc);
			String s = u.getGrade(f, g, h);
			CGUI.setState4(true);
			CGUI.setState5(false);
			if (changed) 
			{
				CGUI.setState6(true);
			}
			return s;
		}

		public void enableChangeMarks() //This function allow to change marks , show buttons or CGUI for changing marks
		{
			CGUI.setState4(false);
			CGUI.setState6(false);
			CGUI.setState5(true);
			changed = true;
		}

		public void saveGrade(float asg1, float asg2, float exam) //purpose is to save grades of students.
		{
			IUnit u = UnitManager.UM().getUnit(cuc);
			IStudent s = StudentManager.get().getStudent(currentStudentID);
			IStudentUnitRecord r = s.getUnitRecord(cuc);
			r.setAsg1(asg1);
			r.setAsg2(asg2);
			r.setExam(exam);
			StudentUnitRecordManager.instance().saveRecord(r);
			CGUI.setState4(true);
			CGUI.setState5(false);
			CGUI.setState6(false);
		}
}
