package packageone;

import files.data;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		JsonPath js= new JsonPath(data.CoursePrice());
		
		//Print no of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		//Purchase Amount
		int puramt=js.getInt("dashboard.purchaseAmount");
		System.out.println(puramt);
		//Print Title of the first course
		String titleFirstCourse =js.get("courses[0].title");
		System.out.println(titleFirstCourse);
	
		//Print All course titles and their respective Prices
		for(int i=0; i<count; i++) {
			String courseTitles= js.get("courses["+i+"].title");
			System.out.println(courseTitles);
			System.out.println(js.get("courses["+i+"].price").toString());	
		}
		
		System.out.println("Print no of copies sold by RPA Course");
		for(int i=0;i<count;i++) {
			String coursesTitle=js.get("courses["+i+"].title");
			if(coursesTitle.equalsIgnoreCase("RPA")) {
				System.out.println(js.get("courses["+i+"].copies").toString());
				break;
			}
		}
		
		
	}

}
