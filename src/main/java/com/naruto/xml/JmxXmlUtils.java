package com.naruto.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
/**
 * @ClassName: JmxXmlUtils 
 * @Description: 将对象生成符合JMX文件的XML文档,生成的文档需要在最后再加一个</hashTree>，然后去掉最外层的节点
 * @author zhaochenxi
 * @date 2016年11月24日 下午2:27:38
 */
public class JmxXmlUtils {

	/**
	 * @Title: toElement 
	 * @Description: 将一个对象转换为xml文档，由于对象中有对象，所以采用递归来解析。
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void toElement(Object object,Element root,Document xmlDoc) {
		if(root==null){
			root=xmlDoc.addElement("jmeterTestPlan");
			root.addAttribute("version", "1.2");
			root.addAttribute("properties", "2.9");
			root.addAttribute("jmeter", "3.0 r1743807");
		}		
		
		if (object != null) {
			if ((object instanceof Number) || (object instanceof Boolean) || (object instanceof String)
					|| (object instanceof Double) || (object instanceof Float)) {
				root.setText(object.toString());
			} else if (object instanceof Map) {
				mapToElement((Map) object, root);
			} else if (object instanceof Collection) {
				collToElement((Collection) object, root);				
			} else {				
				pojoToElement(object, root);
			}
		} else {
			root.setText("");
		}
	}


	/**
	 * @Title: collToElement 
	 * @Description: 解析集合对象 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void collToElement(Collection<?> coll, Element root) {
		for (Iterator<?> it = coll.iterator(); it.hasNext();) {
			Object value = it.next();
			if (coll == value) {
				continue;
			}
			if ((value instanceof Number) || (value instanceof Boolean) || (value instanceof String)
					|| (value instanceof Double) || (value instanceof Float)) {
				Class<?> classes = value.getClass();
				String objName = classes.getName();
				String elementName = objName.substring(objName.lastIndexOf(".") + 1, objName.length());
				Element elementOfObject = root.addElement(elementName);
				elementOfObject.setText(value.toString());
			} else if (value instanceof Map) {
				Class<?> classes = value.getClass();
				String objName = classes.getName();
				String elementName = objName.substring(objName.lastIndexOf(".") + 1, objName.length());
				Element elementOfObject = root.addElement(elementName);
				mapToElement((Map) value, elementOfObject);
			} else if (value instanceof Collection) {
				Class<?> classes = value.getClass();
				String objName = classes.getName();
				String elementName = objName.substring(objName.lastIndexOf(".") + 1, objName.length());
				Element elementOfObject = root.addElement(elementName);
				collToElement((Collection) value, elementOfObject);
			} else {
				toElement(value, root,null);
			}

		}
		
	};

	/**
	 * @Title: mapToElement 
	 * @Description: 解析Map对象 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	@SuppressWarnings({ "rawtypes" })
	private static void mapToElement(Map<String, Object> map, Element root) {
		
		for (Iterator<?> it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String name = (String) entry.getKey();
			if (null == name)
				continue;
			Object value = entry.getValue();
			//不把map作为一个节点
			//Element elementValue = root.addElement(name);
			toElement(value,root,null);
		}
	}

	/**
	 * @Title: pojoToElement 
	 * @Description: 解析对象jmx对象 
	 * @param 
	 * @return void 
	 * @throws 
	 * @author zhaochenxi
	 */
	@SuppressWarnings("unchecked")
	private static void pojoToElement(Object obj, Element root) {
		if(obj instanceof Attribute){
			//如果对象是xml标签的属性
			Attribute attr = (Attribute)obj;
			Map<String,String> map = attr.getMap();
			for (Iterator<?> it = map.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String,String> entry = (Entry<String, String>) it.next();
				String name = (String) entry.getKey();
				if (null == name)
					continue;
				String value = entry.getValue();
				root.addAttribute(name, value);
			}
		}else{
			Class<?> classes = obj.getClass();
			String objName = classes.getName();

			String elementName = objName.substring(objName.lastIndexOf(".") + 1, objName.length());
			Element elementOfObject = null;
			//JmeterTestPlan这个类名不加入到文档红，因为在根节点上已经添加了。
			if(elementName.equals("JmeterTestPlan")){
				elementOfObject = root;
			}else{
				/** 该类为一个节点 */
				if(elementName.endsWith("Prop")||elementName.endsWith("Tree")){
					elementName =  elementName.substring(0, 1).toLowerCase()+ elementName.substring(1, elementName.length());
				}
				elementOfObject = root.addElement(elementName);
			}
					  
			Field[] fields = classes.getDeclaredFields();
			for (Field f : fields) {
				if (Modifier.isStatic(f.getModifiers()))
					continue;
				String name = f.getName();				
				f.setAccessible(true);
				Object value = null;				
				try {
					value = f.get(obj);
				} catch (Exception e) {
					value = null;
				}
				if(value!=null){
					if(name.equals("value")||name.equals("attr")||name.endsWith("Prop")||name.endsWith("map")){
						toElement(value, elementOfObject,null);
					}else{
						Element elementValue = elementOfObject.addElement(name);
						toElement(value, elementValue,null);
					}
				}
								
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		TestPlan testPlan  = new TestPlan();
		List<BoolProp> list = new ArrayList<BoolProp>();
		List<StringProp> slist = new ArrayList<StringProp>();
		BoolProp boolProp = new BoolProp();
		boolProp.setValue(false);
		boolProp.getAttr().getMap().put("name","TestPlan.functional_mode");
		BoolProp boolProp2 = new BoolProp();
		boolProp2.getAttr().getMap().put("name","TestPlan.serialize_threadgroups");
		boolProp2.setValue(true);
		list.add(boolProp);
		list.add(boolProp2);
		
		StringProp stringProp = new StringProp();
		stringProp.setValue("");
		stringProp.getAttr().getMap().put("name", "TestPlan.comments");
		slist.add(stringProp);
		
		StringProp stringProp2 = new StringProp();
		stringProp2.setValue("");
		stringProp2.getAttr().getMap().put("name", "TestPlan.comments");
		slist.add(stringProp2);
		
		ElementProp elementProp = new ElementProp();
		elementProp.getAttr().getMap().put("name","TestPlan.user_defined_variables");
		elementProp.getAttr().getMap().put("elementType","Arguments");
		elementProp.getAttr().getMap().put("guiclass","ArgumentsPanel");
		elementProp.getAttr().getMap().put("testclass","Arguments");
		elementProp.getAttr().getMap().put("testname","用户定义的变量");
		elementProp.getAttr().getMap().put("enabled","true");
		
		CollectionProp coll = new CollectionProp();
		coll.getAttr().getMap().put("name","Arguments.arguments");
		elementProp.setCollectionProp(coll);
		testPlan.setElementProp(elementProp);
		testPlan.setBoolProp(list);
		testPlan.setStringProp(slist);
		
		testPlan.getAttr().getMap().put("guiclass","TestPlanGui");
		testPlan.getAttr().getMap().put("testclass","TestPlan");
		testPlan.getAttr().getMap().put("testname","测试计划");
		testPlan.getAttr().getMap().put("enabled","true");		
		
		JmeterTestPlan hashTree = new JmeterTestPlan();
		hashTree.getHashTree().put("TestPlan", testPlan);
		
		ThreadGroup threadGroup = new ThreadGroup();
		threadGroup.getAttr().getMap().put("guiclass", "ThreadGroupGui");
		threadGroup.getAttr().getMap().put("testclass", "ThreadGroup");
		threadGroup.getAttr().getMap().put("testname", "线程组");
		threadGroup.getAttr().getMap().put("enabled", "true");
		List<LongProp> longProp = new ArrayList<LongProp>();
		
		LongProp longProp1= new LongProp();
		longProp1.setValue(1479974064000L);
		longProp1.getAttr().getMap().put("name","ThreadGroup.start_time");
		
		LongProp longProp2= new LongProp();
		longProp2.setValue(1479974064000L);
		longProp2.getAttr().getMap().put("name","ThreadGroup.end_time");
		longProp.add(longProp1);
		longProp.add(longProp2);
		threadGroup.setLongProp(longProp);
		
		List<BoolProp> boolPropList = new ArrayList<BoolProp>();
		
		BoolProp boolProp3 = new BoolProp();
		boolProp3.getAttr().getMap().put("name","ThreadGroup.scheduler");
		boolProp3.setValue(false);
		boolPropList.add(boolProp3);
		threadGroup.setBoolProp(boolPropList);
						
		List<StringProp> stringPropList = new ArrayList<StringProp>();
		StringProp stringProp3 = new StringProp();
		stringProp3.setValue("测试线程组");
		stringProp3.getAttr().getMap().put("name","TestPlan.comments");
		
		StringProp stringProp4 = new StringProp();
		stringProp4.setValue("continue");
		stringProp4.getAttr().getMap().put("name","ThreadGroup.on_sample_error");
		
		StringProp stringProp5 = new StringProp();
		stringProp5.setValue("1");
		stringProp5.getAttr().getMap().put("name","ThreadGroup.num_threads");
		
		StringProp stringProp6 = new StringProp();
		stringProp6.setValue("1");
		stringProp6.getAttr().getMap().put("name","ThreadGroup.ramp_time");
		
		StringProp stringProp7 = new StringProp();
		stringProp7.setValue("");
		stringProp7.getAttr().getMap().put("name","ThreadGroup.duration");
		
		StringProp stringProp8 = new StringProp();
		stringProp8.setValue("");
		stringProp8.getAttr().getMap().put("name","ThreadGroup.delay");
		
		stringPropList.add(stringProp3);
		stringPropList.add(stringProp4);
		stringPropList.add(stringProp5);
		stringPropList.add(stringProp6);
		stringPropList.add(stringProp7);
		stringPropList.add(stringProp8);
		threadGroup.setStringProp(stringPropList);
		
		ElementProp elementProp2 = new ElementProp();
		elementProp2.getAttr().getMap().put("name","ThreadGroup.main_controller");
		elementProp2.getAttr().getMap().put("elementType","LoopController");
		elementProp2.getAttr().getMap().put("guiclass","LoopControlPanel");
		elementProp2.getAttr().getMap().put("testclass","LoopController");
		elementProp2.getAttr().getMap().put("testname","循环控制器");
		elementProp2.getAttr().getMap().put("enabled","true");
		
		List<BoolProp> boolPropList2 = new ArrayList<BoolProp>();
		
		BoolProp boolProp4 = new BoolProp();
		boolProp4.getAttr().getMap().put("name","TLoopController.continue_forever");
		boolProp4.setValue(false);
		boolPropList2.add(boolProp4);
		
		StringProp stringProp9 = new StringProp();
		stringProp9.setValue("1000");
		stringProp9.getAttr().getMap().put("name","LoopController.loops");
		List<StringProp> slist2 = new ArrayList<StringProp>();
		slist2.add(stringProp9);
		
		elementProp2.setStringProp(slist2);
		elementProp2.setBoolProp(boolPropList2);
		
		threadGroup.setElementProp(elementProp2);
		//threadGroup.setStringProp(slist2);
		
		
		
		/*-------------------------以上为jmx基本配置-----------------------------------------*/
		//以下为httpSample请求配置
		
		HTTPSamplerProxy http = new HTTPSamplerProxy();
		http.getAttr().getMap().put("guiclass", "HttpTestSampleGui");
		http.getAttr().getMap().put("testclass", "HTTPSamplerProxy");
		http.getAttr().getMap().put("testname", "登陆");
		http.getAttr().getMap().put("enabled", "true");
		
		ElementProp httpElementProp = new ElementProp();
		
		httpElementProp.getAttr().getMap().put("name","HTTPsampler.Arguments");
		httpElementProp.getAttr().getMap().put("elementType","Arguments");
		httpElementProp.getAttr().getMap().put("guiclass","HTTPArgumentsPanel");
		httpElementProp.getAttr().getMap().put("Arguments","用户定义的变量");
		httpElementProp.getAttr().getMap().put("enabled","true");
		
		CollectionProp httpColl = new CollectionProp();
		httpColl.getAttr().getMap().put("name","Arguments.arguments");
		List<ElementProp> elementPropList = new ArrayList<ElementProp>();
		ElementProp collElement1 = new ElementProp();
		collElement1.getAttr().getMap().put("name", "userId");
		collElement1.getAttr().getMap().put("elementType", "HTTPArgument");
		
		List<BoolProp> boollist1 = new ArrayList<BoolProp>();
		BoolProp bool1 = new BoolProp();
		bool1.getAttr().getMap().put("name", "HTTPArgument.always_encode");
		bool1.setValue(false);
		BoolProp bool2 = new BoolProp();
		bool2.getAttr().getMap().put("name", "HTTPArgument.use_equals");
		bool2.setValue(true);
		boollist1.add(bool1);
		boollist1.add(bool2);
		collElement1.setBoolProp(boollist1);
		
		List<StringProp> stringList1 = new ArrayList<StringProp>();
		StringProp string0 = new StringProp();
		string0.getAttr().getMap().put("name","Argument.value");
		string0.setValue("15680160126");
		
		StringProp string1 = new StringProp();
		string1.getAttr().getMap().put("name","Argument.metadata");
		string1.setValue("=");
		
		StringProp string2 = new StringProp();
		string2.getAttr().getMap().put("name","Argument.name");
		string2.setValue("userId");
		stringList1.add(string0);
		stringList1.add(string1);
		stringList1.add(string2);
		collElement1.setStringProp(stringList1);
		
		ElementProp collElement2 = new ElementProp();
		collElement2.getAttr().getMap().put("name", "passWord");
		collElement2.getAttr().getMap().put("elementType", "HTTPArgument");
				
		List<BoolProp> boollist2 = new ArrayList<BoolProp>();
		BoolProp bool3 = new BoolProp();
		bool3.getAttr().getMap().put("name", "HTTPArgument.always_encode");
		bool3.setValue(false);
		BoolProp bool4 = new BoolProp();
		bool4.getAttr().getMap().put("name", "HTTPArgument.use_equals");
		bool4.setValue(true);
		boollist2.add(bool3);
		boollist2.add(bool4);
		
		collElement2.setBoolProp(boollist2);
		
		List<StringProp> stringList2 = new ArrayList<StringProp>();
		StringProp string4 = new StringProp();
		string4.getAttr().getMap().put("name","Argument.value");
		string4.setValue("5B1A453BF15B213BB520A6C8455D73CCDB3B7442D1CF983451727137F8A6182E198F89EEA74DD13824B5B74AC84AC651CDCFADEA3C1E21F564904AD5326F10C0963EBB49F4C25FDF1C2F2C5BC3459D1569B10104070042911F9A37C181FCF6CD3A42B99C38F45BEFDC9644B493894D2AF021A73D275C091A52EEE8FC80508C69");
		
		StringProp string5 = new StringProp();
		string5.getAttr().getMap().put("name","Argument.metadata");
		string5.setValue("=");
		
		StringProp string6 = new StringProp();
		string6.getAttr().getMap().put("name","Argument.name");
		string6.setValue("passWord");
		
		stringList2.add(string4);
		stringList2.add(string5);
		stringList2.add(string6);
		
		collElement2.setStringProp(stringList2);
		
		
		elementPropList.add(collElement1);
		elementPropList.add(collElement2);
		httpColl.setElementProp(elementPropList);
		httpElementProp.setCollectionProp(httpColl);
		http.setElementProp(httpElementProp);
		
		//------------------------------------------------------------------------------
		HashTree tree2 = new HashTree();
		tree2.getMap().put("hashTree", http);
		
		//添加<hashTree/>标签
		tree2.getMap().put("hashTree2",new HashTree());
		
		HashTree tree = new HashTree();		
		tree.getMap().put("ThreadGroup", threadGroup);
		tree.getMap().put("hashTree", tree2);
		
				
		hashTree.getHashTree().put("hashTree", tree);
		
		Document xmlDoc = DocumentHelper.createDocument();
		
		JmxXmlUtils.toElement(hashTree,null,xmlDoc);		
		String xmlStr = "";
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8"); // 指定XML编码
		StringWriter writerStr = new StringWriter();
		XMLWriter xmlw = new XMLWriter(writerStr, format);
		try {			
			xmlw.write(xmlDoc);
			xmlw.close();
		} catch (IOException e) {
		}
		xmlStr = writerStr.getBuffer().toString();
		System.out.println(xmlStr);
	}
}