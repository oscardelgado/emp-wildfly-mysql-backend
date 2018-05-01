package com.oscardelgado83.easymenuplanner.pojos;

import com.oscardelgado83.easymenuplanner.pojos.ExportPOJO.ExportPojoIdClass;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder; 
import java.net.URLEncoder;

@Entity
@IdClass(ExportPojoIdClass.class)
public class ExportPOJO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Lob
    private String daysJSON;
    @Lob
    private String coursesJSON;
    @Lob
    private String courseIngredientJSONs;
    @Lob
    private String ingredientJSONs;
    @Lob
    private String savedTemplatesJSONs;

    @Id
    private String deviceId;

    @Id
    private String accountName;

    private String updateTimestamp;
    private String androidVersion;
    private String androidPhoneModel;
    private int appVersion;
    private int dbVersion;
    private String country;
    private String language;
    private int userWeekStartDay;
    private boolean breakfastVisible;
    private boolean lunchVisible;
    private boolean lunchSide1Visible;
    private boolean lunchSide2Visible;
    private boolean dinnerVisible;
    private boolean dinnerSide1Visible;
    private boolean dinnerSide2Visible;
    
	@javax.persistence.Transient 
	public boolean experiment = false;

    @Id
	private boolean fromError;
    
    public static class ExportPojoIdClass implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String deviceId;
        private String accountName;
        private boolean fromError;
        
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
			result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
			result = prime * result + (fromError ? 1231 : 1237);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ExportPojoIdClass other = (ExportPojoIdClass) obj;
			if (accountName == null) {
				if (other.accountName != null)
					return false;
			} else if (!accountName.equals(other.accountName))
				return false;
			if (deviceId == null) {
				if (other.deviceId != null)
					return false;
			} else if (!deviceId.equals(other.deviceId))
				return false;
			if (fromError != other.fromError)
				return false;
			return true;
		}
    }



	public boolean isFromError() {
		return fromError;
	}



	public void setFromError(boolean fromError) {
		this.fromError = fromError;
	}

	
	public String getDaysJSON() {
		if (experiment) {
			return decodeStringUrl(daysJSON);
		} else {
			return daysJSON;
		}
		
	}



	public void setDaysJSON(String daysJSON) {
		if (experiment) {
			this.daysJSON = encodeStringUrl(daysJSON);
		} else {
			this.daysJSON = daysJSON;
		}
	}

	public String getCoursesJSON() {
		return coursesJSON;
	}



	public void setCoursesJSON(String coursesJSON) {
		this.coursesJSON = coursesJSON;
	}



	public String getCourseIngredientJSONs() {
		return courseIngredientJSONs;
	}



	public void setCourseIngredientJSONs(String courseIngredientJSONs) {
		this.courseIngredientJSONs = courseIngredientJSONs;
	}



	public String getIngredientJSONs() {
		return ingredientJSONs;
	}



	public void setIngredientJSONs(String ingredientJSONs) {
		this.ingredientJSONs = ingredientJSONs;
	}



	public String getSavedTemplatesJSONs() {
		return savedTemplatesJSONs;
	}



	public void setSavedTemplatesJSONs(String savedTemplatesJSONs) {
		this.savedTemplatesJSONs = savedTemplatesJSONs;
	}



	public String getDeviceId() {
		return deviceId;
	}



	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}



	public String getAccountName() {
		return accountName;
	}



	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}



	public String getUpdateTimestamp() {
		return updateTimestamp;
	}



	public void setUpdateTimestamp(String updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}



	public String getAndroidVersion() {
		return androidVersion;
	}



	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}



	public String getAndroidPhoneModel() {
		return androidPhoneModel;
	}



	public void setAndroidPhoneModel(String androidPhoneModel) {
		this.androidPhoneModel = androidPhoneModel;
	}



	public int getAppVersion() {
		return appVersion;
	}



	public void setAppVersion(int appVersion) {
		this.appVersion = appVersion;
	}



	public int getDbVersion() {
		return dbVersion;
	}



	public void setDbVersion(int dbVersion) {
		this.dbVersion = dbVersion;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public int getUserWeekStartDay() {
		return userWeekStartDay;
	}



	public void setUserWeekStartDay(int userWeekStartDay) {
		this.userWeekStartDay = userWeekStartDay;
	}



	public boolean isBreakfastVisible() {
		return breakfastVisible;
	}



	public void setBreakfastVisible(boolean breakfastVisible) {
		this.breakfastVisible = breakfastVisible;
	}



	public boolean isLunchVisible() {
		return lunchVisible;
	}



	public void setLunchVisible(boolean lunchVisible) {
		this.lunchVisible = lunchVisible;
	}



	public boolean isLunchSide1Visible() {
		return lunchSide1Visible;
	}



	public void setLunchSide1Visible(boolean lunchSide1Visible) {
		this.lunchSide1Visible = lunchSide1Visible;
	}



	public boolean isLunchSide2Visible() {
		return lunchSide2Visible;
	}



	public void setLunchSide2Visible(boolean lunchSide2Visible) {
		this.lunchSide2Visible = lunchSide2Visible;
	}



	public boolean isDinnerVisible() {
		return dinnerVisible;
	}



	public void setDinnerVisible(boolean dinnerVisible) {
		this.dinnerVisible = dinnerVisible;
	}



	public boolean isDinnerSide1Visible() {
		return dinnerSide1Visible;
	}



	public void setDinnerSide1Visible(boolean dinnerSide1Visible) {
		this.dinnerSide1Visible = dinnerSide1Visible;
	}



	public boolean isDinnerSide2Visible() {
		return dinnerSide2Visible;
	}



	public void setDinnerSide2Visible(boolean dinnerSide2Visible) {
		this.dinnerSide2Visible = dinnerSide2Visible;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public static String encodeStringUrl(String url) {
	      String encodedUrl =null;
	      try {
		   encodedUrl = URLEncoder.encode(url, "UTF-8");
	      } catch (UnsupportedEncodingException e) {
		  return encodedUrl;
	      }
	      return encodedUrl;
	  }

	  public static String decodeStringUrl(String encodedUrl) {
	      String decodedUrl =null;
	      try {
		   decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
	      } catch (UnsupportedEncodingException e) {
		  return decodedUrl;
	      }
	      return decodedUrl;
	  }
}
