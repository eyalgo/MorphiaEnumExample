package org.eyalgo.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity(value = "enum_holders", noClassnameStored = true)
public class EnumHolder {
	@Id
	private ObjectId id;

	private MyEnum myEnum;
	private String otherField;

	public EnumHolder() {
	}

	public MyEnum getMyEnum() {
		return myEnum;
	}

	public void setMyEnum(MyEnum myEnum) {
		this.myEnum = myEnum;
	}

	public String getOtherField() {
		return otherField;
	}

	public void setOtherField(String otherField) {
		this.otherField = otherField;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(myEnum, otherField);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof EnumHolder) {
			EnumHolder that = (EnumHolder) object;
			return Objects.equal(this.myEnum, that.myEnum) && Objects.equal(this.otherField, that.otherField);
		}
		return false;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("myEnum", myEnum).add("otherField", otherField).toString();
	}
}