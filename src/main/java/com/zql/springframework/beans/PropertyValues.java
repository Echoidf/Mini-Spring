package com.zql.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：zql
 * @date: 2023/5/19
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){
        propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues(){
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String name){
        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(name)){
                return propertyValue;
            }
        }
        return null;
    }

}
