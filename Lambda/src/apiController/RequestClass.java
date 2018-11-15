package apiController;

public class RequestClass {
    Integer	serviceNo;
    Integer	markerNo;
    String	ipAddr;
    String	targetUrl;
    String	cookie;
    String	cookiePw;
    String	date;
    String	maxAge;
    String	userAgent;
    
    public Integer getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(Integer serviceNo) {
		this.serviceNo = serviceNo;
	}

	public Integer getMarkerNo() {
		return markerNo;
	}

	public void setMarkerNo(Integer markerNo) {
		this.markerNo = markerNo;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getCookiePw() {
		return cookiePw;
	}

	public void setCookiePw(String cookiePw) {
		this.cookiePw = cookiePw;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(String maxAge) {
		this.maxAge = maxAge;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public RequestClass(Integer	serviceNo, Integer markerNo, String ipAddr, String targetUrl, String cookie, String	cookiePw , String date, String maxAge, String userAgent) {
        this.serviceNo = serviceNo;
        this.markerNo = markerNo;
        this.ipAddr = ipAddr;
        this.targetUrl = targetUrl;
        this.cookie = cookie;
        this.cookiePw = cookiePw;
        this.date = date;
        this.maxAge = maxAge;
        this.userAgent = userAgent;
    }

	public RequestClass() {
    }
}