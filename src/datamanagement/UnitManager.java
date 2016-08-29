package datamanagement;

import java.util.List;
import org.jdom.*;

public class UnitManager 
{

	private static UnitManager self = null;// initialized object of UnitManager and set it to null value;

	private UnitMap UM;//create object of UnitMap and didnt initialize it

	public static UnitManager UM() 
		{
			if (self == null)//checking if self object is already initialized or not
				self = new UnitManager();
			return self;
		}

	private UnitManager()  //defined private function which initialized UM object
		{
			UM = new UnitMap();
		}

	public IUnit getUnit(String uc) 
		{
			IUnit iu = UM.get(uc);
			return iu != null ? iu : createUnit(uc);//condition based reutrn statement.
			//if iu is not null then this function will return iu else it will pass uc variable to createUnit function

		}

	private IUnit createUnit(String unitCode) // private function whose return type is object and accept string unitCode
		{

			IUnit iu;

			for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("unitTable").getChildren("unit"))
				if (unitCode.equals(el.getAttributeValue("uid"))) 
				{
					StudentUnitRecordList slist;

					slist = null;
					//following mentioned attributes will be passed to object Unit
					iu = new Unit(el.getAttributeValue("uid"),el.getAttributeValue("name"), Float.valueOf(
									el.getAttributeValue("ps")).floatValue(), Float.valueOf(el.getAttributeValue("cr"))
									.floatValue(), Float.valueOf(el.getAttributeValue("di")).floatValue(), Float
									.valueOf(el.getAttributeValue("hd")).floatValue(), Float.valueOf(
									el.getAttributeValue("ae")).floatValue(),Integer.valueOf(el.getAttributeValue("asg1wgt"))
									.intValue(), Integer.valueOf(el.getAttributeValue("asg2wgt")).intValue(),
							Integer.valueOf(el.getAttributeValue("examwgt")).intValue(), StudentUnitRecordManager
									.instance().getRecordsByUnit(unitCode));
					UM.put(iu.getUnitCode(), iu);
					return iu;
				}

					throw new RuntimeException("DBMD: createUnit : unit not in file");
		}

	public UnitMap getUnits() 
		{

			UnitMap uM;
			IUnit iu;

			uM = new UnitMap();
			for (Element el : (List<Element>) XMLManager.getXML().getDocument().getRootElement().getChild("unitTable").getChildren("unit")) 
			{
				iu = new UnitProxy(el.getAttributeValue("uid"),el.getAttributeValue("name"));
				uM.put(iu.getUnitCode(), iu);
			} // unit maps are filled with PROXY units
			return uM;
		}

}
