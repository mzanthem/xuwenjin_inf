package cn.com.alo7.inf.common.utils;

import cn.com.alo7.inf.vo.DataVo;
import cn.com.alo7.inf.vo.RootVo;

/**
 * TODO
 * @author mazan
 *
 */
@Deprecated
public class ResultUtils {
	
	/**
	 * 创建一个空的RootVo
	 * @return
	 */
	public static RootVo createRoot(){
		return new RootVo();
	}
	
	public static <T> RootVo createRoot(DataVo<T> data) {
		RootVo rootVo = new RootVo();
		rootVo.setData(data);
		return rootVo;
	}
	
	
	//-----------------------//
	public static <T> DataVo<T> createData(String id, String type, T t){
		DataVo<T> dataVo = new DataVo<T>();
		dataVo.setId(id);
		dataVo.setType(type);
		dataVo.setAttributes(t);
		return dataVo;
	}
	
}
