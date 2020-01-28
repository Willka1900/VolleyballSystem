package com.volleyball.bean;

/**
 * @author liwenxue
 * @date 创建时间：2019年10月29日 下午7:43:35
 * @version 1.0
 **/
public class PCA {
	private String province;// 省

	private String city;// 市

	private String area;// 区

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return this.city + "-" + this.area;
	}

	public String toAll() {
		return "请选择城市-请选择地区";
	}

	public String toCity() {
		return this.city + "-请选择地区";
	}
}
