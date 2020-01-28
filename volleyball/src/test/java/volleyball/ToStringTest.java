package volleyball;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.volleyball.bean.PCA;

/** 
 * @author liwenxue
 * @date 创建时间：2020年1月20日 下午6:37:13 
 * @version 1.0 
 **/
public class ToStringTest {
	public static void main(String[] args){
		/*PCA pca = new PCA();
		pca.setProvince("a");
		pca.setCity("b");
		pca.setArea("c");
		System.out.println(pca.toString());*/
		List<String> list = new ArrayList<String>(); 
        list.add("1"); 
        list.add("2"); 
        list.add("3"); 
        System.out.println(list);  
        System.out.println(list.toString());   
        String json = JSONArray.toJSONString(list);//把list转换成json格式的String类型
        System.out.println("json格式的String类型 " + json);

         System.out.println(StringUtils.strip(list.toString(),"[]")); 
	}
}
