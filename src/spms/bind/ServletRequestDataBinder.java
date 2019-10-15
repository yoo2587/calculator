package spms.bind;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletRequest;

public class ServletRequestDataBinder {

	public ServletRequestDataBinder() {
		// TODO Auto-generated constructor stub
	}
	
	public static Object bind(ServletRequest request, Class<?> dataType, String dataName) throws Exception{
		//value type의 경우 변수명을 이용해서 값을 찾아서 넘기면 된다.
		if(isPrimitiveType(dataType)) {
			return createValueObject(dataType, request.getParameter(dataName));			
		}
		
		//Class의 경우 Class를 찾고 setter를 호출해서 값을 준 후 반환한다. 
		Set<String> paramNames=request.getParameterMap().keySet();
		Object dataObject=dataType.newInstance();
		Method m=null;
		
		for(String paramName:paramNames) {
			m=findSetter(dataType, paramName);
			if(m!=null) {
				System.out.println("dataObject.getClass().getName()=" + dataObject.getClass().getName());
				System.out.println("paramName=" + paramName);
				System.out.println("ServletRequestDataBinder m.getName()=" + m.getName());
				
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0], request.getParameter(paramName)));				
			}			
		}		
		return dataObject;
		
	}
	
	private static boolean isPrimitiveType(Class<?> type) {
		if(type.getName().equals("int") || type==Integer.class ||
				type.getName().equals("long") || type==Long.class ||
				type.getName().equals("float") || type==Float.class ||
				type.getName().equals("double") || type==Double.class ||
				type.getName().equals("boolean") || type==Boolean.class ||
				type==Date.class || type==String.class
				) {
			return true;			
		}					
		return false;
	}
	
	private static Object createValueObject(Class<?> type, String value) {
		if(type.getName().equals("int") || type==Integer.class) {
			return new Integer(value);			
		}else if(type.getName().equals("long") || type==Long.class) {
			return new Long(value);					
		}else if(type.getName().equals("float") || type==Float.class) {
			return new Float(value);					
		}else if(type.getName().equals("double") || type==Double.class) {
			return new Double(value);					
		}else if(type.getName().equals("boolean") || type==Boolean.class) {
			return new Boolean(value);					
		}else if(type==Date.class) {
			return java.sql.Date.valueOf(value);			
		}else {
			return value;			
		}		
	}
	
	private static Method findSetter(Class<?> type, String name) {
		Method[] methods=type.getMethods();
		
		String propName=null;
		for(Method m : methods) {
			if(!m.getName().startsWith("set")) continue;
			
			propName=m.getName().substring(3);
			if(propName.toLowerCase().equals(name.toLowerCase())) {
				return m;				
			}			
		}
		
		
		return null;
	}
	

}
