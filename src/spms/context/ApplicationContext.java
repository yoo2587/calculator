package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import spms.annotation.Component;

public class ApplicationContext {

	Hashtable<String, Object> objTable=new Hashtable<String, Object>(); 
	
	public Object getBean(String key) {
		return objTable.get(key);		
	}
	
	public void addBean(String name, Object obj) {
		objTable.put(name,  obj);		
	}
	
	//private void prepareAnnotationObjects() throws Exception {
	public void prepareObjectsByAnnotation(String basePackage) throws Exception {
		Reflections reflector=new Reflections(basePackage);
		//Component annotation이 붙은 class 들만 찾는다.
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
		String key=null;
		
		//LogInController의 경우   @Component("/auth/login.do") 
		//이므로 key는 /auth/login.do 이며 class는 LogInController이다.
		//objTable에 /auth/login.do를 추가하면서 값은 LogInController의 새 인스턴스
		for(Class<?> clazz : list) {
			key=clazz.getAnnotation(Component.class).value();
			objTable.put(key,  clazz.newInstance());
			
		}	
	}
	

	public void prepareObjectsByProperties(String propertiesPath) throws Exception{
		Properties props =new Properties();
		props.load(new FileReader(propertiesPath));
		
		Context ctx=new InitialContext();
		String key=null;
		String value=null;
		
		for(Object item : props.keySet()) {
			key=(String) item;
			value=props.getProperty(key);
			
			if(key.startsWith("jndi.")) {
				//jndi의 경우 설정값만 저장
				objTable.put(key, ctx.lookup(value));
				
			}else {
				//jndi 이외의 경우 class를 초기화해서 저장
				objTable.put(key, Class.forName(value).newInstance());				
			}
		}
	}
	
	
	public void injectDependency() throws Exception{
		//System.out.println("injectDependency Start");
		for(String key : objTable.keySet()) {
			if(!key.startsWith("jndi.")) {
				//System.out.println("callSetter : " + key + ", " + objTable.get(key).getClass().getName() );
				callSetter(key, objTable.get(key));
			}
		}
	}
	
	private void callSetter(String key, Object obj) throws Exception{
		//Class 내부에 setter이 있을 경우 호출해준다.
		Object dependency=null;
		for(Method m : obj.getClass().getMethods()) {
			if(m.getName().startsWith("set")) {
				if(m.getParameterTypes().length>0) {
					//System.out.println(m.getName() +  ", Type : " + m.getParameterTypes()[0].getName());
					dependency=findObjectByType(m.getParameterTypes()[0]);
					if(dependency !=null) {
						//System.out.println("dependency is not null : " + m.getName() +  ", Type : " + m.getParameterTypes()[0].getName());
						//System.out.println("invoke class name : " + objTable.get(key).getClass().getName() + ", param type[0] : " +
						//		m.getParameterTypes()[0].getName() + ", depencency classname :" + dependency.getClass().getName() + ", dependency value=" + dependency.toString() );
						m.invoke(obj, dependency);
					}
				}			
			}
			
		}
	}
	
	private Object findObjectByType(Class<?> type) {
		//System.out.println("findObjectByType : " + type.getName());
		//왜 전체 objTable 루프를 돌면서 찾을 까???
		for(Object obj : objTable.values()) {
			//System.out.println("obj : " + obj.getClass().getName());
			if(type.isInstance(obj)) {
				System.out.println("findObjectByType, obj : " + obj.getClass().getName() + " is Instance");
				return obj;				
			}
		}		
		return null;
	}

}
